package cn.lanqiao.system.service.impl;

import cn.lanqiao.common.constant.CacheConstants;
import cn.lanqiao.common.constant.Constants;
import cn.lanqiao.common.core.domain.ShoppingCart;
import cn.lanqiao.common.core.redis.RedisCache;
import cn.lanqiao.common.utils.MyClass;
import cn.lanqiao.common.utils.RandomUsernameGenerator;
import cn.lanqiao.common.utils.SendSms;
import cn.lanqiao.common.utils.uuid.IdUtils;
import cn.lanqiao.system.domain.FGoods;
import cn.lanqiao.system.domain.FUsers;
import cn.lanqiao.system.domain.argument.LoginVo;
import cn.lanqiao.system.mapper.FGoodsMapper;
import cn.lanqiao.system.mapper.FUsersMapper;
import cn.lanqiao.system.service.IFShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 购物车Service业务层处理
 *
 * @author lanqiao
 * @date 2024-06-6
 */
@Service
public class IFShoppingCartServiceImpl implements IFShoppingCartService  {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private FGoodsMapper fGoodsMapper;

    @Autowired
    private FUsersMapper fUsersMapper;
    /**
     * 手机端添加购物车数据到 Redis
     *
     * @param usersPhone 用户号码
     * @param coding 商品编码
     */
    @Override
    public int insertShopData(String usersPhone, String coding) {
        if (usersPhone == null || coding == null) {
            return 0;
        }
        // 定义默认的商品数量
        Long defaultQuantity = 1L;

        // 生成唯一标识 Key
        String verKey = CacheConstants.Query_Shopping_KEY + usersPhone;
        // 获取现有的购物车数据
        Map<String,ShoppingCart> shoppingCart = redisCache.getShoppingCart(verKey);

        // 如果购物车为空，需要初始化
        if (shoppingCart == null) {
            return 0;
        }

        // 检查购物车中是否已存在该商品
        if (shoppingCart.containsKey(coding)) {
            // 如果购物车中已经存在相同的商品，则更新数量
            ShoppingCart cartItem = shoppingCart.get(coding);
            cartItem.setQuantity(cartItem.getQuantity() + defaultQuantity);
        } else {
            // 否则，将新商品添加到购物车 (setOrdersPayMethod(2L)表示为默认值也就是null的意思)
            ShoppingCart newCartItem = new ShoppingCart();
            newCartItem.setOrdersPayMethod(2L);
            newCartItem.setQuantity(defaultQuantity);

            // 这里可能需要根据商品编码查询商品数据并设置到 newCartItem 中
            shoppingCart.put(coding, newCartItem);
        }

        // 将更新后的购物车数据存储到 Redis
        redisCache.setShoppingCart(verKey, shoppingCart, Constants.Query_Shopping, TimeUnit.HOURS);
        return 1;
    }

    /**
     * 手机端删除购物车redis数据
     *
     * @param usersPhone 用户号码
     * @param coding 商品编码(根据商品编码查询商品数据)
     * 商品数量(每次添加数据到redis同一商品的数量)
     */
    @Override
    public int deleteShopData(String usersPhone,String coding) {
        if (usersPhone == null || coding == null) {
            return 0;
        }
        // 生成唯一标识 Key
        String verKey = CacheConstants.Query_Shopping_KEY + usersPhone;
        // 获取现有的购物车数据
        Map<String,ShoppingCart> shoppingCart = redisCache.getShoppingCart(verKey);

        if (shoppingCart.containsKey(coding)) {
            // 删除指定的商品
            shoppingCart.remove(coding);
            // TODO: 进行清空redis购物车数据操作
            redisCache.deleteObject(verKey);
        }

        // 将购物车数据存储到 Redis
        redisCache.setShoppingCart(verKey,shoppingCart,Constants.Query_Shopping,TimeUnit.HOURS);
        return 1;
    }

    /**
     * 手机端修改购物车redis数据支付状态为 0 未支付
     *
     * @param usersPhone 用户号码
     *
     */
    @Override
    public int updateShopData(String usersPhone,Long status,Long PayMethod) {
        if (usersPhone == null) {
            return 0;
        }

        // 生成唯一标识 Key
        String verKey = CacheConstants.Query_Shopping_KEY + usersPhone;
        // 获取现有的购物车数据
        Map<String,ShoppingCart> shoppingCart = redisCache.getShoppingCart(verKey);

        //判断redis的数据是否为null
        if (shoppingCart.isEmpty()) {
            return 0;
        } else {
            // 遍历购物车中的每个商品编码，查询对应的商品数据并添加到列表中
            for (Map.Entry<String, ShoppingCart> entry : shoppingCart.entrySet()) {
                ShoppingCart value = entry.getValue();
                if (value.getOrdersPayMethod() == PayMethod) {
                    value.setOrdersPayMethod(status);
                }
            }

            // 将购物车数据存储到 Redis
            redisCache.setShoppingCart(verKey,shoppingCart,Constants.Query_Shopping, TimeUnit.HOURS);
            return 1;
        }
    }

