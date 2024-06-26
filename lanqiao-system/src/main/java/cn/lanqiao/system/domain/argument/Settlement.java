package cn.lanqiao.system.domain.argument;
import cn.lanqiao.common.annotation.Excel;
import java.util.Set;


public class Settlement {
    /** 用户电话号码 */
    @Excel(name = "用户电话号码")
    private String usersPhone;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String ordersNumber;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private Set<String> coDings;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private Long ordersPayMethod;

    /** 支付状态 */
    @Excel(name = "支付状态")
    private Long ordersPayStatuds;

    /** 订单备注信息 */
    @Excel(name = "订单备注信息")
    private String ordersRemark;

    public String getUsersPhone() {
        return usersPhone;
    }

    public void setUsersPhone(String usersPhone) {
        this.usersPhone = usersPhone;
    }

    public String getOrdersNumber() {
        return ordersNumber;
    }

    public void setOrdersNumber(String ordersNumber) {
        this.ordersNumber = ordersNumber;
    }

    public Set<String> getCoDings() {
        return coDings;
    }

    public void setCoDings(Set<String> coDings) {
        this.coDings = coDings;
    }

    public Long getOrdersPayMethod() {
        return ordersPayMethod;
    }

    public void setOrdersPayMethod(Long ordersPayMethod) {
        this.ordersPayMethod = ordersPayMethod;
    }

    public Long getOrdersPayStatuds() {
        return ordersPayStatuds;
    }

    public void setOrdersPayStatuds(Long ordersPayStatuds) {
        this.ordersPayStatuds = ordersPayStatuds;
    }

    public String getOrdersRemark() {
        return ordersRemark;
    }

    public void setOrdersRemark(String ordersRemark) {
        this.ordersRemark = ordersRemark;
    }
}
