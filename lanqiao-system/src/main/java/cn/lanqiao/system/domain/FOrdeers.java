package cn.lanqiao.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.lanqiao.common.annotation.Excel;
import cn.lanqiao.common.core.domain.BaseEntity;

/**
 * 订单管理对象 f_ordeers
 * 
 * @author chuan
 * @date 2024-05-21
 */
public class FOrdeers extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单id */
    private Long ordersId;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private Long ordersNumber;

    /** 买家姓名 */
    @Excel(name = "买家姓名")
    private Long ordersUsersId;

    private FAddress address;

    public FAddress getAddress() {
        return address;
    }

    public void setAddress(FAddress address) {
        this.address = address;
    }

    public String getOrdersSysuserName() {
        return ordersSysuserName;
    }

    public void setOrdersSysuserName(String ordersSysuserName) {
        this.ordersSysuserName = ordersSysuserName;
    }

    /** 配送员 */
    @Excel(name = "配送员")
    private Long ordersSysuserId;

    /** 配送员 */
    @Excel(name = "配送员")
    private String ordersSysuserName;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private Long ordersPayMethod;

    /** 支付状态 */
    @Excel(name = "支付状态")
    private Long ordersPayStatuds;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private Long ordersStatus;

    /** 下单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ordersCreattime;

    /** 备注信息 */
    @Excel(name = "备注信息")
    private String ordersRemark;

    public void setOrdersId(Long ordersId) 
    {
        this.ordersId = ordersId;
    }

    public Long getOrdersId() 
    {
        return ordersId;
    }
    public void setOrdersNumber(Long ordersNumber) 
    {
        this.ordersNumber = ordersNumber;
    }

    public Long getOrdersNumber() 
    {
        return ordersNumber;
    }
    public void setOrdersUsersId(Long ordersUsersId) 
    {
        this.ordersUsersId = ordersUsersId;
    }

    public Long getOrdersUsersId() 
    {
        return ordersUsersId;
    }
    public void setOrdersSysuserId(Long ordersSysuserId) 
    {
        this.ordersSysuserId = ordersSysuserId;
    }

    public Long getOrdersSysuserId() 
    {
        return ordersSysuserId;
    }
    public void setOrdersPayMethod(Long ordersPayMethod) 
    {
        this.ordersPayMethod = ordersPayMethod;
    }

    public Long getOrdersPayMethod() 
    {
        return ordersPayMethod;
    }
    public void setOrdersPayStatuds(Long ordersPayStatuds) 
    {
        this.ordersPayStatuds = ordersPayStatuds;
    }

    public Long getOrdersPayStatuds() 
    {
        return ordersPayStatuds;
    }
    public void setOrdersStatus(Long ordersStatus) 
    {
        this.ordersStatus = ordersStatus;
    }

    public Long getOrdersStatus() 
    {
        return ordersStatus;
    }
    public void setOrdersCreattime(Date ordersCreattime) 
    {
        this.ordersCreattime = ordersCreattime;
    }

    public Date getOrdersCreattime() 
    {
        return ordersCreattime;
    }
    public void setOrdersRemark(String ordersRemark) 
    {
        this.ordersRemark = ordersRemark;
    }

    public String getOrdersRemark() 
    {
        return ordersRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ordersId", getOrdersId())
            .append("ordersNumber", getOrdersNumber())
            .append("ordersUsersId", getOrdersUsersId())
            .append("ordersSysuserId", getOrdersSysuserId())
            .append("ordersPayMethod", getOrdersPayMethod())
            .append("ordersPayStatuds", getOrdersPayStatuds())
            .append("ordersStatus", getOrdersStatus())
            .append("ordersCreattime", getOrdersCreattime())
            .append("ordersRemark", getOrdersRemark())
            .toString();
    }
}
