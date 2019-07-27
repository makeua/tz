package home.maks.util;

import org.springframework.cglib.core.Local;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class Util {
    private Util() {

    }

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return Optional.ofNullable(timestamp)
                .map(Timestamp::toLocalDateTime)
                .orElse(null);
    }

    public static Timestamp toTimeStamp(LocalDateTime localDateTime) {
        return Optional.ofNullable(localDateTime)
                .map(Timestamp::valueOf)
                .orElse(null);
    }

    public static Timestamp toTimestamp(Long ts) {
        return Optional.ofNullable(ts)
                .map(Timestamp::new)
                .orElse(null);
    }

    public static Long toLong(String s) {
        try {
            return Long.parseLong(s);
        } catch (Exception e) {
            return null;
        }
    }
}
