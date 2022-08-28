package com.gon.ex02.service;

import com.gon.ex02.dto.GuestbookDTO;
import com.gon.ex02.dto.PageRequestDTO;
import com.gon.ex02.dto.PageResultDTO;
import com.gon.ex02.entity.Guestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class GuestbookServiceTest {


    @Autowired
    private GuestbookService service;

    @Test
    public void test1() throws Exception {
        //given
        GuestbookDTO book = GuestbookDTO.builder()
                .title("title...1")
                .content("content...1")
                .writer("user...1")
                .build();

        service.register(book);

        //when


        //then
    }

    @Test
    public void test02() throws Exception {
        //given
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

        System.out.println("----------------------------------------------");

        for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
            System.out.println("guestbookDTO = " + guestbookDTO);
        }

        System.out.println("===================================================");

        resultDTO.getPageList().forEach( i -> System.out.println(i));

        //when


        //then
    }

    @Test
    public void test03() throws Exception {
        //given
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("tc")
                .keyword("한글")
                .build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

        System.out.println("resultDTO.isPrev() = " + resultDTO.isPrev());
        System.out.println("resultDTO.isNext() = " + resultDTO.isNext());
        System.out.println("resultDTO.getTotalPage() = " + resultDTO.getTotalPage());

        System.out.println("------------------------------------------------");
        for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
            System.out.println("guestbookDTO = " + guestbookDTO);
        }



        //when


        //then
    }



}