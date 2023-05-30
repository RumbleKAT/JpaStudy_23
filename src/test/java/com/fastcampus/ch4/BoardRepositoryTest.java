package com.fastcampus.ch4;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    @Order(4)
    public void deleteTest(){
        boardRepository.deleteById(1L);

        Board board = boardRepository.findById(1L).orElse(null);
        assertTrue(board == null);
    }


    @Test
    @Order(3)
    public void updateTest(){
        Board board = boardRepository.findById(1L).orElse(null);
        assertTrue(board != null);

        board.setTitle("modified Title");
        boardRepository.save(board);
        Board board1 = boardRepository.findById(1L).orElse(new Board());
        System.out.println("board = " + board);
        System.out.println("board2 = " + board1);

        assertTrue(board.getTitle().equals(board1.getTitle()));
    }

    @Test
    @Order(1)
    public void insertTest(){
        Board board = new Board();
        board.setBno(1L);
        board.setTitle("Test Title");
        board.setContent("This is Text");
        board.setWriter("aaa");
        board.setViewCnt(0L);
        board.setInDate(new Date());
        board.setUpDate(new Date());
        boardRepository.save(board);
    }

    @Test
    @Order(2)
    public void selectTest(){
        Board board = boardRepository.findById(1L).orElse(null);
        assertTrue(board != null);
    }
}