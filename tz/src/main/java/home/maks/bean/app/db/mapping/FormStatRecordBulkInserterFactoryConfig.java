package home.maks.bean.app.db.mapping;

import home.maks.db.BulkInserterFactory;
import home.maks.db.JdbcOperationsFactory;
import home.maks.db.mapping.FormStatRecordBulkInserterFactory;
import home.maks.db.model.tables.records.FormStatRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormStatRecordBulkInserterFactoryConfig {
    @Autowired
    private JdbcOperationsFactory jdbcOperationsFactory;

    @Bean
    public BulkInserterFactory<FormStatRecord> formStatRecordBulkInserterFactory() {
        return new FormStatRecordBulkInserterFactory(jdbcOperationsFactory);
    }
}
