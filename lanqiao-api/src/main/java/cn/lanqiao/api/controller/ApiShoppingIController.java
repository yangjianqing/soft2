package cn.lanqiao.api.controller;

import cn.lanqiao.common.annotation.Log;
import cn.lanqiao.common.constant.CacheConstants;
import cn.lanqiao.common.constant.Constants;
import cn.lanqiao.common.core.controller.BaseController;
import cn.lanqiao.common.core.domain.AjaxResult;
import cn.lanqiao.common.core.domain.entity.Category;
import cn.lanqiao.common.core.page.TableDataInfo;
import cn.lanqiao.common.core.redis.RedisCache;
import cn.lanqiao.common.enums.BusinessType;
import cn.lanqiao.common.utils.*;
import cn.lanqiao.common.utils.uuid.IdUtils;

//import cn.lanqiao.system.Category;
import cn.lanqiao.framework.web.service.TokenService;
import cn.lanqiao.system.domain.*;
import cn.lanqiao.system.domain.argument.*;
import cn.lanqiao.system.mapper.FAddressMapper;
import cn.lanqiao.system.service.*;
import cn.lanqiao.system.service.impl.IFShoppingCartServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;
@Api
@RestController
@RequestMapping("/api/fresh")
public class ApiShoppingIController extends BaseController {
    @Autowired
    private IFGoodsService fGoodsService;
    @Autowired
    private IFOrdeersService fOrdeersService;
    @Autowired
    private IFOrderPartslistService fOrderPartslistService;
    @Autowired
    private IFUsersService ifUsersService;
    @Autowired
    private ICategoryService ifCategoryService;
    @Autowired
    private IFShoppingCartServiceImpl ifShoppingCartService;
    @Autowired
    private IFAppraiseService fAppraiseService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private FadvertisementService fadvertisementService;
    @Autowired
    private FAddressMapper fAddressMapper;
    @Autowired
    private IFAddressService fAddressService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询商品列表
     */
    @ApiOperation("查询商品")
    @GetMapping("/goodsList")
    public TableDataInfo list(FGoods fGoods)
    {
        startPage();
        List<FGoods> list = fGoodsService.selectFGoodsList(fGoods);
        return getDataTable(list);
    }
    /**
     * 查询商品列表2
     */
    @ApiOperation("按大分类查询商品")
    @GetMapping("/goodsList2/{deptId}")
    public AjaxResult list2( @PathVariable("deptId") Long  fGoods)
    {
        startPage();
        List<FGoods> fGoods1 = fGoodsService.selectFGoodsByParentId(fGoods);
        return AjaxResult.success().put("deptId",fGoods1);
    }

    /**
     * 查询分类父类
      * @param category
     * @return
     */
    @ApiOperation("查询父类 ")
    @GetMapping("/categoryList")
    public TableDataInfo list(Category category)
    {
        startPage();
        List<Category> list = ifCategoryService.selectMacroclassification(category);
        return getDataTable(list);
    }

    /**
     * 查询订单管理列表
     */
    @ApiOperation("查询订单管理列表")
    @GetMapping("/ordeersList")
    public TableDataInfo ordeersList(FOrdeers fOrdeers)
    {
        startPage();
        List<FOrdeers> list = fOrdeersService.selectFOrdeersList(fOrdeers);
        return getDataTable(list);
    }

    /**
     * 查询订单明细列表
     *
     */
    @ApiOperation("查询订单明细列表")
    @GetMapping("/orderPartsList")
    public TableDataInfo orderPartsList(FOrderPartslist fOrderPartslist)
    {
        startPage();
        List<FOrderPartslist> list = fOrderPartslistService.selectFOrderPartslistList(fOrderPartslist);
        return getDataTable(list);
    }

    /**
     * 查询超盒算
     */
    @ApiOperation("查询超盒算")
    @GetMapping("/favorableList")
    public TableDataInfo selectFGoodsByFavorable(FGoods fGoods)
    {
        startPage();
        List<FGoods> favorableList = fGoodsService.selectFGoodsByFavorable(fGoods);
        return getDataTable(favorableList);
    }

    /**
     * 分类图片
     */
    @ApiOperation("查询分类图片")
    @GetMapping("/selectPicture")
    public TableDataInfo selectPicture(Category category)
    {
        startPage();
        List<Category> picture = ifCategoryService.selectPicture(category);
        return getDataTable(picture);
    }

