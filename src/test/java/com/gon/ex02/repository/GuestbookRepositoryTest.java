package com.gon.ex02.repository;

import com.gon.ex02.entity.Guestbook;
import com.gon.ex02.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestbookRepositoryTest {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertClosed() throws Exception {

        IntStream.rangeClosed(1,300).forEach(i -> {
            Guestbook book = Guestbook.builder()
                    .title("title..." + i)
                    .content("content..." + i)
                    .writer("user..." + i)
                    .build();

            System.out.println("book = " + guestbookRepository.save(book));

        });

    }

    @Test
    public void insertClosed2() throws Exception {

        Optional<Guestbook> result = guestbookRepository.findById(300L);

        if(result.isPresent()){
            Guestbook guestbook = result.get();
            guestbook.changeTitle("changeTitle.......");
            guestbook.changeContent("changeContent........");

            guestbookRepository.save(guestbook);
        }

    }

    @Test
    public void test03() throws Exception {

        BooleanBuilder builder = new BooleanBuilder();
        QGuestbook qGuestbook = QGuestbook.guestbook;

        PageRequest pageable = PageRequest.of(0, 10, Sort.by("gno"));//1

        String keyword = "1";
        BooleanExpression expression = qGuestbook.title.contains(keyword);

        builder.and(expression);

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestbook -> {
            System.out.println("guestbook = " + guestbook);
        });


    }

    @Test
    public void test04() throws Exception {

        PageRequest pageable = PageRequest.of(0, 10, Sort.by("gno").descending());//1

        QGuestbook qGuestbook = QGuestbook.guestbook;

        BooleanBuilder builder = new BooleanBuilder();

        String keyword = "1";

        BooleanExpression exTitle = qGuestbook.title.contains(keyword);
        BooleanExpression exContent = qGuestbook.title.contains(keyword);
        BooleanExpression exAll = exTitle.or(exContent);

        builder.and(exAll);

        builder.and(qGuestbook.gno.gt(0L));

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestbook -> {
            System.out.println("guestbook = " + guestbook);
        });


    }


}