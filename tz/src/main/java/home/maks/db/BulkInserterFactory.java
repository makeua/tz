package home.maks.db;

public interface BulkInserterFactory<T> {
    BulkInserter<T> createBulkInserter();
}
