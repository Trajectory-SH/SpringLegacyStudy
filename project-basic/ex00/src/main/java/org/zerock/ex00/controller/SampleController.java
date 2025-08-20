package org.zerock.ex00.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SampleController {

    //void 타입인 경우에는 URL경로를 논리 뷰 이름으로 사용한다.
    @GetMapping("/basic ")
    public void basic() {
        log.info("basic---------------");
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    public void all() {
        log.info("All-------------");
    }
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/manager")
    public void manager() {
        log.info("manager===========");
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public void admin() {
        log.info("admin===========");
    }
}
