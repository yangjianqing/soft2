package cn.lanqiao.system.service;

import java.util.List;
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
}
