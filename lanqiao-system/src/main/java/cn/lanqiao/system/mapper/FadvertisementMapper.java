package cn.lanqiao.system.mapper;

import cn.lanqiao.system.domain.Fadvertisement;

import java.util.List;

/**
 * 广告Mapper接口
 *
 * @author chuan
 * @date 2024-06-17
 */
public interface FadvertisementMapper {
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
     * 删除广告管理
     *
     * @param id 广告管理主键
     * @return 结果
     */
    public int deleteFadvertisementById(Long id);

    /**
     * 批量删除广告管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFadvertisementByIds(Long[] ids);

    public List<Fadvertisement> selectFadvertisementList();
}
