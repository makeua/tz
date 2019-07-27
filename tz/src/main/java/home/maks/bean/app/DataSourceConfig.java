package home.maks.bean.app;

import home.maks.config.Config;
import home.maks.db.datasource.CloseableDataSource;
import home.maks.db.datasource.CloseableDataSourceImpl;
import home.maks.exception.TzException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;
import org.jooq.SQL;
import org.jooq.util.h2.H2DSL;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.ClientInfoStatus;
import java.sql.SQLException;
import java.util.*;

@Configuration
public class DataSourceConfig {
    @Autowired
    private Config config;

    @Bean(destroyMethod = "close")
    public CloseableDataSource dataSource(Config config, ServerWrapper h2ServerWrapper) {
        if (!config.isDbMemory()) {
            PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
            pgSimpleDataSource.setServerName(config.getDbHost());
            pgSimpleDataSource.setPortNumber(config.getDbPort());
            pgSimpleDataSource.setDatabaseName(config.getDbName());
            pgSimpleDataSource.setUser(config.getDbUser());
            pgSimpleDataSource.setPassword(config.getDbPass());
            return new CloseableDataSourceImpl<>(pgSimpleDataSource);
        }
        JdbcDataSource memoryDataSource = new JdbcDataSource();
        memoryDataSource.setURL("jdbc:h2:tcp://localhost:9092/mem:" + config.getDbName());
        memoryDataSource.setUser(config.getDbUser());
        memoryDataSource.setPassword(config.getDbPass());
        return new CloseableDataSourceImpl<>(memoryDataSource);
    }

    private Server getH2WebServer() throws SQLException {
        if (config.isDbMemory()) {
            return Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
        }
        return null;
    }

    private Server getH2TcpServer() throws SQLException {
        if (config.isDbMemory()) {
            return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092", "-ifNotExists");
        }
        return null;
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public ServerWrapper h2ServerWrapper() throws SQLException {
        return new ServerWrapper(getH2WebServer(), getH2TcpServer());
    }

    private static final class ServerWrapper {
        private final Server h2WebServer, h2TcpServer;

        public ServerWrapper(Server h2WebServer, Server h2TcpServer) {
            this.h2WebServer = h2WebServer;
            this.h2TcpServer = h2TcpServer;
        }

        public void start() {
            Optional.ofNullable(h2WebServer).ifPresent(this::start);
            Optional.ofNullable(h2TcpServer).ifPresent(this::start);
        }

        public void stop() {
            Optional.ofNullable(h2WebServer).ifPresent(this::stop);
            Optional.ofNullable(h2TcpServer).ifPresent(this::stop);
        }

        private void start(Server server) {
            try {
                server.start();
            } catch (SQLException e) {
                throw new TzException(e);
            }
        }

        private void stop(Server server) {
            server.stop();
        }
    }
}
