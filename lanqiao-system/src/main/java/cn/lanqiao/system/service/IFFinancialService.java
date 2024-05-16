package cn.lanqiao.system.service;

import java.util.List;
import cn.lanqiao.system.domain.FFinancial;

/**
 * 财务报表管理Service接口
 * 
 * @author chuan
 * @date 2024-05-15
 */
public interface IFFinancialService 
{
    /**
     * 查询财务报表管理
     * 
     * @param financialId 财务报表管理主键
     * @return 财务报表管理
     */
    public FFinancial selectFFinancialByFinancialId(Long financialId);

    /**
     * 查询财务报表管理列表
     * 
     * @param fFinancial 财务报表管理
     * @return 财务报表管理集合
     */
    public List<FFinancial> selectFFinancialList(FFinancial fFinancial);

    /**
     * 新增财务报表管理
     * 
     * @param fFinancial 财务报表管理
     * @return 结果
     */
    public int insertFFinancial(FFinancial fFinancial);

    /**
     * 修改财务报表管理
     * 
     * @param fFinancial 财务报表管理
     * @return 结果
     */
    public int updateFFinancial(FFinancial fFinancial);

    /**
     * 批量删除财务报表管理
     * 
     * @param financialIds 需要删除的财务报表管理主键集合
     * @return 结果
     */
    public int deleteFFinancialByFinancialIds(Long[] financialIds);

    /**
     * 删除财务报表管理信息
     * 
     * @param financialId 财务报表管理主键
     * @return 结果
     */
    public int deleteFFinancialByFinancialId(Long financialId);
}
