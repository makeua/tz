package home.maks.db.mapping;

import home.maks.db.*;
import home.maks.db.model.tables.records.FileRecord;

public final class FileRecordBulkInserterFactory extends AbstractBulkInserterFactory<FileRecord> {

    public FileRecordBulkInserterFactory(JdbcOperationsFactory jdbcOperationsFactory) {
        super(jdbcOperationsFactory);
    }

    @Override
    public BulkInserter<FileRecord> createBulkInserter() {
        return new BulkInserterImpl<>(jdbcOperationsFactory, new FileRecordMapping());
    }
}
