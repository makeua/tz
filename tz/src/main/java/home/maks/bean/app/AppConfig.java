package home.maks.bean.app;

import home.maks.config.Config;
import home.maks.db.JdbcOperationsFactory;
import home.maks.db.model.Indexes;
import home.maks.db.model.Keys;
import home.maks.db.model.Tables;
import home.maks.db.model.Tz;
import home.maks.db.model.tables.File;
import home.maks.db.model.tables.FileContent;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Index;
import org.jooq.SortField;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;

@Configuration
public class AppConfig implements InitializingBean {
    @Autowired
    private Config config;
    @Autowired
    private JdbcOperationsFactory jdbcOperationsFactory;
    @Autowired
    private DSLContext dslContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (config.isDbDrop()) {
            jdbcOperationsFactory.getTransactionTemplate()
                    .execute(status -> createSchema());
        }
    }

    private boolean createSchema() {
        JdbcOperations jdbcOperations = jdbcOperationsFactory.getJdbcOperarions();

        jdbcOperations.execute(dslContext.dropSchemaIfExists(Tz.TZ).cascade()
                .getSQL());

        jdbcOperations.execute(dslContext.createSchema(Tz.TZ).getSQL());
        jdbcOperations.execute(dslContext.createTable(Tables.FILE)
                .columns(
                        Tables.FILE.fields())
                .constraints(
                        DSL.constraint(Keys.PK_FILE_FILEID.getName())
                                .primaryKey(Keys.PK_FILE_FILEID.getFieldsArray()))
                .getSQL());

        jdbcOperations.execute(
                dslContext.createTable(Tables.FILE_CONTENT)
                        .columns(Tables.FILE_CONTENT.fields())
                        .getSQL());
        jdbcOperations.execute(dslContext.createIndex(Indexes.NX_FILECONTENT_FILEID_TS)
                .on(Indexes.NX_FILECONTENT_FILEID_TS.getTable(),
                        Indexes.NX_FILECONTENT_FILEID_TS.getFields()
                                .toArray(new SortField<?>[Indexes.NX_FILECONTENT_FILEID_TS.getFields().size()]))
                .getSQL());
        jdbcOperations.execute(dslContext.createIndex(Indexes.NX_FILECONTENT_FILEID_FORMID)
                .on(Indexes.NX_FILECONTENT_FILEID_FORMID.getTable(),
                        Indexes.NX_FILECONTENT_FILEID_FORMID.getFields()
                                .toArray(new SortField<?>[Indexes.NX_FILECONTENT_FILEID_FORMID.getFields().size()]))
                .getSQL());

        jdbcOperations.execute(
                dslContext.createTable(Tables.FORM_STAT)
                        .columns(Tables.FORM_STAT.fields())
                        .getSQL());
        jdbcOperations.execute(dslContext.createIndex(Indexes.NX_FORMSTAT_FILEID_SEQID)
                .on(Indexes.NX_FORMSTAT_FILEID_SEQID.getTable(),
                        Indexes.NX_FORMSTAT_FILEID_SEQID.getFields()
                                .toArray(new SortField<?>[Indexes.NX_FORMSTAT_FILEID_SEQID.getFields().size()]))
                .getSQL());

        jdbcOperations.execute(
                dslContext.createTable(Tables.USER_STAT)
                        .columns(Tables.USER_STAT.fields())
                        .getSQL());
        jdbcOperations.execute(dslContext.createIndex(Indexes.NX_USERSTAT_FILEID_SEQID)
                .on(Indexes.NX_USERSTAT_FILEID_SEQID.getTable(),
                        Indexes.NX_USERSTAT_FILEID_SEQID.getFields()
                                .toArray(new SortField<?>[Indexes.NX_USERSTAT_FILEID_SEQID.getFields().size()]))
                .getSQL());

        return true;
    }
}
