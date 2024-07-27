package zxf.java.csv;

import org.apache.commons.csv.CSVFormat;

import java.util.Arrays;

public class CSVFormatTests {
    private static final String[] content = new String[]{"Abc,d", "Abc \"d", "Abc \n d", "Abc\r\n d", "Abc\t\t d", null};

    public static void main(String[] args) {
        testCSVFormat("DEFAULT", CSVFormat.DEFAULT);
        testCSVFormat("EXCEL", CSVFormat.EXCEL);
        testCSVFormat("INFORMIX_UNLOAD", CSVFormat.INFORMIX_UNLOAD);
        testCSVFormat("INFORMIX_UNLOAD_CSV", CSVFormat.INFORMIX_UNLOAD_CSV);
        testCSVFormat("MONGODB_CSV", CSVFormat.MONGODB_CSV);
        testCSVFormat("MONGODB_TSV", CSVFormat.MONGODB_TSV);
        testCSVFormat("MYSQL", CSVFormat.MYSQL);
        testCSVFormat("ORACLE", CSVFormat.ORACLE);
        testCSVFormat("POSTGRESQL_CSV", CSVFormat.POSTGRESQL_CSV);
        testCSVFormat("POSTGRESQL_TEXT", CSVFormat.POSTGRESQL_TEXT);
        testCSVFormat("RFC4180", CSVFormat.RFC4180);
    }

    private static void testCSVFormat(String name, CSVFormat csvFormat) {
        System.out.println(name + "::" + csvFormat.format(content));
    }
}
