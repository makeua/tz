package home.maks.bean.app;

import home.maks.config.Config;
import home.maks.config.ConfigLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigConfig {
    @Bean
    public Config config(ConfigLoader configLoader) {
        return configLoader.load();
    }
}
