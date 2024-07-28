package zxf.java.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvExport {
    public static void main(String[] args) throws IOException {
        List<CsvBean> beans = Arrays.asList(
                new CsvBean("John", "Doe", "1234, Elm st", 25),
                new CsvBean("Jane", "Smith", "1234 \" Oka st", 30),
                new CsvBean("Bog", "Brown", "1234 \nPine st", 35),
                new CsvBean("Bod", null, "1234 \r\n ABC st", null));
        exportCsv(CsvBean.csvHeader(), CsvBean.csvContents(beans));
    }

    private static void exportCsv(String[] header, List<String[]> contents) throws IOException {
        CSVFormat myFormat = CSVFormat.DEFAULT.builder().setHeader(header).build();
        try (FileWriter fileWriter = new FileWriter("./output/output.csv");
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, myFormat)) {
            for (String[] content : contents) {
                List<String> nornalizeContent = Arrays.stream(content)
                        .map(StringUtils::normalizeSpace)
                        .collect(Collectors.toList());
                csvPrinter.printRecord(nornalizeContent);
            }
        }
    }
}
