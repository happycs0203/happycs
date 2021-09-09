package com.changsu.project.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Entity들의 createdDate modifiedDate를 자동으로 관리하는 역할
 */
@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들도 컬럼으로 인식하게된다.
@EntityListeners(AuditingEntityListener.class) //Auditing기능을 포함시킨다.
public class BaseTimeEntity {

    @CreatedDate //Entity가 생성되어 저장될 때 시간이 자동 저장된다.
    private LocalDateTime createDate;

    @LastModifiedDate //조회한 Entity의 값을 벼경할때 시간이 자동 저장된다.
    private LocalDateTime modifiedDate;


}