    /**
     * 手机端减除购物车redis商品数量
     *
     * @param usersPhone 用户号码
     * @param coding 商品编码(根据商品编码查询商品数据)
     * 商品数量(每次添加数据到redis同一商品的数量)
     */
    @Override
    public int deductionShopData(String usersPhone, String coding) {
        if (usersPhone == null || coding == null) {
            return 0;
        }

        // 生成唯一标识 Key
        String verKey = CacheConstants.Query_Shopping_KEY + usersPhone;
        // 获取现有的购物车数据
        Map<String,ShoppingCart> shoppingCart = redisCache.getShoppingCart(verKey);

        if (!shoppingCart.containsKey(coding)) {
            return 0;
        }

        ShoppingCart cartItem = shoppingCart.get(coding);

        //判断当前修改的商品数量是否为待支付商品
        if (cartItem.getOrdersPayMethod() == 0L) {
            return 0;
        }

        // 如果购物车中已经存在相同的商品，则更新数量
        if (cartItem.getQuantity() > 1) {
            // 如果商品数量大于1，则减少数量
            cartItem.setQuantity(cartItem.getQuantity() - 1L);
            shoppingCart.put(coding,cartItem);
        } else {
            // 如果商品数量为1，则直接移除该商品
            shoppingCart.remove(coding);
        }

        // 将购物车数据存储到 Redis
        redisCache.setShoppingCart(verKey,shoppingCart,Constants.Query_Shopping, TimeUnit.HOURS);
        return 1;
    }

    /**
     * 手机端购物车页面数据
     *
     * @param usersPhone 用户号码
     */
    @Override
    public List<FGoods> selectShopData(String usersPhone,Long status) {
        if (usersPhone == null) {
            return null;
        }

        //创建购物车列表数据
        List<FGoods> shoppingList = new ArrayList<>();
        // 生成唯一标识 Key
        String verKey = CacheConstants.Query_Shopping_KEY + usersPhone;
        // 获取现有的购物车数据
        Map<String,ShoppingCart> shoppingCart = redisCache.getShoppingCart(verKey);

        //判断redis的数据是否为null
        if (shoppingCart.isEmpty()) {
            return null;
        } else {
            // 遍历购物车中的每个商品编码，查询对应的商品数据并添加到列表中
            for (Map.Entry<String, ShoppingCart> entry : shoppingCart.entrySet()) {
                String coding = entry.getKey();
                ShoppingCart value = entry.getValue();
                if (value.getOrdersPayMethod() == status) {
                    // 根据商品编码查询商品数据
                    FGoods fGoods = fGoodsMapper.selectGoodsList(coding);
                    if (fGoods != null) {
                        // 设置商品数量
                        fGoods.setQuantity(value.getQuantity());
                        shoppingList.add(fGoods);
                    }
                }
            }

            return shoppingList;
        }
    }

    /**
     * 手机端redis待付款商品数据
     *
     * @param usersPhone 用户电话
     *
     */
    public List<FGoods> selectShopingData(String usersPhone) {
        if (usersPhone == null) {//判断传参是否为null
            return null;
        }

        List<FGoods> shoppingList = new ArrayList<>();//创建待付款商品数据集合

        String verKey = CacheConstants.Query_Shopping_KEY + usersPhone;//生成key
        Map<String, ShoppingCart> shoppingCart = redisCache.getShoppingCart(verKey);//根据key获取redis商品数据

        if (shoppingCart.isEmpty()) {
            return null;
        }

        Set<String> coding = new HashSet<>();//创建存商品编码集合
        List<Long> quanTity = new ArrayList<>();//创建存商品数量集合
        for (Map.Entry<String, ShoppingCart> entry : shoppingCart.entrySet()) {//取出redis里的商品编码和商品数量
            String codings = entry.getKey();
            ShoppingCart value = entry.getValue();
            if (value.getOrdersPayMethod() == 0L) {
                coding.add(codings);
                quanTity.add(value.getQuantity());
            }
        }

        Map<String, Long> codeQuantityMap = new HashMap<>();//创建商品编码：商品数量键值对map集合
        for (String commonCode : coding) {
            int i = new ArrayList<>(coding).indexOf(commonCode);
            if (i != -1) {
                codeQuantityMap.put(commonCode, quanTity.get(i));//将商品数量添加入对应商品编码
            }
        }

        for (String codings : coding) {
            FGoods fGoods = fGoodsMapper.selectGoodsList(codings);//根据商品编码查询商品数据
            if (fGoods != null) {
                Long quantity = codeQuantityMap.get(codings);//根据商品编码获取商品数量
                fGoods.setQuantity(quantity);//赋值商品数量
                shoppingList.add(fGoods);//将商品数据加入待付款数据集合
            }
        }

        return shoppingList;
    }

