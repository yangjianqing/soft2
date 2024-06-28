package cn.lanqiao.api.controller;

import cn.lanqiao.common.core.domain.AjaxResult;
import cn.lanqiao.framework.config.WebSocketServerUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControllerMenuSon {
    @GetMapping("/sendMessage/{uId}")
    public AjaxResult sendMessage( @PathVariable("uId") Long uId) {
        try {
            WebSocketServerUtil.sendInfo("uId", uId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.success();
    }
}
