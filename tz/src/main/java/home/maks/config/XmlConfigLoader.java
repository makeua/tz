package home.maks.config;

import home.maks.exception.TzException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.io.input.BOMInputStream;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public final class XmlConfigLoader implements ConfigLoader {
    private final File configFile;

    public XmlConfigLoader(File configFile) {
        this.configFile = Objects.requireNonNull(configFile);
    }

    @Override
    public Config load() {
        try (FileInputStream fis = new FileInputStream(configFile);
             Reader r = new InputStreamReader(new BOMInputStream(fis), StandardCharsets.UTF_8)) {
            XMLConfiguration xmlConfiguration = new XMLConfiguration();
            xmlConfiguration.load(r);
            return new XmlConfig(xmlConfiguration);
        } catch (IOException | ConfigurationException e) {
            throw new TzException(e);
        }
    }
}
