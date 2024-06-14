package cn.lanqiao.system.service;

import java.util.List;

import cn.lanqiao.common.core.domain.TreeSelect;
import cn.lanqiao.common.core.domain.entity.Category;
import cn.lanqiao.common.core.domain.entity.SysDept;

/**
 * 商品分类Service接口
 * 
 * @author lanqiao
 * @date 2024-05-15
 */
public interface ICategoryService 
{
    /**
     * 查询商品分类
     * 
     * @param deptId 商品分类主键
     * @return 商品分类
     */
    public Category selectCategoryByDeptId(Long deptId);

    /**
     * 查询商品分类列表
     * 
     * @param category 商品分类
     * @return 商品分类集合
     */
    public List<Category> selectCategoryList(Category category);

    /**
     * 新增商品分类
     * 
     * @param category 商品分类
     * @return 结果
     */
    public int insertCategory(Category category);

    /**
     * 修改商品分类
     * 
     * @param category 商品分类
     * @return 结果
     */
    public int updateCategory(Category category);

    /**
     * 批量删除商品分类
     * 
     * @param deptIds 需要删除的商品分类主键集合
     * @return 结果
     */
    public int deleteCategoryByDeptIds(Long[] deptIds);

    /**
     * 删除商品分类信息
     * 
     * @param deptId 商品分类主键
     * @return 结果
     */
    public int deleteCategoryByDeptId(Long deptId);

    /**
     * 查询树表数据
     * @param category
     * @return
     */
    public List<TreeSelect> selectCateTreeList(Category category);
    /**
     * 构建前端所需要树结构
     *
     * @param cats 部门列表
     * @return 树结构列表
     */
    public List<Category> buildDeptTree(List<Category> cats);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param category 部门列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildDeptTreeSelect(List<Category> category);
    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    public List<Long> selectCategoryIdsListByRoleId(Long roleId);
    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    public Category selectCateById(Long deptId);



    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param deptId 部门ID
     * @return 子部门数
     */
    public int selectNormalChildrenDeptById(Long deptId);

    /**
     * 是否存在部门子节点
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public boolean hasChildByDeptId(Long deptId);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkDeptExistUser(Long deptId);

    /**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     * @return 结果
     */
    public String checkDeptNameUnique(Category dept);

    /**
     * 查询父部门
     * @param category
     * @return
     */
    List<Category>selectMacroclassification(Category category); /**

    /**
     * 查询图片
     * @param category
     * @return
     */
    List<Category> selectPicture(Category category);

}
