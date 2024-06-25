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
import cn.lanqiao.system.domain.Fadvertisement;
import cn.lanqiao.system.service.FadvertisementService;
import cn.lanqiao.common.utils.poi.ExcelUtil;
import cn.lanqiao.common.core.page.TableDataInfo;

/**
 * 广告管理Controller
 * 
 * @author chuan
 * @date 2024-06-25
 */
@RestController
@RequestMapping("/system/advertisement")
public class FadvertisementController extends BaseController
{
    @Autowired
    private FadvertisementService fadvertisementService;

    /**
     * 查询广告管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:advertisement:list')")
    @GetMapping("/list")
    public TableDataInfo list(Fadvertisement fadvertisement)
    {
        List<Fadvertisement> list = fadvertisementService.selectFadvertisementLists(fadvertisement);
        return getDataTable(list);
    }

    /**
     * 导出广告管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:advertisement:export')")
    @Log(title = "广告管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Fadvertisement fadvertisement)
    {
        List<Fadvertisement> list = fadvertisementService.selectFadvertisementLists(fadvertisement);
        ExcelUtil<Fadvertisement> util = new ExcelUtil<Fadvertisement>(Fadvertisement.class);
        util.exportExcel(response, list, "广告管理数据");
    }

    /**
     * 获取广告管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:advertisement:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(fadvertisementService.selectFadvertisementById(id));
    }

    /**
     * 新增广告管理
     */
    @PreAuthorize("@ss.hasPermi('system:advertisement:add')")
    @Log(title = "广告管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Fadvertisement fadvertisement)
    {
        return toAjax(fadvertisementService.insertFadvertisement(fadvertisement));
    }

    /**
     * 修改广告管理
     */
    @PreAuthorize("@ss.hasPermi('system:advertisement:edit')")
    @Log(title = "广告管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Fadvertisement fadvertisement)
    {
        return toAjax(fadvertisementService.updateFadvertisement(fadvertisement));
    }

    /**
     * 删除广告管理
     */
    @PreAuthorize("@ss.hasPermi('system:advertisement:remove')")
    @Log(title = "广告管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fadvertisementService.deleteFadvertisementByIds(ids));
    }
}
