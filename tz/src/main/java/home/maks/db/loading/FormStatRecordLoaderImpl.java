package home.maks.db.loading;

import home.maks.db.JdbcOperationsFactory;
import home.maks.db.mapper.FormStatRecordMapper;
import home.maks.db.mapper.FormStatRecordSeqIdMapper;
import home.maks.db.model.Tables;
import home.maks.db.model.tables.records.FormStatRecord;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.List;

public final class FormStatRecordLoaderImpl extends AbstractLoader implements FormStatRecordLoader {
    private static final int TOP_5 = 5;

    public FormStatRecordLoaderImpl(JdbcOperationsFactory jdbcOperationsFactory, DSLContext dslContext) {
        super(jdbcOperationsFactory, dslContext);
    }

    @Override
    public List<FormStatRecord> load(int fileId) {
        return jdbcOperationsFactory.getJdbcOperarions()
                .query(
                        dslContext.select(Tables.FORM_STAT.fields())
                                .from(Tables.FORM_STAT)
                                .where(Tables.FORM_STAT.FILE_ID.eq(fileId))
                                .orderBy(Tables.FORM_STAT.SEQ_ID)
                                .getSQL(),
                        new Object[]{fileId},
                        new FormStatRecordMapper());
    }

    @Override
    public List<FormStatRecord> gather(int fileId) {
        return jdbcOperationsFactory.getJdbcOperarions()
                .query(dslContext.select(
                        Tables.FILE_CONTENT.FILE_ID,
                        Tables.FILE_CONTENT.FORMID,
                        DSL.count().as(Tables.FORM_STAT.COUNT.getName()))
                                .from(Tables.FILE_CONTENT)
                                .where(
                                        Tables.FILE_CONTENT.FILE_ID.eq(fileId)
                                                .and(Tables.FILE_CONTENT.FORMID.isNotNull()))
                                .groupBy(Tables.FILE_CONTENT.FILE_ID, Tables.FILE_CONTENT.FORMID)
                                .orderBy(DSL.field(Tables.FORM_STAT.COUNT.getName()).desc())
                                .limit(TOP_5)
                                .getSQL(),
                        new Object[]{fileId, TOP_5},
                        new FormStatRecordSeqIdMapper());
    }
}
