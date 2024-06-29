package cn.lanqiao.system.service;

import cn.lanqiao.system.domain.FGoods;

import java.util.List;
import java.util.Set;

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
    public int insertShopData(String usersPhone,String coding);

    /**
     * 手机端删除购物车redis数据
     *
     * @param usersPhone 用户号码
     * @param coding 商品编码(根据商品编码查询商品数据)
     * 商品数量(每次添加数据到redis同一商品的数量)
     */
    public int deleteShopData(String usersPhone,String coding);

    /**
     * 手机端修改购物车redis数据支付状态为未支付
     *
     * @param usersPhone 用户号码
     *
     */
    public int updateShopData(String usersPhone,Long status);

    /**
     * 手机端减除购物车redis商品数量
     *
     * @param usersPhone 用户号码
     * @param coding 商品编码(根据商品编码查询商品数据)
     * 商品数量(每次添加数据到redis同一商品的数量)
     */
    public int deductionShopData(String usersPhone,String coding);

    /**
     * 手机端购物车页面数据
     *
     * @param usersPhone 用户号码
     */
    public List<FGoods> selectShopData(String usersPhone,Long status);

    /**
     * 手机端redis待付款商品数据
     *
     * @param usersPhone 用户电话
     *
     */
    public List<FGoods> selectShopingData(String usersPhone);

    /**
     * 手机端redis根据商品编码获取待付款商品数据进行结账
     *
     * @param usersPhone 用户电话
     * @param coDings 商品编码集合
     *
     */
    public List<FGoods> selectShopDataCoDings(String usersPhone, Set<String> coDings);
}
