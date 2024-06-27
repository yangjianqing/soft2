package cn.lanqiao.system.domain.vo;

import cn.lanqiao.common.annotation.Excel;

public class OrderstatusData {

    /** 用户电话号码 */
    @Excel(name = "用户电话号码")
    private String usersPhone;

    /** 商品条码 */
    @Excel(name = "商品条码")
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
