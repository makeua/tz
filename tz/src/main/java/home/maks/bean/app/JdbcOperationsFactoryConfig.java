package home.maks.bean.app;

import home.maks.db.JdbcOperationsFactory;
import home.maks.db.JdbcOperationsFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JdbcOperationsFactoryConfig {
    @Bean
    public JdbcOperationsFactory jdbcOperationsFactory(DataSource hikariDataSource) {
        return new JdbcOperationsFactoryImpl(hikariDataSource);
    }
}
