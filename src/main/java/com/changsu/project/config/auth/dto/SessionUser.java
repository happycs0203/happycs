package com.changsu.project.config.auth.dto;

import com.changsu.project.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * 직렬화 언제 다른 엔티티와 관계가 형성될지 모른다.
 * 나중에 운영 및 유지보수 때 많은 도움이 된다.
 */
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}