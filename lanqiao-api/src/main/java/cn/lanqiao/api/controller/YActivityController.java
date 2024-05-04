package cn.lanqiao.api.controller;

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
import cn.lanqiao.active.domain.YActivity;
import cn.lanqiao.active.service.IYActivityService;
import cn.lanqiao.common.utils.poi.ExcelUtil;
import cn.lanqiao.common.core.page.TableDataInfo;

/**
 * 活动信息Controller
 * 
 * @author yang
 * @date 2023-04-11
 */
@RestController
@RequestMapping("/active/activity")
public class YActivityController extends BaseController
{
    @Autowired
    private IYActivityService yActivityService;

    /**
     * 查询活动信息列表
     */
    @PreAuthorize("@ss.hasPermi('active:activity:list')")
    @GetMapping("/list")
    public TableDataInfo list(YActivity yActivity)
    {
        startPage();
        List<YActivity> list = yActivityService.selectYActivityList(yActivity);
        return getDataTable(list);
    }

    /**
     * 导出活动信息列表
     */
    @PreAuthorize("@ss.hasPermi('active:activity:export')")
    @Log(title = "活动信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YActivity yActivity)
    {
        List<YActivity> list = yActivityService.selectYActivityList(yActivity);
        ExcelUtil<YActivity> util = new ExcelUtil<YActivity>(YActivity.class);
        util.exportExcel(response, list, "活动信息数据");
    }

    /**
     * 获取活动信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('active:activity:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yActivityService.selectYActivityById(id));
    }

    /**
     * 新增活动信息
     */
    @PreAuthorize("@ss.hasPermi('active:activity:add')")
    @Log(title = "活动信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YActivity yActivity)
    {
        return toAjax(yActivityService.insertYActivity(yActivity));
    }

    /**
     * 修改活动信息
     */
    @PreAuthorize("@ss.hasPermi('active:activity:edit')")
    @Log(title = "活动信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YActivity yActivity)
    {
        return toAjax(yActivityService.updateYActivity(yActivity));
    }


}
