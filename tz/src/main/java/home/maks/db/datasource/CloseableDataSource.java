package home.maks.db.datasource;

import javax.sql.DataSource;
import java.io.Closeable;

public interface CloseableDataSource extends DataSource, Closeable {
    @Override
    void close();
}
