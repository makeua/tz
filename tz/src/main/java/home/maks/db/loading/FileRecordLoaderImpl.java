package home.maks.db.loading;

import home.maks.db.JdbcOperationsFactory;
import home.maks.db.mapper.FileRecordMapper;
import home.maks.db.model.tables.File;
import home.maks.db.model.tables.records.FileRecord;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.List;
import java.util.Objects;

public final class FileRecordLoaderImpl extends AbstractLoader implements FileRecordLoader {
    public FileRecordLoaderImpl(JdbcOperationsFactory jdbcOperationsFactory, DSLContext dslContext) {
        super(jdbcOperationsFactory, dslContext);
    }

    @Override
    public List<FileRecord> getLastFiles(int amount) {
        return jdbcOperationsFactory.getJdbcOperarions()
                .query(dslContext.select(File.FILE.fields())
                                .from(File.FILE)
                                .orderBy(File.FILE.FILE_ID.desc())
                                .limit(amount)
                                .getSQL(), new Object[]{amount},
                        new FileRecordMapper());
    }
}
