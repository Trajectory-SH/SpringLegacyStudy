package org.zerock.ex00.mappers;

import static org.junit.jupiter.api.Assertions.*;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.ex00.domain.BoardVO;
import org.zerock.ex00.domain.Criteria;
import org.zerock.ex00.domain.ReplyVO;
import org.zerock.ex00.mappers.BoardMapper;

import javax.script.ScriptEngine;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class ReplyMapperTest {

    //1566
    @Autowired(required = false)
    private ReplyMapper replyMapper;

    @Test
    public void testInsert() {

        Long bno = 1566L;

        for (int i = 0; i < 10; i++) {
            ReplyVO replyVO = ReplyVO.builder()
                    .bno(bno)
                    .replyText("Sample Reply Test")
                    .replyer("PierreBensusan")
                    .build();

            log.info(replyMapper.insert(replyVO));
        }
    }

}
