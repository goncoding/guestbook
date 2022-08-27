package com.gon.ex02.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="tbl_name")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;


}
