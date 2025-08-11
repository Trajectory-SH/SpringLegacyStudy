package org.zerock.ex00.mappers;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
    //mySql에서 현재 시간을 가져오는 SQL -> @Select는 MyBatis에서 제공하는 어노테이션 매퍼 기능 중 하나로, SQL을 자바 코드에 직접 작성할 수 있게 해줍니다.
    @Select("select now()")
    String getTime();
    /*
    실체는 스프링이 로딩하면서 만들어준다. 인터페이스만 있는데...ibatis = 과거 mybatis의 이름.
    -> CGLIB 바이트코드의 조작
     */


    String getTime2();
}
