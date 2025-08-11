package org.zerock.ex00.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.ex00.domain.BoardVO;
import org.zerock.ex00.service.BoardService;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    //첫번째 개발의 대상 -> List (board의 list)
    @GetMapping("/list")
    public void list(Model model) {
        log.info("list call..........");

        List<BoardVO> list = boardService.list();
        log.info(list);

        model.addAttribute("list", list);
    }

    @GetMapping({"/{job}/{bno}","/{job}/{bno}"})
    //한 메서드를 이용해서 여러가지 URL을 처리 할 수 있다.
    public String read(@PathVariable(name = "bno") Long bno,
                       @PathVariable(name = "job") String job,
                       Model model) {
        log.info("bno: {}", bno);
        log.info("job: {}", job);


        BoardVO boardVO = boardService.get(bno);

        log.info("boardVO: {}", boardVO);

        model.addAttribute("vo", boardVO);

        return "/board/" + job;
    }


/*
    @GetMapping("/modify/{bno}")
    public String modify(@PathVariable(name = "bno") Long bno, Model model) {
        log.info("bno: {}", bno);

        BoardVO boardVO = boardService.get(bno);

        log.info("boardVO: {}", boardVO);

        model.addAttribute("vo", boardVO);

        return "/board/modify";
    }
*/




}
