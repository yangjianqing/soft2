package cn.lanqiao.system.domain.argument;

import cn.lanqiao.common.annotation.Excel;

public class ordersPayMethod {
    /** 用户电话号码 */
    @Excel(name = "用户电话号码")
    private String usersPhone;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String ordersNumber;

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
}
