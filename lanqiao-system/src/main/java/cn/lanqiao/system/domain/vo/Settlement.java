package cn.lanqiao.system.domain.vo;


public class Settlement {
    /**
     * 手机端结算
     *
     * @param usersPhone 用户电话号码
     * @param ordersPayMethod 支付方式
     * @param ordersRemark 订单备注信息
     * @param ordersNumber 订单编号
     */
    private String ordersNumber;
    private String usersPhone;
    private Long ordersPayMethod;
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
