package home.maks.db.mapper;

import home.maks.db.model.Tables;
import home.maks.db.model.tables.records.FormStatRecord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class FormStatRecordSeqIdMapper implements RowMapper<FormStatRecord> {
    private final AtomicInteger seqId = new AtomicInteger(0);

    @Override
    public FormStatRecord mapRow(ResultSet resultSet, int i) throws SQLException {
        FormStatRecord formStatRecord = new FormStatRecord();
        formStatRecord.setFileId(resultSet.getInt(Tables.FORM_STAT.FILE_ID.getName()));
        formStatRecord.setSeqId(seqId.getAndIncrement());
        formStatRecord.setFormid(resultSet.getString(Tables.FORM_STAT.FORMID.getName()));
        formStatRecord.setCount(resultSet.getInt(Tables.FORM_STAT.COUNT.getName()));
        return formStatRecord;
    }
}
