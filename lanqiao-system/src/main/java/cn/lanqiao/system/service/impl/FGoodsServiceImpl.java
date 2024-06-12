package cn.lanqiao.system.service.impl;

import java.util.*;

import cn.lanqiao.common.core.domain.entity.Category;
import cn.lanqiao.common.core.domain.entity.SysUser;
import cn.lanqiao.common.core.page.TableDataInfo;
import cn.lanqiao.common.utils.DateUtils;
import cn.lanqiao.system.mapper.CategoryMapper;
import cn.lanqiao.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private SysUserMapper SysUserMapper;
    @Autowired
    private CategoryMapper categoryMapper;
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
    public List<FGoods> selectFGoodsList(FGoods fGoods) {

        List<FGoods> fGoods1 = fGoodsMapper.selectFGoodsList(fGoods);
        Set<Long> UserId = new HashSet<>();
        Set<Long> CateId=new HashSet<>();

        //遍历会员管理列表数据，判断usersId是否为空，不是则将usersId存储到Set<Long> usersIds = new HashSet<>();
        for (FGoods fm : fGoods1) {
            if(fm.getCreateUser() != null) {
                UserId.add(fm.getCreateUser());
            }
            if(fm.getCategoryId() != null) {
                CateId.add(fm.getCategoryId());
            }
        }

        // 根据会员列表数据的usersId来获取用户users列表数据
        List<SysUser> UsersList = new ArrayList<>();
        if(!UserId.isEmpty()) {
            UsersList=SysUserMapper.selectUserListCIds(new ArrayList<>(UserId));
        }
        // 根据分类列表数据的cateId来获取用户分类列表数据
        List<Category> CateList = new ArrayList<>();
        if(!CateId.isEmpty()) {
            CateList=categoryMapper.selectCateListCIds(new ArrayList<>(CateId));
        }


        //利用map集合键值对，将用户数据存储到map集合里面  (键：UsersId;值：UsersList)
        Map<Long, SysUser> busersMap = new HashMap<>();
        for (SysUser f : UsersList) {
            busersMap.put(f.getUserId(), f);
        }
        //利用map集合键值对，将分类数据存储到map集合里面  (键：CateId;值：CateList)
        Map<Long, Category> busersMap1 = new HashMap<>();
        for (Category c : CateList) {
            busersMap1.put(c.getDeptId(), c);
        }

        // 替换用户姓名
        //进行遍历会员列表数据
        for (FGoods fm : fGoods1) {
            //判断用户列表的用户id是否为空
            if (fm.getCreateUser() != null) {
                //调用map集合根据当前用户的用户id来拿到用户id里面用户列表的数据
                SysUser User = busersMap.get(fm.getCreateUser());
                //判断拿到的用户列表数据是否为空
                if (User != null) {
                    //将会员列表的usersName传递给会员列表的usersName
                    fm.setCreateUserName(User.getUserName());
                }
            }
            //判断用户列表的用户id是否为空
            if (fm.getUpdateUser() != null) {
                //调用map集合根据当前用户的用户id来拿到用户id里面用户列表的数据
                SysUser User1= busersMap.get(fm.getUpdateUser());
                //判断拿到的用户列表数据是否为空
                if (User1 != null) {
                    //将会员列表的usersName传递给会员列表的usersName
                    fm.setUpdateUserName(User1.getUserName());
                }
            }
            //判断用户列表的分类id是否为空
            if (fm.getCategoryId() != null) {
                //调用map集合根据当前用户的用户id来拿到用户id里面用户列表的数据
                Category User2= busersMap1.get(fm.getCategoryId());
                //判断拿到的用户列表数据是否为空
                if (User2 != null) {
                    //将会员列表的usersName传递给会员列表的usersName
                    fm.setCategoryName(User2.getDeptName());
                }
            }

        }
        return fGoods1;
    }


    /**
     * 根据父级分类查询
     * @param fGoods 商品
     * @return
     */
    @Override
    public List<FGoods> selectFGoodsByParentId(Long fGoods) {
        return fGoodsMapper.selectFGoodsByParentId(fGoods);
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
     * 根据商品名称查询
     * @param goodsName
     * @return
     */
    @Override
    public List<FGoods> selectGoodsName(String goodsName) {

        return fGoodsMapper.selectGoodsName(goodsName);
    }

    /**
     * 根据商品分类查询
     * @param goodsType
     * @return
     */
    @Override
    public List<FGoods> selectGoodsType(String goodsType) {
        return fGoodsMapper.selectGoodsType(goodsType);
    }

    /**
     *根据商品编码查询
     * @param coding
     * @return
     */
    @Override
    public FGoods selectGoodsList(Long coding) {
        FGoods fGoods = fGoodsMapper.selectGoodsList(coding);
        //判断商品库存是否为0
        if(fGoods.getNum()==0){
            return null;
        }
        return fGoods;
    }

    @Override
    public int UpdateGoodsNum(FGoods fGoods) {
        // 调用更新方法，只更新num字段
        return fGoodsMapper.UpdateGoodsNum(fGoods);
    }

    /**
     * 查询推荐、小于等于10元的商品、生鲜列表、日用品列表
     * @param sortNum
     * @param fGoods
     * @return
     */
    @Override
    public List<FGoods> recommendedList(int sortNum, FGoods fGoods) {
        List<FGoods> fGoods1 = null;
        if (sortNum ==1 ){
//            查询推荐
            fGoods1 = fGoodsMapper.selectRecommended(fGoods);
        }else if (sortNum ==2){
//            查询生鲜
            fGoods1 = fGoodsMapper.selectFGoodsByFreshList(fGoods);
        } else if(sortNum==3){
//            查询日常用品
            fGoods1 = fGoodsMapper.selectFGoodsByDailyList(fGoods);
        }
        else if(sortNum==4) {
//            查询10元商品
            fGoods1 = fGoodsMapper.selectFGoodsLessTen(fGoods);
        }
        return fGoods1;
    }




    /**
     * 查询超盒算
     * @param fGoods
     * @return
     */
    @Override
    public List<FGoods> selectFGoodsByFavorable(FGoods fGoods) {
        return fGoodsMapper.selectFGoodsByFavorable(fGoods);
    }




    /**
     * 新增商品分类信息
     *
     * @param fGoods 商品对象
     */
    public void insertCategory(FGoods  fGoods)
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
