package cn.lanqiao.system.service.impl;

import java.util.*;

import cn.lanqiao.common.core.domain.entity.Category;
import cn.lanqiao.common.core.domain.entity.SysUser;
import cn.lanqiao.system.domain.FGoods;
import cn.lanqiao.system.mapper.FGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.lanqiao.system.mapper.FPurchaseOrdersMapper;
import cn.lanqiao.system.domain.FPurchaseOrders;
import cn.lanqiao.system.service.IFPurchaseOrdersService;

/**
 * 采购信息Service业务层处理
 * 
 * @author lanqiao
 * @date 2024-05-20
 */
@Service
public class FPurchaseOrdersServiceImpl implements IFPurchaseOrdersService
{
    @Autowired
    private FPurchaseOrdersMapper fPurchaseOrdersMapper;
    @Autowired
    private FGoodsMapper fGoodsMapper;
    /**
     * 查询采购信息
     * 
     * @param orderId 采购信息主键
     * @return 采购信息
     */
    @Override
    public FPurchaseOrders selectFPurchaseOrdersByOrderId(Long orderId)
    {
       return fPurchaseOrdersMapper.selectFPurchaseOrdersByOrderId(orderId);

    }

    /**
     * 查询采购信息列表
     * 
     * @param fPurchaseOrders 采购信息
     * @return 采购信息
     */
    @Override
    public List<FPurchaseOrders> selectFPurchaseOrdersList(FPurchaseOrders fPurchaseOrders)
    {
        List<FPurchaseOrders> fPurchaseOrders1 = fPurchaseOrdersMapper.selectFPurchaseOrdersList(fPurchaseOrders);
        Set<Long> GoodsId = new HashSet<>();

        //遍历会员管理列表数据，判断usersId是否为空，不是则将usersId存储到Set<Long> usersIds = new HashSet<>();
        for (FPurchaseOrders fp : fPurchaseOrders1) {
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
        for (FPurchaseOrders fp : fPurchaseOrders1) {
            //判断用户列表的用户id是否为空
            if (fp.getGoodsId() != null) {
                //调用map集合根据当前用户的用户id来拿到用户id里面用户列表的数据
                FGoods  goods = busersMap.get(fp.getGoodsId());
                //判断拿到的用户列表数据是否为空
                if (goods != null) {
                    //将会员列表的usersName传递给会员列表的usersName
                    fp.setGoodsName(goods.getName());
                }
            }
        }
        return fPurchaseOrders1;
    }

    /**
     * 新增采购信息
     * 
     * @param fPurchaseOrders 采购信息
     * @return 结果
     */
    @Override
    public int insertFPurchaseOrders(FPurchaseOrders fPurchaseOrders)
    {
        return fPurchaseOrdersMapper.insertFPurchaseOrders(fPurchaseOrders);
    }

    /**
     * 修改采购信息
     * 
     * @param fPurchaseOrders 采购信息
     * @return 结果
     */
    @Override
    public int updateFPurchaseOrders(FPurchaseOrders fPurchaseOrders)
    {
        return fPurchaseOrdersMapper.updateFPurchaseOrders(fPurchaseOrders);
    }

    /**
     * 批量删除采购信息
     * 
     * @param orderIds 需要删除的采购信息主键
     * @return 结果
     */
    @Override
    public int deleteFPurchaseOrdersByOrderIds(Long[] orderIds)
    {
        return fPurchaseOrdersMapper.deleteFPurchaseOrdersByOrderIds(orderIds);
    }

    /**
     * 删除采购信息信息
     * 
     * @param orderId 采购信息主键
     * @return 结果
     */
    @Override
    public int deleteFPurchaseOrdersByOrderId(Long orderId)
    {
        return fPurchaseOrdersMapper.deleteFPurchaseOrdersByOrderId(orderId);
    }

}
