package cn.lanqiao.system.mapper;

import cn.lanqiao.common.core.domain.entity.Category;
import cn.lanqiao.common.core.domain.entity.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类Mapper接口
 * 
 * @author lanqiao
 * @date 2024-05-15
 */
public interface CategoryMapper 
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
     * 删除商品分类
     * 
     * @param deptId 商品分类主键
     * @return 结果
     */
    public int deleteCategoryByDeptId(Long deptId);

    /**
     * 批量删除商品分类
     * 
     * @param deptIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCategoryByDeptIds(Long[] deptIds);


    //开始
    /**
     *
     *
     * @param roleId 角色ID
     * @param deptCheckStrictly 部门树选择项是否关联显示
     * @return 选中部门列表
     */
    public List<Long> selectListByRoleId(@Param("roleId") Long roleId, @Param("deptCheckStrictly") boolean deptCheckStrictly);

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    public Category selectCateById(Long deptId);

    /**
     * 根据ID查询所有子部门
     *
     * @param deptId 部门ID
     * @return 部门列表
     */
    public List<Category> selectChildrenDeptById(Long deptId);

    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param deptId 部门ID
     * @return 子部门数
     */
    public int selectNormalChildrenDeptById(Long deptId);

    /**
     * 是否存在子节点
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public int hasChildByDeptId(Long deptId);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public int checkDeptExistUser(Long deptId);

    /**
     * 校验部门名称是否唯一
     *
     * @param deptName 部门名称
     * @param parentId 父部门ID
     * @return 结果
     */
    public Category checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

}
