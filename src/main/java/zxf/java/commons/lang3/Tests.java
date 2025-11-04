package zxf.java.commons.lang3;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.IOException;

public class Tests {
    public static void main(String[] args) {
        System.out.println(RandomStringUtils.random(12, true, false));
        System.out.println(RandomStringUtils.random(12, true, true));

        System.out.println(ObjectUtils.allNotNull(12,23));

        System.out.println(StringUtils.normalizeSpace(""));

        System.out.println(BooleanUtils.toBoolean("On", "on", "off"));


        System.out.println(ExceptionUtils.getStackTrace(new RuntimeException(new IOException(new IllegalAccessException()))));
    }
}
