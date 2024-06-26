package cn.lanqiao.system.service;

import java.util.List;

import cn.lanqiao.system.domain.FGoods;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * 商品Service接口
 *
 * @author lanqiao
 * @date 2024-05-15
 */
public interface IFGoodsService {

//    public List<FGoods> selectFGoodsListApi(FGoods fGoods);
    /**
     * 查询商品
     *
     * @param id 商品主键
     * @return 商品
     */
    public FGoods selectFGoodsById(Long id);

    /**
     * 查询商品列表
     *
     * @param fGoods 商品
     * @return 商品集合
     */
    public List<FGoods> selectFGoodsList(FGoods fGoods);

    /**
     * 查询商品父类列表
     *
     * @param fGoods 商品
     * @return 商品集合
     */
    public List<FGoods> selectFGoodsByParentId(Long fGoods);

    /**
     * 新增商品
     *
     * @param fGoods 商品
     * @return 结果
     */
    public int insertFGoods(FGoods fGoods);

    /**
     * 修改商品
     *
     * @param fGoods 商品
     * @return 结果
     */
    public int updateFGoods(FGoods fGoods);

    /**
     * 批量删除商品
     *
     * @param ids 需要删除的商品主键集合
     * @return 结果
     */
    public int deleteFGoodsByIds(Long[] ids);

    /**
     * 删除商品信息
     *
     * @param id 商品主键
     * @return 结果
     */
    public int deleteFGoodsById(Long id);

    /**
     * 根据商品名称查询
     *
     * @param goodsName
     * @return
     */
    public List<FGoods> selectMapGoodsName( String goodsName);

    /**
     * 根据商品名称查询
     *
     * @param goodsType
     * @return
     */
    public List<FGoods> selectGoodsType(String goodsType);

    /**
     * 根据商品编码查询
     *
     * @param coding
     * @return
     */
    public FGoods selectGoodsList(String coding);

    /**
     * 根据商品名称查询
     *
     * @param FGoods
     * @return
     */
    public int UpdateGoodsNum(FGoods FGoods);

    /**
     * 查询推荐、小于等于10元的商品、生鲜列表、日用品列表
     * @param sortNum
     * @param fGoods
     * @return
     */
    List<FGoods> recommendedList(int sortNum, FGoods fGoods);



    /**
     * 查询超盒算
     *
     * @param fGoods
     * @return
     */
    List<FGoods> selectFGoodsByFavorable( FGoods fGoods);

    /** 查询下单人数
     *
     * @param fGoods 商品
     * @return 商品集合
     */
    public List<FGoods> selectPeopleNum(FGoods fGoods);

//    /**
//     * 查询下单人数
//     *
//     * @param fGoods 商品
//     * @return 商品集合
//     */
//    public List<FGoods> selectPeopleNum2(FGoods fGoods);
    /**
     * 修改商品状态
     * @param fGoods
     * @return
     */
    int  updateFGoodsStatus(FGoods fGoods);

}
