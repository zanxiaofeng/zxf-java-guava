package zxf.java.icu4j;

import org.apache.tika.parser.txt.CharsetDetector;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

public class TestBytes {
    public static void main(String[] args) {
        byte[] binary1Bytes = new byte[]{(byte)0xF0, 0x02, 0x01};
        byte[] binary2Bytes = new byte[]{(byte)0x10, 0x02, 0x01};
        byte[] textBytes = "ABC".getBytes(StandardCharsets.UTF_8);
        System.out.println("Check binary1 bytes: " + checkUTF8(binary1Bytes));
        System.out.println("Check binary2 bytes: " + checkUTF8(binary2Bytes));
        System.out.println("Check text bytes: " + checkUTF8(textBytes));
    }

    private static Boolean checkUTF8(byte[] input) {
        CharsetDecoder detector = Charset.forName("UTF-8").newDecoder();
        try {
            detector.decode(ByteBuffer.wrap(input));
            return true;
        } catch (CharacterCodingException e) {
            e.printStackTrace();
            return false;
        }
    }
}

