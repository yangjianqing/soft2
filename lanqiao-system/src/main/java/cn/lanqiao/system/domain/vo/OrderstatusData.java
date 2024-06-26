package cn.lanqiao.system.domain.vo;


public class OrderstatusData {
    /**
     * 手机端订单状态修改接口
     * @param usersPhone 手机电话
     * @param ordersNumber 商品条码
     *
     */
    private String usersPhone;
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
