package home.maks.db.loading;

import home.maks.db.model.tables.records.UserStatRecord;

import java.util.List;

public interface UserStatRecordLoader {
    List<UserStatRecord> load(int fileId);

    List<UserStatRecord> gather(int fileId);
}
