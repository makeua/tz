package home.maks.db.mapping;

import home.maks.db.model.Tz;
import home.maks.db.model.tables.File;
import home.maks.db.model.tables.records.FileRecord;
import home.maks.util.Util;

public class FileRecordMapping extends AMapping<FileRecord> {
    public FileRecordMapping() {
        super(Tz.TZ.getName(), File.FILE.getName());
        mapInteger(File.FILE.FILE_ID.getName(), FileRecord::getFileId);
        mapText(File.FILE.FILE_NAME.getName(), FileRecord::getFileName);
        mapTimeStamp(File.FILE.FILE_DATE.getName(), fr -> Util.toLocalDateTime(fr.getFileDate()));
    }
}
