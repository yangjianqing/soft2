package cn.lanqiao.system.domain;

import java.math.BigDecimal;
import java.util.List;

import cn.lanqiao.common.core.domain.entity.Category;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.lanqiao.common.annotation.Excel;
import cn.lanqiao.common.core.domain.BaseEntity;

/**
 * 商品对象 f_goods
 * 
 * @author lanqiao
 * @date 2024-05-15
 */
public class FGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String name;

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    /** 商品规格 */
    @Excel(name = "商品规格")
    private String specification;

    /** 类别 */
    @Excel(name = "类别")
    private Long categoryId;

    @Excel(name = "编码")
    private Long coding;


    public Long getCoding() {
        return coding;
    }

    public void setCoding(Long coding) {
        this.coding = coding;
    }

    /** 类别 */
    @Excel(name = "创建人姓名")
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /** 商品价格 */
    @Excel(name = "商品价格")
    private BigDecimal price;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Excel(name = "单位")
    private String unit;

    /** 图片 */
    @Excel(name = "图片")
    private String image;

    /** 描述信息 */
    @Excel(name = "描述信息")
    private String description;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long createUser;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createUserName;

    /** 修改人 */
    @Excel(name = "修改人")
    private Long updateUser;

    /** 修改人 */
    @Excel(name = "修改人")
    private String updateUserName;

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }



    /** 商品数量 */
    @Excel(name = "商品数量")
    private long num;

    /** 结账商品数量 */
    @Excel(name = "结账商品数量")
    private Long quantity;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    /** 商品分类信息 */
    private List<Category> categoryList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setCreateUser(Long createUser) 
    {
        this.createUser = createUser;
    }

    public Long getCreateUser() 
    {
        return createUser;
    }
    public void setUpdateUser(Long updateUser) 
    {
        this.updateUser = updateUser;
    }

    public Long getUpdateUser() 
    {
        return updateUser;
    }

    public List<Category> getCategoryList()
    {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList)
    {
        this.categoryList = categoryList;
    }

    @Override
    public String toString() {
//        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//            .append("id", getId())
//            .append("name", getName())
//            .append("categoryId", getCategoryId())
//            .append("price", getPrice())
//            .append("unit", getUnit())
//            .append("coding", getCoding())
//            .append("image", getImage())
//            .append("description", getDescription())
//            .append("status", getStatus())
//            .append("createTime", getCreateTime())
//            .append("updateTime", getUpdateTime())
//            .append("createUser", getCreateUser())
//            .append("createUser", getSpecification())
//            .append("updateUser", getUpdateUser())
//            .append("categoryList", getCategoryList())
//            .toString();
        return "FGoods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", coding=" + coding +
                ", categoryName='" + categoryName + '\'' +
                ", price=" + price +
                ", unit='" + unit + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createUser=" + createUser +
                ", createUserName='" + createUserName + '\'' +
                ", updateUser=" + updateUser +
                ", updateUserName='" + updateUserName + '\'' +
                ", num=" + num +
                ", quantity=" + quantity +
                ", specifications='" + specification + '\'' +
                ", categoryList=" + categoryList +
                '}';
    }


}
