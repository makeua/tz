package home.maks.config;

import org.apache.commons.configuration.XMLConfiguration;

import java.util.Objects;

public final class XmlConfig extends AbstractConfig {
    private final XMLConfiguration xmlConfiguration;

    public XmlConfig(XMLConfiguration xmlConfiguration) {
        this.xmlConfiguration = Objects.requireNonNull(xmlConfiguration);
    }

    @Override
    public int getServerPort() {
        return xmlConfiguration.getInt(keyFrom(SERVER, "port"));
    }

    @Override
    public boolean isDbDrop() {
        return xmlConfiguration.getBoolean(keyFrom(DB, "drop"));
    }

    @Override
    public boolean isDbMemory() {
        return xmlConfiguration.getBoolean(keyFrom(DB, "memory"));
    }

    @Override
    public String getDbHost() {
        return xmlConfiguration.getString(keyFrom(DB, "host"));
    }

    @Override
    public int getDbPort() {
        return xmlConfiguration.getInt(keyFrom(DB, "port"));
    }

    @Override
    public String getDbName() {
        return xmlConfiguration.getString(keyFrom(DB, "dbname"));
    }

    @Override
    public String getDbUser() {
        return xmlConfiguration.getString(keyFrom(DB, "user"));
    }

    @Override
    public String getDbPass() {
        return xmlConfiguration.getString(keyFrom(DB, "pass"));
    }

    @Override
    public int getDbMinConnections() {
        return xmlConfiguration.getInt(keyFrom(DB, "minConnections"));
    }

    @Override
    public int getDbMaxConnections() {
        return xmlConfiguration.getInt(keyFrom(DB, "maxConnections"));
    }

    @Override
    public int getDbIdleTimeout() {
        return xmlConfiguration.getInt(keyFrom(DB, "idleTimeout"));
    }
}
