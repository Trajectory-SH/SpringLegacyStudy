package org.zerok.ex00.sample;

import org.junit.jupiter.api.Test;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTests {

    @Test
    public void testConnection() throws ClassNotFoundException, SQLException {
        //Driver Class
        Class.forName("com.mysql.jdbc.Driver");

        //URL username password
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/springdb", "springdbuser", "springdbuser");
        System.out.println(connection);

        connection.close();



    }
}
