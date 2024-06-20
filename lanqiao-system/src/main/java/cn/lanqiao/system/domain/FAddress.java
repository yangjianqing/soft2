package cn.lanqiao.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.lanqiao.common.annotation.Excel;
import cn.lanqiao.common.core.domain.BaseEntity;

/**
 * 地址管理对象 f_address
 * 
 * @author chuan
 * @date 2024-05-21
 */
public class FAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 地址id */
    @Excel(name = "地址id")
    private Long addressId;

    /** 用户id */
    private Long adderssUsersId;

    /** 地址排序 */
    @Excel(name = "地址排序")
    private Long addressSort;

    /** 收货人 */
    @Excel(name = "收货人")
    private String addressName;

    /** 性别 */
    @Excel(name = "性别")
    private Long addressSex;

    /** 手机号 */
    @Excel(name = "手机号")
    private String addressPhone;

    /** 地址标签 */
    @Excel(name = "地址标签")
    private Long addressLabel;

    /** 地址经度 */
    @Excel(name = "地址经度")
    private Double addressTude;

    /** 地址纬度 */
    @Excel(name = "地址纬度")
    private Double addressLatit;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String addressDetail;
    /** 详细地址 */
    @Excel(name = "详细地址")
    private String addressLevel;

    public String getAddressLevel() {
        return addressLevel;
    }

    public void setAddressLevel(String addressLevel) {
        this.addressLevel = addressLevel;
    }

    public void setAddressId(Long addressId)
    {
        this.addressId = addressId;
    }

    public Long getAddressId() 
    {
        return addressId;
    }
    public void setAdderssUsersId(Long adderssUsersId) 
    {
        this.adderssUsersId = adderssUsersId;
    }

    public Long getAdderssUsersId() 
    {
        return adderssUsersId;
    }
    public void setAddressSort(Long addressSort) 
    {
        this.addressSort = addressSort;
    }

    public Long getAddressSort() 
    {
        return addressSort;
    }
    public void setAddressName(String addressName) 
    {
        this.addressName = addressName;
    }

    public String getAddressName() 
    {
        return addressName;
    }
    public void setAddressSex(Long addressSex) 
    {
        this.addressSex = addressSex;
    }

    public Long getAddressSex() 
    {
        return addressSex;
    }
    public void setAddressPhone(String addressPhone) 
    {
        this.addressPhone = addressPhone;
    }

    public String getAddressPhone() 
    {
        return addressPhone;
    }
    public void setAddressLabel(Long addressLabel) 
    {
        this.addressLabel = addressLabel;
    }

    public Long getAddressLabel() 
    {
        return addressLabel;
    }
    public void setAddressTude(Double addressTude) 
    {
        this.addressTude = addressTude;
    }

    public Double getAddressTude() 
    {
        return addressTude;
    }
    public void setAddressLatit(Double addressLatit) 
    {
        this.addressLatit = addressLatit;
    }

    public Double getAddressLatit() 
    {
        return addressLatit;
    }
    public void setAddressDetail(String addressDetail) 
    {
        this.addressDetail = addressDetail;
    }

    public String getAddressDetail() 
    {
        return addressDetail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("addressId", getAddressId())
            .append("adderssUsersId", getAdderssUsersId())
            .append("addressSort", getAddressSort())
            .append("addressName", getAddressName())
            .append("addressSex", getAddressSex())
            .append("addressPhone", getAddressPhone())
            .append("addressLabel", getAddressLabel())
            .append("addressTude", getAddressTude())
            .append("addressLatit", getAddressLatit())
            .append("addressLevel", getAddressLevel())
            .append("addressDetail", getAddressDetail())
            .toString();
    }
}
