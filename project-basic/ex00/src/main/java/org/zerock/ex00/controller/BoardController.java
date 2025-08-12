package org.zerock.ex00.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.ex00.domain.BoardVO;
import org.zerock.ex00.domain.Criteria;
import org.zerock.ex00.domain.PageDto;
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
    public void list(
            @ModelAttribute("cri") Criteria criteria,//pageNum과 Amount값을 수집해준다.
            Model model) {
        log.info("list call..........");
        List<BoardVO> list = boardService.getList(criteria);



        model.addAttribute("list", list);

        PageDto pageDto = new PageDto(criteria, boardService.getTotal(criteria));
        log.info("-------------------------------------");
        log.info(pageDto);
        log.info("-------------------------------------");
        model.addAttribute("pageMaker", pageDto);
    }

    @GetMapping({"/{job}/{bno}"})
    //한 메서드를 이용해서 여러가지 URL을 처리 할 수 있다.
    public String read(@PathVariable(name = "bno") Long bno,
                       @PathVariable(name = "job") String job,
                       @ModelAttribute("cri") Criteria criteria,
                       Model model) {
        log.info("bno: {}", bno);
        log.info("job: {}", job);

        if (!(job.equals("read") || job.equals("modify"))) {
            throw new RuntimeException("Bad Request Job");
        }

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

    @GetMapping("/register")
    public void register() {
    }

    @PostMapping("/register")
    public String registerPost(BoardVO boardVO,
                               RedirectAttributes rttr) {

        log.info("boardVO: " + boardVO);

        Long bno = boardService.register(boardVO);

        rttr.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    }
}
