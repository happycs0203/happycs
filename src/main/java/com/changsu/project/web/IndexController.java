package com.changsu.project.web;

import com.changsu.project.config.auth.dto.LoginUser;
import com.changsu.project.config.auth.dto.SessionUser;
import com.changsu.project.service.posts.PostsService;
import com.changsu.project.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    /**
     * desc Model은 서버템플릿 엔진에서 사용할 수 있는 객체를 저장
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());

        //LoginUser SessionUser user로 변견된다. 어느 컨트롤러든지 @LoginUser만 사용하면 세션 정보를 가지고 올 수 있다.
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @RequestMapping(value = "/index")
    public String index_1(){
        return "index_test";
    }

    @GetMapping("/posts/save")
    public String postSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }


}
