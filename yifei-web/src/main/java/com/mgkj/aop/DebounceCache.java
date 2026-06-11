package com.mgkj.aop;

import java.util.concurrent.ConcurrentHashMap;

public class DebounceCache {
    private static final ConcurrentHashMap<String, Long> CACHE = new ConcurrentHashMap<>();

    public static boolean isDuplicate(String key, long intervalMillis) {
        long now = System.currentTimeMillis();
        Long lastTime = CACHE.get(key);

        if (lastTime != null && (now - lastTime) < intervalMillis) {
            return true;
        }

        CACHE.put(key, now);
        return false;
    }

    // 可选：清理过期数据（比如定时任务调用）
    public static void clean(long maxAgeMillis) {
        long now = System.currentTimeMillis();
        CACHE.entrySet().removeIf(e -> now - e.getValue() > maxAgeMillis);
    }
}
