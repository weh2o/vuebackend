package com.joe.vuebackend.utils;

import com.joe.vuebackend.vo.UserInfo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具類
 */
@Component
public class RedisCacheUtil {

    @Setter(onMethod_ = @Autowired)
    private RedisTemplate redisTemplate;

    /**
     * 緩存基本類型的物件
     *
     * @param key   緩存的鍵
     * @param value 緩存的資料
     * @param <T>   資料類型
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 緩存基本類型的物件
     *
     * @param key      緩存的鍵
     * @param value    緩存的資料
     * @param timeout  有效時間
     * @param timeUnit 時間單位
     */
    public <T> void setCacheObject(final String key,
                                   final T value,
                                   final Integer timeout,
                                   final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 設置有效時間
     *
     * @param key     緩存的鍵
     * @param timeout 有效時間
     * @return true=設置成功；false=設置失敗
     */
    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 設置有效時間
     *
     * @param key     緩存的鍵
     * @param timeout 有效時間
     * @param unit    時間單位
     * @return true=設置成功；false=設置失敗
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 獲取緩存資料
     *
     * @param key 緩存的鍵
     * @param <T> 資料類型
     * @return 緩存對應的資料
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除緩存資料
     *
     * @param key 緩存的鍵
     * @return true=刪除成功；false=刪除失敗
     */
    public boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合物件
     *
     * @param collection 多個物件
     * @return
     */
    public long deleteObject(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 緩存List類型資料
     *
     * @param key      緩存的鍵
     * @param dataList 要緩存的List資料
     * @return
     */
    public <T> long setCacheList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 獲取緩存的List資料
     *
     * @param key 緩存的鍵
     * @return 對應的緩存資料
     */
    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 緩存Set類型的資料
     *
     * @param key     緩存的鍵
     * @param dataSet 要緩存的Set資料
     * @return
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext()) {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    /**
     * 獲取緩存的Set資料
     *
     * @param key 緩存的鍵
     * @return
     */
    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存Map類型的資料
     *
     * @param key     緩存的鍵
     * @param dataMap 要緩存的Map資料
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 獲取緩存的Map資料
     *
     * @param key 緩存的鍵
     * @return
     */
    public <T> Map<String, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往Hash中存入資料
     *
     * @param key   緩存的鍵
     * @param hKey  Hash键
     * @param value 資料
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 獲取Hash中的資料
     *
     * @param key  緩存的鍵
     * @param hKey Hash键
     * @return Hash中的資料
     */
    public <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 删除Hash中的資料
     *
     * @param key  緩存的鍵
     * @param hKey Hash键
     */
    public void delCacheMapValue(final String key, final String hKey) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, hKey);
    }

    /**
     * 獲取多個Hash中的資料
     *
     * @param key   緩存的鍵
     * @param hKeys Hash键集合
     * @return Hash物件集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 獲取緩存的基本物件列表
     *
     * @param pattern 字符串前缀
     * @return 物件列表
     */
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 刷新有效時間。 添加一天
     * @param redisKey 緩存的鍵
     * @param userInfo 使用者資料
     */
    public void refreshExpireOneDay(String redisKey, UserInfo userInfo) {
        String expireStr = userInfo.getExpireTokenTime();
        LocalDateTime time = DateUtil.parseToLocalDateTime(expireStr);
        String newExpireStr = DateUtil.formatLocalDateTime(time.plusDays(1));
        userInfo.setExpireTokenTime(newExpireStr);
        setCacheObject(redisKey, userInfo, 1, TimeUnit.DAYS);
    }

}
