package org.zerok.ex00.sample;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.ex00.sample.Restaurant;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DITest {

    @Autowired
    Restaurant restaurant;

    @Test
    public void testExist() {
        System.out.println(restaurant);
    }


}
