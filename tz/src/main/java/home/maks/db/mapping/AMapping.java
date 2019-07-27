package home.maks.db.mapping;

import de.bytefish.pgbulkinsert.mapping.AbstractMapping;
import org.jooq.Record;

public abstract class AMapping<T extends Record> extends AbstractMapping<T> {
    private final String fullTableName;

    public AMapping(String schemaName, String tableName) {
        super(schemaName, tableName);
        this.fullTableName = String.format("%s.%s", schemaName, tableName);
    }

    public final String getFullTableName() {
        return fullTableName;
    }
}
