package home.maks.bean.app.db.mapping;

import home.maks.db.BulkInserterFactory;
import home.maks.db.JdbcOperationsFactory;
import home.maks.db.mapping.UserStatRecordBulkInserterFactory;
import home.maks.db.model.tables.records.UserStatRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserStatRecordBulkInserterFactoryConfig {
    @Autowired
    private JdbcOperationsFactory jdbcOperationsFactory;

    @Bean
    public BulkInserterFactory<UserStatRecord> userStatRecordBulkInserterFactory() {
        return new UserStatRecordBulkInserterFactory(jdbcOperationsFactory);
    }
}
