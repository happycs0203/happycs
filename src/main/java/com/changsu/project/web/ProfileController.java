package com.changsu.project.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {
    private final Environment env;

    @GetMapping("/profile")
    public String profile(){
        List<String> profiles = Arrays.asList(env.getActiveProfiles());
        //현재 실행중인 ActriveProfile을 모두 가져온다.
        //real, oauth,real-db 등이 활성화되어 있다면 3개모두 담겨 있다.
        System.out.println(profiles);
        List<String> realProfiles = Arrays.asList("real","real2","real3");

        String defaultProfile = profiles.isEmpty() ? "default" : profiles.get(0);
        System.out.println(defaultProfile);
        return profiles.stream().filter(realProfiles::contains).findAny().orElse(defaultProfile);
    }

}

