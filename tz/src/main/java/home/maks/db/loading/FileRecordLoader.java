package home.maks.db.loading;

import home.maks.db.model.tables.records.FileRecord;

import java.util.List;

public interface FileRecordLoader {
    List<FileRecord> getLastFiles(int amount);
}
