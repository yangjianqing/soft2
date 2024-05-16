package cn.lanqiao.system.service;

import java.util.List;
import cn.lanqiao.system.domain.FSaledeta;

/**
 * 销售明细管理Service接口
 * 
 * @author chuan
 * @date 2024-05-16
 */
public interface IFSaledetaService 
{
    /**
     * 查询销售明细管理
     * 
     * @param saledetaId 销售明细管理主键
     * @return 销售明细管理
     */
    public FSaledeta selectFSaledetaBySaledetaId(Long saledetaId);

    /**
     * 查询销售明细管理列表
     * 
     * @param fSaledeta 销售明细管理
     * @return 销售明细管理集合
     */
    public List<FSaledeta> selectFSaledetaList(FSaledeta fSaledeta);

    /**
     * 新增销售明细管理
     * 
     * @param fSaledeta 销售明细管理
     * @return 结果
     */
    public int insertFSaledeta(FSaledeta fSaledeta);

    /**
     * 修改销售明细管理
     * 
     * @param fSaledeta 销售明细管理
     * @return 结果
     */
    public int updateFSaledeta(FSaledeta fSaledeta);

    /**
     * 批量删除销售明细管理
     * 
     * @param saledetaIds 需要删除的销售明细管理主键集合
     * @return 结果
     */
    public int deleteFSaledetaBySaledetaIds(Long[] saledetaIds);

    /**
     * 删除销售明细管理信息
     * 
     * @param saledetaId 销售明细管理主键
     * @return 结果
     */
    public int deleteFSaledetaBySaledetaId(Long saledetaId);
}
