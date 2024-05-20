package cn.lanqiao.system.mapper;

import java.util.List;
import cn.lanqiao.system.domain.FSales;

/**
 * 销售记录管理Mapper接口
 * 
 * @author chuan
 * @date 2024-05-16
 */
public interface FSalesMapper 
{
    /**
     * 查询销售记录管理
     * 
     * @param salesId 销售记录管理主键
     * @return 销售记录管理
     */
    public FSales selectFSalesBySalesId(Long salesId);

    /**
     * 查询销售记录管理列表
     * 
     * @param fSales 销售记录管理
     * @return 销售记录管理集合
     */
    public List<FSales> selectFSalesList(FSales fSales);

    /**
     * 新增销售记录管理
     * 
     * @param fSales 销售记录管理
     * @return 结果
     */
    public int insertFSales(FSales fSales);

    /**
     * 修改销售记录管理
     * 
     * @param fSales 销售记录管理
     * @return 结果
     */
    public int updateFSales(FSales fSales);

    /**
     * 删除销售记录管理
     * 
     * @param salesId 销售记录管理主键
     * @return 结果
     */
    public int deleteFSalesBySalesId(Long salesId);

    /**
     * 批量删除销售记录管理
     * 
     * @param salesIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFSalesBySalesIds(Long[] salesIds);
}
