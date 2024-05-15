package cn.lanqiao.web.controller.system;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.lanqiao.common.core.domain.entity.SysDictData;
import cn.lanqiao.common.utils.StringUtils;
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
import cn.lanqiao.system.domain.FAddress;
import cn.lanqiao.system.service.IFAddressService;
import cn.lanqiao.common.utils.poi.ExcelUtil;
import cn.lanqiao.common.core.page.TableDataInfo;

/**
 * 地址管理Controller
 * 
 * @author chuan
 * @date 2024-05-15
 */
@RestController
@RequestMapping("/system/address")
public class FAddressController extends BaseController
{
    @Autowired
    private IFAddressService fAddressService;

    /**
     * 查询地址管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:address:list')")
    @GetMapping("/list")
    public TableDataInfo list(FAddress fAddress)
    {
        startPage();
        List<FAddress> list = fAddressService.selectFAddressList(fAddress);
        return getDataTable(list);
    }

    /**
     * 导出地址管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:address:export')")
    @Log(title = "地址管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FAddress fAddress)
    {
        List<FAddress> list = fAddressService.selectFAddressList(fAddress);
        ExcelUtil<FAddress> util = new ExcelUtil<FAddress>(FAddress.class);
        util.exportExcel(response, list, "地址管理数据");
    }

    /**
     * 获取地址管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:address:query')")
    @GetMapping(value = "/{addressId}")
    public AjaxResult getInfo(@PathVariable("addressId") Long addressId)
    {
        return AjaxResult.success(fAddressService.selectFAddressByAddressId(addressId));
    }

    /**
     * 新增地址管理
     */
    @PreAuthorize("@ss.hasPermi('system:address:add')")
    @Log(title = "地址管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FAddress fAddress)
    {
        return toAjax(fAddressService.insertFAddress(fAddress));
    }

    /**
     * 修改地址管理
     */
    @PreAuthorize("@ss.hasPermi('system:address:edit')")
    @Log(title = "地址管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FAddress fAddress)
    {
        return toAjax(fAddressService.updateFAddress(fAddress));
    }

    /**
     * 删除地址管理
     */
    @PreAuthorize("@ss.hasPermi('system:address:remove')")
    @Log(title = "地址管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{addressIds}")
    public AjaxResult remove(@PathVariable Long[] addressIds)
    {
        return toAjax(fAddressService.deleteFAddressByAddressIds(addressIds));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{usersId}")
    public AjaxResult dictType(@PathVariable String usersId)
    {
        List<SysDictData> data = fAddressService.selectUsersIdByType(usersId);
        return AjaxResult.success(data);
    }
}
