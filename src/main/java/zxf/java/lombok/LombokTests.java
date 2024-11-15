package zxf.java.lombok;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class LombokTests {


    public void copyFile(@NonNull String in, @NonNull String out) throws IOException {
        @Cleanup FileInputStream inStream = new FileInputStream(in);
        @Cleanup FileOutputStream outStream = new FileOutputStream(out);
        byte[] b = new byte[65536];
        while (true) {
            int r = inStream.read(b);
            if (r == -1) break;
            outStream.write(b, 0, r);
        }
    }

    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Bean {
        private String id;
        private String name;
        @ToString.Exclude
        private String password;
    }
}

