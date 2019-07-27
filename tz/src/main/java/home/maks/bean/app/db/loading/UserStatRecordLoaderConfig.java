package home.maks.bean.app.db.loading;

import home.maks.db.JdbcOperationsFactory;
import home.maks.db.loading.UserStatRecordLoader;
import home.maks.db.loading.UserStatRecordLoaderImpl;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserStatRecordLoaderConfig {
    @Autowired
    private JdbcOperationsFactory jdbcOperationsFactory;
    @Autowired
    private DSLContext dslContext;

    @Bean
    public UserStatRecordLoader userStatLoader() {
        return new UserStatRecordLoaderImpl(jdbcOperationsFactory, dslContext);
    }
}
