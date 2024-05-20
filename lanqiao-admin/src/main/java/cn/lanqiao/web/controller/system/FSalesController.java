package cn.lanqiao.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.lanqiao.system.service.IFUsersService;
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
import cn.lanqiao.system.domain.FSales;
import cn.lanqiao.system.service.IFSalesService;
import cn.lanqiao.common.utils.poi.ExcelUtil;
import cn.lanqiao.common.core.page.TableDataInfo;

/**
 * 销售记录管理Controller
 * 
 * @author chuan
 * @date 2024-05-16
 */
@RestController
@RequestMapping("/system/sales")
public class FSalesController extends BaseController
{
    @Autowired
    private IFSalesService fSalesService;

    @Autowired
    private IFUsersService ifUsersService;
    /**
     * 查询销售记录管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:sales:list')")
    @GetMapping("/list")
    public TableDataInfo list(FSales fSales)
    {
        startPage();
        List<FSales> list = fSalesService.selectFSalesList(fSales);
        return getDataTable(list);
    }

    /**
     * 导出销售记录管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:sales:export')")
    @Log(title = "销售记录管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FSales fSales)
    {
        List<FSales> list = fSalesService.selectFSalesList(fSales);
        ExcelUtil<FSales> util = new ExcelUtil<FSales>(FSales.class);
        util.exportExcel(response, list, "销售记录管理数据");
    }

    /**
     * 获取销售记录管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sales:query')")
    @GetMapping(value = "/{salesId}")
    public AjaxResult getInfo(@PathVariable("salesId") Long salesId)
    {
        return AjaxResult.success(fSalesService.selectFSalesBySalesId(salesId));
    }

    /**
     * 新增销售记录管理
     */
    @PreAuthorize("@ss.hasPermi('system:sales:add')")
    @Log(title = "销售记录管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FSales fSales)
    {
        return toAjax(fSalesService.insertFSales(fSales));
    }

    /**
     * 修改销售记录管理
     */
    @PreAuthorize("@ss.hasPermi('system:sales:edit')")
    @Log(title = "销售记录管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FSales fSales)
    {
        return toAjax(fSalesService.updateFSales(fSales));
    }

    /**
     * 删除销售记录管理
     */
    @PreAuthorize("@ss.hasPermi('system:sales:remove')")
    @Log(title = "销售记录管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{salesIds}")
    public AjaxResult remove(@PathVariable Long[] salesIds)
    {
        return toAjax(fSalesService.deleteFSalesBySalesIds(salesIds));
    }

    //发送users用户数据给前端
    @GetMapping(value = "/initUsers")
    public AjaxResult initUsers()
    {
        AjaxResult success = AjaxResult.success();
        success.put("users",ifUsersService.selectUsersAll());
        return success;
    }
}
