package home.maks.db.loading;

import home.maks.db.JdbcOperationsFactory;
import org.jooq.DSLContext;

import java.util.Objects;

public abstract class AbstractLoader {
    protected final JdbcOperationsFactory jdbcOperationsFactory;
    protected final DSLContext dslContext;

    public AbstractLoader(JdbcOperationsFactory jdbcOperationsFactory, DSLContext dslContext) {
        this.jdbcOperationsFactory = Objects.requireNonNull(jdbcOperationsFactory);
        this.dslContext = Objects.requireNonNull(dslContext);
    }
}
