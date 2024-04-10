package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManger() throws SQLException {
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
    }

    @Test
    void datasourceDriverManager() throws SQLException {
        // DriverManagerDataSource - 항상 새로운 커넥션을 획득
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(dataSource);
    }

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        // 커넥션 풀링
        HikariDataSource dataSource = new HikariDataSource(); // 히카리 꺼... 성능 굿
        dataSource.setJdbcUrl(URL);
        dataSource.setPassword(PASSWORD);
        dataSource.setUsername(USERNAME);
        dataSource.setMaximumPoolSize(10); // default = 10
        dataSource.setPoolName("MyPool");

        useDataSource(dataSource);
        Thread.sleep(1000);

    }

    private void useDataSource(DataSource dataSource) throws SQLException {
        // 10개를 넘기면, 넘긴 놈들은 무한대기에 걸리게 된다.
        // db 를 close 하지 않아 반환하지 않았기 때문에 pool 에서 받을 수 있는 것이 없기 때문이다.
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
    }
}
