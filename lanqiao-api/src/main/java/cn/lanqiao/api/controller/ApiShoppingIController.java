package cn.lanqiao.api.controller;

import cn.lanqiao.common.core.controller.BaseController;
import cn.lanqiao.common.core.page.TableDataInfo;
import cn.lanqiao.system.domain.FGoods;
import cn.lanqiao.system.domain.FOrdeers;
import cn.lanqiao.system.domain.FOrderPartslist;
import cn.lanqiao.system.service.IFGoodsService;

import cn.lanqiao.system.service.IFOrdeersService;
import cn.lanqiao.system.service.IFOrderPartslistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.lanqiao.common.utils.PageUtils.startPage;
@RestController
@RequestMapping("/fresh")
public class ApiShoppingIController extends BaseController {
    @Autowired
    private IFGoodsService fGoodsService;
    @Autowired
    private IFOrdeersService fOrdeersService;
    @Autowired
    private IFOrderPartslistService fOrderPartslistService;

    /**
     * 查询商品列表
     */

    @GetMapping("/goodsList")
    public TableDataInfo list(FGoods fGoods)
    {
        startPage();
        List<FGoods> list = fGoodsService.selectFGoodsList(fGoods);
        return getDataTable(list);
    }
    /**
     * 查询订单管理列表
     */
    @GetMapping("/ordeersList")
    public TableDataInfo list(FOrdeers fOrdeers)
    {
        startPage();
        List<FOrdeers> list = fOrdeersService.selectFOrdeersList(fOrdeers);
        return getDataTable(list);
    }
    /**
     * 查询订单明细列表
     */
    @GetMapping("/orderPartsList")
    public TableDataInfo list(FOrderPartslist fOrderPartslist)
    {
        startPage();
        List<FOrderPartslist> list = fOrderPartslistService.selectFOrderPartslistList(fOrderPartslist);
        return getDataTable(list);
    }


}
