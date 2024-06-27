package cn.lanqiao.system.domain.argument;

import cn.lanqiao.common.annotation.Excel;
import cn.lanqiao.system.domain.FGoods;

import java.math.BigDecimal;
import java.util.List;

public class FormData {

    /** 用户电话号码 */
    @Excel(name = "用户电话号码")
    private String memberPhone;

    /** 总金额积分 */
    @Excel(name = "总金额积分")
    private BigDecimal totalPrice;

    /** 折扣积分 */
    @Excel(name = "折扣积分")
    private BigDecimal memberJian;

    /** 订单支付方式 */
    @Excel(name = "订单支付方式")
    private String ordersPayMethod;

    /** 购物车数据 */
    @Excel(name = "购物车数据")
    private List<FGoods> productsInCart;

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getMemberJian() {
        return memberJian;
    }

    public void setMemberJian(BigDecimal memberJian) {
        this.memberJian = memberJian;
    }

    public String getOrdersPayMethod() {
        return ordersPayMethod;
    }

    public void setOrdersPayMethod(String ordersPayMethod) {
        this.ordersPayMethod = ordersPayMethod;
    }

    public List<FGoods> getProductsInCart() {
        return productsInCart;
    }

    public void setProductsInCart(List<FGoods> productsInCart) {
        this.productsInCart = productsInCart;
    }
}
