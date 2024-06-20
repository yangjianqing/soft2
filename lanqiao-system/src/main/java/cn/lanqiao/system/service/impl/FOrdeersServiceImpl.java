package cn.lanqiao.system.service.impl;

import java.math.BigDecimal;
import java.util.*;

import cn.lanqiao.common.constant.CacheConstants;
import cn.lanqiao.common.core.domain.entity.SysUser;
import cn.lanqiao.common.core.redis.RedisCache;
import cn.lanqiao.common.utils.DateUtils;
import cn.lanqiao.common.utils.OrderNumberGenerator;
import cn.lanqiao.system.domain.*;
import cn.lanqiao.system.domain.vo.Settlement;
import cn.lanqiao.system.mapper.*;
import cn.lanqiao.system.service.IFGoodsService;
import cn.lanqiao.system.service.IFUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    @Autowired
    private IFGoodsService fGoodsService;

    @Autowired
    private FUsersMapper fUsersMapper;

    @Autowired
    private FOrderPartslistMapper fOrderPartslistMapper;

    @Autowired
    private IFShoppingCartServiceImpl ifShoppingCartService;

    @Autowired
    private IFOrdeersService fOrdeersService;

    @Autowired
    private FOrderPartslistServiceImpl fOrderPartslistService;

    @Autowired
    private IFUsersService ifUsersService;

    @Autowired
    private RedisCache redisCache;
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
        // 创建存储用户地址id的存储器
        Set<Long> addressIds = new HashSet<>();
        // 创建存储用户id的存储器
        Set<Long> usersIds = new HashSet<>();
        // 创建存储配送员id的存储器
        Set<Long> SysuserId = new HashSet<>();

        // 遍历订单管理列表数据
        for (FOrdeers fOrdeers2 : fOrdeers1) {
            if (fOrdeers2.getOrdersSysuserId() == null) {
                if (fOrdeers2.getOrdersUsersId() != null) {
                    usersIds.add(fOrdeers2.getOrdersUsersId());
                }
            } else {
                //判断addressIds是否为空，不是则将addressIds存储到Set<Long> addressIds = new HashSet<>();
                if (fOrdeers2.getOrdersUsersId() != null) {
                    addressIds.add(fOrdeers2.getOrdersUsersId());
                }
            }
            //判断SysuserId是否为空，不是则将SysuserId存储到Set<Long> SysuserId = new HashSet<>();
            if (fOrdeers2.getOrdersSysuserId() != null) {
                SysuserId.add(fOrdeers2.getOrdersSysuserId());
            }
        }

        // 根据订单列表数据的AddsId来获取用户地址列表的数据
        List<FUsers> UsersList = new ArrayList<>();
        if (!usersIds.isEmpty()) {
            UsersList = fUsersMapper.selectUsersListCIds(new ArrayList<>(usersIds));
        }
        // 根据订单列表数据的AddsId来获取用户地址列表的数据
        List<FAddress> AddreessList = new ArrayList<>();
        if (!addressIds.isEmpty()) {
            AddreessList = addressMapper.selectaddressIdByType(new ArrayList<>(addressIds));
        }
        List<SysUser> SysuserIdList = new ArrayList<>();
        if (!SysuserId.isEmpty()) {
            SysuserIdList = sysUserMapper.selectUsersListuserIds(new ArrayList<>(SysuserId));
        }

        Map<Long, FUsers> UsersMap = new HashMap<>();
        for (FUsers f : UsersList) {
            UsersMap.put(f.getUsersId(), f);
        }
        Map<Long, FAddress> addressMap = new HashMap<>();
        for (FAddress f : AddreessList) {
            addressMap.put(f.getAddressId(), f);
        }
        Map<Long, SysUser> susUserMap = new HashMap<>();
        for (SysUser sysUser : SysuserIdList) {
            susUserMap.put(sysUser.getUserId(), sysUser);
        }

        // 进行遍历订单列表数据
        for (FOrdeers fm : fOrdeers1) {
            if (fm.getOrdersSysuserId() == null) {
                if (fm.getOrdersUsersId() != null) {
                    //调用map集合根据当前订单的配送员id来拿到员工人员表配送员id里面的配送员名称数据
                    FUsers fUsers = UsersMap.get(fm.getOrdersUsersId());
                    //判断拿到的员工人员列表数据是否为空
                    if (fUsers != null) {
                        //将员工人员列表的配送员名称NickName传递给订单列表的SysuserName
                        fm.setfUsers(fUsers);
                    }
                }
            } else {
                if (fm.getOrdersUsersId() != null) {
                    //调用map集合根据当前订单的用户地址id来拿到用户地址id里面用户地址列表的数据
                    FAddress fAddress = addressMap.get(fm.getOrdersUsersId());
                    //判断拿到的用户地址列表数据是否为空
                    if (fAddress != null) {
                        //将用户地址列表数据传递给订单列表的Address对象
                        fm.setAddress(fAddress);
                    }
                }
            }
            // 替换配送员姓名
            if (fm.getOrdersSysuserId() != null) {
                //调用map集合根据当前订单的配送员id来拿到员工人员表配送员id里面的配送员名称数据
                SysUser sysUser = susUserMap.get(fm.getOrdersSysuserId());
                //判断拿到的员工人员列表数据是否为空
                if (sysUser != null) {
                    //将员工人员列表的配送员名称NickName传递给订单列表的SysuserName
                    fm.setOrdersSysuserName(sysUser.getNickName());
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

    /**
     * 电脑端结算
     *
     * @param productsInCart 订单集合
     */
    @Override
    public void settle(String memberPhone, BigDecimal totalPrice, BigDecimal memberJian, List<FGoods> productsInCart)
    {
        if (productsInCart.size() != 0) {
            // 设置 memberJian 默认值为 0
            if (memberJian == null) {
                memberJian = new BigDecimal(0);
            }
            // TODO: 进行新增订单操作
            //创建订单编号
            String OrderNu = OrderNumberGenerator.generateOrderNumber();
            //根据用户电话查询用户数据，获取用户id
            FUsers fUsers = ifUsersService.selectUsersusersPhone(memberPhone);
            Long usersId = null;//定义usersId的默认值
            if (fUsers != null && fUsers.getUsersId() != null) {//判断查询是否为空
                usersId = fUsers.getUsersId();//给usersId赋值
            }
            //创建订单对象传值,调用新增订单
            fOrdeersService.insertFOrdeers(new FOrdeers(OrderNu,usersId,2L,0L,2L));
            // TODO: 进行新增订单明细操作
            for (FGoods fGood : productsInCart) {
                //利用前端传递的商品编号(fGood.getId())，查询商品信息
                FGoods fGoods1 = fGoodsService.selectGoodsList(fGood.getId());
                //创建订单详情传值,调用新增订单详情
                fOrderPartslistService.insertFOrderPartslist(new FOrderPartslist(fGoods1.getId(),OrderNu,fGood.getQuantity()));
                //更新商品数量
                fGoods1.setNum(fGoods1.getNum()-fGood.getQuantity());
                fGoodsService.updateFGoods(fGoods1);
            }
            //更新会员数据
            fUsers.setMemberTotal(fUsers.getMemberTotal().add(totalPrice).subtract(memberJian));
            ifUsersService.updateFUsers(fUsers);
            //更新会员等级为高等会员
            if (fUsers.getMemberTotal().compareTo(new BigDecimal(100)) >= 0 && fUsers.getMemberGrade() != 1L) {
                fUsers.setMemberGrade(1L);
                ifUsersService.updateFUsers(fUsers);
            }
        }
    }

    /**
     * 手机端结算
     *
     * @param usersPhone 用户电话号码
     * @param ordersPayMethod 支付方式
     * @param ordersPayStatuds 支付状态
     * @param ordersRemark 订单备注信息
     * @param settlement 手机端结算对象数据
     */
    private int deliveryIndex = 0; // 将deliveryIndex定义为类的成员变量，以保持索引状态
    @Override
    public void insertShopping(Settlement settlement)
    {
        if (settlement != null && settlement.getUsersPhone() != null) {
            List<FGoods> GoodsList = ifShoppingCartService.selectShopData(settlement.getUsersPhone());
            if (GoodsList != null) {
                // TODO: 进行新增订单操作
                FUsers fUsers = fUsersMapper.selectUsersusersPhone(settlement.getUsersPhone());//根据电话查询用户id
                List<SysUser> sysUsers = sysUserMapper.selectSysUserAll();//查询全部配送员信息
                if (deliveryIndex >= sysUsers.size()) {
                    deliveryIndex = 0; // 如果超出配送员数量，重置索引
                }
                SysUser sysUser = sysUsers.get(deliveryIndex);//根据索引取配送员信息
                //创建订单对象传值,调用新增订单
                fOrdeersService.insertFOrdeers(new FOrdeers(settlement.getOrdersNumber(),fUsers.getUsersId(),sysUser.getUserId(),settlement.getOrdersPayMethod(),0L,0L,settlement.getOrdersRemark()));
                deliveryIndex++; // 为下一次调用准备索引

                // TODO: 进行新增订单明细操作
                for (FGoods fGoods : GoodsList) {
                    //利用前端传递的商品编号(fGood.getId()) 查询商品信息
                    FGoods fGoods1 = fGoodsService.selectGoodsList(fGoods.getCoding());
                    //创建订单详情传值,调用新增订单详情
                    fOrderPartslistService.insertFOrderPartslist(new FOrderPartslist(fGoods.getId(),settlement.getOrdersNumber(),fGoods.getQuantity()));
                    //更新商品数量
                    fGoods1.setNum(fGoods1.getNum()-fGoods.getQuantity());
                    fGoodsService.updateFGoods(fGoods1);
                }

                // TODO: 进行清空redis购物车数据操作
                // 生成唯一标识 Key
                String verKey = CacheConstants.Query_Shopping_KEY + settlement.getUsersPhone();
                redisCache.deleteObject(verKey);
            }
        }
    }

    /**
     * 手机端订单状态修改接口
     * @param usersPhone 手机电话
     * @param ordersNumber 商品条码
     *
     */
    @Override
    public void updateSettlement(String usersPhone, String ordersNumber) {
        if (usersPhone != null) {
            FUsers fUsers = fUsersMapper.selectUsersusersPhone(usersPhone);
            if (fUsers != null && ordersNumber != null) {
                fOrdeersMapper.updateOrdersStatus(ordersNumber);
            }
        }
    }

    /**
     * 手机端用户全部订单
     *
     * @param usersPhone 用户电话号码
     */
    @Override
    public List<FOrderPartslist> selectOrders(String usersPhone) {
        FUsers fUsers = fUsersMapper.selectUsersusersPhone(usersPhone);
        List<FOrdeers> fOrdeers = fOrdeersMapper.selectOrders(fUsers.getUsersId());
        List<FOrderPartslist> fOrderPartslist = new ArrayList<>();
        for (FOrdeers fOrdeer : fOrdeers) {
            List<FOrderPartslist> fOrderPartslists = fOrderPartslistMapper.selectFOrderPartslist(fOrdeer.getOrdersNumber());
            fOrderPartslist.addAll(fOrderPartslists);
        }
        return fOrderPartslist;
    }

    /**
     * 手机端用户待付款订单
     *
     * @param usersPhone 用户电话号码
     */
    @Override
    public List<FOrderPartslist> selectpaymentOrders(String usersPhone) {
        FUsers fUsers = fUsersMapper.selectUsersusersPhone(usersPhone);
        List<FOrdeers> fOrdeers = fOrdeersMapper.selectpaymentOrders(fUsers.getUsersId());
        List<FOrderPartslist> fOrderPartslist = new ArrayList<>();
        for (FOrdeers fOrdeer : fOrdeers) {
            List<FOrderPartslist> fOrderPartslists = fOrderPartslistMapper.selectFOrderPartslist(fOrdeer.getOrdersNumber());
            fOrderPartslist.addAll(fOrderPartslists);
        }
        return fOrderPartslist;
    }


    /**
     * 手机端用户待发货订单
     *
     * @param usersPhone 用户电话号码
     */
    @Override
    public List<FOrderPartslist> selectwaitingOrders(String usersPhone) {
        FUsers fUsers = fUsersMapper.selectUsersusersPhone(usersPhone);
        List<FOrdeers> fOrdeers = fOrdeersMapper.selectwaitingOrders(fUsers.getUsersId());
        List<FOrderPartslist> fOrderPartslist = new ArrayList<>();
        for (FOrdeers fOrdeer : fOrdeers) {
            List<FOrderPartslist> fOrderPartslists = fOrderPartslistMapper.selectFOrderPartslist(fOrdeer.getOrdersNumber());
            fOrderPartslist.addAll(fOrderPartslists);
        }
        return fOrderPartslist;
    }

    /**
     * 手机端用户待收货订单
     *
     * @param usersPhone 用户电话号码
     */
    @Override
    public List<FOrderPartslist> selectReceiveOrders(String usersPhone) {
        FUsers fUsers = fUsersMapper.selectUsersusersPhone(usersPhone);
        List<FOrdeers> fOrdeers = fOrdeersMapper.selectReceiveOrders(fUsers.getUsersId());
        List<FOrderPartslist> fOrderPartslist = new ArrayList<>();
        for (FOrdeers fOrdeer : fOrdeers) {
            List<FOrderPartslist> fOrderPartslists = fOrderPartslistMapper.selectFOrderPartslist(fOrdeer.getOrdersNumber());
            fOrderPartslist.addAll(fOrderPartslists);
        }
        return fOrderPartslist;
    }

    /**
     * 手机端用户待评价订单
     *
     * @param usersPhone 用户电话号码
     */
    @Override
    public List<FOrderPartslist> selectevaluateOrders(String usersPhone) {
        FUsers fUsers = fUsersMapper.selectUsersusersPhone(usersPhone);
        List<FOrdeers> fOrdeers = fOrdeersMapper.selectevaluateOrders(fUsers.getUsersId());
        List<FOrderPartslist> fOrderPartslist = new ArrayList<>();
        for (FOrdeers fOrdeer : fOrdeers) {
            List<FOrderPartslist> fOrderPartslists = fOrderPartslistMapper.selectFOrderPartslist(fOrdeer.getOrdersNumber());
            fOrderPartslist.addAll(fOrderPartslists);
        }
        return fOrderPartslist;
    }
}