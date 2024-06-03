package cn.lanqiao.system.service;

import java.util.List;
import cn.lanqiao.system.domain.FPurchaseOrders;

/**
 * 采购信息Service接口
 * 
 * @author lanqiao
 * @date 2024-05-20
 */
public interface IFPurchaseOrdersService 
{
    /**
     * 查询采购信息
     * 
     * @param orderId 采购信息主键
     * @return 采购信息
     */
    public FPurchaseOrders selectFPurchaseOrdersByOrderId(Long orderId);

    /**
     * 查询采购信息列表
     * 
     * @param fPurchaseOrders 采购信息
     * @return 采购信息集合
     */
    public List<FPurchaseOrders> selectFPurchaseOrdersList(FPurchaseOrders fPurchaseOrders);

    /**
     * 新增采购信息
     * 
     * @param fPurchaseOrders 采购信息
     * @return 结果
     */
    public int insertFPurchaseOrders(FPurchaseOrders fPurchaseOrders);

    /**
     * 修改采购信息
     * 
     * @param fPurchaseOrders 采购信息
     * @return 结果
     */
    public int updateFPurchaseOrders(FPurchaseOrders fPurchaseOrders);

    /**
     * 批量删除采购信息
     * 
     * @param orderIds 需要删除的采购信息主键集合
     * @return 结果
     */
    public int deleteFPurchaseOrdersByOrderIds(Long[] orderIds);

    /**
     * 删除采购信息信息
     * 
     * @param orderId 采购信息主键
     * @return 结果
     */
    public int deleteFPurchaseOrdersByOrderId(Long orderId);


}
