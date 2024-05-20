package cn.lanqiao.system.mapper;

import java.util.List;
import cn.lanqiao.system.domain.FMembers;

/**
 * 会员管理Mapper接口
 *
 * @author chuan
 * @date 2024-05-15
 */
public interface FMembersMapper
{
    /**
     * 查询会员管理
     *
     * @param memberId 会员管理主键
     * @return 会员管理
     */
    public FMembers selectFMembersByMemberId(Long memberId);

    /**
     * 查询会员管理列表
     *
     * @param fMembers 会员管理
     * @return 会员管理集合
     */
    public List<FMembers> selectFMembersList(FMembers fMembers);

    /**
     * 新增会员管理
     *
     * @param fMembers 会员管理
     * @return 结果
     */
    public int insertFMembers(FMembers fMembers);

    /**
     * 修改会员管理
     *
     * @param fMembers 会员管理
     * @return 结果
     */
    public int updateFMembers(FMembers fMembers);

    /**
     * 删除会员管理
     *
     * @param memberId 会员管理主键
     * @return 结果
     */
    public int deleteFMembersByMemberId(Long memberId);

    /**
     * 批量删除会员管理
     *
     * @param memberIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFMembersByMemberIds(Long[] memberIds);
}
