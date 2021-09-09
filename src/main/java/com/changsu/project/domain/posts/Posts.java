package com.changsu.project.domain.posts;

import com.changsu.project.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//롬복은 코드를 단순화 시켜주지만 필수 어노테이션이 아니기때문에 위에 놓는다.
//getter메소드를 자동으로 생성
@Getter
//기본생성자 자동추가 public Posts() {} 와 같은 효과!!
@NoArgsConstructor
//테이블과 링크될 클래스다. 기본값으로 카멜케이스 이름을 언더스코어 네이밍으로 테이블이름을 매칭
//ex SalesManger.java -> sales_manager table
@Entity
public class Posts extends BaseTimeEntity {
    //PK 필드를 나타낸다.
    @Id
    //PK의 생성규칙 GenerationType.IDENTITY를 추가해야지 auto increment가 된다. (스프링부트 2.0부터 시작)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //굳이 선언하지 않더라도 해당클래스의 필드는 모두 칼럼이된다.
    //사용하는 이유는 기본값외에 추가로 변경이 필요한 옵션이 있으면 사용
    //varchar(255)가 기본값인데 사이즈를 500으로 늘리고 싶거나 타입을 TEXT로 변경하고 싶거나 할때 사용
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    //해당클래스의 빌더 패턴 클래스를 생성
    //생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    //Setter메소드기 없는 이유
    //인스턴스 값들이 언제 어디서 변하는지 코드상 명확하게 구분할 수 가 없어, 차후 기능변경시 어려워지기때문이다.
    //명확히 그 목적과 의도를 나타낼 수 있는 메소드를 생성한다.!!!

    /*
     *  public void setStatus(boolean status){
     *         this.status = status;
     *  }
     *
     *  public void cancelOrder(){
     *      this.status = false;
     *  }
     */

}
