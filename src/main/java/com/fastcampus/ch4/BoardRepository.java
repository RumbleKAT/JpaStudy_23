package com.fastcampus.ch4;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board,Long> {

    @Query("SELECT b FROM Board b")// JPQL은 대소문자 구분에 주의
    List<Board> findAllBoard();

    @Query("SELECT b FROM Board b WHERE b.title=?1 AND b.writer=?2")
    List<Board> findByTitleAndWriter2(String title, String writer);

    @Query(value="SELECT * FROM board", nativeQuery = true)
    List<Board> findAllBoardBySql();

    @Query(value="SELECT title, writer FROM board", nativeQuery = true)
    List<Object[]> findTitleAndWriterBoardBySql();

    int countAllByWriter(String writer);
    List<Board> findByWriter(String writer);

    List<Board> findByTitleAndWriter(String title, String writer);
    @Transactional
    int deleteByWriter(String writer);
}
