package cn.lanqiao.system.domain;

import java.util.Date;
import java.util.List;

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
    private String ordersNumber;

    /** 买家id */
    @Excel(name = "买家id")
    private Long ordersUsersId;

    /** 买家姓名 */
    @Excel(name = "买家姓名")
    private String ordersUsersName;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String usersPhone;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String addressDetail;

    /** 地址实体类 */
    @Excel(name = "地址实体类")
    private FAddress address;

    /** 用户实体类 */
    @Excel(name = "用户实体类")
    private FUsers fUsers;

    /** 用户实体类 */
    @Excel(name = "用户实体类")
    private List<FOrderPartslist> fOrderPartslist;

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

    public FOrdeers() {
    }

    public FOrdeers(String ordersNumber, Long ordersPayMethod, Long ordersPayStatuds, Long ordersStatus) {
        this.ordersNumber = ordersNumber;
        this.ordersPayMethod = ordersPayMethod;
        this.ordersPayStatuds = ordersPayStatuds;
        this.ordersStatus = ordersStatus;
    }

    public FOrdeers(String ordersNumber,Long ordersUsersId, Long ordersPayMethod, Long ordersPayStatuds, Long ordersStatus) {
        this.ordersNumber = ordersNumber;
        this.ordersUsersId = ordersUsersId;
        this.ordersPayMethod = ordersPayMethod;
        this.ordersPayStatuds = ordersPayStatuds;
        this.ordersStatus = ordersStatus;
    }

    public FOrdeers(String ordersNumber, Long ordersUsersId, Long ordersSysuserId, Long ordersPayMethod, Long ordersPayStatuds, Long ordersStatus, String ordersRemark) {
        this.ordersNumber = ordersNumber;
        this.ordersUsersId = ordersUsersId;
        this.ordersSysuserId = ordersSysuserId;
        this.ordersPayMethod = ordersPayMethod;
        this.ordersPayStatuds = ordersPayStatuds;
        this.ordersStatus = ordersStatus;
        this.ordersRemark = ordersRemark;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getUsersPhone() {
        return usersPhone;
    }

    public void setUsersPhone(String usersPhone) {
        this.usersPhone = usersPhone;
    }

    public String getOrdersUsersName() {
        return ordersUsersName;
    }

    public void setOrdersUsersName(String ordersUsersName) {
        this.ordersUsersName = ordersUsersName;
    }

    public List<FOrderPartslist> getfOrderPartslist() {
        return fOrderPartslist;
    }

    public void setfOrderPartslist(List<FOrderPartslist> fOrderPartslist) {
        this.fOrderPartslist = fOrderPartslist;
    }

    public FUsers getfUsers() {
        return fUsers;
    }

    public void setfUsers(FUsers fUsers) {
        this.fUsers = fUsers;
    }

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


    public void setOrdersId(Long ordersId)
    {
        this.ordersId = ordersId;
    }

    public Long getOrdersId() 
    {
        return ordersId;
    }
    public void setOrdersNumber(String ordersNumber)
    {
        this.ordersNumber = ordersNumber;
    }

    public String getOrdersNumber()
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

    public FOrdeers(Long ordersId, String ordersNumber, Long ordersUsersId, String ordersUsersName, FAddress address, FUsers fUsers, List<FOrderPartslist> fOrderPartslist, Long ordersSysuserId, String ordersSysuserName, Long ordersPayMethod, Long ordersPayStatuds, Long ordersStatus, Date ordersCreattime, String ordersRemark) {
        this.ordersId = ordersId;
        this.ordersNumber = ordersNumber;
        this.ordersUsersId = ordersUsersId;
        this.ordersUsersName = ordersUsersName;
        this.address = address;
        this.fUsers = fUsers;
        this.fOrderPartslist = fOrderPartslist;
        this.ordersSysuserId = ordersSysuserId;
        this.ordersSysuserName = ordersSysuserName;
        this.ordersPayMethod = ordersPayMethod;
        this.ordersPayStatuds = ordersPayStatuds;
        this.ordersStatus = ordersStatus;
        this.ordersCreattime = ordersCreattime;
        this.ordersRemark = ordersRemark;
    }
}