    /**
     * 查询推荐列表、生鲜列表、10元商品列表
     */
    @ApiOperation("查询推荐列表")
    @GetMapping("/recommendedList/{sortNum}")
    public TableDataInfo recommendedList(@PathVariable("sortNum") int sortNum, FGoods fGoods)
    {
        startPage();
        List<FGoods> fGoods2=fGoodsService.recommendedList(sortNum,fGoods);
        return getDataTable(fGoods2);
    }

    /**
     * 查询广告数据
     *
     */
     @ApiOperation("查询广告数据")
     @GetMapping("/selectFadvertisementList")
     public AjaxResult selectFadvertisementList()
     {
         try {
             List<Fadvertisement> fadvertisements = fadvertisementService.selectFadvertisementList();
             return AjaxResult.success(fadvertisements);
         } catch (Exception e) {
             e.printStackTrace();
             return AjaxResult.error("系统异常");
         }
     }

    /**
     * 根据商品名称查询
     */
    @ApiOperation("根据商品名称查询")
    @GetMapping("/goods/{goodsName}")
    public AjaxResult goodsName( @PathVariable("goodsName") String goodsName)
    {
        startPage();
        List<FGoods> fGoodsName = fGoodsService.selectMapGoodsName(goodsName);
        return AjaxResult.success().put("goodsName",fGoodsName);
    }

    /**
     * 根据商品id查询
     * @param goodsListCoding
     * @return
     */
    @ApiOperation("根据商品id查询")
    @GetMapping("/goodsId/{id}")
    public AjaxResult goodsListCoding( @PathVariable("id")Long goodsListCoding)
    {
        startPage();
        FGoods fGoodsName = fGoodsService.selectFGoodsById(goodsListCoding);
        return AjaxResult.success().put("id",fGoodsName);
    }

