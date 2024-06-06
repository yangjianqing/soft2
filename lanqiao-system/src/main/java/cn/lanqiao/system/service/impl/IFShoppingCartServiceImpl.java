package cn.lanqiao.system.service.impl;

import cn.lanqiao.common.constant.CacheConstants;
import cn.lanqiao.common.constant.Constants;
import cn.lanqiao.common.core.redis.RedisCache;
import cn.lanqiao.system.domain.FGoods;
import cn.lanqiao.system.mapper.FGoodsMapper;
import cn.lanqiao.system.service.IFShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 购物车Service业务层处理
 *
 * @author lanqiao
 * @date 2024-06-6
 */
@Service
public class IFShoppingCartServiceImpl implements IFShoppingCartService {
    @Autowired
    private RedisCache redisCache;

    /**
     * 手机端添加购物车数据到Redis
     *
     * @param usersPhone 用户手机号
     * @param coding     商品编号
     * @param quantity   商品数量
     */
    @Override
    public void insertShopData(String usersPhone, Long coding,Long quantity) {
        // 生成唯一标识 Key
        String verKey = CacheConstants.Query_Shopping_KEY + usersPhone;
        // 获取现有的购物车数据
        Map<Long, Long> shoppingCart = redisCache.getShoppingCart(verKey);
        if (shoppingCart != null && shoppingCart.containsKey(coding)) {
            // 如果购物车中已经存在相同的商品，则更新数量
            Long existingQuantity = shoppingCart.get(coding);
            quantity += existingQuantity;
        }
        // 将当前商品添加到购物车
        shoppingCart.put(coding,quantity);
        // 将购物车数据存储到 Redis
        redisCache.setShoppingCart(verKey,shoppingCart,Constants.Query_Shopping,TimeUnit.HOURS);
    }

}