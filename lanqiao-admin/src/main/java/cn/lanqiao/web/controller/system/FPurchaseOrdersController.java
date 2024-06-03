package cn.lanqiao.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.lanqiao.system.domain.FGoods;
import cn.lanqiao.system.service.IFGoodsService;
import cn.lanqiao.system.service.impl.FGoodsServiceImpl;
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
import cn.lanqiao.system.domain.FPurchaseOrders;
import cn.lanqiao.system.service.IFPurchaseOrdersService;
import cn.lanqiao.common.utils.poi.ExcelUtil;
import cn.lanqiao.common.core.page.TableDataInfo;

/**
 * 采购信息Controller
 * 
 * @author lanqiao
 * @date 2024-05-20
 */
@RestController
@RequestMapping("/system/orders")
public class FPurchaseOrdersController extends BaseController
{
    @Autowired
    private IFPurchaseOrdersService fPurchaseOrdersService;
    @Autowired
    private IFGoodsService fGoodsService;

    @PreAuthorize("@ss.hasPermi('system:orders:list')")
    @GetMapping("/goodsListAll")
    public  List<FGoods> goodsListAll(FGoods fGoods)
    {
        List<FGoods> list = fGoodsService.selectFGoodsList(fGoods);
        return list;
    }
    /**
     * 查询采购信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:orders:list')")
    @GetMapping("/list")
    public TableDataInfo list(FPurchaseOrders fPurchaseOrders)
    {
        startPage();
        List<FPurchaseOrders> list = fPurchaseOrdersService.selectFPurchaseOrdersList(fPurchaseOrders);
        return getDataTable(list);
    }

    /**
     * 导出采购信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:orders:export')")
    @Log(title = "采购信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FPurchaseOrders fPurchaseOrders)
    {
        List<FPurchaseOrders> list = fPurchaseOrdersService.selectFPurchaseOrdersList(fPurchaseOrders);
        ExcelUtil<FPurchaseOrders> util = new ExcelUtil<FPurchaseOrders>(FPurchaseOrders.class);
        util.exportExcel(response, list, "采购信息数据");
    }

    /**
     * 获取采购信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:orders:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        return AjaxResult.success(fPurchaseOrdersService.selectFPurchaseOrdersByOrderId(orderId));
    }

    /**
     * 新增采购信息
     */
    @PreAuthorize("@ss.hasPermi('system:orders:add')")
    @Log(title = "采购信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FPurchaseOrders fPurchaseOrders)
    {
        return toAjax(fPurchaseOrdersService.insertFPurchaseOrders(fPurchaseOrders));
    }

    /**
     * 修改采购信息
     */
    @PreAuthorize("@ss.hasPermi('system:orders:edit')")
    @Log(title = "采购信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FPurchaseOrders fPurchaseOrders)
    {
        return toAjax(fPurchaseOrdersService.updateFPurchaseOrders(fPurchaseOrders));
    }

    /**
     * 删除采购信息
     */
    @PreAuthorize("@ss.hasPermi('system:orders:remove')")
    @Log(title = "采购信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(fPurchaseOrdersService.deleteFPurchaseOrdersByOrderIds(orderIds));
    }
}
