package home.maks.db.loading;

import home.maks.db.JdbcOperationsFactory;
import home.maks.db.mapper.UserStatRecordMapper;
import home.maks.db.mapper.UserStatRecordSeqIdMapper;
import home.maks.db.model.Tables;
import home.maks.db.model.tables.records.UserStatRecord;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.sql.Timestamp;
import java.util.List;

public final class UserStatRecordLoaderImpl extends AbstractLoader implements UserStatRecordLoader {
    private static final int LIMIT = 100;

    public UserStatRecordLoaderImpl(JdbcOperationsFactory jdbcOperationsFactory, DSLContext dslContext) {
        super(jdbcOperationsFactory, dslContext);
    }

    @Override
    public List<UserStatRecord> load(int fileId) {
        return jdbcOperationsFactory.getJdbcOperarions()
                .query(dslContext.select(
                        Tables.USER_STAT.fields())
                                .from(Tables.USER_STAT)
                                .where(Tables.USER_STAT.FILE_ID.eq(fileId))
                                .orderBy(Tables.USER_STAT.SEQ_ID)
                                .limit(LIMIT)
                                .getSQL(),
                        new Object[]{fileId, LIMIT},
                        new UserStatRecordMapper());
    }

    @Override
    public List<UserStatRecord> gather(int fileId) {
        return jdbcOperationsFactory.getJdbcOperarions()
                .query(dslContext.select(
                        Tables.FILE_CONTENT.FILE_ID,
                        Tables.FILE_CONTENT.SSOID,
                        Tables.FILE_CONTENT.FORMID)
                                .from(Tables.FILE_CONTENT,
                                        dslContext.select(Tables.FILE_CONTENT.TS.max())
                                                .from(Tables.FILE_CONTENT)
                                                .where(Tables.FILE_CONTENT.FILE_ID.eq(fileId))
                                                .asTable("MAX_TS", "MAX_TS"))
                                .where(Tables.FILE_CONTENT.TS
                                        .greaterOrEqual(
                                                DSL.timestampAdd(
                                                        DSL.field(DSL.name("MAX_TS", "MAX_TS"), Timestamp.class),
                                                        -1,
                                                        DatePart.HOUR)))
                                .and(Tables.FILE_CONTENT.SSOID.isNotNull())
                                .and(Tables.FILE_CONTENT.FORMID.isNotNull())
                                .and(Tables.FILE_CONTENT.FILE_ID.eq(fileId))
                                .orderBy(Tables.FILE_CONTENT.TS.desc())
                                .getSQL(),
                        new Object[]{fileId, -1, fileId},
                        new UserStatRecordSeqIdMapper());
    }
}
