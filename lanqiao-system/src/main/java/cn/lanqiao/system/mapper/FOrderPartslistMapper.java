package cn.lanqiao.system.mapper;

import java.util.List;
import cn.lanqiao.system.domain.FOrderPartslist;

/**
 * 订单明细Mapper接口
 * 
 * @author lanqiao
 * @date 2024-05-22
 */
public interface FOrderPartslistMapper 
{
    /**
     * 查询订单明细
     * 
     * @param id 订单明细主键
     * @return 订单明细
     */
    public FOrderPartslist selectFOrderPartslistById(Long id);

    /**
     * 查询订单明细列表
     * 
     * @param fOrderPartslist 订单明细
     * @return 订单明细集合
     */
    public List<FOrderPartslist> selectFOrderPartslistList(FOrderPartslist fOrderPartslist);

    /**
     * 新增订单明细
     * 
     * @param fOrderPartslist 订单明细
     * @return 结果
     */
    public int insertFOrderPartslist(FOrderPartslist fOrderPartslist);

    /**
     * 修改订单明细
     * 
     * @param fOrderPartslist 订单明细
     * @return 结果
     */
    public int updateFOrderPartslist(FOrderPartslist fOrderPartslist);

    /**
     * 删除订单明细
     * 
     * @param id 订单明细主键
     * @return 结果
     */
    public int deleteFOrderPartslistById(Long id);

    /**
     * 批量删除订单明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFOrderPartslistByIds(Long[] ids);
}
