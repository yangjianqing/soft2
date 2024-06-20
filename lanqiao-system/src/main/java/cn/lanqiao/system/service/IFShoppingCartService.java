package cn.lanqiao.system.service;

import cn.lanqiao.system.domain.FGoods;

import java.util.List;

/**
 * 购物车Service接口
 *
 * @author lanqiao
 * @date 2024-06-6
 */
public interface IFShoppingCartService {

    /**
     * 手机端添加购物车数据到redis
     *
     * @param usersPhone 用户号码
     * @param coding 商品编码(根据商品编码查询商品数据)
     * 商品数量(每次添加数据到redis同一商品的数量)
     */
    public void insertShopData(String usersPhone,Long coding);

    /**
     * 手机端删除购物车redis数据
     *
     * @param usersPhone 用户号码
     * @param coding 商品编码(根据商品编码查询商品数据)
     * 商品数量(每次添加数据到redis同一商品的数量)
     */
    public void deleteShopData(String usersPhone,Long coding);

    /**
     * 手机端减除购物车redis商品数量
     *
     * @param usersPhone 用户号码
     * @param coding 商品编码(根据商品编码查询商品数据)
     * 商品数量(每次添加数据到redis同一商品的数量)
     */
    public void deductionShopData(String usersPhone,Long coding);

    /**
     * 手机端购物车页面数据
     *
     * @param usersPhone 用户号码
     */
    public List<FGoods> selectShopData(String usersPhone);


}
