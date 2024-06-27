package cn.lanqiao.system.domain.argument;
import cn.lanqiao.common.annotation.Excel;


public class Settlement {

    /** 用户电话号码 */
    @Excel(name = "用户电话号码")
    private String ordersNumber;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private String usersPhone;

    /** 订单备注信息 */
    @Excel(name = "订单备注信息")
    private Long ordersPayMethod;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String ordersRemark;

    public Settlement() {
    }

    public Settlement(String ordersNumber, String usersPhone, Long ordersPayMethod, String ordersRemark) {
        this.ordersNumber = ordersNumber;
        this.usersPhone = usersPhone;
        this.ordersPayMethod = ordersPayMethod;
        this.ordersRemark = ordersRemark;
    }

    public String getUsersPhone() {
        return usersPhone;
    }

    public void setUsersPhone(String usersPhone) {
        this.usersPhone = usersPhone;
    }

    public Long getOrdersPayMethod() {
        return ordersPayMethod;
    }

    public void setOrdersPayMethod(Long ordersPayMethod) {
        this.ordersPayMethod = ordersPayMethod;
    }

    public String getOrdersRemark() {
        return ordersRemark;
    }

    public void setOrdersRemark(String ordersRemark) {
        this.ordersRemark = ordersRemark;
    }

    public String getOrdersNumber() {
        return ordersNumber;
    }

    public void setOrdersNumber(String ordersNumber) {
        this.ordersNumber = ordersNumber;
    }
}
