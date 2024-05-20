package zxf.java.icu4j;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

public class TestBytes {
    public static void main(String[] args) {
        byte[] binaryBytes = new byte[]{0x00, 0x00, 0x01};
        byte[] invalidUtf8Bytes = {(byte) 0xF0, (byte) 0xC1, (byte) 0x8C, (byte) 0xBC, (byte) 0xD1};
        byte[] textBytes = "ABC".getBytes(StandardCharsets.UTF_8);
        System.out.println("Check binary bytes: " + checkUTF8(binaryBytes));
        System.out.println("Check invalidUtf8 bytes: " + checkUTF8(invalidUtf8Bytes));
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

