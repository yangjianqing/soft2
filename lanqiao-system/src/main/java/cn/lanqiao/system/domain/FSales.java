package cn.lanqiao.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.lanqiao.common.annotation.Excel;
import cn.lanqiao.common.core.domain.BaseEntity;

/**
 * 销售记录管理对象 f_sales
 * 
 * @author chuan
 * @date 2024-05-16
 */
public class FSales extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 销售记录id */
    @Excel(name = "销售记录id")
    private Long salesId;

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private Long usersId;

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private String usersName;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal salesAllamount;

    /** 付款方式 */
    @Excel(name = "付款方式")
    private Long salesWay;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private Long salesStatus;

    /** 销售日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "销售日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date salesCreationtime;

    public void setSalesId(Long salesId) 
    {
        this.salesId = salesId;
    }

    public Long getSalesId() 
    {
        return salesId;
    }
    public void setUsersId(Long usersId) 
    {
        this.usersId = usersId;
    }

    public Long getUsersId() 
    {
        return usersId;
    }
    public void setSalesAllamount(BigDecimal salesAllamount) 
    {
        this.salesAllamount = salesAllamount;
    }

    public BigDecimal getSalesAllamount() 
    {
        return salesAllamount;
    }
    public void setSalesWay(Long salesWay) 
    {
        this.salesWay = salesWay;
    }

    public Long getSalesWay() 
    {
        return salesWay;
    }
    public void setSalesStatus(Long salesStatus) 
    {
        this.salesStatus = salesStatus;
    }

    public Long getSalesStatus() 
    {
        return salesStatus;
    }
    public void setSalesCreationtime(Date salesCreationtime) 
    {
        this.salesCreationtime = salesCreationtime;
    }

    public Date getSalesCreationtime() 
    {
        return salesCreationtime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("salesId", getSalesId())
            .append("usersId", getUsersId())
            .append("salesAllamount", getSalesAllamount())
            .append("salesWay", getSalesWay())
            .append("salesStatus", getSalesStatus())
            .append("salesCreationtime", getSalesCreationtime())
            .toString();
    }
}
