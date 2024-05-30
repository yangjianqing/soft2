package cn.lanqiao.api.controller;

import cn.lanqiao.common.annotation.Anonymous;
import cn.lanqiao.common.constant.CacheConstants;
import cn.lanqiao.common.constant.Constants;
import cn.lanqiao.common.core.controller.BaseController;
import cn.lanqiao.common.core.domain.AjaxResult;
import cn.lanqiao.common.core.page.TableDataInfo;
import cn.lanqiao.common.core.redis.RedisCache;
import cn.lanqiao.common.utils.MyClass;
import cn.lanqiao.common.utils.SecurityUtils;
import cn.lanqiao.common.utils.SendSms;
import cn.lanqiao.common.utils.uuid.IdUtils;
import cn.lanqiao.system.domain.FGoods;
import cn.lanqiao.system.domain.FOrdeers;
import cn.lanqiao.system.domain.FOrderPartslist;
import cn.lanqiao.system.domain.FUsers;
import cn.lanqiao.system.service.IFGoodsService;
import cn.lanqiao.system.service.IFOrdeersService;
import cn.lanqiao.system.service.IFOrderPartslistService;
import cn.lanqiao.system.service.IFUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private RedisCache redisCache;
    /**
     * 查询商品列表
     */

    @GetMapping("/goodsList")
    public TableDataInfo list(FGoods fGoods)
    {
        startPage();
        List<FGoods> list = fGoodsService.selectFGoodsList(fGoods);
        return getDataTable(list);
    }
    /**
     * 查询订单管理列表
     */
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
    @GetMapping("/goods/{goodsName}")
    public AjaxResult goodsName( @PathVariable("goodsName")String goodsName)
    {
//        startPage();
        List<FGoods> fGoodsName = fGoodsService.selectGoodsName(goodsName);
        return AjaxResult.success().put("goods",fGoodsName);
    }

    /**
     * 根据商品分类查询
     */
    @GetMapping("/goodsTpye/{goodsType}")
    public AjaxResult goodsType( @PathVariable("goodsType")String goodsType)
    {
        //startPage();
        List<FGoods> fGoodstype = fGoodsService.selectGoodsType(goodsType);
        return AjaxResult.success().put("goodsTpye",fGoodstype);
    }

    /**
     * 根据电话号码获取用户手机app会员信息
     */
    @GetMapping("/users/{usersPhone}")
    public AjaxResult getUsers( @PathVariable("usersPhone") String usersPhone)
    {
        return AjaxResult.success().put("users",ifUsersService.selectUsersusersPhone(usersPhone));
    }

    /**
     * 验证码接口
     */
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
     */
    @PostMapping("/registered")
    public AjaxResult Registered(String usersPhone, String usersPassword, String code, String uuid)
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
                fUsers.setMemberTotal(0L);
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
     */
    @PostMapping("/Login")
    public AjaxResult Login(String usersPhone, String usersPassword)
    {
        if (ifUsersService.selectUsersusersPhone(usersPhone) != null) {
            if (SecurityUtils.matchesPassword(usersPassword, ifUsersService.selectUsersusersPhone(usersPhone).getUsersPassword())) {
                return AjaxResult.error("登录成功");
            } else {
                return AjaxResult.error("登录失败,密码错误");
            }
        } else {
            return AjaxResult.error("登录失败,用户账号不存在");
        }
    }

}
