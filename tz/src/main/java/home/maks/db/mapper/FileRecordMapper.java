package home.maks.db.mapper;


import home.maks.db.model.tables.File;
import home.maks.db.model.tables.records.FileRecord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class FileRecordMapper implements RowMapper<FileRecord> {
    @Override
    public FileRecord mapRow(ResultSet resultSet, int i) throws SQLException {
        FileRecord fileRecord = new FileRecord();
        fileRecord.setFileId(resultSet.getInt(File.FILE.FILE_ID.getName()));
        fileRecord.setFileName(resultSet.getString(File.FILE.FILE_NAME.getName()));
        fileRecord.setFileDate(resultSet.getTimestamp(File.FILE.FILE_DATE.getName()));
        return fileRecord;
    }
}
