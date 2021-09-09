package com.changsu.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * SpringBootApplication은 스프링부트의 자동설정, 스프링 Bean 읽기 생성을 모두 자동으로 설정
 * 제일 먼저 읽어가기 때문에 항상 프로젝트의 최상단에 위치해야된다!
 * WAS란 웹애플리케이션 서버
 *
 */
//@EnableJpaAuditing //JPA Auditing 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        //SpringApplication.run으로 내장 WAS를 실행한다.항상 톰켓을 설치할 필요가 없게 된다.
        //스프링부트에서
        SpringApplication.run(Application.class, args);
    }
}
