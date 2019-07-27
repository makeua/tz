package home.maks.bean.app.db.loading;

import home.maks.db.JdbcOperationsFactory;
import home.maks.db.loading.FormStatRecordLoader;
import home.maks.db.loading.FormStatRecordLoaderImpl;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormStatRecordLoaderConfig {
    @Autowired
    private JdbcOperationsFactory jdbcOperationsFactory;
    @Autowired
    private DSLContext dslContext;

    @Bean
    public FormStatRecordLoader formStatLoader() {
        return new FormStatRecordLoaderImpl(jdbcOperationsFactory, dslContext);
    }
}
