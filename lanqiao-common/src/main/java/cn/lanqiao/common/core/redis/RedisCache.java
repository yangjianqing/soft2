package cn.lanqiao.common.core.redis;

import java.util.*;
import java.util.concurrent.TimeUnit;

import cn.lanqiao.common.core.domain.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * spring redis 工具类
 *
 * @author lanqiao
 **/
@SuppressWarnings(value = { "unchecked", "rawtypes" })
@Component
public class RedisCache
{
    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key, final T value)
    {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit)
    {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等，并存储商品数量
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param quantity 商品数量
     * @param timeout 缓存的超时时间
     * @param timeUnit 时间单位
     */
    public <T> void setDataObject(final String key, final T value, Long quantity, final Integer timeout, final TimeUnit timeUnit) {
        // 将商品数量和值一起存入Redis
        redisTemplate.opsForHash().put(key, "value", value);
        redisTemplate.opsForHash().put(key, "quantity", quantity);
        // 设置超时时间
        redisTemplate.expire(key, timeout, timeUnit);
    }


    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout)
    {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @param unit 时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit)
    {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获取有效时间
     *
     * @param key Redis键
     * @return 有效时间
     */
    public long getExpire(final String key)
    {
        return redisTemplate.getExpire(key);
    }

    /**
     * 判断 key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public Boolean hasKey(String key)
    {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key)
    {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public boolean deleteObject(final String key)
    {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return
     */
    public boolean deleteObject(final Collection collection)
    {
        return redisTemplate.delete(collection) > 0;
    }

    /**
     * 缓存List数据
     *
     * @param key 缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> long setCacheList(final String key, final List<T> dataList)
    {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getCacheList(final String key)
    {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 缓存Set
     *
     * @param key 缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet)
    {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext())
        {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    /**
     * 获得缓存的set
     *
     * @param key
     * @return
     */
    public <T> Set<T> getCacheSet(final String key)
    {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap)
    {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> getCacheMap(final String key)
    {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 将购物车数据存储到 Redis，包括商品编码、数量和支付状态
     *
     * @param key Redis 中的键
     * @param dataMap 包含商品编码、数量和支付状态的 Map
     * @param expireTime 过期时间
     * @param timeUnit 时间单位
     */
    public void setShoppingCart(final String key, final Map<String, ShoppingCart> dataMap, long expireTime, TimeUnit timeUnit) {
        Map<String, String> stringMap = new HashMap<>();
        for (Map.Entry<String, ShoppingCart> entry : dataMap.entrySet()) {
            ShoppingCart item = entry.getValue();
            String value = item.getOrdersPayMethod() + ":" + item.getQuantity(); // 以支付状态:商品数量的形式存储
            stringMap.put(String.valueOf(entry.getKey()), value);
        }
        redisTemplate.opsForHash().putAll(key, stringMap);
        redisTemplate.expire(key, expireTime, timeUnit);
    }


    /**
     * 从 Redis 中删除指定 key 下的指定 mapKey 集合数据
     *
     * @param key     Redis 中的 key
     * @param mapKeys 要删除的 mapKey 的集合
     */
    public void deleteFromMap(final String key, Set<String> mapKeys) {
        redisTemplate.opsForHash().delete(key, mapKeys.toArray());
    }


    /**
     * 从Redis中获取购物车数据
     *
     * @param key Redis中的键
     * @return 包含商品编码、数量和支付状态的购物车 Map
     */
    public Map<String, ShoppingCart> getShoppingCart(final String key) {
        Map<String, String> stringMap = redisTemplate.opsForHash().entries(key);
        Map<String, ShoppingCart> resultMap = new HashMap<>();
        for (Map.Entry<String, String> entry : stringMap.entrySet()) {
            String mapKey = entry.getKey();
            String value = entry.getValue();
            String[] parts = value.split(":");
            if (parts.length == 2) {
                String ordersPayMethod = parts[0];
                int quantity = Integer.parseInt(parts[1]);

                // 构造 ShoppingCart 对象
                ShoppingCart shoppingCart = new ShoppingCart();
                shoppingCart.setOrdersPayMethod(Long.valueOf(ordersPayMethod));
                shoppingCart.setQuantity((long)quantity);

                // 将结果放入返回的 resultMap 中
                resultMap.put(mapKey, shoppingCart);
            }
        }
        return resultMap;
    }


    /**
     * 往Hash中存入数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @param value 值
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value)
    {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public <T> T getCacheMapValue(final String key, final String hKey)
    {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys)
    {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 删除Hash中的某条数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @return 是否成功
     */
    public boolean deleteCacheMapValue(final String key, final String hKey)
    {
        return redisTemplate.opsForHash().delete(key, hKey) > 0;
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern)
    {
        return redisTemplate.keys(pattern);
    }
}
