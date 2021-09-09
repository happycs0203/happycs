package com.changsu.project.web.dto;

import com.changsu.project.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Controller -> Service -> Ropository -> Dto -> Entity

//Entity클래스와 거의 유사한 형태 Entity는 데이터베이스와 맞다은 클래스이다.
// Dto 클래스를 추가로 생성했다.
//절때로 Entity 클래스를 Request/Response클래스로 사용해서는 안된다.
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
