package zxf.java.caffeine;

import com.github.benmanes.caffeine.cache.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CaffeineTests {
    public static void main(String[] args) {
        testManuallyPut();
        testSyncLoading();
        testAsyncLoading();
        testSizeBasedEviction();
        testTimeBasedEviction();
        testReferenceBasedEviction();
        testRefreshing();
        testStatistics();
    }

    private static void testManuallyPut() {
        Cache<String, DataObject> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();

        String key = "A";
        DataObject dataObject = cache.getIfPresent(key);
        System.out.println(dataObject);
        cache.put(key, new DataObject("test 1"));
        dataObject = cache.getIfPresent(key);
        System.out.println(dataObject);
    }

    private static void testSyncLoading() {
        LoadingCache<Request, String> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build(k -> k.toString());

        String dataObject = cache.get(new Request("a", "1"));
        System.out.println(dataObject);

        Map<Request, String> dataObjectMap
                = cache.getAll(Arrays.asList(new Request("a", "1"), new Request("a", "2")));
        System.out.println(dataObjectMap);
    }

    private static void testAsyncLoading() {
        AsyncLoadingCache<Request, String> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .buildAsync(k -> k.toString());

        cache.get(new Request("a", "1")).thenAccept(System.out::println);
        cache.getAll(Arrays.asList(new Request("a", "1"), new Request("a", "2"))).thenAccept(System.out::println);
    }

    private static void testSizeBasedEviction() {
        LoadingCache<String, DataObject> cache1 = Caffeine.newBuilder()
                .maximumSize(1)
                .build(k -> DataObject.get("Data for " + k));
        System.out.println(cache1.estimatedSize());

        cache1.get("A");
        System.out.println(cache1.estimatedSize());

        cache1.get("B");
        System.out.println(cache1.estimatedSize());
        cache1.cleanUp();
        System.out.println(cache1.estimatedSize());


        LoadingCache<String, DataObject> cache2 = Caffeine.newBuilder()
                .maximumWeight(10)
                .weigher((k, v) -> 5)
                .build(k -> DataObject.get("Data for " + k));
        System.out.println(cache2.estimatedSize());

        cache2.get("A");
        System.out.println(cache2.estimatedSize());

        cache2.get("B");
        System.out.println(cache2.estimatedSize());
        cache2.cleanUp();
        System.out.println(cache2.estimatedSize());
    }

    private static void testTimeBasedEviction() {
        LoadingCache<String, DataObject> cache1 = Caffeine.newBuilder()
                .expireAfterAccess(5, TimeUnit.MINUTES)
                .build(k -> DataObject.get("Data for " + k));

        LoadingCache<String, DataObject> cache2 = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .weakKeys()
                .weakValues()
                .build(k -> DataObject.get("Data for " + k));

        LoadingCache<String, DataObject> cache3 = Caffeine.newBuilder().expireAfter(new Expiry<String, DataObject>() {
            @Override
            public long expireAfterCreate(
                    String key, DataObject value, long currentTime) {
                return value.getData().length() * 1000;
            }

            @Override
            public long expireAfterUpdate(
                    String key, DataObject value, long currentTime, long currentDuration) {
                return currentDuration;
            }

            @Override
            public long expireAfterRead(
                    String key, DataObject value, long currentTime, long currentDuration) {
                return currentDuration;
            }
        }).build(k -> DataObject.get("Data for " + k));
    }

    private static void testReferenceBasedEviction() {
        LoadingCache<String, DataObject> cache1 = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .weakKeys()
                .weakValues()
                .build(k -> DataObject.get("Data for " + k));

        LoadingCache<String, DataObject> cache2 = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .softValues()
                .build(k -> DataObject.get("Data for " + k));
    }

    private static void testRefreshing() {
        LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
                .refreshAfterWrite(1, TimeUnit.MINUTES)
                .build(k -> DataObject.get("Data for " + k));
    }

    private static void testStatistics() {
        LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .recordStats()
                .build(k -> DataObject.get("Data for " + k));

        cache.get("A");
        cache.get("A");

        System.out.println(cache.stats());
    }

    static class DataObject {
        private final String data;


        DataObject(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }

        public static DataObject get(String data) {
            return new DataObject(data);
        }

        @Override
        public String toString() {
            return String.format("Data: %s", data);
        }
    }

    @Data
    @AllArgsConstructor
    public static class Request {
        private String path;
        private String filename;
    }
}
