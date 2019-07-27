package home.maks.db;

import java.util.Objects;

public abstract class AbstractBulkInserterFactory<T> implements BulkInserterFactory<T> {
    protected final JdbcOperationsFactory jdbcOperationsFactory;

    public AbstractBulkInserterFactory(JdbcOperationsFactory jdbcOperationsFactory) {
        this.jdbcOperationsFactory = Objects.requireNonNull(jdbcOperationsFactory);
    }
}
