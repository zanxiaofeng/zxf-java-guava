package zxf.java.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ExecutionException;

import static java.util.concurrent.TimeUnit.MINUTES;

public class CacheTests {
    public static void main(String[] args) throws ExecutionException {
        LoadingCache<Request, String> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(1, MINUTES)
                .build(
                        new com.google.common.cache.CacheLoader<Request, String>() {
                            @Override
                            public String load(Request key) {
                                System.out.println("Loading - " + key);
                                return key.toString();
                            }
                        }
                );
        System.out.println("cache.get: " + cache.get(new Request("abc", "123")));
        System.out.println("cache.get: " + cache.get(new Request("abc", "123")));
        System.out.println("cache.get: " + cache.get(new Request("ddd", "123")));
        System.out.println("cache.get: " + cache.get(new Request("ddd", "123")));
    }

    @Data
    @AllArgsConstructor
    public static class Request {
        private String path;
        private String filename;
    }
}
