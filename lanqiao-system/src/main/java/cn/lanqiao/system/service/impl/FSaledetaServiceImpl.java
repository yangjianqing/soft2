package cn.lanqiao.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.lanqiao.system.mapper.FSaledetaMapper;
import cn.lanqiao.system.domain.FSaledeta;
import cn.lanqiao.system.service.IFSaledetaService;

/**
 * 销售明细管理Service业务层处理
 * 
 * @author chuan
 * @date 2024-05-16
 */
@Service
public class FSaledetaServiceImpl implements IFSaledetaService 
{
    @Autowired
    private FSaledetaMapper fSaledetaMapper;

    /**
     * 查询销售明细管理
     * 
     * @param saledetaId 销售明细管理主键
     * @return 销售明细管理
     */
    @Override
    public FSaledeta selectFSaledetaBySaledetaId(Long saledetaId)
    {
        return fSaledetaMapper.selectFSaledetaBySaledetaId(saledetaId);
    }

    /**
     * 查询销售明细管理列表
     * 
     * @param fSaledeta 销售明细管理
     * @return 销售明细管理
     */
    @Override
    public List<FSaledeta> selectFSaledetaList(FSaledeta fSaledeta)
    {
        return fSaledetaMapper.selectFSaledetaList(fSaledeta);
    }

    /**
     * 新增销售明细管理
     * 
     * @param fSaledeta 销售明细管理
     * @return 结果
     */
    @Override
    public int insertFSaledeta(FSaledeta fSaledeta)
    {
        return fSaledetaMapper.insertFSaledeta(fSaledeta);
    }

    /**
     * 修改销售明细管理
     * 
     * @param fSaledeta 销售明细管理
     * @return 结果
     */
    @Override
    public int updateFSaledeta(FSaledeta fSaledeta)
    {
        return fSaledetaMapper.updateFSaledeta(fSaledeta);
    }

    /**
     * 批量删除销售明细管理
     * 
     * @param saledetaIds 需要删除的销售明细管理主键
     * @return 结果
     */
    @Override
    public int deleteFSaledetaBySaledetaIds(Long[] saledetaIds)
    {
        return fSaledetaMapper.deleteFSaledetaBySaledetaIds(saledetaIds);
    }

    /**
     * 删除销售明细管理信息
     * 
     * @param saledetaId 销售明细管理主键
     * @return 结果
     */
    @Override
    public int deleteFSaledetaBySaledetaId(Long saledetaId)
    {
        return fSaledetaMapper.deleteFSaledetaBySaledetaId(saledetaId);
    }
}
