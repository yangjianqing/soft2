package cn.lanqiao.system.service;

import java.math.BigDecimal;
import java.util.List;

import cn.lanqiao.system.domain.FGoods;
import cn.lanqiao.system.domain.FOrdeers;
import cn.lanqiao.system.domain.FOrderPartslist;
import cn.lanqiao.system.domain.FormData;
import cn.lanqiao.system.domain.vo.OrderstatusData;
import cn.lanqiao.system.domain.vo.Settlement;

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
     * @param formData 购物车数据对象
     *
     */
    public void settle(FormData formData);

    /**
     * 手机端结算
     *
     * @param settlement 手机端结算数据对象
     *
     */
    public void insertShopping(Settlement settlement);

    /**
     * 手机端用户全部订单
     *
     * @param usersPhone 用户电话号码
     */
    public List<FOrderPartslist> selectOrders(String usersPhone);

    /**
     * 手机端用户待付款订单
     *
     * @param usersPhone 用户电话号码
     */
    public List<FOrderPartslist> selectpaymentOrders(String usersPhone);

    /**
     * 手机端用户待发货订单
     *
     * @param usersPhone 用户电话号码
     */
    public List<FOrderPartslist> selectwaitingOrders(String usersPhone);

    /**
     * 手机端用户待收货订单
     *
     * @param usersPhone 用户电话号码
     */
    public List<FOrderPartslist> selectReceiveOrders(String usersPhone);

    /**
     * 手机端用户待评价订单
     *
     * @param usersPhone 用户电话号码
     */
    public List<FOrderPartslist> selectevaluateOrders(String usersPhone);

    /**
     * 手机端订单状态修改接口
     * @param orderstatusData 订单状态接口数据
     *
     */
     public int updateSettlement(OrderstatusData orderstatusData);
}
