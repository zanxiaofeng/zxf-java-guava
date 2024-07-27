package zxf.java.csv;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CsvBean {
    private String firstName;
    private String lastName;
    private String address;
    private Integer age;

    public static String[] csvHeader() {
        return new String[]{"firstName", "lastName", "address", "age"};
    }

    public static List<String[]> csvContents(List<CsvBean> beans) {
        return beans.stream().map(CsvBean::toCsvRecord).collect(Collectors.toList());
    }

    private String[] toCsvRecord() {
        return new String[]{firstName, lastName, address, age == null ? "" : String.valueOf(age)};
    }
}
