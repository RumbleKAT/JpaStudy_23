package com.fastcampus.ch4;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board,Long> {
    int countAllByWriter(String writer);
    List<Board> findByWriter(String writer);

    List<Board> findByTitleAndWriter(String title, String writer);
    @Transactional
    int deleteByWriter(String writer);
}
