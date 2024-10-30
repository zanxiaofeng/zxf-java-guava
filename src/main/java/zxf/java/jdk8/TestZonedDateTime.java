package zxf.java.jdk8;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestZonedDateTime {
    public static void main(String[] args) {
        DateTimeFormatter sydneyFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.of("Australia/Sydney"));
        DateTimeFormatter shanghaiFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.of("Asia/Shanghai"));

        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        String sydneyNow = sydneyFormatter.format(zonedDateTime);
        System.out.println("Sydney. " + sydneyNow + ", " + ZonedDateTime.parse(sydneyNow, sydneyFormatter));

        String shanghaiNow = shanghaiFormatter.format(zonedDateTime);
        System.out.println("Shanghai. " + shanghaiNow + ", " + ZonedDateTime.parse(shanghaiNow, shanghaiFormatter));
    }
}
