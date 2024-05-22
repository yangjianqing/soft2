package cn.lanqiao.system.service.impl;

import java.util.*;

import cn.lanqiao.common.core.domain.entity.SysUser;
import cn.lanqiao.common.utils.DateUtils;
import cn.lanqiao.common.utils.OrderNumberGenerator;
import cn.lanqiao.system.domain.FUsers;
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
    private FUsersMapper fUsersMapper;

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
        // 创建存储userid的存储器
        Set<Long> usersIds = new HashSet<>();
        // 创建存储depTid的存储器
        Set<Long> depTidIds = new HashSet<>();

        // 遍历订单管理列表数据，判断usersId是否为空，不是则将usersId存储到Set<Long> usersIds = new HashSet<>();
        for (FOrdeers fm : fOrdeers1) {
            if(fm.getOrdersUsersId() != null) {
                usersIds.add(fm.getOrdersUsersId());
            }
            if(fm.getOrdersSysuserId() != null) {
                depTidIds.add(fm.getOrdersSysuserId());
            }
        }

        // 根据订单列表数据的usersId来获取用户users列表数据
        List<FUsers> UsersList = new ArrayList<>();
        if(!usersIds.isEmpty()) {
            UsersList = fUsersMapper.selectUsersListCIds(new ArrayList<>(usersIds));
        }
        // 根据订单列表数据的usersId来获取用户users列表数据
        List<SysUser> depTidIdsList = new ArrayList<>();
        if(!depTidIds.isEmpty()) {
            depTidIdsList = sysUserMapper.selectUsersListdeliveryIds(new ArrayList<>(depTidIds));
        }

        // 利用map集合键值对，将用户数据存储到map集合里面  (键：UsersId;值：UsersList)
        Map<Long, FUsers> busersMap = new HashMap<>();
        for (FUsers f : UsersList) {
            busersMap.put(f.getUsersId(), f);
        }
        Map<Long, SysUser> susUserMap = new HashMap<>();
        for (SysUser s : depTidIdsList) {
            susUserMap.put(s.getDeptId(), s);
        }

        // 替换用户姓名
        // 进行遍历订单列表数据
        for (FOrdeers fm : fOrdeers1) {
            //判断订单列表的用户id是否为空
            if (fm.getOrdersUsersId() != null) {
                //调用map集合根据当前订单的用户id来拿到用户id里面用户列表的数据
                FUsers fUsers = busersMap.get(fm.getOrdersUsersId());
                //判断拿到的用户列表数据是否为空
                if (fUsers != null) {
                    //将会员列表的usersName传递给会员列表的usersName
                    fm.setOrdersUsersName(fUsers.getUsersName());
                }
            }
            if (fm.getOrdersSysuserId() != null) {
                //调用map集合根据当前订单的用户id来拿到用户id里面用户列表的数据
                SysUser sysUser = susUserMap.get(fm.getOrdersSysuserId());
                //判断拿到的用户列表数据是否为空
                if (sysUser != null) {
                    //将会员列表的usersName传递给会员列表的usersName
                    fm.setOrdersSysuserName(sysUser.getUserName());
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
        fOrdeers.setOrdersNumber(OrderNumberGenerator.generate());
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
