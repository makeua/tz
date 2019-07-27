package home.maks.db.mapper;

import home.maks.db.model.Tables;
import home.maks.db.model.tables.records.FormStatRecord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class FormStatRecordMapper implements RowMapper<FormStatRecord> {
    @Override
    public FormStatRecord mapRow(ResultSet resultSet, int i) throws SQLException {
        FormStatRecord formStatRecord = new FormStatRecord();
        formStatRecord.setFileId(resultSet.getInt(Tables.FORM_STAT.FILE_ID.getName()));
        formStatRecord.setSeqId(resultSet.getInt(Tables.FORM_STAT.SEQ_ID.getName()));
        formStatRecord.setFormid(resultSet.getString(Tables.FORM_STAT.FORMID.getName()));
        formStatRecord.setCount(resultSet.getInt(Tables.FORM_STAT.COUNT.getName()));
        return formStatRecord;
    }
}
