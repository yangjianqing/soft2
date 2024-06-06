package cn.lanqiao.system.mapper;

import java.util.List;
import java.util.Map;

import cn.lanqiao.common.core.domain.entity.Category;
import cn.lanqiao.system.domain.FGoods;

/**
 * 商品Mapper接口
 * 
 * @author lanqiao
 * @date 2024-05-15
 */
public interface FGoodsMapper 
{
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
     * 删除商品
     * 
     * @param id 商品主键
     * @return 结果
     */
    public int deleteFGoodsById(Long id);

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFGoodsByIds(Long[] ids);

    /**
     * 批量删除商品分类
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCategoryByDeptIds(Long[] ids);
    
    /**
     * 批量新增商品分类
     * 
     * @param categoryList 商品分类列表
     * @return 结果
     */
    public int batchCategory(List<Category> categoryList);
    

    /**
     * 通过商品主键删除商品分类信息
     * 
     * @param id 商品ID
     * @return 结果
     */
    public int deleteCategoryByDeptId(Long id);
    /**
     * 根据Id查
     * @param GoodsId
     * @return
     */
    List<FGoods> selectListName(List<Long> GoodsId);

    /**
     *根据商品名称查询
     * @param
     * @return
     */
    public   List<FGoods> selectGoodsName(String goodsName);
    /**
     *根据商品分类称查询
     * @param
     * @return
     */
    public   List<FGoods> selectGoodsType(String gooodsType);

    /**
     *根据商品名称查询
     * @param coding
     * @return
     */
    public   FGoods selectGoodsList(Long coding); /**
     *根据商品编码增减数量
     * @param fGoods
     * @return
     */
    public   int  UpdateGoodsNum(FGoods fGoods);

    /**
     * 查询优惠并排序
     * @param fGoods
     * @return
     */
     List<FGoods> selectProfit(FGoods fGoods);

}
