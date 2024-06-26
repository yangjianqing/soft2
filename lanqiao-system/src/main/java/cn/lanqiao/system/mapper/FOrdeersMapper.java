package cn.lanqiao.system.mapper;

import java.util.List;
import cn.lanqiao.system.domain.FOrdeers;
import org.apache.ibatis.annotations.Param;

/**
 * 订单管理Mapper接口
 * 
 * @author chuan
 * @date 2024-05-21
 */
public interface FOrdeersMapper 
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
     * 删除订单管理
     * 
     * @param ordersId 订单管理主键
     * @return 结果
     */
    public int deleteFOrdeersByOrdersId(Long ordersId);

    /**
     * 批量删除订单管理
     * 
     * @param ordersIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFOrdeersByOrdersIds(Long[] ordersIds);

    /**
     * 手机端用户全部订单
     *
     * @param usersId 用户Id
     */
    public List<FOrdeers> selectOrders(Long usersId);

    /**
     * 手机端用户待付款订单
     *
     * @param usersId 用户Id
     */
    public List<FOrdeers> selectpaymentOrders(Long usersId);

    /**
     * 手机端用户待发货订单
     *
     * @param usersId 用户Id
     */
    public List<FOrdeers> selectwaitingOrders(Long usersId);

    /**
     * 手机端用户待收货订单
     *
     * @param usersId 用户Id
     */
    public List<FOrdeers> selectReceiveOrders(Long usersId);

    /**
     * 手机端用户待评价订单
     *
     * @param usersId 用户Id
     */
    public List<FOrdeers> selectevaluateOrders(Long usersId);

    /**
     * 根据订单编号修改订单数据
     *
     * @param ordersNumber 订单编号
     */
    public int updateOrdersStatus(String ordersNumber);

    /**
     * 手机端根据用户id及订单编号查询订单数据
     *
     * @param ordersUsersId 用户Id
     * @param ordersNumber 订单编号
     *
     */
    public FOrdeers selectOrder(@Param("ordersUsersId")Long ordersUsersId,@Param("ordersNumber")String ordersNumber);
}