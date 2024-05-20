package zxf.java.tika;

import org.apache.tika.detect.EncodingDetector;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.txt.UniversalEncodingDetector;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TikaTests {
    public static void main(String[] args) throws IOException {
        EncodingDetector encodingDetector = new UniversalEncodingDetector();

        byte[] binaryBytes = new byte[]{0x00, 0x00, 0x01};
        Charset binaryCharset = encodingDetector.detect(new ByteArrayInputStream(binaryBytes), new Metadata());
        System.out.println("Binary Charset: " + binaryCharset);

        byte[] invalidUtf8Bytes = {(byte) 0xF0, (byte) 0xC1, (byte) 0x8C, (byte) 0xBC, (byte) 0xD1};
        Charset invalidUtfCharset = encodingDetector.detect(new ByteArrayInputStream(invalidUtf8Bytes), new Metadata());
        System.out.println("InvalidUtf Charset: " + invalidUtfCharset);

        byte[] textBytes = "ABC".getBytes(StandardCharsets.UTF_8);
        Charset textCharset = encodingDetector.detect(new ByteArrayInputStream(textBytes), new Metadata());
        System.out.println("Text Charset: " + textCharset);
    }
}
