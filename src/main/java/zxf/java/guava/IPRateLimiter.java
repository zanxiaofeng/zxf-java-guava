package zxf.java.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class IPRateLimiter {
    private final ConcurrentHashMap<String, IPRateLimitEntry> limiterMap = new ConcurrentHashMap<>();
    private static final Double REQUESTS_PER_SECOND = 1.0;
    private static final Long EXPIRE_MILLIS = 10 * 60 * 1000L;

    public IPRateLimiter() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::cleanUp, 10, 10, TimeUnit.MINUTES);
    }

    public Boolean tryAcquire(String ip) {
        IPRateLimitEntry entry = limiterMap.compute(ip, (k, v) -> {
            if (v == null) {
                return new IPRateLimitEntry(RateLimiter.create(REQUESTS_PER_SECOND));
            }
            v.lastAccess = System.currentTimeMillis();
            return v;
        });
        return entry.rateLimiter.tryAcquire();
    }

    private void cleanUp() {
        limiterMap.entrySet().removeIf(e -> e.getValue().isExpired());
    }


    private static class IPRateLimitEntry {
        RateLimiter rateLimiter;
        Long lastAccess;

        IPRateLimitEntry(RateLimiter rateLimiter) {
            this.rateLimiter = rateLimiter;
            this.lastAccess = System.currentTimeMillis();
        }

        public Boolean isExpired() {
            return System.currentTimeMillis() - this.lastAccess > EXPIRE_MILLIS;
        }
    }
}
