package com.changsu.project.domain.persons;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Persons {

    //IDENTTITY 데이터베이스에 위임 AutIncrement
    //SEQUENCE 데이터베이스 시퀀스 오브젝트 사용 @SequenceGenerator 필요
    //TABKE 키생성용 테이블 사용 모든 DB에서 사용 TableGenerator 필요
    //AUTO 방언에 따라 자동지정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500,columnDefinition = "TEXT")
    private String name;

    @Column(columnDefinition = "integer")
    private Integer age;

    @Builder
    public void Persons(String name, Integer age){
        this.name = name;
        this.age = age;
    }
}
