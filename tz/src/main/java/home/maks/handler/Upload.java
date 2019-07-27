package home.maks.handler;

import home.maks.csv.CsvParserFactory;
import home.maks.csv.importer.CsvDbImporter;
import home.maks.csv.importer.CsvStatisticsGatherer;
import home.maks.db.loading.FileIdGetter;
import home.maks.db.BulkInserter;
import home.maks.db.BulkInserterFactory;
import home.maks.db.loading.FormStatRecordLoader;
import home.maks.db.loading.UserStatRecordLoader;
import home.maks.db.model.tables.records.FileContentRecord;
import home.maks.db.model.tables.records.FileRecord;
import home.maks.db.model.tables.records.FormStatRecord;
import home.maks.db.model.tables.records.UserStatRecord;
import org.apache.commons.csv.CSVParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class Upload {
    private static final Logger LOG = LoggerFactory.getLogger(Upload.class);

    @Autowired
    private CsvParserFactory csvParserFactory;
    @Autowired
    private FileIdGetter fileIdGetter;
    @Autowired
    private BulkInserterFactory<FileRecord> fileRecordBulkInserterFactory;
    @Autowired
    private BulkInserterFactory<FileContentRecord> fileContentRecordBulkInserterFactory;
    @Autowired
    private FormStatRecordLoader formStatLoader;
    @Autowired
    private UserStatRecordLoader userStatLoader;
    @Autowired
    private BulkInserterFactory<FormStatRecord> formStatRecordBulkInserterFactory;
    @Autowired
    private BulkInserterFactory<UserStatRecord> userStatRecordBulkInserterFactory;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFile(
            HttpServletRequest request,
            @RequestParam("fileInput") MultipartFile fileInput) throws IOException {
        String fileName = fileInput.getOriginalFilename();
        int fileId = fileIdGetter.getMaxFieldId() + 1;

        try (CSVParser csvParser = csvParserFactory.createCsvParser(fileInput.getInputStream());
             BulkInserter<FileRecord> fileRecordBulkInserter = fileRecordBulkInserterFactory.createBulkInserter();
             BulkInserter<FileContentRecord> fileContentRecordBulkInserter = fileContentRecordBulkInserterFactory.createBulkInserter();
             BulkInserter<FormStatRecord> formStatRecordBulkInserter = formStatRecordBulkInserterFactory.createBulkInserter();
             BulkInserter<UserStatRecord> userStatRecordBulkInserter = userStatRecordBulkInserterFactory.createBulkInserter()) {

            CsvDbImporter importer = new CsvDbImporter(fileId,
                    fileName,
                    csvParser,
                    fileRecordBulkInserter,
                    fileContentRecordBulkInserter);
            importer.importFile();
        }

        try (BulkInserter<FormStatRecord> formStatRecordBulkInserter = formStatRecordBulkInserterFactory.createBulkInserter();
             BulkInserter<UserStatRecord> userStatRecordBulkInserter = userStatRecordBulkInserterFactory.createBulkInserter()) {
            CsvStatisticsGatherer gatherer = new CsvStatisticsGatherer(fileId,
                    formStatLoader,
                    userStatLoader,
                    formStatRecordBulkInserter,
                    userStatRecordBulkInserter);
            gatherer.gatherStatistics();
        }
        return "redirect:/index";
    }


}
