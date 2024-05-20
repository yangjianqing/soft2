package cn.lanqiao.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.lanqiao.common.core.domain.entity.Category;
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
import cn.lanqiao.system.service.ICategoryService;
import cn.lanqiao.common.utils.poi.ExcelUtil;

/**
 * 商品分类Controller
 * 
 * @author lanqiao
 * @date 2024-05-15
 */
@RestController
@RequestMapping("/system/category")
public class CategoryController extends BaseController
{
    @Autowired
    private ICategoryService categoryService;

    /**
     * 查询商品分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:list')")
    @GetMapping("/list")
    public AjaxResult list(Category category)
    {
        List<Category> list = categoryService.selectCategoryList(category);
        return AjaxResult.success(list);
    }

    /**
     * 导出商品分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:export')")
    @Log(title = "商品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Category category)
    {
        List<Category> list = categoryService.selectCategoryList(category);
        ExcelUtil<Category> util = new ExcelUtil<Category>(Category.class);
        util.exportExcel(response, list, "商品分类数据");
    }

    /**
     * 获取商品分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:category:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable("deptId") Long deptId)
    {
        return AjaxResult.success(categoryService.selectCategoryByDeptId(deptId));
    }

    /**
     * 新增商品分类
     */
    @PreAuthorize("@ss.hasPermi('system:category:add')")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Category category)
    {
        return toAjax(categoryService.insertCategory(category));
    }

    /**
     * 修改商品分类
     */
    @PreAuthorize("@ss.hasPermi('system:category:edit')")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Category category)
    {
        return toAjax(categoryService.updateCategory(category));
    }

    /**
     * 删除商品分类
     */
    @PreAuthorize("@ss.hasPermi('system:category:remove')")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deptIds}")
    public AjaxResult remove(@PathVariable Long[] deptIds)
    {
        return toAjax(categoryService.deleteCategoryByDeptIds(deptIds));
    }
}
