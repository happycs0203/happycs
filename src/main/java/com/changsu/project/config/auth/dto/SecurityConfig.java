package com.changsu.project.config.auth.dto;

import com.changsu.project.domain.user.Role;
import com.changsu.project.service.posts.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정들을 활성화 시켜준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private  final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected  void configure(HttpSecurity http) throws  Exception{
        http.csrf().disable()
                .headers().frameOptions().disable() //h2-console 화면을 사용하기 위해 해당 옵선들을 disable 한다.
                .and()
                    .authorizeRequests() //url별 관리를 설정하는 옵션의 시작점, authorizeRequest가 선언되야만 antMatchers옵션을 사용가능
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()//URL http메소드벼로 관리가 가능 permitAll로 전체 연ㄹ람 권한을 주었다.
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated() //설정된 값들 이외 나머지 URL들을 나타넨다.
                .and()
                    .logout()
                        .logoutSuccessUrl("/") //로그아웃 기능의 여러 진입점 성공시 /로 이동한다.
                .and()
                    .oauth2Login()//로그인 성공 이후 사용자 정보를 가져올때 설정담당
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService); //로그인 성공시 후속조치를 진행한다. UserService인터페이스의 구현체를 등록


    }
}
