package com.fastcampus.ch4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardRepositoryTest2 {

    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    public void testData(){
        for(int i=0;i<100;i++){
            Board board = new Board();
            board.setBno((long)i);
            board.setTitle("title " + i);
            board.setContent("Content " + i);
            board.setWriter("writer" + (i%5));
            board.setViewCnt((long)(Math.random()*100));
            board.setInDate(new Date());
            board.setUpDate(new Date());
            boardRepository.save(board);
        }
    }

    @Test
    public void deleteTest(){
        assertTrue(boardRepository.deleteByWriter("writer1")==20);
        List<Board> list = boardRepository.findByWriter("writer1");
        assertTrue(list.size() == 0);
    }

    @Test
    public void findTest(){
       List<Board> list = boardRepository.findByWriter("writer1");
       assertTrue(list.size() == 20);
       list.forEach(System.out::println);
    }

    @Test
    public void countTest(){
       assertTrue(boardRepository.countAllByWriter("writer1")==20);
    }

}