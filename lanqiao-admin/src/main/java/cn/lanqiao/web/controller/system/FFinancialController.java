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
import cn.lanqiao.system.domain.FFinancial;
import cn.lanqiao.system.service.IFFinancialService;
import cn.lanqiao.common.utils.poi.ExcelUtil;
import cn.lanqiao.common.core.page.TableDataInfo;

/**
 * 财务报表管理Controller
 * 
 * @author chuan
 * @date 2024-05-15
 */
@RestController
@RequestMapping("/system/financial")
public class FFinancialController extends BaseController
{
    @Autowired
    private IFFinancialService fFinancialService;

    /**
     * 查询财务报表管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:financial:list')")
    @GetMapping("/list")
    public TableDataInfo list(FFinancial fFinancial)
    {
        startPage();
        List<FFinancial> list = fFinancialService.selectFFinancialList(fFinancial);
        return getDataTable(list);
    }

    /**
     * 导出财务报表管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:financial:export')")
    @Log(title = "财务报表管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FFinancial fFinancial)
    {
        List<FFinancial> list = fFinancialService.selectFFinancialList(fFinancial);
        ExcelUtil<FFinancial> util = new ExcelUtil<FFinancial>(FFinancial.class);
        util.exportExcel(response, list, "财务报表管理数据");
    }

    /**
     * 获取财务报表管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:financial:query')")
    @GetMapping(value = "/{financialId}")
    public AjaxResult getInfo(@PathVariable("financialId") Long financialId)
    {
        return AjaxResult.success(fFinancialService.selectFFinancialByFinancialId(financialId));
    }

    /**
     * 新增财务报表管理
     */
    @PreAuthorize("@ss.hasPermi('system:financial:add')")
    @Log(title = "财务报表管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FFinancial fFinancial)
    {
        return toAjax(fFinancialService.insertFFinancial(fFinancial));
    }

    /**
     * 修改财务报表管理
     */
    @PreAuthorize("@ss.hasPermi('system:financial:edit')")
    @Log(title = "财务报表管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FFinancial fFinancial)
    {
        return toAjax(fFinancialService.updateFFinancial(fFinancial));
    }

    /**
     * 删除财务报表管理
     */
    @PreAuthorize("@ss.hasPermi('system:financial:remove')")
    @Log(title = "财务报表管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{financialIds}")
    public AjaxResult remove(@PathVariable Long[] financialIds)
    {
        return toAjax(fFinancialService.deleteFFinancialByFinancialIds(financialIds));
    }
}
