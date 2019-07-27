package home.maks.bean.app.db.mapping;

import home.maks.db.BulkInserterFactory;
import home.maks.db.JdbcOperationsFactory;
import home.maks.db.mapping.FileContentRecordBulkInserterFactory;
import home.maks.db.model.tables.records.FileContentRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileContentRecordBulkInserterFactoryConfig {
    @Autowired
    private JdbcOperationsFactory jdbcOperationsFactory;

    @Bean
    public BulkInserterFactory<FileContentRecord> fileContentRecordBulkInserterFactory() {
        return new FileContentRecordBulkInserterFactory(jdbcOperationsFactory);
    }
}
