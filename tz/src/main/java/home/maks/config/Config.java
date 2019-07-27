package home.maks.config;

public interface Config {
    String SERVER = "server";
    String DB = "db";

    int getServerPort();

    boolean isDbDrop();
    boolean isDbMemory();
    String getDbHost();
    int getDbPort();
    String getDbName();
    String getDbUser();
    String getDbPass();
    int getDbMinConnections();
    int getDbMaxConnections();
    int getDbIdleTimeout();
}
