package cn.lanqiao.system.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import cn.lanqiao.common.constant.UserConstants;
import cn.lanqiao.common.core.domain.TreeSelect;
import cn.lanqiao.common.core.domain.entity.Category;
import cn.lanqiao.common.core.domain.entity.SysDept;
import cn.lanqiao.common.core.domain.entity.SysRole;
import cn.lanqiao.common.core.domain.entity.SysUser;
import cn.lanqiao.common.exception.ServiceException;
import cn.lanqiao.common.utils.DateUtils;
import cn.lanqiao.common.utils.SecurityUtils;
import cn.lanqiao.common.utils.StringUtils;
import cn.lanqiao.common.utils.spring.SpringUtils;
import cn.lanqiao.system.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.lanqiao.system.mapper.CategoryMapper;
import cn.lanqiao.system.service.ICategoryService;

/**
 * 商品分类Service业务层处理
 * 
 * @author lanqiao
 * @date 2024-05-15
 */
@Service
public class CategoryServiceImpl implements ICategoryService
{
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private SysRoleMapper roleMapper;

    /**
     * 查询商品分类
     * 
     * @param deptId 商品分类主键
     * @return 商品分类
     */
    @Override
    public Category selectCategoryByDeptId(Long deptId)
    {
        return categoryMapper.selectCategoryByDeptId(deptId);
    }

    /**
     * 查询商品分类列表
     * 
     * @param category 商品分类
     * @return 商品分类
     */
    @Override
    public List<Category> selectCategoryList(Category category)
    {
        return categoryMapper.selectCategoryList(category);
    }

    /**
     * 新增商品分类
     * 
     * @param category 商品分类
     * @return 结果
     */
    @Override
    public int insertCategory(Category category)
    {
        category.setCreateTime(DateUtils.getNowDate());
        return categoryMapper.insertCategory(category);
    }

    /**
     * 修改商品分类
     * 
     * @param category 商品分类
     * @return 结果
     */
    @Override
    public int updateCategory(Category category)
    {
        category.setUpdateTime(DateUtils.getNowDate());
        return categoryMapper.updateCategory(category);
    }

    /**
     * 批量删除商品分类
     * 
     * @param deptIds 需要删除的商品分类主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByDeptIds(Long[] deptIds)
    {
        return categoryMapper.deleteCategoryByDeptIds(deptIds);
    }

    /**
     * 删除商品分类信息
     * 
     * @param deptId 商品分类主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByDeptId(Long deptId)
    {
        return categoryMapper.deleteCategoryByDeptId(deptId);
    }

    /**
     * 查询树表
     * @param category
     * @return
     */
    @Override
    public List<TreeSelect> selectCateTreeList(Category category)
        {
            List<Category> cates = this.selectCategoryList(category);
            List<TreeSelect> categories = buildDeptTreeSelect(cates);
            return categories;
        }

    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<Category> depts)
    {
        List<Category> deptTrees = buildDeptTree(depts);
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    @Override
    public List<Long> selectCategoryIdsListByRoleId(Long roleId)
    {
        SysRole role = roleMapper.selectRoleById(roleId);
        return categoryMapper.selectListByRoleId(roleId, role.isDeptCheckStrictly());
    }


    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
    public Category selectCateById(Long deptId)
    {
        return categoryMapper.selectCategoryByDeptId(deptId);
    }



//开始
    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param deptId 部门ID
     * @return 子部门数
     */
    @Override
    public int selectNormalChildrenDeptById(Long deptId)
    {
        return categoryMapper.selectNormalChildrenDeptById(deptId);
    }

    /**
     * 是否存在子节点
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public boolean hasChildByDeptId(Long deptId)
    {
        int result = categoryMapper.hasChildByDeptId(deptId);
        return result > 0;
    }

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDeptExistUser(Long deptId)
    {
        int result = categoryMapper.checkDeptExistUser(deptId);
        return result > 0;
    }

    /**
     * 校验部门名称是否唯一
     *
     * @param cate 部门信息
     * @return 结果
     */
    @Override
    public String checkDeptNameUnique(Category cate)
    {
        Long deptId = StringUtils.isNull(cate.getDeptId()) ? -1L : cate.getDeptId();
        Category info = categoryMapper.checkDeptNameUnique(cate.getDeptName(), cate.getParentId());
        if (StringUtils.isNotNull(info) && info.getDeptId().longValue() != deptId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 查询父部门的信息
     * @param category
     * @return
     */
    @Override
    public List<Category> selectMacroclassification(Category category) {
      return categoryMapper.selectMacroclassification(category);
    }

    @Override
    public List<Category> selectPicture(Category category) {
        return categoryMapper.selectPicture(category);
    }

//结束

    public List<Category> buildDeptTree(List<Category> cats)
    {
        List<Category> returnList = new ArrayList<Category>();
        List<Long> tempList = new ArrayList<Long>();
        for (Category cat : cats)
        {
            tempList.add(cat.getDeptId());
        }
        for (Category cat : cats)
        {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(cat.getParentId()))
            {
                recursionFn(cats, cat);
                returnList.add(cat);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = cats;
        }
        return returnList;
    }
    /**
     * 递归列表
     */
    private void recursionFn(List<Category> list, Category t)
    {
        // 得到子节点列表
        List<Category> childList = getChildList(list, t);
        t.setChildren(childList);
        for (Category tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<Category> getChildList(List<Category> list, Category t)
    {
        List<Category> tlist = new ArrayList<Category>();
        Iterator<Category> it = list.iterator();
        while (it.hasNext())
        {
            Category n = (Category) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getDeptId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<Category> list, Category t)
    {
        return getChildList(list, t).size() > 0;
    }
}
