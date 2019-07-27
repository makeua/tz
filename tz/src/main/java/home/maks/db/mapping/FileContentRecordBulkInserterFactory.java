package home.maks.db.mapping;

import home.maks.db.AbstractBulkInserterFactory;
import home.maks.db.BulkInserter;
import home.maks.db.BulkInserterImpl;
import home.maks.db.JdbcOperationsFactory;
import home.maks.db.model.tables.records.FileContentRecord;

public final class FileContentRecordBulkInserterFactory extends AbstractBulkInserterFactory<FileContentRecord> {
    public FileContentRecordBulkInserterFactory(JdbcOperationsFactory jdbcOperationsFactory) {
        super(jdbcOperationsFactory);
    }

    @Override
    public BulkInserter<FileContentRecord> createBulkInserter() {
        return new BulkInserterImpl<>(jdbcOperationsFactory, new FileContentRecordMapping());
    }
}
