package cn.lanqiao.api.controller;

import cn.lanqiao.active.domain.YActivity;
import cn.lanqiao.active.service.IYActivityService;
import cn.lanqiao.common.annotation.Log;
import cn.lanqiao.common.core.controller.BaseController;
import cn.lanqiao.common.core.domain.AjaxResult;
import cn.lanqiao.common.core.domain.entity.SysUser;
import cn.lanqiao.common.core.page.TableDataInfo;
import cn.lanqiao.common.enums.BusinessType;
import cn.lanqiao.common.utils.poi.ExcelUtil;
import cn.lanqiao.system.domain.FUsers;
import cn.lanqiao.system.mapper.FUsersMapper;
import cn.lanqiao.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 活动信息Controller
 * 
 * @author yang
 * @date 2023-04-11
 */
@RestController
@RequestMapping("/api")

public class MoblieApiController extends BaseController
{

    @Autowired
    private FUsersMapper fUsersService;
    /**
     * 查询活动信息列表
     */

    @GetMapping("/userlist")
    public TableDataInfo list(FUsers fUsers)
    {
        startPage();
        List<FUsers> list = fUsersService.selectFUsersList(fUsers);
        return getDataTable(list);
    }




}
