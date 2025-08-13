package org.zerok.ex00.sample;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.ex00.domain.BoardVO;
import org.zerock.ex00.domain.Criteria;
import org.zerock.ex00.mappers.BoardMapper;

import javax.script.ScriptEngine;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class BoardMapperTests {

    @Autowired(required = false)
    BoardMapper boardMapper;

    @Test
    public void test1() {
        log.info("=======================");
        log.info("BoardMapper"+boardMapper.getClass());
        log.info("=======================");
    }

    @Test
    public void testList() {
        boardMapper.getList().forEach(boardVO -> log.info(boardVO));
    }

    @Test
    public void testInsert() {
        BoardVO boardVO = new BoardVO();
        boardVO.setTitle("newTest");
        boardVO.setContent("hello World");
        boardVO.setWriter("JSH");

        log.info("count :" + boardMapper.insert(boardVO));
        log.info("bno :" + boardVO.getBno());
    }

    @Test
    public void testSelect() {
        Long bno = 3L;
        log.info(boardMapper.select(bno));
    }

    @Test
    public void testUpdate() {
        BoardVO boardVO = new BoardVO();
        boardVO.setTitle("update my Title");
        boardVO.setContent("hello my name is JSH");
        boardVO.setBno(3L);
        int updateCount = boardMapper.update(boardVO);

        log.info("update: " + updateCount);
    }

    @Test
    public void testPage() {
        Criteria criteria = new Criteria();
        criteria.setPageNum(2);
        //1,10

        List<BoardVO> list = boardMapper.getPage(criteria);

        list.forEach(boardVO -> log.info(boardVO+"hello criteria"));
    }

}
