package zxf.java.jdk8;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestZonedDateTime {
    private static ZoneId sydneyZoneId = ZoneId.of("Australia/Sydney");
    private static ZoneId shanghaiZoneId = ZoneId.of("Asia/Shanghai");
    private static DateTimeFormatter sydneyFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(sydneyZoneId);
    private static DateTimeFormatter shanghaiFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(shanghaiZoneId);

    public static void main(String[] args) {
        testLocalDateTime();
        testZonedDateTime();
        testLocalDateTimeToZonedDateTime();
        testZonedDateTimeToLocalDateTime();
    }

    private static void testZonedDateTime() {
        ZonedDateTime now = ZonedDateTime.now();

        String sydneyNow = sydneyFormatter.format(now);
        System.out.println("Sydney Formatter for ZonedDateTime: " + sydneyNow + ", " + ZonedDateTime.parse(sydneyNow, sydneyFormatter));

        String shanghaiNow = shanghaiFormatter.format(now);
        System.out.println("Shanghai Formatter for ZonedDateTime: " + shanghaiNow + ", " + ZonedDateTime.parse(shanghaiNow, shanghaiFormatter));
    }

    private static void testLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();

        //Formatter with zoneId do not take effective to LocalDateTime
        String sydneyNow = sydneyFormatter.format(now);
        System.out.println("Sydney Formatter for LocalDateTime: " + sydneyNow + ", " + LocalDateTime.parse(sydneyNow, sydneyFormatter));

        //Formatter with zoneId do not take effective to LocalDateTime
        String shanghaiNow = shanghaiFormatter.format(now);
        System.out.println("Shanghai Formatter for LocalDateTime: " + shanghaiNow + ", " + LocalDateTime.parse(shanghaiNow, shanghaiFormatter));
    }

    private static void testLocalDateTimeToZonedDateTime() {
        ////now(ZoneId targetZoneId) = LocalDataTime + Default ZoneId => LocalDataTime + targetZoneId
        System.out.println("LocalDateTime to ZonedDateTime by LocalDateTime.now(ZoneId)" + LocalDateTime.now(sydneyZoneId));
        //atZone(ZoneId targetZoneId) = LocalDataTime + targetZoneId
        System.out.println("LocalDateTime to ZonedDateTime by LocalDateTime.atZone(ZoneId)" + LocalDateTime.now().atZone(sydneyZoneId));
    }

    private static void testZonedDateTimeToLocalDateTime() {
        System.out.println("ZonedDateTime.now().toLocalDateTime(): " + ZonedDateTime.now().toLocalDateTime());
        System.out.println("ZonedDateTime.now(sydneyZoneId).toLocalDateTime(): " + ZonedDateTime.now(sydneyZoneId).toLocalDateTime());
        System.out.println("ZonedDateTime.now().withZoneSameLocal(sydneyZoneId).toLocalDateTime(): " + ZonedDateTime.now().withZoneSameLocal(sydneyZoneId).toLocalDateTime());
        System.out.println("ZonedDateTime.now().withZoneSameInstant(sydneyZoneId).toLocalDateTime(): " + ZonedDateTime.now().withZoneSameInstant(sydneyZoneId).toLocalDateTime());
    }
}
