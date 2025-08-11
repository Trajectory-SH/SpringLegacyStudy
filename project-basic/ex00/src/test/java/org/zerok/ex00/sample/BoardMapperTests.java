package org.zerok.ex00.sample;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.ex00.mappers.BoardMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class BoardMapperTests {
    @Autowired(required = false)
    BoardMapper boardMapper;

    @Test
    public void test1() {
        log.info(boardMapper);
    }

    @Test
    public void testList() {
        boardMapper.getList().forEach(boardVO -> log.info(boardVO));
    }
}
