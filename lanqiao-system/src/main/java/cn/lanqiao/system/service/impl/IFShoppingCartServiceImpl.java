package cn.lanqiao.system.service.impl;

import cn.lanqiao.common.constant.CacheConstants;
import cn.lanqiao.common.constant.Constants;
import cn.lanqiao.common.core.redis.RedisCache;
import cn.lanqiao.system.domain.FGoods;
import cn.lanqiao.system.mapper.FGoodsMapper;
import cn.lanqiao.system.service.IFShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private FGoodsMapper fGoodsMapper;
    /**
     * 手机端添加购物车数据到redis
     *
     * @param usersPhone 用户号码
     * @param coding 商品编码(根据商品编码查询商品数据)
     * 商品数量(每次添加数据到redis同一商品的数量)
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


    /**
     * 手机端购物车页面数据
     *
     * @param usersPhone 用户号码
     */
    @Override
    public List<FGoods> selectShopData(String usersPhone) {
        //创建购物车列表数据
        List<FGoods> shoppingList = new ArrayList<>();
        // 生成唯一标识 Key
        String verKey = CacheConstants.Query_Shopping_KEY + usersPhone;
        // 获取已存入的购物车数据
        Map<Long, Long> shoppingCart = redisCache.getShoppingCart(verKey);
        //判断redis的数据是否为null
        if (shoppingCart != null && !shoppingCart.isEmpty()) {
            // 遍历购物车中的每个商品编码，查询对应的商品数据并添加到列表中
            for (Map.Entry<Long, Long> entry : shoppingCart.entrySet()) {
                Long coding = entry.getKey();
                Long quantity = entry.getValue();
                // 根据商品编码查询商品数据
                FGoods fGoods = fGoodsMapper.selectGoodsList(coding);
                if (fGoods != null) {
                    // 设置商品数量
                    fGoods.setQuantity(quantity);
                    shoppingList.add(fGoods);
                }
            }
        }
        return shoppingList;
    }
}