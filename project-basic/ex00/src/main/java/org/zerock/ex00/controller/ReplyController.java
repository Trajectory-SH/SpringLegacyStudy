package org.zerock.ex00.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.zerock.ex00.domain.Criteria;
import org.zerock.ex00.domain.MemberVO;
import org.zerock.ex00.domain.PageDto;
import org.zerock.ex00.domain.ReplyVO;
import org.zerock.ex00.service.ReplyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
@Log4j2

public class ReplyController {

    private final ReplyService replyService;


    @PostMapping("/register")
    public ResponseEntity<Map<String, Long>> register(
            @RequestBody ReplyVO replyVO,
            @AuthenticationPrincipal MemberVO memberVO
    ){


        log.info(memberVO);
        log.info(replyVO);

        if(memberVO == null || ! memberVO.getUid().equals(replyVO.getReplyer())){

            log.info("--------------ERROR--------------");
            return ResponseEntity.badRequest().build();

        }


        log.info(replyVO);
        Long rno = replyService.register(replyVO);

        int replyCount = replyService.getReplyCountOfBoard(replyVO.getBno());

        return ResponseEntity.ok(Map.of("RNO", rno, "COUNT", (long) replyCount));

    }

    @GetMapping("/{rno}")
    public ReplyVO get(@PathVariable("rno")Long rno){

        return replyService.get(rno);
    }

    @DeleteMapping("/{rno}")
    public ResponseEntity<Map<String, Boolean>> delete(
            @PathVariable("rno")Long rno,
            @AuthenticationPrincipal MemberVO memberVO
    ) {

        try {
            checkAuthor(rno, memberVO);
        }catch(Exception e) {
            log.error("==========================");
            log.error(e.getMessage());
            return ResponseEntity
                    .status(403)
                    .build();
        }

        boolean result = replyService.remove(rno);

        return ResponseEntity.ok(Map.of("Result", result));

    }

    @PutMapping("/{rno}")
    public ResponseEntity<Map<String, Boolean>> modify(
            @PathVariable("rno")Long rno ,
            @RequestBody ReplyVO replyVO,
            @AuthenticationPrincipal MemberVO memberVO
    ) {

        try {
            checkAuthor(rno, memberVO);
        }catch(Exception e) {
            log.error("==========================");
            log.error(e.getMessage());
            return ResponseEntity
                    .status(403)
                    .build();
        }

        replyVO.setRno(rno);

        boolean result = replyService.modify(replyVO);

        return ResponseEntity.ok(Map.of("Result", result));

    }

    private void checkAuthor(Long rno, MemberVO memberVO){

        log.info("==============CHECK AUTHOR================");

        log.info("MEMBERVO");
        log.info(memberVO);

        if(rno == null || memberVO == null){
            throw new RuntimeException("NOT EXIST");
        }

        ReplyVO replyVO = replyService.get(rno);

        log.info("REPLYVO");
        log.info(replyVO);

        if( ! replyVO.getReplyer().equals(memberVO.getUid())){
            throw new RuntimeException("NOT PERMITTED");
        }//

    }



    @GetMapping("/list/{bno}")
    public ResponseEntity<Map<String, Object>> getListOfBoard(
            @PathVariable("bno") Long bno,
            @AuthenticationPrincipal MemberVO memberVO,
            Criteria criteria){

        if(memberVO == null) {
            return ResponseEntity.status(403).build();
        }

        log.info("bno: " + bno);
        log.info(criteria);

        List<ReplyVO> replyList = replyService.getListWithBno(criteria, bno);

        int total = replyService.getTotalWithBno(criteria, bno);

        PageDto pageDto = new PageDto(criteria, total);


        Map<String, Object> map = new HashMap<>();
        map.put("replyList", replyList);
        map.put("pageDto", pageDto);

        return ResponseEntity.ok(map);

    }

}
