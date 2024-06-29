package cn.lanqiao.system.service.impl;

import java.math.BigDecimal;
import java.util.*;

import cn.lanqiao.common.constant.CacheConstants;
import cn.lanqiao.common.constant.OrdeersConstant;
import cn.lanqiao.common.core.domain.entity.SysUser;
import cn.lanqiao.common.core.redis.RedisCache;
import cn.lanqiao.common.utils.DateUtils;
import cn.lanqiao.common.utils.OrderNumberGenerator;
import cn.lanqiao.system.domain.*;
import cn.lanqiao.system.domain.argument.FormData;
import cn.lanqiao.system.domain.argument.OrdersStatus;
import cn.lanqiao.system.domain.argument.Settlement;
import cn.lanqiao.system.domain.argument.ordersPayMethod;
import cn.lanqiao.system.mapper.*;
import cn.lanqiao.system.service.IFGoodsService;
import cn.lanqiao.system.service.IFShoppingCartService;
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
    private IFShoppingCartService ifShoppingCartService;

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
    public FOrdeers selectFOrdeersByOrdersId(Long ordersId) {
        return fOrdeersMapper.selectFOrdeersByOrdersId(ordersId);
    }

    /**
     * 查询订单管理列表
     *
     * @param fOrdeers 订单管理
     * @return 订单管理
     */
    @Override
    public List<FOrdeers> selectFOrdeersList(FOrdeers fOrdeers) {
        List<FOrdeers> fOrdeers1 = fOrdeersMapper.selectFOrdeersList(fOrdeers);
        // 创建存储地址id的存储器
        Set<Long> addressIds = new HashSet<>();
        // 创建存储会员用户id的存储器
        Set<Long> usersIds = new HashSet<>();
        // 创建存储配送员id的存储器
        Set<Long> SysuserId = new HashSet<>();

        // 遍历订单管理列表数据
        for (FOrdeers fOrdeers2 : fOrdeers1) {
            //判断配送员id是否为null
            if (fOrdeers2.getOrdersSysuserId() == null) {
                //判断会员用户id是否为null
                if (fOrdeers2.getOrdersUsersId() != null) {
                    usersIds.add(fOrdeers2.getOrdersUsersId());//将会员用户id存入set集合
                }
            } else {
                //判断addressIds是否为空，不是则将addressIds存储到Set<Long> addressIds = new HashSet<>();
                if (fOrdeers2.getOrdersUsersId() != null) {
                    addressIds.add(fOrdeers2.getOrdersUsersId());//将地址id存入set集合
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

        // 进行遍历订单列表数据,添加值
        for (FOrdeers fm : fOrdeers1) {
            // TODO: 电脑端订单数据 --- 无配送员
            if (fm.getOrdersSysuserId() == null) { //判断是否有配送员id
                // TODO: 电脑端有会员结算类订单
                //判断会员id是否为null
                if (fm.getOrdersUsersId() != null) {
                    //调用map集合根据当前订单的配送员id来拿到员工人员表配送员id里面的配送员名称数据
                    FUsers fUsers = UsersMap.get(fm.getOrdersUsersId());
                    //判断拿到的员工人员列表数据是否为空
                    if (fUsers != null) {
                        //将员工人员列表的配送员名称NickName传递给订单列表的SysuserName
                        fm.setOrdersUsersName(fUsers.getUsersName());
                        fm.setUsersPhone(fUsers.getUsersPhone());
                        fm.setOrdersSysuserName(OrdeersConstant.Order_Type_Status);
                        fm.setAddressDetail(OrdeersConstant.Order_Type_Status);
                        fm.setOrdersRemark(OrdeersConstant.Order_Offline_Purchase);
                    }
                } else {
                    // TODO: 电脑端无会员结算类订单 --- 游客
                    fm.setOrdersUsersName(OrdeersConstant.Order_UsersName_Status);
                    fm.setUsersPhone(OrdeersConstant.Order_Type_Status);
                    fm.setOrdersSysuserName(OrdeersConstant.Order_Type_Status);
                    fm.setAddressDetail(OrdeersConstant.Order_Type_Status);
                    fm.setOrdersRemark(OrdeersConstant.Order_Offline_Purchase);
                }
            } else {
                // TODO: 手机端订单数据 --- 有配送员
                //判断会员地址id是否为null
                if (fm.getOrdersUsersId() != null) {
                    // TODO: 替换手机端地址会员名称信息
                    //调用map集合根据当前订单的用户地址id来拿到用户地址id里面用户地址列表的数据
                    FAddress fAddress = addressMap.get(fm.getOrdersUsersId());
                    //判断拿到的用户地址列表数据是否为空
                    if (fAddress != null) {
                        //将用户地址列表数据传递给订单列表的Address对象
                        fm.setOrdersUsersName(fAddress.getAddressName());
                        fm.setUsersPhone(fAddress.getAddressPhone());
                        fm.setAddressDetail(fAddress.getAddressDetail());
                        if (fm.getOrdersRemark() == null) {
                            fm.setOrdersRemark(OrdeersConstant.Order_Type_Status);
                        }
                    }
                    // TODO: 替换配送员姓名
                    //调用map集合根据当前订单的配送员id来拿到员工人员表配送员id里面的配送员名称数据
                    SysUser sysUser = susUserMap.get(fm.getOrdersSysuserId());
                    //判断拿到的员工人员列表数据是否为空
                    if (sysUser != null) {
                        //将员工人员列表的配送员名称NickName传递给订单列表的SysuserName
                        fm.setOrdersSysuserName(sysUser.getNickName());
                    }
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
    public int insertFOrdeers(FOrdeers fOrdeers) {
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
    public int updateFOrdeers(FOrdeers fOrdeers) {
        return fOrdeersMapper.updateFOrdeers(fOrdeers);
    }

    /**
     * 批量删除订单管理
     *
     * @param ordersIds 需要删除的订单管理主键
     * @return 结果
     */
    @Override
    public int deleteFOrdeersByOrdersIds(Long[] ordersIds) {
        return fOrdeersMapper.deleteFOrdeersByOrdersIds(ordersIds);
    }

    /**
     * 删除订单管理信息
     *
     * @param ordersId 订单管理主键
     * @return 结果
     */
    @Override
    public int deleteFOrdeersByOrdersId(Long ordersId) {
        return fOrdeersMapper.deleteFOrdeersByOrdersId(ordersId);
    }

    /**
     * 电脑端结算
     *
     * @param formData 购物车数据对象
     */
    @Override
    public int settle(FormData formData)
    {
        if (formData.getOrdersPayMethod() == null || formData.getProductsInCart().size() == 0) // 检查必要字段是否为空
        {
            return 0;
        }

        // TODO: 进行新增订单操作
        //创建订单编号
        String OrderNu = OrderNumberGenerator.generateOrderNumber();
        //根据用户电话查询用户数据，获取用户id
        FUsers fUsers = ifUsersService.selectUsersusersPhone(formData.getMemberPhone());
        if (fUsers == null) {//判断是否为游客支付
            //创建订单对象传值,调用新增订单
            fOrdeersService.insertFOrdeers(new FOrdeers(OrderNu, null, Long.parseLong(formData.getOrdersPayMethod()), 1L, 3L));
        } else {
            // 设置 memberJian 默认值为 0
            BigDecimal memberJian = formData.getMemberJian();
            if (memberJian == null) {
                memberJian = new BigDecimal(0);
            }

            //创建订单对象传值,调用新增订单
            fOrdeersService.insertFOrdeers(new FOrdeers(OrderNu, fUsers.getUsersId(), Long.parseLong(formData.getOrdersPayMethod()), 1L, 3L));
            //更新会员数据（会员积分+总金额积分-抵扣积分)
            fUsers.setMemberTotal(fUsers.getMemberTotal().add(formData.getTotalPrice()).subtract(memberJian));
            //更新会员等级为高等会员
            if (fUsers.getMemberTotal().compareTo(new BigDecimal(100)) >= 0 && fUsers.getMemberGrade() != 1L) {
                fUsers.setMemberGrade(1L);
            }
            //更新会员数据
            ifUsersService.updateFUsers(fUsers);
        }

        // TODO: 进行新增订单明细操作
        for (FGoods fGood : formData.getProductsInCart()) {
            //利用前端传递的商品编号(fGood.getId())，查询商品信息
            FGoods fGoods1 = fGoodsService.selectGoodsList(String.valueOf(fGood.getId()));
            //创建订单详情传值,调用新增订单详情
            fOrderPartslistService.insertFOrderPartslist(new FOrderPartslist(fGoods1.getId(), OrderNu, fGood.getQuantity()));
            //更新商品数量
            fGoods1.setNum(fGoods1.getNum() - fGood.getQuantity());
            fGoodsService.updateFGoods(fGoods1);
        }
        return 1;
    }

    /**
     * 手机端结算
     *
     * @param settlement 手机端结算对象数据
     *
     */
    private int deliveryIndex = 0; // 将deliveryIndex定义为类的成员变量，以保持索引状态
    @Override
    public int insertShopping(Settlement settlement)
    {
        if (settlement.getUsersPhone() == null || settlement.getOrdersPayStatuds() == null)
        {
            return 0;
        }

        FUsers fUsers = fUsersMapper.selectUsersusersPhone(settlement.getUsersPhone());//根据电话查询用户id
        if (fUsers == null) {
            return 0;
        }

        List<SysUser> sysUsers = sysUserMapper.selectSysUserAll();//查询全部配送员信息
        if (sysUsers.isEmpty()) {
            return 0;
        }

        if (deliveryIndex >= sysUsers.size()) {
            deliveryIndex = 0; // 如果超出配送员数量，重置索引
        }
        SysUser sysUser = sysUsers.get(deliveryIndex);//根据索引取配送员信息

        if (settlement.getOrdersPayStatuds() == 1L) {
            // TODO: 购物车页面 (结算全部商品，购物车页面支付宝结账成功)
            // 进行新增订单操作 (创建订单对象传值,调用新增订单)
            fOrdeersService.insertFOrdeers(new FOrdeers(settlement.getOrdersNumber(), fUsers.getUsersId(),
                    sysUser.getUserId(), settlement.getOrdersPayMethod(), 1L, 2L,
                    settlement.getOrdersRemark()));
            deliveryIndex++; // 为下一次调用准备索引
            String verKey = CacheConstants.Query_Shopping_KEY + settlement.getUsersPhone();// 生成唯一标识 Key
            Set<String> coding = new HashSet<>();//创建存商品编码集合

            List<FGoods> GoodsList = ifShoppingCartService.selectShopData(settlement.getUsersPhone(),1L);
            if (GoodsList.isEmpty()) {
                return 0;
            }

            // 进行新增订单明细操作
            for (FGoods fGoods : GoodsList) {
                fOrderPartslistService.insertFOrderPartslist(new FOrderPartslist(fGoods.getId(),//创建订单详情传值,调用新增订单详情
                        settlement.getOrdersNumber(), fGoods.getQuantity(), 2L));
                fGoods.setNum(fGoods.getNum() - fGoods.getQuantity());//更新商品数量
                fGoodsService.updateFGoods(fGoods);//更新商品数据
                coding.add(fGoods.getCoding());
            }

            redisCache.deleteFromMap(verKey, coding);// 删除已处理的商品数据
            return 1;
        }

        if (settlement.getOrdersPayStatuds() == 0L && !settlement.getCoDings().isEmpty()) {
            // TODO: 待支付页面 (结算一个或多个商品)
            // 进行新增订单操作 (创建订单对象传值,调用新增订单)
            fOrdeersService.insertFOrdeers(new FOrdeers(OrderNumberGenerator.generateOrderNumber(), fUsers.getUsersId(),
                    sysUser.getUserId(), settlement.getOrdersPayMethod(), 1L, 2L,
                    settlement.getOrdersRemark()));
            deliveryIndex++; // 为下一次调用准备索引

            //根据商品编码去redis购物车获取数据
            List<FGoods> fGoods1 = ifShoppingCartService.selectShopDataCoDings(settlement.getUsersPhone(),settlement.getCoDings());
            if (fGoods1.isEmpty()) {
                return 0;
            }

            // 进行新增订单明细操作
            for (FGoods fGoods : fGoods1) {
                fOrderPartslistService.insertFOrderPartslist(new FOrderPartslist(fGoods.getId(),//创建订单详情传值,调用新增订单详情
                        settlement.getOrdersNumber(), fGoods.getQuantity(), 2L));
                fGoods.setNum(fGoods.getNum() - fGoods.getQuantity());//更新商品数量
                fGoodsService.updateFGoods(fGoods);//更新商品数据
            }

            return 1;
        }

        return 1;
    }

    /**
     * 手机端用户全部订单
     *
     * @param usersPhone 用户电话号码
     */
    @Override
    public List<FOrderPartslist> selectOrders(String usersPhone)
    {
        FUsers fUsers = fUsersMapper.selectUsersusersPhone(usersPhone);
        if (fUsers == null) {
            return Collections.emptyList(); // 如果用户不存在，返回空列表
        }

        List<FOrdeers> fOrdeers = fOrdeersMapper.selectOrders(fUsers.getUsersId());
        if (fOrdeers.size() == 0) {
            return Collections.emptyList(); // 如果集合为0，返回空列表
        }

        List<FOrderPartslist> fOrderPartslist = new ArrayList<>();//创建手机端app订单集合数据
        for (FOrdeers fOrdeer : fOrdeers) {
            List<FOrderPartslist> fOrderPartslists = fOrderPartslistMapper.selectFOrderPartslist(fOrdeer.getOrdersNumber());//三表联查，查询数据
            fOrderPartslist.addAll(fOrderPartslists);
        }

        return fOrderPartslist;
    }

    /**
     * 手机端订单 + 订单状态修改接口
     * @param orderstatus 订单状态接口数据
     *
     */
    @Override
    public int updateOrdersStatus(OrdersStatus orderstatus)
    {
        if (orderstatus.getUsersPhone() == null) {
            return 0; // 提前返回，参数为空时直接返回失败
        }

        FUsers fUsers = fUsersMapper.selectUsersusersPhone(orderstatus.getUsersPhone());
        if (fUsers == null) {
            return 0; // 如果用户不存在，直接返回失败
        }

        fOrderPartslistMapper.updateOrdersStatus(orderstatus.getPartsId());

        FOrderPartslist fOrderPartslist = fOrderPartslistMapper.selectFOrderPartslistById(orderstatus.getPartsId());
        if (fOrderPartslist == null) {
            return 0;
        }

        int i = fOrdeersMapper.updateFOrdeersName(fOrderPartslist.getOrderId());
        if (i > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}