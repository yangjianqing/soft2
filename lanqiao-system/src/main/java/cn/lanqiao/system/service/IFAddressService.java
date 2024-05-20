package cn.lanqiao.system.service;

import java.util.List;

import cn.lanqiao.common.core.domain.entity.SysDictData;
import cn.lanqiao.system.domain.FAddress;

/**
 * 地址管理Service接口
 * 
 * @author chuan
 * @date 2024-05-15
 */
public interface IFAddressService 
{
    /**
     * 查询地址管理
     * 
     * @param addressId 地址管理主键
     * @return 地址管理
     */
    public FAddress selectFAddressByAddressId(Long addressId);

    /**
     * 查询地址管理列表
     * 
     * @param fAddress 地址管理
     * @return 地址管理集合
     */
    public List<FAddress> selectFAddressList(FAddress fAddress);

    /**
     * 新增地址管理
     * 
     * @param fAddress 地址管理
     * @return 结果
     */
    public int insertFAddress(FAddress fAddress);

    /**
     * 修改地址管理
     * 
     * @param fAddress 地址管理
     * @return 结果
     */
    public int updateFAddress(FAddress fAddress);

    /**
     * 批量删除地址管理
     * 
     * @param addressIds 需要删除的地址管理主键集合
     * @return 结果
     */
    public int deleteFAddressByAddressIds(Long[] addressIds);

    /**
     * 删除地址管理信息
     * 
     * @param addressId 地址管理主键
     * @return 结果
     */
    public int deleteFAddressByAddressId(Long addressId);

    /**
     * 根据字典类型查询字典数据
     *
     * @param usersId 字典类型
     * @return 字典数据集合信息
     */
    public List<FAddress> selectUsersIdByType(String usersId);
}