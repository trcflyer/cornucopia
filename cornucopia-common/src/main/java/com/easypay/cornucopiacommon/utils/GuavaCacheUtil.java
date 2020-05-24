package com.easypay.cornucopiacommon.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GuavaCacheUtil {
    /**
     * @desction: 使用google guava缓存处理
     */
    private static Cache<String,Object> cache;
    static {
        cache = CacheBuilder.newBuilder()
                .maximumSize(1000)// 设置缓存的最大容量
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .initialCapacity(100)
                // 设置并发级别为cpu核心数
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }

    /**
     * @desction: 获取缓存
     */
    public  static Object get(String key){
        return StringUtils.isNotEmpty(key)?cache.getIfPresent(key):null;
    }
    /**
     * @desction: 放入缓存
     */
    public static void put(String key,Object value){
        if(StringUtils.isNotEmpty(key) && value !=null){
            cache.put(key,value);
        }
    }
    /**
     * @desction: 移除缓存
     */
    public static void remove(String key){
        if(StringUtils.isNotEmpty(key)){
            cache.invalidate(key);
        }
    }
    /**
     * @desction: 批量删除缓存
     */
    public static void remove(List<String> keys){
        if(keys !=null && keys.size() >0){
            cache.invalidateAll(keys);
        }
    }
}
