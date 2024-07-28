package zxf.java.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;

public class CsvImport {
    public static void main(String[] args) throws IOException {
        try (FileReader fileReader = new FileReader("./output/output.csv")) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build()
                    .parse(fileReader);
            for (CSVRecord record : records) {
                String firstName = record.get("firstName");
                String lastName = record.get("lastName");
                String address = record.get("address");
                String age = record.get("age");
                System.out.printf("firstName=%s, lastName=%s, address=%s, age=%s\n", firstName, lastName, address, age);
            }
        }
    }
}
