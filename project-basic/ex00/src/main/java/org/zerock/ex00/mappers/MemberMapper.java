package org.zerock.ex00.mappers;

import org.zerock.ex00.domain.MemberAuthVO;
import org.zerock.ex00.domain.MemberVO;

public interface MemberMapper {

    void insert(MemberVO memberVO);

    void insertAuth(MemberAuthVO memberAuthVO);

    //uid만 Parameter로 받는다면 나머지는 Spring Security가 대신 처리해준다.
    MemberVO select(String uid);
}
