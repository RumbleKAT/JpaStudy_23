package com.fastcampus.ch4;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardRepositoryTest3 {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    public EntityManager em;

    @BeforeEach
    public void testData(){
        for(int i=1;i<=100;i++){
            Board board = new Board();
            board.setBno((long)i);
            board.setTitle("title" + i);
            board.setContent("Content " + i);
            board.setWriter("writer" + (i%5));
            board.setViewCnt((long)(Math.random()*100));
            board.setInDate(new Date());
            board.setUpDate(new Date());
            boardRepository.save(board);
        }
    }

    @Test
    @DisplayName("@Query로 JPQL작성 테스트")
    public void queryAnnoTest(){
        List<Board> list = boardRepository.findAllBoard();
        assertTrue(list.size() == 100);
    }

    @Test
    @DisplayName("@Query로 JPQL작성 테스트 - 매개변수 순서")
    public void queryAnnoTest2(){
        List<Board> list = boardRepository.findByTitleAndWriter2("title1","writer1");
        list.forEach(System.out::println);
        assertTrue(list.size() == 1);
    }

    @Test
    @DisplayName("@Query로 JPQL작성 테스트 - 매개변수 이름")
    public void queryAnnoTest3(){
        List<Board> list = boardRepository.findByTitleAndWriter("title1","writer1");
        list.forEach(System.out::println);
        assertTrue(list.size() == 1);
    }

    @Test
    @DisplayName("@Query를 네이티브 쿼리(SQL) 작성 테스트 - 매개변수 이용")
    public void queryAnnoTest4(){
       List<Board> allBoardBySql =  boardRepository.findAllBoardBySql();
        assertTrue(allBoardBySql.size() == 100);
    }

    @Test
    @DisplayName("@Query를 네이티브 쿼리(SQL) 작성 테스트 - 매개변수 이용")
    public void queryAnnoTest5(){
        List<Object[]> list =  boardRepository.findTitleAndWriterBoardBySql();
        list.stream().map(arr -> Arrays.toString(arr)).forEach(System.out::println);
        assertTrue(list.size() == 100);
    }



    @Test
    @DisplayName("createQuery로 JPQL 작성 테스트")
    public void createQueryTest(){
        String query = "SELECT b FROM Board b";
        TypedQuery<Board> tQuery = em.createQuery(query, Board.class);
        List<Board> list = tQuery.getResultList();

        list.forEach(System.out::println);
        assertTrue(list.size() == 100);
    }

}