package cn.lanqiao.system.service.impl;

import java.util.List;

import cn.lanqiao.common.core.domain.entity.Category;
import cn.lanqiao.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import cn.lanqiao.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import cn.lanqiao.system.mapper.FGoodsMapper;
import cn.lanqiao.system.domain.FGoods;
import cn.lanqiao.system.service.IFGoodsService;

/**
 * 商品Service业务层处理
 * 
 * @author lanqiao
 * @date 2024-05-15
 */
@Service
public class FGoodsServiceImpl implements IFGoodsService 
{
    @Autowired
    private FGoodsMapper fGoodsMapper;

    /**
     * 查询商品
     * 
     * @param id 商品主键
     * @return 商品
     */
    @Override
    public FGoods selectFGoodsById(Long id)
    {
        return fGoodsMapper.selectFGoodsById(id);
    }

    /**
     * 查询商品列表
     * 
     * @param fGoods 商品
     * @return 商品
     */
    @Override
    public List<FGoods> selectFGoodsList(FGoods fGoods)
    {
        return fGoodsMapper.selectFGoodsList(fGoods);
    }

    /**
     * 新增商品
     * 
     * @param fGoods 商品
     * @return 结果
     */
    @Transactional
    @Override
    public int insertFGoods(FGoods fGoods)
    {
        fGoods.setCreateTime(DateUtils.getNowDate());
        int rows = fGoodsMapper.insertFGoods(fGoods);
        insertCategory(fGoods);
        return rows;
    }

    /**
     * 修改商品
     * 
     * @param fGoods 商品
     * @return 结果
     */
    @Transactional
    @Override
    public int updateFGoods(FGoods fGoods)
    {
        fGoods.setUpdateTime(DateUtils.getNowDate());
        fGoodsMapper.deleteCategoryByDeptId(fGoods.getId());
        insertCategory(fGoods);
        return fGoodsMapper.updateFGoods(fGoods);
    }

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的商品主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteFGoodsByIds(Long[] ids)
    {
        fGoodsMapper.deleteCategoryByDeptIds(ids);
        return fGoodsMapper.deleteFGoodsByIds(ids);
    }

    /**
     * 删除商品信息
     * 
     * @param id 商品主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteFGoodsById(Long id)
    {
        fGoodsMapper.deleteCategoryByDeptId(id);
        return fGoodsMapper.deleteFGoodsById(id);
    }

    /**
     * 新增商品分类信息
     * 
     * @param fGoods 商品对象
     */
    public void insertCategory(FGoods fGoods)
    {
        List<Category> categoryList = fGoods.getCategoryList();
        Long id = fGoods.getId();
        if (StringUtils.isNotNull(categoryList))
        {
            List<Category> list = new ArrayList<Category>();
            for (Category category : categoryList)
            {
                category.setDeptId(id);
                list.add(category);
            }
            if (list.size() > 0)
            {
                fGoodsMapper.batchCategory(list);
            }
        }
    }
}
