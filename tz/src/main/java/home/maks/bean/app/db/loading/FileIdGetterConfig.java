package home.maks.bean.app.db.loading;

import home.maks.db.loading.FileIdGetter;
import home.maks.db.loading.FileIdGetterImpl;
import home.maks.db.JdbcOperationsFactory;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileIdGetterConfig {
    @Autowired
    private JdbcOperationsFactory jdbcOperationsFactory;
    @Autowired
    private DSLContext dslContext;

    @Bean
    public FileIdGetter fileIdGetter() {
        return new FileIdGetterImpl(jdbcOperationsFactory, dslContext);
    }
}
