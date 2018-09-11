package controller.test;

import com.alibaba.fastjson.JSONObject;
import com.gohead.core.entity.User;
import com.gohead.core.entity.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by super on 2018/9/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:/spring-context.xml", "classpath*:/spring-context-mvc.xml", "classpath*:/mybatis-config.xml"})
public class UserInfoControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testUserList() throws Exception {
        //创建文章列表的请求
        //请求方式为get
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.request(HttpMethod.GET, "/user_info/query/page/1/pageSize/10");
        //此请求并不需要添加请求参数
        mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void saveUser() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/user_info/add");
        User user = new User();
        user.setPassword("123456");
        user.setUserName("kotlin");
        user.setRoleName("admin");
        UserInfo userinfo = new UserInfo();
        userinfo.setUser(user);
        userinfo.setUserName("kotlin");
        userinfo.setEmail("yinte112@163.com");
        userinfo.setPhone("15990010342");
        userinfo.setUserAvator("wwhhdhhdhd");
        userinfo.setWork("enginer");
        String requestParam = JSONObject.toJSONString(userinfo);
        mockMvc.perform(mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON).content(requestParam)).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void delete() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.delete("/user_info/delete/93");
        User user = new User();
        user.setId(92);
        String requestParam = JSONObject.toJSONString(user);
        mockMvc.perform(mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON).content(requestParam)).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void update() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.put("/user_info/update");
        User user = new User();
        user.setId(94);
        user.setPassword("123456");
        user.setUserName("java");
        UserInfo userinfo = new UserInfo();
        userinfo.setUser(user);
        userinfo.setUserName("kotlin");
        userinfo.setEmail("yinte112@163.com");
        userinfo.setPhone("15990010342");
        userinfo.setUserAvator("www.baidu.com");
        userinfo.setWork("enginer");
        userinfo.setUid(94);
        userinfo.setId(90);
        String requestParam = JSONObject.toJSONString(userinfo);
        mockMvc.perform(mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON).content(requestParam)).andExpect(status().isOk())
                .andDo(print());
    }

}
