package home.maks.csv.importer;

import home.maks.csv.CsvRecordMapper;
import home.maks.db.BulkInserter;
import home.maks.db.model.tables.records.FileContentRecord;
import home.maks.db.model.tables.records.FileRecord;
import home.maks.util.Util;
import org.apache.commons.csv.CSVParser;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.StreamSupport;

public class CsvDbImporter {
    private final int fileId;
    private final String fileName;
    private final CSVParser csvParser;
    private final BulkInserter<FileRecord> fileRecordBulkInserter;
    private final BulkInserter<FileContentRecord> fileContentRecordBulkInserter;

    public CsvDbImporter(int fileId,
                         String fileName,
                         CSVParser csvParser,
                         BulkInserter<FileRecord> fileRecordBulkInserter,
                         BulkInserter<FileContentRecord> fileContentRecordBulkInserter) {
        this.fileId = fileId;
        this.fileName = Objects.requireNonNull(fileName);
        this.csvParser = Objects.requireNonNull(csvParser);
        this.fileRecordBulkInserter = Objects.requireNonNull(fileRecordBulkInserter);
        this.fileContentRecordBulkInserter = Objects.requireNonNull(fileContentRecordBulkInserter);
    }

    public void importFile() {
        saveFile(fileId, fileName);
        saveFileContent(fileId, csvParser);
    }

    private void saveFile(int fileId, String fileName) {
        FileRecord fileRecord = new FileRecord();
        fileRecord.setFileId(fileId);
        fileRecord.setFileName(fileName);
        fileRecord.setFileDate(Util.toTimeStamp(LocalDateTime.now()));
        fileRecordBulkInserter.add(fileRecord);
    }

    private void saveFileContent(int fileId, CSVParser csvParser) {
        StreamSupport.stream(csvParser.spliterator(), false)
                .map(new CsvRecordMapper(fileId))
                .forEach(fileContentRecordBulkInserter::add);
    }
}
