package cn.lanqiao.system.mapper;

import java.util.List;
import cn.lanqiao.system.domain.FUsers;

/**
 * 用户管理Mapper接口
 *
 * @author chuan
 * @date 2024-05-14
 */
public interface FUsersMapper
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
     * 删除用户管理
     *
     * @param usersId 用户管理主键
     * @return 结果
     */
    public int deleteFUsersByUsersId(Long usersId);

    /**
     * 批量删除用户管理
     *
     * @param usersIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFUsersByUsersIds(Long[] usersIds);
}
