package com.changsu.project.service.posts;

import com.changsu.project.domain.posts.Posts;
import com.changsu.project.domain.posts.PostsRepository;
import com.changsu.project.web.dto.PostsListResponseDto;
import com.changsu.project.web.dto.PostsResponseDto;
import com.changsu.project.web.dto.PostsSaveRequestDto;
import com.changsu.project.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


//Controller와 Service에서 @Autowired가 없는 이유는 RequiredArgsConstructor를 추가 해서이다.
//@Autowired setter 생성자 Bean을 주입하는 방식이다.
//final이 선언된 모든 필드를 인자값으로 하는 생성자를 대체해준다.
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {

        return postsRepository.save(requestDto.toEntity()).getId();
    }

    /**
     * @desc  업데이트되는 부분
     * @param id
     * @param requestDto
     * @return
     */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당게시글이 없습니다. id=" + id));
        //쿼리를 날리는 부분이 없다. JPA의 영속성 컨텍스트 때문이다.
        //엔티티를 영구 저장하는 환경
        //JPA의 엔티티 매니저가 활성화된 상태로 트랜잭션 안에서 데이터 비에스에서 데이터를 가져오면 유지된다.
        //Update ㅝ리를 날릴 필요가 없다. 더티체킹이라고한다.
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    /**
     * 트랜잭션 범위는 유지하되 조회기능만 남겨두어 조회속도가 개선되기때문에 서비스 메소드에 사용하면 좋다.
     * @return List<PostsListResponseDto>
     */
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) //이부분은 람다식이다.
                .collect(Collectors.toList());
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    /**
     * @desc JpaRepository에서 이미 delete메소드를 지원하고 있으며 이를 활용한다.
     * 엔티티를 파라미터로 삭제할 수도 있고, deleteById메소드를 이용하면 id로 삭제할 수도 있따.
     * 존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제한다.
     * @param id
     */
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

}
