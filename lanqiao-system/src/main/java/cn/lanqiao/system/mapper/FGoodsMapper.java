package cn.lanqiao.system.mapper;

import java.util.List;

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
}
