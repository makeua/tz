package home.maks.db.loading;

import home.maks.db.model.tables.records.FormStatRecord;

import java.util.List;

public interface FormStatRecordLoader {
    List<FormStatRecord> load(int fileId);

    List<FormStatRecord> gather(int fileId);
}
