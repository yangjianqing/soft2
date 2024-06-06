package cn.lanqiao.system.service;

/**
 * 购物车Service接口
 *
 * @author lanqiao
 * @date 2024-06-6
 */
public interface IFShoppingCartService {
    /**
     * 手机端添加购物车数据到redis
     * @param
     */
    public void insertShopData(String usersPhone,Long coding, Long quantity);
}
