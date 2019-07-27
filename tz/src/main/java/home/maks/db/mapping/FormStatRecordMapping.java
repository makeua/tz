package home.maks.db.mapping;

import home.maks.db.model.Tables;
import home.maks.db.model.Tz;
import home.maks.db.model.tables.records.FormStatRecord;

public final class FormStatRecordMapping extends AMapping<FormStatRecord> {
    public FormStatRecordMapping() {
        super(Tz.TZ.getName(), Tables.FORM_STAT.getName());
        mapInteger(Tables.FORM_STAT.FILE_ID.getName(), FormStatRecord::getFileId);
        mapInteger(Tables.FORM_STAT.SEQ_ID.getName(), FormStatRecord::getSeqId);
        mapText(Tables.FORM_STAT.FORMID.getName(), FormStatRecord::getFormid);
        mapInteger(Tables.FORM_STAT.COUNT.getName(), FormStatRecord::getCount);
    }
}