    /**
     * 新增评价管理
     */
    @ApiOperation("根据id新增评价")
    @Log(title = "评价管理", businessType = BusinessType.INSERT)
    @PostMapping("/insertFAppraise")
    public AjaxResult add(@RequestBody FAppraise fAppraise)
    {
        try {
            return toAjax(fAppraiseService.insertFAppraise(fAppraise));
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }

    /**
     * 查询下单人数
     */
    @ApiOperation("下单人数")
    @GetMapping("/peopleNum")
    public TableDataInfo listPeopleNum(FGoods fGoods)
    {
        startPage();
        List<FGoods> list = fGoodsService.selectPeopleNum(fGoods);
        return getDataTable(list);
    }

    /**
     * 根据电话号码获取用户手机app会员信息
     * @param usersPhone 电话号码
     *
     */
    @ApiOperation("手机端个人详情页面接口")
    @GetMapping("/getUsers/{usersPhone}")
    public AjaxResult getUsers(@PathVariable("usersPhone") String usersPhone)
    {
        try {
            return AjaxResult.success().put("users",ifUsersService.selectUsersusersPhone(usersPhone));
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }

    /**
     * 查询地址
     * @param usersPhone 电话号码
     *
     */
    @ApiOperation("手机端个人地址详情页面接口")
    @GetMapping("/selectUsersAddressList/{usersPhone}")
    public AjaxResult selectUsersAddressList(@PathVariable("usersPhone") String usersPhone)
    {
        try {
            FUsers fUsers = ifUsersService.selectUsersusersPhone(usersPhone);
            List<FAddress> fAddresses = fAddressMapper.selectUsersIdByType(String.valueOf(fUsers.getUsersId()));
            return AjaxResult.success(fAddresses);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }

    /**
     * 根据id查询地址
     * @param addressId 地址id
     *
     */
    @ApiOperation("手机端个人地址详情页面接口")
    @GetMapping("/selectUsersAddress/{addressId}")
    public AjaxResult selectUsersAddress(@PathVariable("addressId") Long addressId)
    {
        try {
            FAddress fAddress = fAddressMapper.selectFAddressByAddressId(addressId);
            if (fAddress == null) {
                return AjaxResult.error("查无此地址信息");
            } else {
                return AjaxResult.success(fAddress);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }

    /**
     * 新增地址
     */
    @ApiOperation("手机端个人地址详情新增地址接口")
    @PostMapping("/addUsersAddressList")
    public AjaxResult addUsersAddressList(@RequestBody FAddress fAddress)
    {
        try {
            return toAjax(fAddressService.insertFAddress(fAddress));
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }

    /**
     * 修改地址
     */
    @ApiOperation("手机端个人地址详情修改地址接口")
    @PutMapping("/editUsersAddressList")
    public AjaxResult editUsersAddressList(@RequestBody FAddress fAddress)
    {
        try {
            return toAjax(fAddressService.updateFAddress(fAddress));
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }

    /**
     * 删除地址
     */
    @ApiOperation("手机端个人地址详情删除地址接口")
    @DeleteMapping("removeUsersAddressList/{addressIds}")
    public AjaxResult removeUsersAddressList(@PathVariable Long[] addressIds)
    {
        try {
            return toAjax(fAddressService.deleteFAddressByAddressIds(addressIds));
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }

    /**
     * 根据电话号码修改用户手机app会员信息
     * @param fUsers 会员对象
     *
     */
    @ApiOperation("手机端修改个人详情页面数据接口")
    @GetMapping("/updateFUsers")
    public AjaxResult updateFUsers(@RequestBody FUsers fUsers)
    {
        try {
            return toAjax(ifUsersService.updateFUsers(fUsers));
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }

    /**
     * 根据电话号码修改用户手机app会员信息
     * @param usersPhone 会员电话
     * @param usersPassword 会员密码
     *
     */
    @ApiOperation("根据电话注册手机端会员账号")
    @PostMapping("/updatePassword")
    public void updatePassword(@RequestParam("usersPhone") String usersPhone,@RequestParam("usersPassword") String usersPassword)
    {
        try {
            if (usersPhone != null && usersPassword != null) {
                FUsers fUsers = ifUsersService.selectUsersusersPhone(usersPhone);
                fUsers.setUsersPassword(SecurityUtils.encryptPassword(usersPassword));
                ifUsersService.updateFUsers(fUsers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证码接口
     *
     */
    @ApiOperation("手机端发送验证码接口")
    @GetMapping("/sendCode")
    public AjaxResult sendCode() throws Exception {
        try {
            // 保存验证码信息
            String uuid = IdUtils.simpleUUID();//生成uid
            //String code = MyClass.generateCode();//生成随机4位数验证码
            String code = "1234";
            String verKey = CacheConstants.PHONE_CODE_KEY + uuid;
            //将verKey添加到redis
            redisCache.setCacheObject(verKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
            //SendSms.sendVerCode(code);//调用发送验证码
            //将验证码唯一标识符放入AjaxResult对象中。
            return AjaxResult.success("验证码发送成功").put("uuid", uuid);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }

    /**
     * 根据电话注册手机app会员账号
     */
    @ApiOperation("根据电话注册手机端会员账号")
    @PostMapping("/LoginUsers")
    public AjaxResult LoginUsers(@RequestBody LoginVo user)
    {
        try {
            // TODO: 在这里进行电话号码是否有效校验操作
            if (user.getUsersPhone().matches("^(0\\d{2,3}-\\d{7,8}|1[34578]\\d{9})$")) {
                FUsers fUsers = ifUsersService.selectUsersusersPhone(user.getUsersPhone());
                String verKey = CacheConstants.PHONE_CODE_KEY + user.getUuid();
                Object cacheObject = redisCache.getCacheObject(verKey);//获取redis缓存的验证码
                String cacheString = cacheObject.toString();
                // TODO: 在这里进行查询用户电话是否为空判断校验操作
                if (fUsers != null) {
                    // TODO: 在这里进行登录操作
                    if (cacheString.equals(user.getCode())) {
                        String token = tokenService.createApiToken(fUsers);
                        fUsers.setToken(token);
                        return AjaxResult.success("登录成功", fUsers);
                    } else {
                        return AjaxResult.error("登录失败,验证码错误");
                    }
                } else if (cacheString.equals(user.getCode())) {
                    // TODO: 在这里进行注册操作
                    FUsers fUser =  new FUsers(RandomUsernameGenerator.generateRandomUsername(), "2", user.getUsersPhone(), 0L,new BigDecimal(0));
                    ifUsersService.insertFUsers(fUser);
                    String token = tokenService.createApiToken(fUser);
                    fUser.setToken(token);
                    return AjaxResult.success("保存成功", fUser);
                    } else {
                        return AjaxResult.error("注册失败,验证码错误");
                    }
                } else {
                return AjaxResult.error("电话号码无效");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }

    /**
     * 手机端添加购物车数据到redis
     *
     * @param usersPhone 用户号码
     * @param coding 商品编码(根据商品编码查询商品数据)
     * 商品数量(每次添加数据到redis同一商品的数量)
     */
    @ApiOperation("手机端添加购物车数据到redis")
    @GetMapping(value = "/insertShopData/{usersPhone}/{coding}")
    public AjaxResult insertShopData(@PathVariable("usersPhone") String usersPhone, @PathVariable("coding") Long coding)
    {
        try {
            ifShoppingCartService.insertShopData(usersPhone,coding);
            return AjaxResult.success("加入购物车成功");
        } catch (Exception ex){
            ex.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }

    /**
     * 手机端删除购物车redis数据
     *
     * @param usersPhone 用户号码
     * @param coding 商品编码(根据商品编码查询商品数据)
     * 商品数量(每次添加数据到redis同一商品的数量)
     */
    @ApiOperation("手机端删除购物车redis数据")
    @GetMapping(value = "/deleteShopData/{usersPhone}/{coding}")
    public AjaxResult deleteShopData(@PathVariable("usersPhone") String usersPhone, @PathVariable("coding") Long coding)
    {
        try {
            ifShoppingCartService.deleteShopData(usersPhone,coding);
            return AjaxResult.success("删除购物车指定数据成功");
        } catch (Exception ex){
            ex.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }

    /**
     * 手机端减除购物车redis商品数量
     *
     * @param usersPhone 用户号码
     * @param coding 商品编码(根据商品编码查询商品数据)
     * 商品数量(每次添加数据到redis同一商品的数量)
     */
    @ApiOperation("手机端减除购物车redis商品数量")
    @GetMapping(value = "/deductionShopData/{usersPhone}/{coding}")
    public AjaxResult deductionShopData(@PathVariable("usersPhone") String usersPhone, @PathVariable("coding") Long coding)
    {
        try {
            ifShoppingCartService.deductionShopData(usersPhone,coding);
            return AjaxResult.success("减掉商品数量成功");
        } catch (Exception ex){
            ex.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }

    /**
     * 手机端购物车页面数据
     *
     * @param usersPhone 用户号码
     */
    @ApiOperation("手机端购物车页面数据")
    @GetMapping("/selectShopData/{usersPhone}")
    public AjaxResult selectShopData(@PathVariable("usersPhone") String usersPhone)
    {
        try {
            List<FGoods> fGoods = ifShoppingCartService.selectShopData(usersPhone);
            if (fGoods.isEmpty()) {
                return AjaxResult.success("查无购物车数据");
            } else {
                return AjaxResult.success(fGoods);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }

    /**
     * 手机端结算
     * @param settlement 手机端结算数据对象
     *
     */
    @ApiOperation("手机端购物车结算")
    @PostMapping  (value = "/insertSettlement")
    public AjaxResult insertSettlement(@RequestBody Settlement settlement)
    {
        try {
            int i = fOrdeersService.insertShopping(settlement);
            if (i == 0) {
                return AjaxResult.success("付款失败，数据异常");
            } else {
                return AjaxResult.success("付款成功");
            }
        } catch (Exception ex){
            ex.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }

    /**
     * 手机端订单支付状态修改接口(修改支付状态为1：已付款)
     * @param ordersPayMethod 订单状态接口数据
     *
     */
    @ApiOperation("手机端订单支付状态修改接口")
    @PostMapping(value = "/updateSettlement")
    public AjaxResult updateSettlement(@RequestBody ordersPayMethod ordersPayMethod)
    {
        try {
            int i = fOrdeersService.updateSettlement(ordersPayMethod);
            if (i == 1) {
                return AjaxResult.success("修改成功");
            } else {
                return AjaxResult.success("修改失败");
            }
        } catch (Exception ex){
            ex.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }

    /**
     * 手机端订单+订单明细的订单状态修改接口(修改订单状态为3：已完成)
     * @param orderstatus 订单状态接口数据
     *
     */
    @ApiOperation("手机端订单支付状态修改接口")
    @PostMapping(value = "/updateOrdersStatus")
    public AjaxResult updateOrdersStatus(@RequestBody OrdersStatus orderstatus)
    {
        try {
            int i = fOrdeersService.updateOrdersStatus(orderstatus);
            if (i == 0 ) {
                return AjaxResult.success("修改失败");
            } else {
                return AjaxResult.success("修改成功");
            }
        } catch (Exception ex){
            ex.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }

    /**
     * 手机端用户全部订单
     *
     * @param usersPhone 用户电话号码
     */
    @ApiOperation("手机端用户全部订单接口")
    @GetMapping  (value = "/selectOrders/{usersPhone}")
    public AjaxResult selectOrders(@PathVariable("usersPhone") String usersPhone)
    {
        try {
            List<FOrderPartslist> fOrderPartslists = fOrdeersService.selectOrders(usersPhone);
            if (fOrderPartslists.isEmpty()) {
                return AjaxResult.success("此用户无订单"); // 或者其他适当的处理方式
            } else {
                return AjaxResult.success(fOrderPartslists);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }
}