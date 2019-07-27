package home.maks.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.io.input.BOMInputStream;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CsvParserFactory {
    private static final CSVFormat FORMAT = CSVFormat.DEFAULT
            .withDelimiter(';')
            .withQuote('"')
            .withFirstRecordAsHeader();

    public CSVParser createCsvParser(InputStream is) throws IOException {
        Reader r = new InputStreamReader(new BOMInputStream(is), StandardCharsets.UTF_8);
        return CSVParser.parse(r, FORMAT);
    }
}
