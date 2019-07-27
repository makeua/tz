package home.maks.db;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.transaction.support.TransactionOperations;

public interface JdbcOperationsFactory {
    TransactionOperations getTransactionTemplate();

    JdbcOperations getJdbcOperarions();
}
