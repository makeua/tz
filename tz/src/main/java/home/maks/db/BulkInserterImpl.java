package home.maks.db;

import de.bytefish.pgbulkinsert.PgBulkInsert;
import de.bytefish.pgbulkinsert.mapping.AbstractMapping;
import de.bytefish.pgbulkinsert.model.ColumnDefinition;
import de.bytefish.pgbulkinsert.util.OutParameter;
import de.bytefish.pgbulkinsert.util.PostgreSqlUtils;
import home.maks.db.mapping.AMapping;
import org.jooq.Record;
import org.postgresql.PGConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BulkInserterImpl<T extends Record> implements BulkInserter<T> {
    private static final int DEFAULT_FLUSH_AT = 50_000;

    private final JdbcOperationsFactory jdbcOperationsFactory;
    private final AMapping<T> mapping;
    private final int flushAt;

    private final List<T> entities = new ArrayList<>();

    public BulkInserterImpl(JdbcOperationsFactory jdbcOperationsFactory, AMapping<T> mapping) {
        this(jdbcOperationsFactory, mapping, DEFAULT_FLUSH_AT);
    }

    public BulkInserterImpl(JdbcOperationsFactory jdbcOperationsFactory, AMapping<T> mapping, int flushAt) {
        this.jdbcOperationsFactory = Objects.requireNonNull(jdbcOperationsFactory);
        this.mapping = Objects.requireNonNull(mapping);
        this.flushAt = flushAt;
    }

    @Override
    public void close() {
        flush();
    }

    public void add(T entity) {
        Objects.requireNonNull(entity);
        this.entities.add(entity);

        if (this.entities.size() >= flushAt) {
            flush();
        }
    }

    public void flush() {
        if (this.entities.size() > 0) {
            jdbcOperationsFactory.getTransactionTemplate()
                    .execute(status -> jdbcOperationsFactory.getJdbcOperarions()
                            .execute((Connection connection) -> trySave(connection)));
            this.entities.clear();
        }
    }

    private int trySave(Connection connection) throws SQLException {
        OutParameter<PGConnection> pgConnectionOutParameter = new OutParameter<>();
        PostgreSqlUtils.tryGetPGConnection(connection, pgConnectionOutParameter);

        if (pgConnectionOutParameter.get() != null) {
            new PgBulkInsert<>(mapping).saveAll(pgConnectionOutParameter.get(), entities);
        } else {
            saveViaPreparedStatement(connection);
        }
        return entities.size();
    }

    private void saveViaPreparedStatement(Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(createSql(mapping))) {
            List<ColumnDefinition<T>> columns = mapping.getColumns();
            for (Record record : entities) {
                for (int i = 0; i < columns.size(); i++) {
                    ps.setObject(i + 1, record.get(i));
                }
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private String createSql(AMapping<T> mapping) {
        StringBuilder sb = new StringBuilder("INSERT INTO ");
        sb.append(mapping.getFullTableName());
        sb.append("(");
        sb.append(mapping.getColumns()
                .stream()
                .map(ColumnDefinition::getColumnName)
                .collect(Collectors.joining(", ")));
        sb.append(") VALUES (");
        sb.append(mapping.getColumns()
                .stream()
                .map(cd -> "?")
                .collect(Collectors.joining(", ")));
        sb.append(")");
        return sb.toString();
    }
}
