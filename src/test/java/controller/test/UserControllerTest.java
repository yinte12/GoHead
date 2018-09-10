package controller.test;

import com.alibaba.fastjson.JSONObject;
import com.gohead.core.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by super on 2018/9/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:/spring-context.xml", "classpath*:/spring-context-mvc.xml", "classpath*:/mybatis-config.xml"})
public class UserControllerTest {
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
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.request(HttpMethod.GET, "/users/query/page/1/pageSize/10");
        //此请求并不需要添加请求参数
        mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void saveUser() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/users/add");
        User user = new User();
        user.setPassword("123456");
        user.setUserName("kotlin");
        user.setRoleName("admin");
        String requestParam = JSONObject.toJSONString(user);
        mockMvc.perform(mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON).content(requestParam)).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void update() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.put("/users/update");
        User user = new User();
        user.setId(89);
        user.setPassword("789112");
        user.setUserName("java");
        String requestParam = JSONObject.toJSONString(user);
        mockMvc.perform(mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON).content(requestParam)).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void delete() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.delete("/users/delete/89");
        User user = new User();
        user.setId(89);
        String requestParam = JSONObject.toJSONString(user);
        mockMvc.perform(mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON).content(requestParam)).andExpect(status().isOk())
                .andDo(print());
    }
}
