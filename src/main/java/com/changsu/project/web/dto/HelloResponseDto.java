package com.changsu.project.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter //get메소드를 자동을 생성
@RequiredArgsConstructor //final필드가 포함된 생성자를 생성해준다.
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
