package org.zerock.ex00.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.zerock.ex00.domain.Criteria;
import org.zerock.ex00.domain.PageDto;
import org.zerock.ex00.domain.ReplyVO;
import org.zerock.ex00.service.ReplyService;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
@Log4j2
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/{rno}")
    public ReplyVO get(@PathVariable("rno") Long rno) {

        return replyService.get(rno);
    }

    @DeleteMapping("/{rno}")
    public Map<String, Boolean> delete(@PathVariable("rno") Long rno) {
        boolean result = replyService.remove(rno);
        return Map.of("Result", result);
    }

    @PutMapping("/{rno}")
    public Map<String, Boolean> modify(@PathVariable("rno") Long rno,
                                       @RequestBody ReplyVO replyVO) {

        replyVO.setRno(rno);
        boolean result = replyService.modify(replyVO);

        return Map.of("Result", result);
    }

    @PostMapping("/register")
    public Map<String, Long> register(@RequestBody ReplyVO replyVO) {

        log.info("---------------------");
        log.info("---------------------");
        log.info(replyVO);
        log.info("---------------------");
        log.info("---------------------");

        Long rno = replyService.register(replyVO);
        int replyCount = replyService.getReplyCountOfBoard(replyVO.getBno());
        // SELECT LAST_INSERT_ID() -> 마지막으로 AutoIncrement된 ID를 Return

        return Map.of("RNO", rno, "COUNT", (long) replyCount);
    }


    @GetMapping(value = "/list/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getListOfBoard(@PathVariable("bno") Long bno,
                                              @ModelAttribute Criteria cri) {


        List<ReplyVO> replyList = replyService.getListWithBno(cri, bno);

        int total = replyService.getTotalWithBno(cri, bno);

        PageDto pageDto = new PageDto(cri, total);
        return Map.of("replyList", replyList, "pageDto", pageDto);
    }

    @GetMapping("/txtest")
    public String[] get(String str) {
        replyService.insertTwo(str);

        return new String[]{"a", "b"};
    }
}