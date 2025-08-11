package org.zerock.ex00.mappers;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
class TimeTest {
    @Autowired(required = false)
    TimeMapper timeMapper;

    @Test
    public void test1() {

        log.info(timeMapper.getClass().getName());

        log.info("------------------------------");
        log.info(timeMapper.getTime());
        log.info("------------------------------");

        log.info("------------------------------");
    }

    @Test
    public void test2() {
        log.info(timeMapper.getTime2());
    }
}