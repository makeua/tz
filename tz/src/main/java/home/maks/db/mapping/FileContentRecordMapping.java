package home.maks.db.mapping;

import home.maks.db.model.Tz;
import home.maks.db.model.tables.FileContent;
import home.maks.db.model.tables.records.FileContentRecord;
import home.maks.util.Util;

public class FileContentRecordMapping extends AMapping<FileContentRecord> {
    public FileContentRecordMapping() {
        super(Tz.TZ.getName(), FileContent.FILE_CONTENT.getName());
        mapInteger(FileContent.FILE_CONTENT.FILE_ID.getName(), FileContentRecord::getFileId);
        mapInteger(FileContent.FILE_CONTENT.SEQ_ID.getName(), FileContentRecord::getSeqId);
        mapText(FileContent.FILE_CONTENT.SSOID.getName(), FileContentRecord::getSsoid);
        mapTimeStamp(FileContent.FILE_CONTENT.TS.getName(), fcr -> Util.toLocalDateTime(fcr.getTs()));
        mapText(FileContent.FILE_CONTENT.GRP.getName(), FileContentRecord::getGrp);
        mapText(FileContent.FILE_CONTENT.TYPE.getName(), FileContentRecord::getType);
        mapText(FileContent.FILE_CONTENT.SUBTYPE.getName(), FileContentRecord::getSubtype);
        mapText(FileContent.FILE_CONTENT.URL.getName(), FileContentRecord::getUrl);
        mapText(FileContent.FILE_CONTENT.ORGID.getName(), FileContentRecord::getOrgid);
        mapText(FileContent.FILE_CONTENT.FORMID.getName(), FileContentRecord::getFormid);
        mapText(FileContent.FILE_CONTENT.LTPA.getName(), FileContentRecord::getLtpa);
        mapText(FileContent.FILE_CONTENT.SUDIRRESPONSE.getName(), FileContentRecord::getSudirresponse);
        mapText(FileContent.FILE_CONTENT.YMDH.getName(), FileContentRecord::getYmdh);
    }
}
