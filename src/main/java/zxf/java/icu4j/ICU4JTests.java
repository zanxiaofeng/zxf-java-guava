package zxf.java.icu4j;

import org.apache.tika.parser.txt.CharsetDetector;
import org.apache.tika.parser.txt.CharsetMatch;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ICU4JTests {
    public static void main(String[] args) throws IOException {
        byte[] binaryBytes = new byte[]{0x00, 0x66, 0x00};
        byte[] textBytes = "ABC".getBytes(StandardCharsets.UTF_8);
        CharsetDetector detector = new CharsetDetector();
        detector.setText(new ByteArrayInputStream(binaryBytes));
        CharsetMatch binaryCharsetMatch = detector.detect();
        System.out.println("Binary Charset Match: " + binaryCharsetMatch);
        detector.setText(new ByteArrayInputStream(textBytes));
        CharsetMatch textCharsetMatch = detector.detect();
        System.out.println("Text Charset Match: " + textCharsetMatch);
    }
}
