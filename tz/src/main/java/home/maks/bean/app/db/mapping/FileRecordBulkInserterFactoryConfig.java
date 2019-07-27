package home.maks.bean.app.db.mapping;

import home.maks.db.BulkInserterFactory;
import home.maks.db.JdbcOperationsFactory;
import home.maks.db.mapping.FileRecordBulkInserterFactory;
import home.maks.db.model.tables.records.FileRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileRecordBulkInserterFactoryConfig {
    @Autowired
    private JdbcOperationsFactory jdbcOperationsFactory;

    @Bean
    public BulkInserterFactory<FileRecord> fileRecordBulkInserterFactory() {
        return new FileRecordBulkInserterFactory(jdbcOperationsFactory);
    }
}
