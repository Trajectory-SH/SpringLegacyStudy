package org.zerok.ex00.sample;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.ex00.sample.Restaurant;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DITest {

    @Autowired
    Restaurant restaurant;

    @Autowired
    DataSource dataSource;

    @Test
    public void testCon() throws SQLException {

        Connection con = dataSource.getConnection();
        System.out.println(con);

        con.close();
    }

    @Test
    public void testExist() {
        System.out.println(restaurant);
    }


}
