package zxf.java.guava;

import com.google.common.base.Strings;
import com.google.common.base.CaseFormat;

public class StringsTests {
    public static void main(String[] args) {
        System.out.println("Strings.isNullOrEmpty: " + Strings.isNullOrEmpty(""));
        System.out.println("Strings.emptyToNull: " + Strings.emptyToNull(""));
        System.out.println("Strings.nullToEmpty: " + Strings.nullToEmpty(null));
    }
}
