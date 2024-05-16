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
import cn.lanqiao.system.domain.FSaledeta;
import cn.lanqiao.system.service.IFSaledetaService;
import cn.lanqiao.common.utils.poi.ExcelUtil;
import cn.lanqiao.common.core.page.TableDataInfo;

/**
 * 销售明细管理Controller
 * 
 * @author chuan
 * @date 2024-05-16
 */
@RestController
@RequestMapping("/system/saledeta")
public class FSaledetaController extends BaseController
{
    @Autowired
    private IFSaledetaService fSaledetaService;

    /**
     * 查询销售明细管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:saledeta:list')")
    @GetMapping("/list")
    public TableDataInfo list(FSaledeta fSaledeta)
    {
        startPage();
        List<FSaledeta> list = fSaledetaService.selectFSaledetaList(fSaledeta);
        return getDataTable(list);
    }

    /**
     * 导出销售明细管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:saledeta:export')")
    @Log(title = "销售明细管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FSaledeta fSaledeta)
    {
        List<FSaledeta> list = fSaledetaService.selectFSaledetaList(fSaledeta);
        ExcelUtil<FSaledeta> util = new ExcelUtil<FSaledeta>(FSaledeta.class);
        util.exportExcel(response, list, "销售明细管理数据");
    }

    /**
     * 获取销售明细管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:saledeta:query')")
    @GetMapping(value = "/{saledetaId}")
    public AjaxResult getInfo(@PathVariable("saledetaId") Long saledetaId)
    {
        return AjaxResult.success(fSaledetaService.selectFSaledetaBySaledetaId(saledetaId));
    }

    /**
     * 新增销售明细管理
     */
    @PreAuthorize("@ss.hasPermi('system:saledeta:add')")
    @Log(title = "销售明细管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FSaledeta fSaledeta)
    {
        return toAjax(fSaledetaService.insertFSaledeta(fSaledeta));
    }

    /**
     * 修改销售明细管理
     */
    @PreAuthorize("@ss.hasPermi('system:saledeta:edit')")
    @Log(title = "销售明细管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FSaledeta fSaledeta)
    {
        return toAjax(fSaledetaService.updateFSaledeta(fSaledeta));
    }

    /**
     * 删除销售明细管理
     */
    @PreAuthorize("@ss.hasPermi('system:saledeta:remove')")
    @Log(title = "销售明细管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{saledetaIds}")
    public AjaxResult remove(@PathVariable Long[] saledetaIds)
    {
        return toAjax(fSaledetaService.deleteFSaledetaBySaledetaIds(saledetaIds));
    }
}
