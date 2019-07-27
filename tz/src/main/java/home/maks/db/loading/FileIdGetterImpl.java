package home.maks.db.loading;

import home.maks.db.JdbcOperationsFactory;
import home.maks.db.loading.FileIdGetter;
import home.maks.db.model.tables.File;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.Objects;
import java.util.Optional;

public final class FileIdGetterImpl extends AbstractLoader implements FileIdGetter {
    public FileIdGetterImpl(JdbcOperationsFactory jdbcOperationsFactory, DSLContext dslContext) {
        super(jdbcOperationsFactory, dslContext);
    }

    @Override
    public Integer getMaxFieldId() {
        return Optional.ofNullable(
                jdbcOperationsFactory.getJdbcOperarions()
                        .queryForObject(
                                dslContext.select(DSL.max(File.FILE.FILE_ID))
                                        .from(File.FILE)
                                        .getSQL(),
                                Integer.class))
                .orElse(0);
    }
}
