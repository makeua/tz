package home.maks.db.mapper;

import home.maks.db.model.Tables;
import home.maks.db.model.tables.records.UserStatRecord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public final class UserStatRecordSeqIdMapper implements RowMapper<UserStatRecord> {
    private final AtomicInteger seqId = new AtomicInteger(0);

    @Override
    public UserStatRecord mapRow(ResultSet resultSet, int i) throws SQLException {
        UserStatRecord userStatRecord = new UserStatRecord();
        userStatRecord.setFileId(resultSet.getInt(Tables.USER_STAT.FILE_ID.getName()));
        userStatRecord.setSeqId(seqId.getAndIncrement());
        userStatRecord.setSsoid(resultSet.getString(Tables.USER_STAT.SSOID.getName()));
        userStatRecord.setFormid(resultSet.getString(Tables.USER_STAT.FORMID.getName()));
        return userStatRecord;
    }
}
