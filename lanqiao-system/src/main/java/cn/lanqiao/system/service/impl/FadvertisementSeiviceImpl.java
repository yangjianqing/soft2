package cn.lanqiao.system.service.impl;

import cn.lanqiao.system.domain.FAddress;
import cn.lanqiao.system.domain.FGoods;
import cn.lanqiao.system.domain.FUsers;
import cn.lanqiao.system.domain.Fadvertisement;
import cn.lanqiao.system.mapper.FGoodsMapper;
import cn.lanqiao.system.mapper.FadvertisementMapper;
import cn.lanqiao.system.service.FadvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    /**
     * 查询广告管理
     *
     * @param id 广告管理主键
     * @return 广告管理
     */
    @Override
    public Fadvertisement selectFadvertisementById(Long id)
    {
        return fadvertisementMapper.selectFadvertisementById(id);
    }

    /**
     * 查询广告管理列表
     *
     * @param fadvertisement 广告管理
     * @return 广告管理
     */
    @Override
    public List<Fadvertisement> selectFadvertisementLists(Fadvertisement fadvertisement)
    {
        List<Fadvertisement> fAdvertisements = fadvertisementMapper.selectFadvertisementLists(fadvertisement);
        // 创建存储商品id的存储器
        Set<Long> goodsIds = new HashSet<>();
        for (Fadvertisement fAdvertisement : fAdvertisements) {
            if (fAdvertisement.getGoodsId() != null) {
                goodsIds.add(fAdvertisement.getGoodsId());
            }
        }

        // 根据订单列表数据的goodsIds来获取用户地址列表的数据
        List<FGoods> FGoodsList = new ArrayList<>();
        if (!goodsIds.isEmpty()) {
            FGoodsList = fGoodsMapper.selectListName(new ArrayList<>(goodsIds));
        }

        Map<Long, FGoods> goodsMap = new HashMap<>();
        for (FGoods fGoods : FGoodsList) {
            goodsMap.put(fGoods.getId(),fGoods);
        }

        for (Fadvertisement fAdvertisement : fAdvertisements) {
            if (fAdvertisement.getGoodsId() != null) {
                FGoods fGoods = goodsMap.get(fAdvertisement.getGoodsId());
                if (fGoods != null) {
                    fAdvertisement.setfGoods(fGoods);
                }
            }
        }
        return fAdvertisements;
    }

    /**
     * 新增广告管理
     *
     * @param fadvertisement 广告管理
     * @return 结果
     */
    @Override
    public int insertFadvertisement(Fadvertisement fadvertisement)
    {
        return fadvertisementMapper.insertFadvertisement(fadvertisement);
    }

    /**
     * 修改广告管理
     *
     * @param fadvertisement 广告管理
     * @return 结果
     */
    @Override
    public int updateFadvertisement(Fadvertisement fadvertisement)
    {
        return fadvertisementMapper.updateFadvertisement(fadvertisement);
    }

    /**
     * 批量删除广告管理
     *
     * @param ids 需要删除的广告管理主键
     * @return 结果
     */
    @Override
    public int deleteFadvertisementByIds(Long[] ids)
    {
        return fadvertisementMapper.deleteFadvertisementByIds(ids);
    }

    /**
     * 删除广告管理信息
     *
     * @param id 广告管理主键
     * @return 结果
     */
    @Override
    public int deleteFadvertisementById(Long id)
    {
        return fadvertisementMapper.deleteFadvertisementById(id);
    }

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
