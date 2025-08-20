package org.zerock.ex00.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.ex00.domain.*;
import org.zerock.ex00.service.BoardService;
import org.zerock.ex00.util.UpDownUtil;

import java.util.Arrays;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class
BoardController {

    private final BoardService boardService;
    private final UpDownUtil upDownUtil;
    //list
//  @GetMapping("/list")
//  public void list(Model model){
//    log.info("list................");
//
//    java.util.List<BoardVO> list = boardService.list();
//
//    log.info(list);
//    log.info("리스트 출력");
//    log.info("리스트 출력");
//    log.info("리스트 출력");
//    log.info("리스트 출력");
//
//    model.addAttribute("list",list);
//
//  }

    @GetMapping("/list")
    public void list(
            @ModelAttribute("cri") Criteria criteria,
            Model model){
        log.info("list................");
        log.info("criteria: " + criteria);

        java.util.List<BoardVO> list = boardService.getList(criteria);

        log.info(list);
        log.info("리스트 출력");


        model.addAttribute("list",list);

        PageDto pageDTO = new PageDto(criteria, boardService.getTotal(criteria));

        model.addAttribute("pageMaker", pageDTO);

    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping({"/read/{bno}"})
    public String readGET(
            @PathVariable(name = "bno") Long bno,
            @ModelAttribute("cri") Criteria criteria,
            Model model ) {

        BoardVO boardVO = boardService.get(bno);

        log.info("boardVO: " + boardVO);

        model.addAttribute("vo", boardVO);

        return "/board/read";

    }

    //안전한 방법
    @PreAuthorize("isAuthenticated()")
    @GetMapping({"/modify/{bno}"})
    public String modifyGET(
            @PathVariable(name = "bno") Long bno,
            @ModelAttribute("cri") Criteria criteria,
            @AuthenticationPrincipal MemberVO memberVO,
            Model model ) {

        BoardVO boardVO = boardService.get(bno);

        log.info("boardVO: " + boardVO);


        log.info("-========================================");
        log.info("-========================================");
        log.info(memberVO);
        log.info("-========================================");

        if(memberVO != null){
            if( !memberVO.getUid().equals(boardVO.getWriter())){
                throw new AccessDeniedException("NOT_OWNER");
            }
        }



        model.addAttribute("vo", boardVO);

        return "/board/modify";

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/register")
    public void register() {
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/register")
    public String registerPost(BoardVO boardVO,
                               @RequestParam(value = "files", required = false) MultipartFile[] files,
                               RedirectAttributes rttr) {


        log.info("boardVO: " + boardVO);

        log.info("------------------------------------------");
        log.info(Arrays.toString(files));

        List<AttachVO> attachVOList = upDownUtil.upload(files);

        boardVO.setAttachVOList(attachVOList);

        Long bno = boardService.register(boardVO);

        log.info("bno: " + bno);

        rttr.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    }


    @PostMapping("/remove/{bno}")
    public String remove(
            @PathVariable(name="bno") Long bno,
            @RequestParam(value = "anos",required = false)Long[] anos,
            @RequestParam(value = "fullNames",required = false)String[] fullNames,
            @AuthenticationPrincipal MemberVO memberVO,
            RedirectAttributes rttr){

        //기존의 게시물을 조회해서 비교하고
        BoardVO boardVO = boardService.get(bno);

        if (boardVO == null) {
            return "/board/list";
        }
        if (!boardVO.getWriter().equals(memberVO.getUid())) {
            throw new AccessDeniedException("NOT_OWNER");
        }
        //비교가 끝나면 변경해서 저장

        boardVO.setTitle("해당 글은 삭제 되었습니다");
        boardVO.setContent("해당 글은 삭제 되었습니다.");
        
        boardVO.setDelFlag(true);

        log.info("boardVO: " + boardVO);
        
        boardService.modify(boardVO,anos);

        upDownUtil.deleteFiles(fullNames);

        rttr.addFlashAttribute("result", boardVO.getBno());

        return "redirect:/board/list";

    }
    //안전하지 않은 방법
    @PreAuthorize("principal.username==#boardVO.writer")
    @PostMapping("/modify/{bno}")
    public String modify(
            @PathVariable(name = "bno") Long bno,
            @RequestParam(value = "files", required = false) MultipartFile[] files,
            @RequestParam(value = "anos", required = false) Long[] anos,
            @RequestParam(value = "fullNames", required = false) String[] fullNames,
            BoardVO boardVO,
            RedirectAttributes rttr) {

        boardVO.setBno(bno);

        List<AttachVO> attachVOList = upDownUtil.upload(files);
        if (attachVOList != null && attachVOList.size() > 0) {
            boardVO.setAttachVOList(attachVOList);
        }


        log.info("boardVO: " + boardVO);

        boardService.modify(boardVO, anos);

        upDownUtil.deleteFiles(fullNames);

        rttr.addFlashAttribute("result", boardVO.getBno());

        return "redirect:/board/read/" + bno;

    }



//  @GetMapping("/modify/{bno}")
//  public String modify(
//          @PathVariable(name = "bno") Long bno, Model model ) {
//
//    log.info("bno: " + bno);
//
//    BoardVO boardVO = boardService.get(bno);
//
//    log.info("boardVO: " + boardVO);
//
//    model.addAttribute("vo", boardVO);
//
//    return "/board/modify";
//
//  }




}
