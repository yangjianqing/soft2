package cn.lanqiao.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.lanqiao.common.annotation.Excel;
import cn.lanqiao.common.core.domain.BaseEntity;

/**
 * 地址管理对象 f_address
 * 
 * @author chuan
 * @date 2024-05-15
 */
public class FAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 地址id */
    @Excel(name = "地址id")
    private Long addressId;

    /** 用户id */
    private String adderssUsersId;

    /** 地址排序 */
    @Excel(name = "地址排序")
    private String addressSort;

    /** 收货人 */
    @Excel(name = "收货人")
    private String addressConsigneeName;

    /** 性别 */
    @Excel(name = "性别")
    private String addressSex;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String addressPhone;

    /** 地址标签 */
    @Excel(name = "地址标签")
    private String addressLabel;

    /** 省级编号 */
    @Excel(name = "省级编号")
    private String addressProvinceCode;

    /** 省级名称 */
    @Excel(name = "省级名称")
    private String addressProvinceName;

    /** 市级编号 */
    @Excel(name = "市级编号")
    private String addressCityCode;

    /** 市级名称 */
    @Excel(name = "市级名称")
    private String addressCityName;

    /** 区级编号 */
    @Excel(name = "区级编号")
    private String addressDistrictCode;

    /** 区级名称 */
    @Excel(name = "区级名称")
    private String addressDistrictName;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String addressDetail;

    public void setAddressId(Long addressId) 
    {
        this.addressId = addressId;
    }

    public Long getAddressId() 
    {
        return addressId;
    }
    public void setAdderssUsersId(String adderssUsersId)
    {
        this.adderssUsersId = adderssUsersId;
    }

    public String getAdderssUsersId()
    {
        return adderssUsersId;
    }
    public void setAddressSort(String addressSort) 
    {
        this.addressSort = addressSort;
    }

    public String getAddressSort() 
    {
        return addressSort;
    }
    public void setAddressConsigneeName(String addressConsigneeName) 
    {
        this.addressConsigneeName = addressConsigneeName;
    }

    public String getAddressConsigneeName() 
    {
        return addressConsigneeName;
    }
    public void setAddressSex(String addressSex) 
    {
        this.addressSex = addressSex;
    }

    public String getAddressSex() 
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
    public void setAddressLabel(String addressLabel) 
    {
        this.addressLabel = addressLabel;
    }

    public String getAddressLabel() 
    {
        return addressLabel;
    }
    public void setAddressProvinceCode(String addressProvinceCode) 
    {
        this.addressProvinceCode = addressProvinceCode;
    }

    public String getAddressProvinceCode() 
    {
        return addressProvinceCode;
    }
    public void setAddressProvinceName(String addressProvinceName) 
    {
        this.addressProvinceName = addressProvinceName;
    }

    public String getAddressProvinceName() 
    {
        return addressProvinceName;
    }
    public void setAddressCityCode(String addressCityCode) 
    {
        this.addressCityCode = addressCityCode;
    }

    public String getAddressCityCode() 
    {
        return addressCityCode;
    }
    public void setAddressCityName(String addressCityName) 
    {
        this.addressCityName = addressCityName;
    }

    public String getAddressCityName() 
    {
        return addressCityName;
    }
    public void setAddressDistrictCode(String addressDistrictCode) 
    {
        this.addressDistrictCode = addressDistrictCode;
    }

    public String getAddressDistrictCode() 
    {
        return addressDistrictCode;
    }
    public void setAddressDistrictName(String addressDistrictName) 
    {
        this.addressDistrictName = addressDistrictName;
    }

    public String getAddressDistrictName() 
    {
        return addressDistrictName;
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
            .append("addressConsigneeName", getAddressConsigneeName())
            .append("addressSex", getAddressSex())
            .append("addressPhone", getAddressPhone())
            .append("addressLabel", getAddressLabel())
            .append("addressProvinceCode", getAddressProvinceCode())
            .append("addressProvinceName", getAddressProvinceName())
            .append("addressCityCode", getAddressCityCode())
            .append("addressCityName", getAddressCityName())
            .append("addressDistrictCode", getAddressDistrictCode())
            .append("addressDistrictName", getAddressDistrictName())
            .append("addressDetail", getAddressDetail())
            .toString();
    }
}
