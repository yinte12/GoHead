package com.gohead.core.websocket;

import com.gohead.core.entity.UserInfo;
import com.gohead.core.service.UserInfoService;
import org.apache.log4j.Logger;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by super on 2018/10/12.
 */

//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping。无需在web.xml中配置。
@ServerEndpoint(value = "/websocket/{userId}", configurator = SpringConfigurator.class)
public class SimpleWebSocket {
    private static final Logger log = Logger.getLogger(SimpleWebSocket.class);// 日志文件

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private static Map<String, Session> ssrSessions = new ConcurrentHashMap<>();

    @Resource
    private UserInfoService userInfoService;

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        this.session = session;
        ssrSessions.put(userId, session);
        addOnlineCount();           //在线数加1
        log.info("有新连接加入！" + userId + "当前在线人数为" + getOnlineCount());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", 1);
        map.put("size", 10);
        List<UserInfo> list = userInfoService.findUserInfo(map);
        ssrSessions.get(userId).getAsyncRemote().sendText(list.toString());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("userId") String userId, Session session) {
        ssrSessions.remove(userId, session);
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(@PathParam("userId") String userId, String message, Session session) {
        log.info("来自客户端的消息:" + message);
        ssrSessions.get(userId).getAsyncRemote().sendText(message);
        //群发消息
//        for (Session s : ssrSessions.values()) {
//            try {
//                s.getAsyncRemote().sendText(message);
//            } catch (Exception e) {
//                e.printStackTrace();
//                continue;
//            }
//        }
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
//        this.session.getBasicRemote().sendText(message);
        this.session.getAsyncRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        SimpleWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        SimpleWebSocket.onlineCount--;
    }
}
