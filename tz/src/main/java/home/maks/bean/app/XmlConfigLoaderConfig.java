package home.maks.bean.app;

import home.maks.config.ConfigLoader;
import home.maks.config.XmlConfigLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class XmlConfigLoaderConfig {
    @Bean
    public ConfigLoader configLoader(@Value("${config}") String configPath) {
        return new XmlConfigLoader(new File(configPath));
    }
}
