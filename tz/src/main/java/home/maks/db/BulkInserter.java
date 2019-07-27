package home.maks.db;

import java.io.Closeable;

public interface BulkInserter<T> extends Closeable {
    @Override
    void close();

    void add(T entity);
}
