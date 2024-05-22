package cn.lanqiao.system.service.impl;

import java.util.List;
import cn.lanqiao.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.lanqiao.system.mapper.FUsersMapper;
import cn.lanqiao.system.domain.FUsers;
import cn.lanqiao.system.service.IFUsersService;

/**
 * 用户管理Service业务层处理
 *
 * @author chuan
 * @date 2024-05-20
 */
@Service
public class FUsersServiceImpl implements IFUsersService
{
    @Autowired
    private FUsersMapper fUsersMapper;

    /**
     * 查询用户管理
     *
     * @param usersId 用户管理主键
     * @return 用户管理
     */
    @Override
    public FUsers selectFUsersByUsersId(Long usersId)
    {
        return fUsersMapper.selectFUsersByUsersId(usersId);
    }

    /**
     * 查询用户管理列表
     *
     * @param fUsers 用户管理
     * @return 用户管理
     */
    @Override
    public List<FUsers> selectFUsersList(FUsers fUsers)
    {
        return fUsersMapper.selectFUsersList(fUsers);
    }

    /**
     * 新增用户管理
     *
     * @param fUsers 用户管理
     * @return 结果
     */
    @Override
    public int insertFUsers(FUsers fUsers)
    {
        fUsers.setCreateTime(DateUtils.getNowDate());
        return fUsersMapper.insertFUsers(fUsers);
    }

    /**
     * 修改用户管理
     *
     * @param fUsers 用户管理
     * @return 结果
     */
    @Override
    public int updateFUsers(FUsers fUsers)
    {
        return fUsersMapper.updateFUsers(fUsers);
    }

    /**
     * 批量删除用户管理
     *
     * @param usersIds 需要删除的用户管理主键
     * @return 结果
     */
    @Override
    public int deleteFUsersByUsersIds(Long[] usersIds)
    {
        return fUsersMapper.deleteFUsersByUsersIds(usersIds);
    }

    /**
     * 删除用户管理信息
     *
     * @param usersId 用户管理主键
     * @return 结果
     */
    @Override
    public int deleteFUsersByUsersId(Long usersId)
    {
        return fUsersMapper.deleteFUsersByUsersId(usersId);
    }

    @Override
    public List<FUsers> selectUsersListCIds(List<Long> UsersId) {
        return fUsersMapper.selectUsersListCIds(UsersId);
    }

    @Override
    public List<FUsers> selectUsersAll() {
        return fUsersMapper.selectUsersAll();
    }
}
