package com.gon.ex02.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
//해당 클래스를 상속받는 엔티티에서 해당 클래스 필드를 컬럼으로 사용
@MappedSuperclass //테이블 생성 안됨 , 상속 받은 테이블에 생김 - 슈퍼클래스와 매핑
//AuditingEntityListener를 지정하면 . BaseEntity를  상속받는 곳에서는 AuditingEntityListener를 지정할 필요가 없다 .
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public class BaseEntity {

    @CreatedDate
    @Column(name="regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;

}
