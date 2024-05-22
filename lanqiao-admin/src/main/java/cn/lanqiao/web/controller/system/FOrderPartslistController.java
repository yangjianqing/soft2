package cn.lanqiao.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import cn.lanqiao.system.domain.FOrderPartslist;
import cn.lanqiao.system.service.IFOrderPartslistService;
import cn.lanqiao.common.utils.poi.ExcelUtil;
import cn.lanqiao.common.core.page.TableDataInfo;

/**
 * 订单明细Controller
 * 
 * @author lanqiao
 * @date 2024-05-22
 */
@RestController
@RequestMapping("/system/partslist")
public class FOrderPartslistController extends BaseController
{
    @Autowired
    private IFOrderPartslistService fOrderPartslistService;

    /**
     * 查询订单明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:partslist:list')")
    @GetMapping("/list")
    public TableDataInfo list(FOrderPartslist fOrderPartslist)
    {
        startPage();
        List<FOrderPartslist> list = fOrderPartslistService.selectFOrderPartslistList(fOrderPartslist);
        return getDataTable(list);
    }

    /**
     * 导出订单明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:partslist:export')")
    @Log(title = "订单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FOrderPartslist fOrderPartslist)
    {
        List<FOrderPartslist> list = fOrderPartslistService.selectFOrderPartslistList(fOrderPartslist);
        ExcelUtil<FOrderPartslist> util = new ExcelUtil<FOrderPartslist>(FOrderPartslist.class);
        util.exportExcel(response, list, "订单明细数据");
    }

    /**
     * 获取订单明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:partslist:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(fOrderPartslistService.selectFOrderPartslistById(id));
    }

    /**
     * 新增订单明细
     */
    @PreAuthorize("@ss.hasPermi('system:partslist:add')")
    @Log(title = "订单明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FOrderPartslist fOrderPartslist)
    {
        return toAjax(fOrderPartslistService.insertFOrderPartslist(fOrderPartslist));
    }

    /**
     * 修改订单明细
     */
    @PreAuthorize("@ss.hasPermi('system:partslist:edit')")
    @Log(title = "订单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FOrderPartslist fOrderPartslist)
    {
        return toAjax(fOrderPartslistService.updateFOrderPartslist(fOrderPartslist));
    }

    /**
     * 删除订单明细
     */
    @PreAuthorize("@ss.hasPermi('system:partslist:remove')")
    @Log(title = "订单明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fOrderPartslistService.deleteFOrderPartslistByIds(ids));
    }
}
