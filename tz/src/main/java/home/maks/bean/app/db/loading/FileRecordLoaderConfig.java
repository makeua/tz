package home.maks.bean.app.db.loading;

import home.maks.db.JdbcOperationsFactory;
import home.maks.db.loading.FileRecordLoader;
import home.maks.db.loading.FileRecordLoaderImpl;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileRecordLoaderConfig {
    @Autowired
    private JdbcOperationsFactory jdbcOperationsFactory;
    @Autowired
    private DSLContext dslContext;

    @Bean
    public FileRecordLoader fileLoader() {
        return new FileRecordLoaderImpl(jdbcOperationsFactory, dslContext);
    }
}
