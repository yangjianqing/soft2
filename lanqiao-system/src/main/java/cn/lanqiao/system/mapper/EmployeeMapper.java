package cn.lanqiao.system.mapper;

import java.util.List;
import cn.lanqiao.system.domain.Employee;

/**
 * 员工信息Mapper接口
 * 
 * @author lanqiao
 * @date 2024-05-14
 */
public interface EmployeeMapper 
{
    /**
     * 查询员工信息
     * 
     * @param id 员工信息主键
     * @return 员工信息
     */
    public Employee selectEmployeeById(Long id);

    /**
     * 查询员工信息列表
     * 
     * @param employee 员工信息
     * @return 员工信息集合
     */
    public List<Employee> selectEmployeeList(Employee employee);

    /**
     * 新增员工信息
     * 
     * @param employee 员工信息
     * @return 结果
     */
    public int insertEmployee(Employee employee);

    /**
     * 修改员工信息
     * 
     * @param employee 员工信息
     * @return 结果
     */
    public int updateEmployee(Employee employee);

    /**
     * 删除员工信息
     * 
     * @param id 员工信息主键
     * @return 结果
     */
    public int deleteEmployeeById(Long id);

    /**
     * 批量删除员工信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEmployeeByIds(Long[] ids);
}
