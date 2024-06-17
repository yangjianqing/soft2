package cn.lanqiao.system.service.impl;

import cn.lanqiao.system.domain.FGoods;
import cn.lanqiao.system.domain.Fadvertisement;
import cn.lanqiao.system.mapper.FGoodsMapper;
import cn.lanqiao.system.mapper.FadvertisementMapper;
import cn.lanqiao.system.service.FadvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 广告Service业务层处理
 *
 * @author chuan
 * @date 2024-06-17
 */
@Service
public class FadvertisementSeiviceImpl implements FadvertisementService {
    @Autowired
    private FadvertisementMapper fadvertisementMapper;
    @Autowired
    private FGoodsMapper fGoodsMapper;
    @Override
    public List<Fadvertisement> selectFadvertisementList() {
        List<Fadvertisement> fadvertisements = fadvertisementMapper.selectFadvertisementList();
        for (Fadvertisement fadvertisement : fadvertisements) {
            FGoods fGoods = fGoodsMapper.selectFGoodsById(fadvertisement.getGoodsId());
            fadvertisement.setfGoods(fGoods);
        }
        return fadvertisements;
    }
}
