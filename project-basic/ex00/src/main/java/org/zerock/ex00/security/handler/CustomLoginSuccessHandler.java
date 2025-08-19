package org.zerock.ex00.security.handler;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

        log.info("==============");
        log.info("4");
        log.info("==============");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("==============");
        log.info("3");
        log.info("==============");

        //사용자의 권한 -> Collection 타입
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roleNames = new ArrayList<>();
        authentication.getAuthorities().forEach(auth ->{
            roleNames.add(auth.getAuthority());
        });


        if (roleNames.contains("ROLE_ADMIN")) {
            response.sendRedirect("/sample/admin");
            return;
        }

        if (roleNames.contains("ROLE_MANAGER")) {
            response.sendRedirect("/sample/manager");
            return;
        }

        if (roleNames.contains("ROLE_USER")) {
            response.sendRedirect("/board/list");
            return;
        }
    }
}
