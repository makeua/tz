package home.maks.config;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class AbstractConfig implements Config {
    protected final String keyFrom(String... parts) {
        return Arrays.stream(parts)
                .collect(Collectors.joining("."));
    }
}
