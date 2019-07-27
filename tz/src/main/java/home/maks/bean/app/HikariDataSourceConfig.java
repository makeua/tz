package home.maks.bean.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import home.maks.config.Config;
import home.maks.db.datasource.CloseableDataSource;
import home.maks.db.datasource.CloseableDataSourceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HikariDataSourceConfig {
    @Bean(destroyMethod = "close")
    public CloseableDataSource hikariDataSource(CloseableDataSource dataSource, Config config) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDataSource(dataSource);
        hikariConfig.setIdleTimeout(config.getDbIdleTimeout());
        hikariConfig.setMinimumIdle(config.getDbMinConnections());
        hikariConfig.setMaximumPoolSize(config.getDbMaxConnections());
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        return new CloseableDataSourceImpl<>(hikariDataSource, h -> h.close());
    }
}
