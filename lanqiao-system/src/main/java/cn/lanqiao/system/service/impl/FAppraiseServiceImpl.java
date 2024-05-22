package cn.lanqiao.system.service.impl;

import java.util.*;

import cn.lanqiao.system.domain.FGoods;
import cn.lanqiao.system.domain.FPurchaseOrders;
import cn.lanqiao.system.domain.FUsers;
import cn.lanqiao.system.mapper.FGoodsMapper;
import cn.lanqiao.system.mapper.FUsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.lanqiao.system.mapper.FAppraiseMapper;
import cn.lanqiao.system.domain.FAppraise;
import cn.lanqiao.system.service.IFAppraiseService;

/**
 * 评价管理Service业务层处理
 * 
 * @author lanqiao
 * @date 2024-05-21
 */
@Service
public class FAppraiseServiceImpl implements IFAppraiseService 
{
    @Autowired
    private FAppraiseMapper fAppraiseMapper;
    @Autowired
    private FGoodsMapper fGoodsMapper;
    @Autowired
    private FUsersMapper fUsersMapper;

    /**
     * 查询评价管理
     * 
     * @param reviewid 评价管理主键
     * @return 评价管理
     */
    @Override
    public FAppraise selectFAppraiseByReviewid(Long reviewid)
    {
        return fAppraiseMapper.selectFAppraiseByReviewid(reviewid);
    }

    /**
     * 查询评价管理列表
     * 
     * @param fAppraise 评价管理
     * @return 评价管理
     */
    @Override
    public List<FAppraise> selectFAppraiseList(FAppraise fAppraise)
    {

        List<FAppraise> fAppraises = fAppraiseMapper.selectFAppraiseList(fAppraise);
        Set<Long> GoodsId = new HashSet<>();
        // 创建存储userid的存储器
        Set<Long> UsersIds = new HashSet<>();

        //遍历会员管理列表数据，判断usersId是否为空，不是则将usersId存储到Set<Long> usersIds = new HashSet<>();
        for (FAppraise fp : fAppraises) {
            if(fp.getGoodsid() != null) {
                GoodsId.add(fp.getGoodsid());
            }
            if(fp.getUserid() != null) {
                UsersIds.add(fp.getUserid());
            }
        }

        // 根据会员列表数据的usersId来获取用户users列表数据
        List<FGoods> GoodsList = new ArrayList<>();
        List<FUsers> UserList = new ArrayList<>();

        if(!GoodsId.isEmpty()) {
            GoodsList=fGoodsMapper.selectListName(new ArrayList<>(GoodsId));
        }
        if(!UsersIds.isEmpty()) {
            UserList=fUsersMapper.selectUsersListCIds(new ArrayList<>(UsersIds));
        }
        //利用map集合键值对，将用户数据存储到map集合里面  (键：UsersId;值：UsersList)
        Map<Long, FGoods> busersMap = new HashMap<>();
        Map<Long, FUsers> busersMap1 = new HashMap<>();
        for (FGoods f : GoodsList) {
            busersMap.put(f.getId(), f);
        }
        for (FUsers f : UserList) {
            busersMap1.put(f.getUsersId(), f);
        }
        // 替换用户姓名
        //进行遍历会员列表数据
        for (FAppraise fp : fAppraises) {
            //判断商品列表的商品id是否为空
            if (fp.getGoodsid() != null) {
                //调用map集合根据当前用户的用户id来拿到用户id里面用户列表的数据
                FGoods  goods = busersMap.get(fp.getGoodsid());
                //判断拿到的用户列表数据是否为空
                if (goods != null) {
                    //将会员列表的usersName传递给会员列表的usersName
                    fp.setGoodsName(goods.getName());
                }
            }
            //判断用户列表的用户id是否为空
            if (fp.getUserid() != null) {
                //调用map集合根据当前用户的用户id来拿到用户id里面用户列表的数据
                FUsers  users = busersMap1.get(fp.getUserid());
                //判断拿到的用户列表数据是否为空
                if (users != null) {
                    //将会员列表的usersName传递给会员列表的usersName
                    fp.setUserName(users.getUsersName());
                }
            }
        }
        return fAppraises;
    }

    /**
     * 新增评价管理
     * 
     * @param fAppraise 评价管理
     * @return 结果
     */
    @Override
    public int insertFAppraise(FAppraise fAppraise)
    {
        return fAppraiseMapper.insertFAppraise(fAppraise);
    }

    /**
     * 修改评价管理
     * 
     * @param fAppraise 评价管理
     * @return 结果
     */
    @Override
    public int updateFAppraise(FAppraise fAppraise)
    {
        return fAppraiseMapper.updateFAppraise(fAppraise);
    }

    /**
     * 批量删除评价管理
     * 
     * @param reviewids 需要删除的评价管理主键
     * @return 结果
     */
    @Override
    public int deleteFAppraiseByReviewids(Long[] reviewids)
    {
        return fAppraiseMapper.deleteFAppraiseByReviewids(reviewids);
    }

    /**
     * 删除评价管理信息
     * 
     * @param reviewid 评价管理主键
     * @return 结果
     */
    @Override
    public int deleteFAppraiseByReviewid(Long reviewid)
    {
        return fAppraiseMapper.deleteFAppraiseByReviewid(reviewid);
    }
}
