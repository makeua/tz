package home.maks.db.mapping;

import home.maks.db.model.Tables;
import home.maks.db.model.Tz;
import home.maks.db.model.tables.records.UserStatRecord;

public final class UserStatRecordMapping extends AMapping<UserStatRecord> {
    public UserStatRecordMapping() {
        super(Tz.TZ.getName(), Tables.USER_STAT.getName());
        mapInteger(Tables.USER_STAT.FILE_ID.getName(), UserStatRecord::getFileId);
        mapInteger(Tables.USER_STAT.SEQ_ID.getName(), UserStatRecord::getSeqId);
        mapText(Tables.USER_STAT.SSOID.getName(), UserStatRecord::getSsoid);
        mapText(Tables.USER_STAT.FORMID.getName(), UserStatRecord::getFormid);
    }
}
