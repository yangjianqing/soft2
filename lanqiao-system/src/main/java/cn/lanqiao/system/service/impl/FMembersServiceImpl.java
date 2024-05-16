package cn.lanqiao.system.service.impl;

import java.util.*;

import cn.lanqiao.system.domain.FUsers;
import cn.lanqiao.system.mapper.FUsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.lanqiao.system.mapper.FMembersMapper;
import cn.lanqiao.system.domain.FMembers;
import cn.lanqiao.system.service.IFMembersService;

/**
 * 会员管理Service业务层处理
 *
 * @author chuan
 * @date 2024-05-15
 */
@Service
public class FMembersServiceImpl implements IFMembersService
{
    @Autowired
    private FMembersMapper fMembersMapper;

    @Autowired
    private FUsersMapper fUsersMapper;

    /**
     * 查询会员管理
     *
     * @param memberId 会员管理主键
     * @return 会员管理
     */
    @Override
    public FMembers selectFMembersByMemberId(Long memberId)
    {
        return fMembersMapper.selectFMembersByMemberId(memberId);
    }

    /**
     * 查询会员管理列表
     *
     * @param fMembers 会员管理
     * @return 会员管理
     */
    @Override
    public List<FMembers> selectFMembersList(FMembers fMembers)
    {
        //拿到会员列表数据
        List<FMembers> fMember = fMembersMapper.selectFMembersList(fMembers);
        //创建存储userid的存储器
        Set<Long> usersIds = new HashSet<>();

        //遍历会员管理列表数据，判断usersId是否为空，不是则将usersId存储到Set<Long> usersIds = new HashSet<>();
        for (FMembers fm : fMember) {
            if(fm.getUsersId() != null) {
                usersIds.add(fm.getUsersId());
            }
        }

        // 根据会员列表数据的usersId来获取用户users列表数据
        List<FUsers> UsersList = new ArrayList<>();
        if(!usersIds.isEmpty()) {
            UsersList = fUsersMapper.selectUsersListCIds(new ArrayList<>(usersIds));
        }

        //利用map集合键值对，将用户数据存储到map集合里面  (键：UsersId;值：UsersList)
        Map<Long, FUsers> busersMap = new HashMap<>();
        for (FUsers f : UsersList) {
            busersMap.put(f.getUsersId(), f);
        }

        // 替换用户姓名
        //进行遍历会员列表数据
        for (FMembers fm : fMember) {
            //判断会员列表的用户id是否为空
            if (fm.getUsersId() != null) {
                //调用map集合根据当前会员的用户id来拿到用户id里面用户列表的数据
                FUsers fUsers = busersMap.get(fm.getUsersId());
                //判断拿到的用户列表数据是否为空
                if (fUsers != null) {
                    //将会员列表的usersName传递给会员列表的usersName
                    fm.setUsersName(fUsers.getUsersName());
                }
            }
        }
        return fMember;
    }

    /**
     * 新增会员管理
     *
     * @param fMembers 会员管理
     * @return 结果
     */
    @Override
    public int insertFMembers(FMembers fMembers)
    {
        return fMembersMapper.insertFMembers(fMembers);
    }

    /**
     * 修改会员管理
     *
     * @param fMembers 会员管理
     * @return 结果
     */
    @Override
    public int updateFMembers(FMembers fMembers)
    {
        return fMembersMapper.updateFMembers(fMembers);
    }

    /**
     * 批量删除会员管理
     *
     * @param memberIds 需要删除的会员管理主键
     * @return 结果
     */
    @Override
    public int deleteFMembersByMemberIds(Long[] memberIds)
    {
        return fMembersMapper.deleteFMembersByMemberIds(memberIds);
    }

    /**
     * 删除会员管理信息
     *
     * @param memberId 会员管理主键
     * @return 结果
     */
    @Override
    public int deleteFMembersByMemberId(Long memberId)
    {
        return fMembersMapper.deleteFMembersByMemberId(memberId);
    }
}
