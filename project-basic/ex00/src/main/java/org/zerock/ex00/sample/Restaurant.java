package org.zerock.ex00.sample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
@RequiredArgsConstructor
public class Restaurant {

    //생성자 주입
    private final Chef chef;


}
