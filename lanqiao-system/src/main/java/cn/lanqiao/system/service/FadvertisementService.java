package cn.lanqiao.system.service;

import java.util.List;
import cn.lanqiao.system.domain.Fadvertisement;

/**
 * 广告Service接口
 *
 * @author lanqiao
 * @date 2024-06-17
 */
public interface FadvertisementService {
    /**
     * 查询广告管理
     *
     * @param id 广告管理主键
     * @return 广告管理
     */
    public Fadvertisement selectFadvertisementById(Long id);

    /**
     * 查询广告管理列表
     *
     * @param fadvertisement 广告管理
     * @return 广告管理集合
     */
    public List<Fadvertisement> selectFadvertisementLists(Fadvertisement fadvertisement);

    /**
     * 新增广告管理
     *
     * @param fadvertisement 广告管理
     * @return 结果
     */
    public int insertFadvertisement(Fadvertisement fadvertisement);

    /**
     * 修改广告管理
     *
     * @param fadvertisement 广告管理
     * @return 结果
     */
    public int updateFadvertisement(Fadvertisement fadvertisement);

    /**
     * 批量删除广告管理
     *
     * @param ids 需要删除的广告管理主键集合
     * @return 结果
     */
    public int deleteFadvertisementByIds(Long[] ids);

    /**
     * 删除广告管理信息
     *
     * @param id 广告管理主键
     * @return 结果
     */
    public int deleteFadvertisementById(Long id);

    /**
     * 查询广告数据
     *
     */
    List<Fadvertisement> selectFadvertisementList();
}
