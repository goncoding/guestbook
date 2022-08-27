package com.gon.ex02.repository;

import com.gon.ex02.entity.Memo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void test01() throws Exception {
        //given
        System.out.println("memberRepository = " + memberRepository.getClass().getName()); //동적 프록시

        //when
        IntStream.rangeClosed(1,100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample..." + i).build();
            memberRepository.save(memo);
        });

        //then
    }

    @Transactional
    @Test
    public void test02() throws Exception {
        //given

        //when
        long mno = 100L;
        Memo one = memberRepository.getOne(mno);
        System.out.println("===========================================");
        System.out.println("one = " + one);



        //then
    }

    @Test
    public void test03() throws Exception {

        Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();
        System.out.println("memo = " + memberRepository.save(memo));

    }
 @Test
    public void test04() throws Exception {

     memberRepository.deleteById(100L);

    }

    @Test
    public void test05() throws Exception {


        PageRequest pageable = PageRequest.of(0, 10);
        Page<Memo> result = memberRepository.findAll(pageable);

        System.out.println("result.getTotalPages() = " + result.getTotalPages());

    }

    @Test
    public void test06() throws Exception {

        Sort sort1 = Sort.by("mno").descending();

        PageRequest pageable = PageRequest.of(0, 10, sort1);
        Page<Memo> reuslt = memberRepository.findAll(pageable);

        reuslt.get().forEach(memo -> {
            System.out.println("memo = " + memo);
        });


    }
    @Test
    public void test07() throws Exception {

        PageRequest pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Memo> result = memberRepository.findByMnoBetween(10L, 50L, pageable);

        result.get().forEach(memo -> System.out.println("memo = " + memo));


    }

    @Test
    @Transactional
    @Commit
    public void test08() throws Exception {

        memberRepository.deleteMemoByMnoLessThan(10L);

    }




}