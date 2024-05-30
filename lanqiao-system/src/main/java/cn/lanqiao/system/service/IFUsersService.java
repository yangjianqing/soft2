package cn.lanqiao.system.service;

import java.util.List;
import cn.lanqiao.system.domain.FUsers;

/**
 * 用户管理Service接口
 *
 * @author chuan
 * @date 2024-05-20
 */
public interface IFUsersService
{
    /**
     * 查询用户管理
     *
     * @param usersId 用户管理主键
     * @return 用户管理
     */
    public FUsers selectFUsersByUsersId(Long usersId);

    /**
     * 查询用户管理列表
     *
     * @param fUsers 用户管理
     * @return 用户管理集合
     */
    public List<FUsers> selectFUsersList(FUsers fUsers);

    /**
     * 新增用户管理
     *
     * @param fUsers 用户管理
     * @return 结果
     */
    public int insertFUsers(FUsers fUsers);

    /**
     * 修改用户管理
     *
     * @param fUsers 用户管理
     * @return 结果
     */
    public int updateFUsers(FUsers fUsers);

    /**
     * 批量删除用户管理
     *
     * @param usersIds 需要删除的用户管理主键集合
     * @return 结果
     */
    public int deleteFUsersByUsersIds(Long[] usersIds);

    /**
     * 删除用户管理信息
     *
     * @param usersId 用户管理主键
     * @return 结果
     */
    public int deleteFUsersByUsersId(Long usersId);

    /**
     * 根据用户ids 获取信息
     * @param UsersId
     * @return
     */
    List<FUsers> selectUsersListCIds(List<Long> UsersId);

    /**
     * 查询所有用户
     * @return
     */
    public List<FUsers> selectUsersAll();

    /**
     * 根据用户usersPhone 获取信息
     * @param usersPhone
     * @return
     */
    FUsers selectUsersusersPhone(String usersPhone);
}
