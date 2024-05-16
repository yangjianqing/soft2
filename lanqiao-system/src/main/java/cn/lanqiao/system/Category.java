package cn.lanqiao.system;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.lanqiao.common.annotation.Excel;
import cn.lanqiao.common.core.domain.TreeEntity;

/**
 * 商品分类对象 category
 * 
 * @author lanqiao
 * @date 2024-05-15
 */
public class Category extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门id */
    private Long deptId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String deptName;

    /** 删除标志 */
    private String cFlag;

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }
    public void setcFlag(String cFlag) 
    {
        this.cFlag = cFlag;
    }

    public String getcFlag() 
    {
        return cFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deptId", getDeptId())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("deptName", getDeptName())
            .append("orderNum", getOrderNum())
            .append("cFlag", getcFlag())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
