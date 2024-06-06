package cn.lanqiao.system.service;

import java.math.BigDecimal;
import java.util.List;

import cn.lanqiao.system.domain.FGoods;
import cn.lanqiao.system.domain.FOrdeers;

/**
 * 订单管理Service接口
 * 
 * @author chuan
 * @date 2024-05-21
 */
public interface IFOrdeersService 
{
    /**
     * 查询订单管理
     * 
     * @param ordersId 订单管理主键
     * @return 订单管理
     */
    public FOrdeers selectFOrdeersByOrdersId(Long ordersId);

    /**
     * 查询订单管理列表
     * 
     * @param fOrdeers 订单管理
     * @return 订单管理集合
     */
    public List<FOrdeers> selectFOrdeersList(FOrdeers fOrdeers);

    /**
     * 新增订单管理
     * 
     * @param fOrdeers 订单管理
     * @return 结果
     */
    public int insertFOrdeers(FOrdeers fOrdeers);

    /**
     * 修改订单管理
     * 
     * @param fOrdeers 订单管理
     * @return 结果
     */
    public int updateFOrdeers(FOrdeers fOrdeers);

    /**
     * 批量删除订单管理
     * 
     * @param ordersIds 需要删除的订单管理主键集合
     * @return 结果
     */
    public int deleteFOrdeersByOrdersIds(Long[] ordersIds);

    /**
     * 删除订单管理信息
     * 
     * @param ordersId 订单管理主键
     * @return 结果
     */
    public int deleteFOrdeersByOrdersId(Long ordersId);

    /**
     * 电脑端结算
     * @param productsInCart
     */
    public void settle(String memberPhone, BigDecimal totalPrice, BigDecimal memberJian, List<FGoods> productsInCart);

    /**
     * 手机端结算
     *
     * @param usersPhone 用户电话号码
     * @param ordersPayMethod 支付方式
     * @param ordersPayStatuds 支付状态
     * @param ordersRemark 订单备注信息
     * @param GoodsList 订单集合
     */
    public void insertShopping(String usersPhone,Long ordersPayMethod,Long ordersPayStatuds,String ordersRemark,List<FGoods> GoodsList);
}
