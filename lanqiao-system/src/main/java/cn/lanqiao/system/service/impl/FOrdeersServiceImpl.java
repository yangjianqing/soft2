package cn.lanqiao.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.lanqiao.system.mapper.FOrdeersMapper;
import cn.lanqiao.system.domain.FOrdeers;
import cn.lanqiao.system.service.IFOrdeersService;

/**
 * 订单管理Service业务层处理
 * 
 * @author chuan
 * @date 2024-05-21
 */
@Service
public class FOrdeersServiceImpl implements IFOrdeersService 
{
    @Autowired
    private FOrdeersMapper fOrdeersMapper;

    /**
     * 查询订单管理
     * 
     * @param ordersId 订单管理主键
     * @return 订单管理
     */
    @Override
    public FOrdeers selectFOrdeersByOrdersId(Long ordersId)
    {
        return fOrdeersMapper.selectFOrdeersByOrdersId(ordersId);
    }

    /**
     * 查询订单管理列表
     * 
     * @param fOrdeers 订单管理
     * @return 订单管理
     */
    @Override
    public List<FOrdeers> selectFOrdeersList(FOrdeers fOrdeers)
    {
        return fOrdeersMapper.selectFOrdeersList(fOrdeers);
    }

    /**
     * 新增订单管理
     * 
     * @param fOrdeers 订单管理
     * @return 结果
     */
    @Override
    public int insertFOrdeers(FOrdeers fOrdeers)
    {
        return fOrdeersMapper.insertFOrdeers(fOrdeers);
    }

    /**
     * 修改订单管理
     * 
     * @param fOrdeers 订单管理
     * @return 结果
     */
    @Override
    public int updateFOrdeers(FOrdeers fOrdeers)
    {
        return fOrdeersMapper.updateFOrdeers(fOrdeers);
    }

    /**
     * 批量删除订单管理
     * 
     * @param ordersIds 需要删除的订单管理主键
     * @return 结果
     */
    @Override
    public int deleteFOrdeersByOrdersIds(Long[] ordersIds)
    {
        return fOrdeersMapper.deleteFOrdeersByOrdersIds(ordersIds);
    }

    /**
     * 删除订单管理信息
     * 
     * @param ordersId 订单管理主键
     * @return 结果
     */
    @Override
    public int deleteFOrdeersByOrdersId(Long ordersId)
    {
        return fOrdeersMapper.deleteFOrdeersByOrdersId(ordersId);
    }
}
