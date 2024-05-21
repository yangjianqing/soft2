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
import cn.lanqiao.system.domain.FAppraise;
import cn.lanqiao.system.service.IFAppraiseService;
import cn.lanqiao.common.utils.poi.ExcelUtil;
import cn.lanqiao.common.core.page.TableDataInfo;

/**
 * 评价管理Controller
 * 
 * @author lanqiao
 * @date 2024-05-21
 */
@RestController
@RequestMapping("/system/appraise")
public class FAppraiseController extends BaseController
{
    @Autowired
    private IFAppraiseService fAppraiseService;

    /**
     * 查询评价管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:appraise:list')")
    @GetMapping("/list")
    public TableDataInfo list(FAppraise fAppraise)
    {
        startPage();
        List<FAppraise> list = fAppraiseService.selectFAppraiseList(fAppraise);
        return getDataTable(list);
    }

    /**
     * 导出评价管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:appraise:export')")
    @Log(title = "评价管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FAppraise fAppraise)
    {
        List<FAppraise> list = fAppraiseService.selectFAppraiseList(fAppraise);
        ExcelUtil<FAppraise> util = new ExcelUtil<FAppraise>(FAppraise.class);
        util.exportExcel(response, list, "评价管理数据");
    }

    /**
     * 获取评价管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:appraise:query')")
    @GetMapping(value = "/{reviewid}")
    public AjaxResult getInfo(@PathVariable("reviewid") Long reviewid)
    {
        return AjaxResult.success(fAppraiseService.selectFAppraiseByReviewid(reviewid));
    }

    /**
     * 新增评价管理
     */
    @PreAuthorize("@ss.hasPermi('system:appraise:add')")
    @Log(title = "评价管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FAppraise fAppraise)
    {
        return toAjax(fAppraiseService.insertFAppraise(fAppraise));
    }

    /**
     * 修改评价管理
     */
    @PreAuthorize("@ss.hasPermi('system:appraise:edit')")
    @Log(title = "评价管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FAppraise fAppraise)
    {
        return toAjax(fAppraiseService.updateFAppraise(fAppraise));
    }

    /**
     * 删除评价管理
     */
    @PreAuthorize("@ss.hasPermi('system:appraise:remove')")
    @Log(title = "评价管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{reviewids}")
    public AjaxResult remove(@PathVariable Long[] reviewids)
    {
        return toAjax(fAppraiseService.deleteFAppraiseByReviewids(reviewids));
    }
}
