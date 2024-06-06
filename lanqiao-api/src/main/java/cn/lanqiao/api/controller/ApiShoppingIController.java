package cn.lanqiao.api.controller;

import cn.lanqiao.common.constant.CacheConstants;
import cn.lanqiao.common.constant.Constants;
import cn.lanqiao.common.core.controller.BaseController;
import cn.lanqiao.common.core.domain.AjaxResult;
import cn.lanqiao.common.core.domain.R;
import cn.lanqiao.common.core.domain.entity.Category;
import cn.lanqiao.common.core.page.TableDataInfo;
import cn.lanqiao.common.core.redis.RedisCache;
import cn.lanqiao.common.utils.MyClass;
import cn.lanqiao.common.utils.OrderNumberGenerator;
import cn.lanqiao.common.utils.SecurityUtils;
import cn.lanqiao.common.utils.SendSms;
import cn.lanqiao.common.utils.uuid.IdUtils;

//import cn.lanqiao.system.Category;
import cn.lanqiao.system.domain.*;
import cn.lanqiao.system.service.*;
import cn.lanqiao.system.service.impl.IFShoppingCartServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.loadtime.Aj;
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
    private RedisCache redisCache;
    /**
     * 查询商品列表
     */
    @ApiOperation("按大分类查询商品   /api/fresh/goodsList?pageNum=1&pageSize=10&categoryId=1000")
    @GetMapping("/goodsList")
    public TableDataInfo list(FGoods fGoods)
    {
        startPage();
        List<FGoods> list = fGoodsService.selectFGoodsList(fGoods);
        return getDataTable(list);
    }
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
    public TableDataInfo list(FOrdeers fOrdeers)
    {
        startPage();
        List<FOrdeers> list = fOrdeersService.selectFOrdeersList(fOrdeers);
        return getDataTable(list);
    }
    /**
     * 查询订单明细列表
     */
    @ApiOperation("查询订单明细列表")
    @GetMapping("/orderPartsList")
    public TableDataInfo list(FOrderPartslist fOrderPartslist)
    {
        startPage();
        List<FOrderPartslist> list = fOrderPartslistService.selectFOrderPartslistList(fOrderPartslist);
        return getDataTable(list);
    }
    /**
     * 根据商品名称查询
     */

    @ApiOperation("根据商品名称查询")
    @GetMapping("/goods/{goodsName}")
    public AjaxResult goodsName( @PathVariable("goodsName")String goodsName)
    {
        startPage();
        List<FGoods> fGoodsName = fGoodsService.selectGoodsName(goodsName);
        return AjaxResult.success().put("goods",fGoodsName);
    }

    @ApiOperation("根据商品id查询")
    @GetMapping("/goodsId/{id}")
    public AjaxResult goodsListCoding( @PathVariable("id")Long goodsListCoding)
    {
        startPage();
        FGoods fGoodsName = fGoodsService.selectFGoodsById(goodsListCoding);
        return AjaxResult.success().put("id",fGoodsName);
    }



    /**
     * 根据电话号码获取用户手机app会员信息
     * @param usersPhone 电话号码
     *
     */
    @ApiOperation("手机端个人详情页面接口")
    @GetMapping("/users/{usersPhone}")
    public AjaxResult getUsers(@PathVariable("usersPhone") String usersPhone)
    {
        return AjaxResult.success().put("users",ifUsersService.selectUsersusersPhone(usersPhone));
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
            return AjaxResult.error("验证码发送失败");
        }
    }

    /**
     * 根据电话注册手机app会员账号
     * @param usersPhone 用户账号(电话号码)
     * @param usersPassword 用户密码
     * @param code 验证码
     * @param uuid 验证码信息
     */
    @ApiOperation("根据电话注册手机端会员账号")
    @PostMapping("/registered")
    public AjaxResult Registered(@PathVariable("usersPhone") String usersPhone, @PathVariable("usersPassword") String usersPassword, @PathVariable("code") String code, @PathVariable("uuid") String uuid)
    {
        String verKey = CacheConstants.PHONE_CODE_KEY + uuid;
        Object cacheObject = redisCache.getCacheObject(verKey);//获取redis缓存的验证码

        // TODO: 在这里进行用户名和密码的校验操作
        String cacheString = cacheObject.toString();
        if (usersPhone.matches("^(0\\d{2,3}-\\d{7,8}|1[34578]\\d{9})$")) {
            if (cacheString.equals(code)) {
                FUsers fUsers = new FUsers();
                fUsers.setUsersPhone(usersPhone);
                fUsers.setUsersPassword(SecurityUtils.encryptPassword(usersPassword));
                fUsers.setMemberGrade(0L);
                fUsers.setMemberTotal(new BigDecimal(0));
                return toAjax(ifUsersService.insertFUsers(fUsers));
            } else {
                return AjaxResult.error("注册失败");
            }
        } else {
            return AjaxResult.error("电话号码无效");
        }
    }

    /**
     * 根据电话注册手机app登录会员账号
     *
     * @param usersPhone 用户账号(电话号码)
     * @param usersPassword 用户密码
     */
    @ApiOperation("根据电话注册手机端登录会员账号")
    @PostMapping("/Login")
    public AjaxResult Login(@PathVariable("usersPhone") String usersPhone, @PathVariable("usersPassword") String usersPassword)
    {
        FUsers fUsers = ifUsersService.selectUsersusersPhone(usersPhone);
        if (fUsers != null) {
            if (SecurityUtils.matchesPassword(usersPassword,fUsers.getUsersPassword())) {
                return AjaxResult.error("登录成功");
            } else {
                return AjaxResult.error("登录失败,密码错误");
            }
        } else {
            return AjaxResult.error("登录失败,用户账号不存在");
        }
    }

    /**
     * 手机端结算
     *
     * @param usersPhone 用户电话号码
     * @param ordersPayMethod 支付方式
     * @param ordersPayStatuds 支付状态
     * @param ordersRemark 订单备注信息
     * @param GoodsList 订单集合
     */
    @ApiOperation("手机端购物车结算")
    @PostMapping  (value = "/insertSettlement")
    public AjaxResult insertSettlement(@PathVariable("usersPhone") String usersPhone, @PathVariable("ordersPayMethod") Long ordersPayMethod, @PathVariable("ordersPayStatuds") Long ordersPayStatuds, @PathVariable("ordersRemark") String ordersRemark, @RequestBody List<FGoods> GoodsList)
    {
        try {
            fOrdeersService.insertShopping(usersPhone,ordersPayMethod,ordersPayStatuds,ordersRemark,GoodsList);
            return AjaxResult.success("付款成功");
        } catch (Exception ex){
            ex.getMessage();
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
            ifShoppingCartService.insertShopData(usersPhone,coding,1L);
            return AjaxResult.success("加入购物车成功");
        } catch (Exception ex){
            ex.getMessage();
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
        List<FGoods> fGoods = ifShoppingCartService.selectShopData(usersPhone);
        System.out.println(fGoods);
        return AjaxResult.success(fGoods);
    }

}