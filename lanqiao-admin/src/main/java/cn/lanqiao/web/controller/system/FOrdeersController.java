package cn.lanqiao.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import cn.lanqiao.system.domain.FormData;
import cn.lanqiao.system.service.ISysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.lanqiao.common.annotation.Log;
import cn.lanqiao.common.core.controller.BaseController;
import cn.lanqiao.common.core.domain.AjaxResult;
import cn.lanqiao.common.enums.BusinessType;
import cn.lanqiao.system.domain.FOrdeers;
import cn.lanqiao.system.service.IFOrdeersService;
import cn.lanqiao.common.utils.poi.ExcelUtil;
import cn.lanqiao.common.core.page.TableDataInfo;

/**
 * 订单管理Controller
 * 
 * @author chuan
 * @date 2024-05-21
 */
@RestController
@RequestMapping("/system/ordeers")
public class FOrdeersController extends BaseController
{
    @Autowired
    private IFOrdeersService fOrdeersService;

    @Autowired
    private ISysUserService iSysUserService;

    /**
     * 查询订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:ordeers:list')")
    @GetMapping("/list")
    public TableDataInfo list(FOrdeers fOrdeers)
    {
        startPage();
        List<FOrdeers> list = fOrdeersService.selectFOrdeersList(fOrdeers);
        return getDataTable(list);
    }

    /**
     * 导出订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:ordeers:export')")
    @Log(title = "订单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FOrdeers fOrdeers)
    {
        List<FOrdeers> list = fOrdeersService.selectFOrdeersList(fOrdeers);
        ExcelUtil<FOrdeers> util = new ExcelUtil<FOrdeers>(FOrdeers.class);
        util.exportExcel(response, list, "订单管理数据");
    }

    /**
     * 获取订单管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:ordeers:query')")
    @GetMapping(value = "/{ordersId}")
    public AjaxResult getInfo(@PathVariable("ordersId") Long ordersId)
    {
        return AjaxResult.success(fOrdeersService.selectFOrdeersByOrdersId(ordersId));
    }

    /**
     * 新增订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:ordeers:add')")
    @Log(title = "订单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FOrdeers fOrdeers)
    {
        return toAjax(fOrdeersService.insertFOrdeers(fOrdeers));
    }

    /**
     * 修改订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:ordeers:edit')")
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FOrdeers fOrdeers)
    {
        return toAjax(fOrdeersService.updateFOrdeers(fOrdeers));
    }

    /**
     * 删除订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:ordeers:remove')")
    @Log(title = "订单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ordersIds}")
    public AjaxResult remove(@PathVariable Long[] ordersIds)
    {
        return toAjax(fOrdeersService.deleteFOrdeersByOrdersIds(ordersIds));
    }

    @GetMapping(value = "/listDelivery")
    public AjaxResult listDelivery()
    {
        AjaxResult success = AjaxResult.success();
        success.put("Delivery",iSysUserService.selectSysUserAll());
        return success;
    }

    /**
     * 收银结算
     * @param formData 购物车数据对象
     *
     */
    @PostMapping(value = "/addGoodsList")
    public AjaxResult addGoodsList(@RequestBody FormData formData)
    {
        try {
            if (formData.getProductsInCart() == null) {
                return AjaxResult.error("购物车数据异常 请重新结账");
            } else {
                System.out.println(formData);
                fOrdeersService.settle(formData);
                return AjaxResult.success("结账成功");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }
}
