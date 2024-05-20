package cn.lanqiao.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.lanqiao.common.core.domain.entity.Category;
import cn.lanqiao.common.core.domain.entity.SysDept;
import cn.lanqiao.system.service.ICategoryService;
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


}
