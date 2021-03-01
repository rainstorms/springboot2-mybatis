package mythware.utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface DateTimes {

    static LocalDateTime parseyyyyMMddHHmmssString(String timeStr) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(timeStr, df);
    }

    static String parseDateTime2yyyyMMddHHmmss(LocalDateTime dateTime) {
        if (null == dateTime) return null;
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    static String dateTime2String(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    static long currentTimeSeconds() {
        long millis = System.currentTimeMillis();
        return millis / 1000;
    }
}
