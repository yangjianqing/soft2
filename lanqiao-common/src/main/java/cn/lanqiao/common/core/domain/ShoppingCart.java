package cn.lanqiao.common.core.domain;

import cn.lanqiao.common.annotation.Excel;

public class ShoppingCart {
    /** 订单支付方式 */
    @Excel(name = "订单支付方式")
    private Long ordersPayMethod;

    /** 商品数量 */
    @Excel(name = "商品数量")
    private Long quantity;

    public Long getOrdersPayMethod() {
        return ordersPayMethod;
    }

    public void setOrdersPayMethod(Long ordersPayMethod) {
        this.ordersPayMethod = ordersPayMethod;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
