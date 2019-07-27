package home.maks.db;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionOperations;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Objects;

public final class JdbcOperationsFactoryImpl implements JdbcOperationsFactory {
    private final TransactionOperations transactionOperations;
    private final JdbcOperations jdbcOperations;

    public JdbcOperationsFactoryImpl(DataSource dataSource) {
        Objects.requireNonNull(dataSource, "DataSource cannot be null");
        this.transactionOperations = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
        this.jdbcOperations = new JdbcTemplate(dataSource);
    }

    @Override
    public TransactionOperations getTransactionTemplate() {
        return transactionOperations;
    }

    @Override
    public JdbcOperations getJdbcOperarions() {
        return jdbcOperations;
    }
}
