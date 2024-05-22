package cn.lanqiao.system.service.impl;

import java.util.*;

import cn.lanqiao.common.core.domain.entity.SysUser;
import cn.lanqiao.common.utils.DateUtils;
import cn.lanqiao.common.utils.OrderNumberGenerator;
import cn.lanqiao.system.domain.FAddress;
import cn.lanqiao.system.domain.FUsers;
import cn.lanqiao.system.mapper.FAddressMapper;
import cn.lanqiao.system.mapper.FUsersMapper;
import cn.lanqiao.system.mapper.SysUserMapper;
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

    @Autowired
    private FAddressMapper addressMapper;

    @Autowired
    private SysUserMapper sysUserMapper;
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
        List<FOrdeers> fOrdeers1 = fOrdeersMapper.selectFOrdeersList(fOrdeers);
        // 创建存储depTid的存储器
        Set<Long> depTidIds = new HashSet<>();
        // 创建存储userid的存储器
        Set<Long> usersIds = new HashSet<>();

        // 遍历订单管理列表数据，判断usersId是否为空，不是则将usersId存储到Set<Long> usersIds = new HashSet<>();
        for (FOrdeers fm : fOrdeers1) {
            if(fm.getOrdersSysuserId() != null) {
                depTidIds.add(fm.getOrdersSysuserId());
            }
            if(fm.getOrdersUsersId() != null) {
                usersIds.add(fm.getOrdersUsersId());
            }
        }

        // 根据订单列表数据的usersId来获取用户users列表数据
        List<SysUser> depTidIdsList = new ArrayList<>();
        if(!depTidIds.isEmpty()) {
            depTidIdsList = sysUserMapper.selectUsersListdeliveryIds(new ArrayList<>(depTidIds));
        }
        // 根据订单列表数据的AddsId来获取用户users列表数据
        List<FAddress> AddreessList = new ArrayList<>();
        if(!usersIds.isEmpty()) {
            AddreessList = addressMapper.selectaddressIdByType(new ArrayList<>(usersIds));
        }

        Map<Long, SysUser> susUserMap = new HashMap<>();
        for (SysUser s : depTidIdsList) {
            susUserMap.put(s.getDeptId(), s);
        }

        Map<Long, FAddress> addressMap = new HashMap<>();
        for (FAddress f : AddreessList) {
            addressMap.put(f.getAddressId(), f);
        }

        // 替换用户姓名
        // 进行遍历订单列表数据
        for (FOrdeers fm : fOrdeers1) {
            if (fm.getOrdersSysuserId() != null) {
                //调用map集合根据当前订单的用户id来拿到用户id里面用户列表的数据
                SysUser sysUser = susUserMap.get(fm.getOrdersSysuserId());
                //判断拿到的用户列表数据是否为空
                if (sysUser != null) {
                    //将会员列表的usersName传递给会员列表的usersName
                    fm.setOrdersSysuserName(sysUser.getUserName());
                }
            }
            if (fm.getOrdersUsersId() != null) {
                //调用map集合根据当前订单的用户id来拿到用户id里面用户列表的数据
                FAddress fAddress = addressMap.get(fm.getOrdersUsersId());
                //判断拿到的用户列表数据是否为空
                if (fAddress != null) {
                    //将会员列表的usersName传递给会员列表的usersName
                    fm.setAddress(fAddress);
                }
            }
        }
        return fOrdeers1;
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
        fOrdeers.setOrdersCreattime(DateUtils.getNowDate());
        fOrdeers.setOrdersNumber(OrderNumberGenerator.generateOrderNumber());
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
