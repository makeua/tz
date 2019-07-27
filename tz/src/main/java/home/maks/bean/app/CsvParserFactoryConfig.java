package home.maks.bean.app;

import home.maks.csv.CsvParserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CsvParserFactoryConfig {
    @Bean
    public CsvParserFactory csvParserFactory() {
        return new CsvParserFactory();
    }
}
