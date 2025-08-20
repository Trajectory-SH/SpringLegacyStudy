package org.zerock.ex00.security;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.ex00.domain.MemberAuthVO;
import org.zerock.ex00.domain.MemberVO;
import org.zerock.ex00.mappers.MemberMapper;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({
        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/security-context.xml"
})
@Log4j2
public class MemberTests {

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired(required = false)
    MemberMapper memberMapper;

    @Test
    public void testInsert() {
        for (int i = 0; i < 100; i++) {
            MemberVO memberVO = new MemberVO();
            memberVO.setUid("user" + i);
            memberVO.setUpw(passwordEncoder.encode("1111"));
            memberVO.setUname("USER" + i);
            memberVO.setEmail("user" + i + "@aaa.com");

            List<MemberAuthVO> authList = new ArrayList<>();

            MemberAuthVO userAuthVO = new MemberAuthVO("ROLE_USER", "user" + i);
            authList.add(userAuthVO);

            if (i >= 50) {
                MemberAuthVO managerAuthVO = new MemberAuthVO("ROLE_MANAGER" , "user" + i);
                authList.add(managerAuthVO);
            }

            if (i >= 90) {
                MemberAuthVO adminAuthVO = new MemberAuthVO("ROLE_ADMIN" , "user" + i);
                authList.add(adminAuthVO);
            }

            memberVO.setAuthVOList(authList);

            memberMapper.insert(memberVO);

            for (MemberAuthVO memberAuthVO : authList) {
                memberMapper.insertAuth(memberAuthVO);
            }
        }//end for
    }


    @Test
    public void testSelect() {
        String uid = "user99";
        log.info("======================");
        log.info(memberMapper.select(uid));

    }













}
