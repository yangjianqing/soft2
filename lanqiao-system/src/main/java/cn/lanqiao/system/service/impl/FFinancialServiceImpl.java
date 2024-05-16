package cn.lanqiao.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.lanqiao.system.mapper.FFinancialMapper;
import cn.lanqiao.system.domain.FFinancial;
import cn.lanqiao.system.service.IFFinancialService;

/**
 * 财务报表管理Service业务层处理
 * 
 * @author chuan
 * @date 2024-05-15
 */
@Service
public class FFinancialServiceImpl implements IFFinancialService 
{
    @Autowired
    private FFinancialMapper fFinancialMapper;

    /**
     * 查询财务报表管理
     * 
     * @param financialId 财务报表管理主键
     * @return 财务报表管理
     */
    @Override
    public FFinancial selectFFinancialByFinancialId(Long financialId)
    {
        return fFinancialMapper.selectFFinancialByFinancialId(financialId);
    }

    /**
     * 查询财务报表管理列表
     * 
     * @param fFinancial 财务报表管理
     * @return 财务报表管理
     */
    @Override
    public List<FFinancial> selectFFinancialList(FFinancial fFinancial)
    {
        return fFinancialMapper.selectFFinancialList(fFinancial);
    }

    /**
     * 新增财务报表管理
     * 
     * @param fFinancial 财务报表管理
     * @return 结果
     */
    @Override
    public int insertFFinancial(FFinancial fFinancial)
    {
        return fFinancialMapper.insertFFinancial(fFinancial);
    }

    /**
     * 修改财务报表管理
     * 
     * @param fFinancial 财务报表管理
     * @return 结果
     */
    @Override
    public int updateFFinancial(FFinancial fFinancial)
    {
        return fFinancialMapper.updateFFinancial(fFinancial);
    }

    /**
     * 批量删除财务报表管理
     * 
     * @param financialIds 需要删除的财务报表管理主键
     * @return 结果
     */
    @Override
    public int deleteFFinancialByFinancialIds(Long[] financialIds)
    {
        return fFinancialMapper.deleteFFinancialByFinancialIds(financialIds);
    }

    /**
     * 删除财务报表管理信息
     * 
     * @param financialId 财务报表管理主键
     * @return 结果
     */
    @Override
    public int deleteFFinancialByFinancialId(Long financialId)
    {
        return fFinancialMapper.deleteFFinancialByFinancialId(financialId);
    }
}
