package com.changsu.project;

import com.changsu.project.config.auth.dto.SecurityConfig;
import com.changsu.project.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//테스트를 진행할때 JUnit에 내장된 실행자 외에 다른 싱행자를 싱행시킨다.
//JUnit 사이에 연결자 역할을 한다.
@RunWith(SpringRunner.class)
//Web(Spring MVC)에 집중할 수 있는 어노테이션
//@Controller, @ControllerAdvice 등을 사용할 수 있다.
//스캔대상에서 SecurityConfig를 제외한다.
@WebMvcTest(controllers = HelloController.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
    })
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 빈을 주입받는다.
    private MockMvc mvc;
    //웹 API를 테스트 할 때 사용한다.
    //스프링 MVC테스트의 시작점
    //HTTP GET,POST등에 대한 API테스트를 할 수 있다.

    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";
        //MockMvc를 통해 /hello 주소로 HTTP GET요청을 한다. //mvc.perform의 결과
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws  Exception{
        String name = "hello";
        int amount = 1000;

        //param은 꼭 String만 가능하니 변환을 해야된다.
        //API테스트 할 때 사용될 요청파라미터를 설정한다.
        mvc.perform(get("/hello/dto")
                .param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
                //jsonPath JSON응답값을 필드별로 검증할 수 있는 메소드이다.
    }
}
