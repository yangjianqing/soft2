package cn.lanqiao.system.domain;

import java.math.BigDecimal;
import java.util.List;

public class FormData {
    private String memberPhone;
    private BigDecimal totalPrice;
    private BigDecimal memberJian;
    private List<FGoods> productsInCart;

    public FormData() {
    }

    public FormData(String memberPhone, BigDecimal totalPrice, BigDecimal memberJian, List<FGoods> productsInCart) {
        this.memberPhone = memberPhone;
        this.totalPrice = totalPrice;
        this.memberJian = memberJian;
        this.productsInCart = productsInCart;
    }

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

    public List<FGoods> getProductsInCart() {
        return productsInCart;
    }

    public void setProductsInCart(List<FGoods> productsInCart) {
        this.productsInCart = productsInCart;
    }

    @Override
    public String toString() {
        return "FormData{" +
                "memberPhone='" + memberPhone + '\'' +
                ", totalPrice=" + totalPrice +
                ", memberJian=" + memberJian +
                ", productsInCart=" + productsInCart +
                '}';
    }
}
