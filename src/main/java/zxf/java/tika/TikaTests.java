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
        byte[] binaryBytes = new byte[]{0x00, 0x00, 0x00};
        byte[] textBytes = "ABC".getBytes(StandardCharsets.UTF_8);
        EncodingDetector encodingDetector = new UniversalEncodingDetector();
        Charset binaryCharset = encodingDetector.detect(new ByteArrayInputStream(binaryBytes), new Metadata());
        System.out.println("Binary Charset: " + binaryCharset);
        Charset textCharset = encodingDetector.detect(new ByteArrayInputStream(textBytes), new Metadata());
        System.out.println("Text Charset: " + textCharset);
    }
}
