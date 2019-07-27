package home.maks.csv.importer;

import home.maks.db.BulkInserter;
import home.maks.db.JdbcOperationsFactory;
import home.maks.db.loading.FormStatRecordLoader;
import home.maks.db.loading.UserStatRecordLoader;
import home.maks.db.model.Tables;
import home.maks.db.model.tables.records.FormStatRecord;
import home.maks.db.model.tables.records.UserStatRecord;
import org.jooq.impl.DSL;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class CsvStatisticsGatherer {
    private final int fileId;
    private final FormStatRecordLoader formStatRecordLoader;
    private final UserStatRecordLoader userStatRecordLoader;
    private final BulkInserter<FormStatRecord> formStatRecordBulkInserter;
    private final BulkInserter<UserStatRecord> userStatRecordBulkInserter;

    public CsvStatisticsGatherer(int fileId,
                                 FormStatRecordLoader formStatRecordLoader,
                                 UserStatRecordLoader userStatRecordLoader,
                                 BulkInserter<FormStatRecord> formStatRecordBulkInserter,
                                 BulkInserter<UserStatRecord> userStatRecordBulkInserter) {
        this.formStatRecordLoader = Objects.requireNonNull(formStatRecordLoader);
        this.userStatRecordLoader = Objects.requireNonNull(userStatRecordLoader);
        this.formStatRecordBulkInserter = Objects.requireNonNull(formStatRecordBulkInserter);
        this.userStatRecordBulkInserter = Objects.requireNonNull(userStatRecordBulkInserter);
        this.fileId = fileId;
    }

    public void gatherStatistics() {
        gatherFormStatistic();
        gatherUserStatistic();
    }

    private void gatherFormStatistic() {
        formStatRecordLoader.gather(fileId)
                .stream()
                .forEach(formStatRecordBulkInserter::add);
    }

    private void gatherUserStatistic() {
        userStatRecordLoader.gather(fileId)
                .stream()
                .forEach(userStatRecordBulkInserter::add);
    }
}
