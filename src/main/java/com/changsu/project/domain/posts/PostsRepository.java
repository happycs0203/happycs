package com.changsu.project.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//JpaRepository <Entity 클래스, PK타입>를 상속하면 기본적인 CRUD메소드가 자동으로 생성된다.
public interface PostsRepository extends JpaRepository<Posts, Long> {

    //SpringDataJpa에서 제공하지 않는 메소드는 쿼리로 작성해도 된다!! (가독성이 좋아서 사용해도된다!)
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
