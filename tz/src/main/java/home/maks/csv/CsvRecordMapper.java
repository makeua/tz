package home.maks.csv;

import home.maks.db.model.tables.records.FileContentRecord;
import home.maks.util.Util;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class CsvRecordMapper implements Function<CSVRecord, FileContentRecord> {
    private final int fileId;
    private final AtomicInteger seqNum = new AtomicInteger(0);

    public CsvRecordMapper(int fileId) {
        this.fileId = fileId;
    }

    @Override
    public FileContentRecord apply(CSVRecord csvRecord) {
        FileContentRecord record = new FileContentRecord();
        record.setFileId(fileId);
        record.setSeqId(seqNum.getAndIncrement());
        record.setSsoid(StringUtils.trimToNull(csvRecord.get(CsvHeaders.SSOID)));
        record.setTs(Util.toTimestamp(Util.toLong(csvRecord.get(CsvHeaders.TS))));
        record.setGrp(StringUtils.trimToNull(csvRecord.get(CsvHeaders.GRP)));
        record.setType(StringUtils.trimToNull(csvRecord.get(CsvHeaders.TYPE)));
        record.setSubtype(StringUtils.trimToNull(csvRecord.get(CsvHeaders.SUBTYPE)));
        record.setUrl(StringUtils.trimToNull(csvRecord.get(CsvHeaders.URL)));
        record.setOrgid(StringUtils.trimToNull(csvRecord.get(CsvHeaders.ORGID)));
        record.setFormid(StringUtils.trimToNull(csvRecord.get(CsvHeaders.FORMID)));
        record.setLtpa(StringUtils.trimToNull(csvRecord.get(CsvHeaders.LTPA)));
        record.setSudirresponse(StringUtils.trimToNull(csvRecord.get(CsvHeaders.SUDIRRESPONSE)));
        record.setYmdh(StringUtils.trimToNull(csvRecord.get(CsvHeaders.YMDH)));
        return record;
    }
}
