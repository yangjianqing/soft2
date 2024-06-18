package cn.lanqiao.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.lanqiao.common.utils.SecurityUtils;
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
import cn.lanqiao.system.domain.FUsers;
import cn.lanqiao.system.service.IFUsersService;
import cn.lanqiao.common.utils.poi.ExcelUtil;
import cn.lanqiao.common.core.page.TableDataInfo;

/**
 * 用户管理Controller
 *
 * @author chuan
 * @date 2024-05-20
 */
@RestController
@RequestMapping("/system/users")
public class FUsersController extends BaseController
{
    @Autowired
    private IFUsersService fUsersService;

    /**
     * 查询用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:users:list')")
    @GetMapping("/list")
    public TableDataInfo list(FUsers fUsers)
    {
        startPage();
        List<FUsers> list = fUsersService.selectFUsersList(fUsers);
        return getDataTable(list);
    }

    /**
     * 导出用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:users:export')")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FUsers fUsers)
    {
        List<FUsers> list = fUsersService.selectFUsersList(fUsers);
        ExcelUtil<FUsers> util = new ExcelUtil<FUsers>(FUsers.class);
        util.exportExcel(response, list, "用户管理数据");
    }

    /**
     * 获取用户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:users:query')")
    @GetMapping(value = "/{usersId}")
    public AjaxResult getInfo(@PathVariable("usersId") Long usersId)
    {
        return AjaxResult.success(fUsersService.selectFUsersByUsersId(usersId));
    }

    /**
     * 新增用户管理
     */
    @PreAuthorize("@ss.hasPermi('system:users:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FUsers fUsers)
    {
        return toAjax(fUsersService.insertFUsers(fUsers));
    }

    /**
     * 修改用户管理
     */
    @PreAuthorize("@ss.hasPermi('system:users:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FUsers fUsers)
    {
        return toAjax(fUsersService.updateFUsers(fUsers));
    }

    /**
     * 删除用户管理
     */
    @PreAuthorize("@ss.hasPermi('system:users:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{usersIds}")
    public AjaxResult remove(@PathVariable Long[] usersIds)
    {
        return toAjax(fUsersService.deleteFUsersByUsersIds(usersIds));
    }

    /**
     * 获取用户管理详细信息
     */
    @GetMapping(value = "/type/{usersId}")
    public AjaxResult getUsers(@PathVariable("usersId") Long usersId)
    {
        FUsers fUsers = fUsersService.selectFUsersByUsersId(usersId);
        return AjaxResult.success().put("usersList",fUsers);
    }

    /**
     * 根据电话号码获取用户信息
     */
    @GetMapping(value = "/member/{memberPhone}")
    public AjaxResult selectMemberName(@PathVariable("memberPhone") String memberPhone)
    {
        FUsers fUsers = fUsersService.selectUsersusersPhone(memberPhone);
        if (fUsers == null || fUsers.getUsersName() == null || fUsers.getMemberTotal() == null) {
            return AjaxResult.error("查无此会员 请重新输入或新增会员");
        }
        return AjaxResult.success(fUsers);
    }
}
