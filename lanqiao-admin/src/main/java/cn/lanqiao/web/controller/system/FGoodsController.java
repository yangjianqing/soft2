package cn.lanqiao.web.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import cn.lanqiao.common.core.domain.entity.Category;
import cn.lanqiao.common.core.domain.entity.SysDept;
import cn.lanqiao.common.utils.OrderNumberGenerator;
import cn.lanqiao.system.domain.FOrdeers;
import cn.lanqiao.system.domain.FOrderPartslist;
import cn.lanqiao.system.service.ICategoryService;
import cn.lanqiao.system.service.IFOrdeersService;
import cn.lanqiao.system.service.impl.FOrderPartslistServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.lanqiao.common.annotation.Log;
import cn.lanqiao.common.core.controller.BaseController;
import cn.lanqiao.common.core.domain.AjaxResult;
import cn.lanqiao.common.enums.BusinessType;
import cn.lanqiao.system.domain.FGoods;
import cn.lanqiao.system.service.IFGoodsService;
import cn.lanqiao.common.utils.poi.ExcelUtil;
import cn.lanqiao.common.core.page.TableDataInfo;

/**
 * 商品Controller
 * 
 * @author lanqiao
 * @date 2024-05-15
 */
@RestController
@RequestMapping("/system/goods")
public class FGoodsController extends BaseController
{
    @Autowired
    private IFGoodsService fGoodsService;
    @Autowired
    private ICategoryService IcategoryService ;

    @Autowired
    private IFOrdeersService fOrdeersService;

    @Autowired
    private FOrderPartslistServiceImpl fOrderPartslistService;
    /**
     * 查询商品列表
     */
    @PreAuthorize("@ss.hasPermi('system:goods:list')")
    @GetMapping("/list")
    public TableDataInfo list(FGoods fGoods)
    {
        startPage();
        List<FGoods> list = fGoodsService.selectFGoodsList(fGoods);
        return getDataTable(list);
    }

    /**
     * 导出商品列表
     */
    @PreAuthorize("@ss.hasPermi('system:goods:export')")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FGoods fGoods)
    {
        List<FGoods> list = fGoodsService.selectFGoodsList(fGoods);
        ExcelUtil<FGoods> util = new ExcelUtil<FGoods>(FGoods.class);
        util.exportExcel(response, list, "商品数据");
    }

    /**
     * 获取商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:goods:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(fGoodsService.selectFGoodsById(id));
    }

    /**
     * 新增商品
     */
    @PreAuthorize("@ss.hasPermi('system:goods:add')")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FGoods fGoods)
    {
        return toAjax(fGoodsService.insertFGoods(fGoods));
    }

    /**
     * 修改商品
     */
    @PreAuthorize("@ss.hasPermi('system:goods:edit')")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FGoods fGoods)
    {
        return toAjax(fGoodsService.updateFGoods(fGoods));
    }

    /**
     * 删除商品
     */
    @PreAuthorize("@ss.hasPermi('system:goods:remove')")
    @Log(title = "商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fGoodsService.deleteFGoodsByIds(ids));
    }
    /**
     * 获取部门树列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/categoryTree")
    public AjaxResult categoryTree(Category category)
    {
        return AjaxResult.success(IcategoryService.selectCateTreeList(category));
    }

    /**
     * 根据编号获取商品详细信息
     */
    @GetMapping(value = "/Goods/{coding}")
    public AjaxResult getGoodsList(@PathVariable("coding") String coding)
    {
        return AjaxResult.success().put("GoodsList",fGoodsService.selectGoodsList(Long.parseLong(coding)));
    }

    /**
     * 收银结算
     */
    @PostMapping(value = "/addGoodsList")
    public AjaxResult addGoodsList(@RequestBody List<FGoods> fGoods)
    {
        //判断前端传的购物车是否为空
        if (fGoods.size() != 0) {
            //调用随机生成订单号工具类
            String OrderNu = OrderNumberGenerator.generateOrderNumber();
            //创建订单对象进行赋值
            FOrdeers ordeers = new FOrdeers();
            ordeers.setOrdersNumber(OrderNu);//订单编号
            ordeers.setOrdersPayMethod(2L);//支付方式：现金
            ordeers.setOrdersPayStatuds(0L);//支付状态：已支付
            ordeers.setOrdersStatus(2L);//订单状态：已完成
            //调用订单新增方法，新增订单数据
            fOrdeersService.insertFOrdeers(ordeers);
            //利用for循环遍历出购物车内容(订单号:id,price:单价,Quantity:商品数量)
            for (FGoods fGood : fGoods) {
                //调用查询商品详情方法，查询商品详情
                List<FGoods> fGoods1 = fGoodsService.selectGoodsList(fGood.getId());
                //遍历出商品全部数据
                for (FGoods goods : fGoods1) {
                    //调用订单详情新增方法，新增订单详情数据
                    fOrderPartslistService.insertFOrderPartslist(new FOrderPartslist(
                            goods.getId(),//商品id
                            OrderNu,//订单编号
                            fGood.getQuantity()//商品数量
                    ));
                }
            }
            //返回结账成功提示信息
            return AjaxResult.success("结账成功");
        } else {
            //返回结账异常提示信息
            return AjaxResult.error("结账异常");
        }
    }

}