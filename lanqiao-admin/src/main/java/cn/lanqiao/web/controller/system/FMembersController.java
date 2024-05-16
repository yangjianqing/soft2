package cn.lanqiao.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.lanqiao.system.service.IFUsersService;
import cn.lanqiao.system.service.impl.FUsersServiceImpl;
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
import cn.lanqiao.system.domain.FMembers;
import cn.lanqiao.system.service.IFMembersService;
import cn.lanqiao.common.utils.poi.ExcelUtil;
import cn.lanqiao.common.core.page.TableDataInfo;

/**
 * 会员管理Controller
 *
 * @author chuan
 * @date 2024-05-15
 */
@RestController
@RequestMapping("/system/members")
public class FMembersController extends BaseController
{
    @Autowired
    private IFMembersService fMembersService;

    @Autowired
    private IFUsersService ifUsersService;

    /**
     * 查询会员管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:members:list')")
    @GetMapping("/list")
    public TableDataInfo list(FMembers fMembers)
    {
        startPage();
        List<FMembers> list = fMembersService.selectFMembersList(fMembers);
        return getDataTable(list);
    }

    /**
     * 导出会员管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:members:export')")
    @Log(title = "会员管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FMembers fMembers)
    {
        List<FMembers> list = fMembersService.selectFMembersList(fMembers);
        ExcelUtil<FMembers> util = new ExcelUtil<FMembers>(FMembers.class);
        util.exportExcel(response, list, "会员管理数据");
    }

    /**
     * 获取会员管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:members:query')")
    @GetMapping(value = "/{memberId}")
    public AjaxResult getInfo(@PathVariable("memberId") Long memberId)
    {
        return AjaxResult.success(fMembersService.selectFMembersByMemberId(memberId));
    }

    /**
     * 新增会员管理
     */
    @PreAuthorize("@ss.hasPermi('system:members:add')")
    @Log(title = "会员管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FMembers fMembers)
    {
        return toAjax(fMembersService.insertFMembers(fMembers));
    }

    /**
     * 修改会员管理
     */
    @PreAuthorize("@ss.hasPermi('system:members:edit')")
    @Log(title = "会员管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FMembers fMembers)
    {
        return toAjax(fMembersService.updateFMembers(fMembers));
    }

    /**
     * 删除会员管理
     */
    @PreAuthorize("@ss.hasPermi('system:members:remove')")
    @Log(title = "会员管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{memberIds}")
    public AjaxResult remove(@PathVariable Long[] memberIds)
    {
        return toAjax(fMembersService.deleteFMembersByMemberIds(memberIds));
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