    /**
     * 手机端redis待付款商品数据 (回显)
     *
     * @param usersPhone 用户电话
     * @param coding 商品编码
     *
     */
    public FGoods selectSho(String usersPhone, String coding)
    {
        if (usersPhone == null || coding == null) {//判断传参是否为null
            return null;
        }

        String verKey = CacheConstants.Query_Shopping_KEY + usersPhone;//生成key
        Map<String, ShoppingCart> shoppingCart = redisCache.getShoppingCart(verKey);//根据key获取redis商品数据

        if (shoppingCart.isEmpty()) {
            return null;
        }
        ShoppingCart shoppingCart1 = shoppingCart.get(coding);
        if (shoppingCart1.getOrdersPayMethod() != 0) {
            return null;
        }

        FGoods fGoods = fGoodsMapper.selectGoodsList(coding);//创建待付款商品数据集合
        fGoods.setQuantity(shoppingCart1.getQuantity());

        return fGoods;
    }


    /**
     * 手机端redis根据商品编码获取待付款商品数据进行结账
     *
     * @param usersPhone 用户电话
     * @param coDings 商品编码集合
     *
     */
    public List<FGoods> selectShopDataCoDings(String usersPhone, Set<String> coDings)
    {
        if (usersPhone == null || coDings.isEmpty()) {//判断传参是否为null
            return null;
        }

        List<FGoods> shoppingList = new ArrayList<>();//创建待付款商品数据集合

        String verKey = CacheConstants.Query_Shopping_KEY + usersPhone;//生成key
        Map<String, ShoppingCart> shoppingCart = redisCache.getShoppingCart(verKey);//根据key获取redis商品数据

        if (shoppingCart.isEmpty()) {
            return null;
        }

        Set<String> coding = new HashSet<>();//创建存商品编码集合
        List<Long> quanTity = new ArrayList<>();//创建存商品数量集合
        for (Map.Entry<String, ShoppingCart> entry : shoppingCart.entrySet()) {//取出redis里的商品编码和商品数量
            String codings = entry.getKey();
            ShoppingCart value = entry.getValue();
            if (value.getOrdersPayMethod() == 0L) {
                coding.add(codings);
                value.setOrdersPayMethod(1L);
                quanTity.add(value.getQuantity());
            }
        }

        coding.retainAll(coDings);//取同样商品编码值
        Map<String, Long> codeQuantityMap = new HashMap<>();//创建商品编码：商品数量键值对map集合
        for (String commonCode : coding) {
            int i = new ArrayList<>(coDings).indexOf(commonCode);
            if (i != -1) {
                codeQuantityMap.put(commonCode, quanTity.get(i));//将商品数量添加入对应商品编码
            }
        }

        for (String codings : coding) {
            FGoods fGoods = fGoodsMapper.selectGoodsList(codings);//根据商品编码查询商品数据
            if (fGoods != null) {
                Long quantity = codeQuantityMap.get(codings);//根据商品编码获取商品数量
                fGoods.setQuantity(quantity);//赋值商品数量
                shoppingList.add(fGoods);//将商品数据加入待付款数据集合
            }
        }

        redisCache.deleteFromMap(verKey, coding);// 删除已处理的商品数据
        return shoppingList;
    }

    /**
     * 验证码接口
     *
     */
    public String sendCode()
    {
        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();//生成uid
//        String code = MyClass.generateCode();//生成随机4位数验证码
        String code = "1234";
        String verKey = CacheConstants.PHONE_CODE_KEY + uuid;
        //将verKey添加到redis
        redisCache.setCacheObject(verKey,code,Constants.CAPTCHA_EXPIRATION,TimeUnit.MINUTES);
//        try {
//            SendSms.sendVerCode(code);//调用发送验证码
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        return uuid;
    }

    /**
     * 根据电话登录 + 注册手机app会员账号
     *
     */
    @Override
    public FUsers LoginUsers(LoginVo user) {
        // TODO: 在这里进行电话号码是否有效校验操作
        if (!user.getUsersPhone().matches("^(0\\d{2,3}-\\d{7,8}|1[34578]\\d{9})$")) {
            return null;
        }

        FUsers fUsers = fUsersMapper.selectUsersusersPhone(user.getUsersPhone());
        String verKey = CacheConstants.PHONE_CODE_KEY + user.getUuid();
        Object cacheObject = redisCache.getCacheObject(verKey);//获取redis缓存的验证码
        String cacheString = cacheObject.toString();

        // TODO: 在这里进行查询用户电话是否为空判断校验操作
        if (fUsers != null && cacheString.equals(user.getCode())) {
            // TODO: 在这里进行登录操作
            return fUsers;
        }

        if (!cacheString.equals(user.getCode())) {
            return null;
        }

        // TODO: 在这里进行注册操作
        FUsers fUser =  new FUsers(RandomUsernameGenerator.generateRandomUsername(), "2", user.getUsersPhone(), 0L,new BigDecimal(0));
        fUsersMapper.insertFUsers(fUser);
        return fUser;
    }
}