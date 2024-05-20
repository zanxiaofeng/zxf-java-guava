package zxf.java.icu4j;

import org.apache.tika.parser.txt.CharsetDetector;
import org.apache.tika.parser.txt.CharsetMatch;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ICU4JTests {
    public static void main(String[] args) throws IOException {
        CharsetDetector detector = new CharsetDetector();

        byte[] binaryBytes = new byte[]{0x00, 0x00, 0x01};
        detector.setText(new ByteArrayInputStream(binaryBytes));
        CharsetMatch binaryCharsetMatch = detector.detect();
        System.out.println("Binary Charset Match: " + binaryCharsetMatch);

        byte[] invalidUtf8Bytes = {(byte) 0xF0, (byte) 0xC1, (byte) 0x8C, (byte) 0xBC, (byte) 0xD1};
        detector.setText(new ByteArrayInputStream(invalidUtf8Bytes));
        CharsetMatch invalidUtf8CharsetMatch = detector.detect();
        System.out.println("InvalidUtf8 Charset Match: " + invalidUtf8CharsetMatch);

        byte[] textBytes = "ABC".getBytes(StandardCharsets.UTF_16);
        detector.setText(new ByteArrayInputStream(textBytes));
        CharsetMatch textCharsetMatch = detector.detect();
        System.out.println("Text Charset Match: " + textCharsetMatch);
    }
}
