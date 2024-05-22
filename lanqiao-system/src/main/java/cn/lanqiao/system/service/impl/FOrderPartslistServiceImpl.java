package cn.lanqiao.system.service.impl;

import java.util.*;

import cn.lanqiao.system.domain.FGoods;
import cn.lanqiao.system.domain.FPurchaseOrders;
import cn.lanqiao.system.mapper.FGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.lanqiao.system.mapper.FOrderPartslistMapper;
import cn.lanqiao.system.domain.FOrderPartslist;
import cn.lanqiao.system.service.IFOrderPartslistService;

/**
 * 订单明细Service业务层处理
 * 
 * @author lanqiao
 * @date 2024-05-22
 */
@Service
public class FOrderPartslistServiceImpl implements IFOrderPartslistService 
{
    @Autowired
    private FOrderPartslistMapper fOrderPartslistMapper;
    @Autowired
    private FGoodsMapper fGoodsMapper;

    /**
     * 查询订单明细
     * 
     * @param id 订单明细主键
     * @return 订单明细
     */
    @Override
    public FOrderPartslist selectFOrderPartslistById(Long id)
    {
        return fOrderPartslistMapper.selectFOrderPartslistById(id);
    }

    /**
     * 查询订单明细列表
     * 
     * @param fOrderPartslist 订单明细
     * @return 订单明细
     */
    @Override
    public List<FOrderPartslist> selectFOrderPartslistList(FOrderPartslist fOrderPartslist)
    {
        List<FOrderPartslist> fOrderPartslists = fOrderPartslistMapper.selectFOrderPartslistList(fOrderPartslist);
        Set<Long> GoodsId = new HashSet<>();

        //遍历会员管理列表数据，判断usersId是否为空，不是则将usersId存储到Set<Long> usersIds = new HashSet<>();
        for (FOrderPartslist fp : fOrderPartslists) {
            if(fp.getGoodsId() != null) {
                GoodsId.add(fp.getGoodsId());
            }
        }
        // 根据会员列表数据的usersId来获取用户users列表数据
        List<FGoods> GoodsList = new ArrayList<>();
        if(!GoodsId.isEmpty()) {
            GoodsList=fGoodsMapper.selectListName(new ArrayList<>(GoodsId));
        }
        //利用map集合键值对，将用户数据存储到map集合里面  (键：UsersId;值：UsersList)
        Map<Long, FGoods> busersMap = new HashMap<>();
        for (FGoods f : GoodsList) {
            busersMap.put(f.getId(), f);
        }
        // 替换用户姓名
        //进行遍历会员列表数据
        for (FOrderPartslist fp : fOrderPartslists) {
            //判断用户列表的用户id是否为空
            if (fp.getGoodsId() != null) {
                //调用map集合根据当前用户的用户id来拿到用户id里面用户列表的数据
                FGoods  goods = busersMap.get(fp.getGoodsId());
                //判断拿到的用户列表数据是否为空
                if (goods != null) {
                    //将会员列表的usersName传递给会员列表的usersName
                    fp.setGoods(goods);
                }
            }
        }
        return fOrderPartslists;
    }

    /**
     * 新增订单明细
     * 
     * @param fOrderPartslist 订单明细
     * @return 结果
     */
    @Override
    public int insertFOrderPartslist(FOrderPartslist fOrderPartslist)
    {
        return fOrderPartslistMapper.insertFOrderPartslist(fOrderPartslist);
    }

    /**
     * 修改订单明细
     * 
     * @param fOrderPartslist 订单明细
     * @return 结果
     */
    @Override
    public int updateFOrderPartslist(FOrderPartslist fOrderPartslist)
    {
        return fOrderPartslistMapper.updateFOrderPartslist(fOrderPartslist);
    }

    /**
     * 批量删除订单明细
     * 
     * @param ids 需要删除的订单明细主键
     * @return 结果
     */
    @Override
    public int deleteFOrderPartslistByIds(Long[] ids)
    {
        return fOrderPartslistMapper.deleteFOrderPartslistByIds(ids);
    }

    /**
     * 删除订单明细信息
     * 
     * @param id 订单明细主键
     * @return 结果
     */
    @Override
    public int deleteFOrderPartslistById(Long id)
    {
        return fOrderPartslistMapper.deleteFOrderPartslistById(id);
    }
}
