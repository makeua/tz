package home.maks.db.mapping;

import home.maks.db.*;
import home.maks.db.model.tables.records.UserStatRecord;

public final class UserStatRecordBulkInserterFactory extends AbstractBulkInserterFactory<UserStatRecord> {
    public UserStatRecordBulkInserterFactory(JdbcOperationsFactory jdbcOperationsFactory) {
        super(jdbcOperationsFactory);
    }

    @Override
    public BulkInserter<UserStatRecord> createBulkInserter() {
        return new BulkInserterImpl<>(jdbcOperationsFactory, new UserStatRecordMapping());
    }
}
