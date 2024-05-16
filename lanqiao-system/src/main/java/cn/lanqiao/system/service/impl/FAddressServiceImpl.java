package cn.lanqiao.system.service.impl;

import java.util.List;

import cn.lanqiao.common.core.domain.entity.SysDictData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.lanqiao.system.mapper.FAddressMapper;
import cn.lanqiao.system.domain.FAddress;
import cn.lanqiao.system.service.IFAddressService;

/**
 * 地址管理Service业务层处理
 * 
 * @author chuan
 * @date 2024-05-15
 */
@Service
public class FAddressServiceImpl implements IFAddressService 
{
    @Autowired
    private FAddressMapper fAddressMapper;

    /**
     * 查询地址管理
     * 
     * @param addressId 地址管理主键
     * @return 地址管理
     */
    @Override
    public FAddress selectFAddressByAddressId(Long addressId)
    {
        return fAddressMapper.selectFAddressByAddressId(addressId);
    }

    /**
     * 查询地址管理列表
     * 
     * @param fAddress 地址管理
     * @return 地址管理
     */
    @Override
    public List<FAddress> selectFAddressList(FAddress fAddress)
    {
        return fAddressMapper.selectFAddressList(fAddress);
    }

    /**
     * 新增地址管理
     * 
     * @param fAddress 地址管理
     * @return 结果
     */
    @Override
    public int insertFAddress(FAddress fAddress)
    {
        return fAddressMapper.insertFAddress(fAddress);
    }

    /**
     * 修改地址管理
     * 
     * @param fAddress 地址管理
     * @return 结果
     */
    @Override
    public int updateFAddress(FAddress fAddress)
    {
        return fAddressMapper.updateFAddress(fAddress);
    }

    /**
     * 批量删除地址管理
     * 
     * @param addressIds 需要删除的地址管理主键
     * @return 结果
     */
    @Override
    public int deleteFAddressByAddressIds(Long[] addressIds)
    {
        return fAddressMapper.deleteFAddressByAddressIds(addressIds);
    }

    /**
     * 删除地址管理信息
     * 
     * @param addressId 地址管理主键
     * @return 结果
     */
    @Override
    public int deleteFAddressByAddressId(Long addressId)
    {
        return fAddressMapper.deleteFAddressByAddressId(addressId);
    }

    @Override
    public List<FAddress> selectUsersIdByType(String usersId) {
        return fAddressMapper.selectUsersIdByType(usersId);
    }


}
