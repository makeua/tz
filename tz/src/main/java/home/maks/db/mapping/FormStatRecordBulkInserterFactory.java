package home.maks.db.mapping;

import home.maks.db.AbstractBulkInserterFactory;
import home.maks.db.BulkInserter;
import home.maks.db.BulkInserterImpl;
import home.maks.db.JdbcOperationsFactory;
import home.maks.db.model.tables.records.FormStatRecord;

public class FormStatRecordBulkInserterFactory extends AbstractBulkInserterFactory<FormStatRecord> {
    public FormStatRecordBulkInserterFactory(JdbcOperationsFactory jdbcOperationsFactory) {
        super(jdbcOperationsFactory);
    }

    @Override
    public BulkInserter<FormStatRecord> createBulkInserter() {
        return new BulkInserterImpl<>(jdbcOperationsFactory, new FormStatRecordMapping());
    }
}
