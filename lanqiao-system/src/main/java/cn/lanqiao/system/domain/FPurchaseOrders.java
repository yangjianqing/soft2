package cn.lanqiao.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.lanqiao.common.annotation.Excel;
import cn.lanqiao.common.core.domain.BaseEntity;

/**
 * 采购信息对象 f_purchase_orders
 * 
 * @author lanqiao
 * @date 2024-05-20
 */
public class FPurchaseOrders extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单Id */
    private Long orderId;

    /** 商品Id */
    @Excel(name = "商品Id")
    private Long goodsId;

    /** 商品Id */
    @Excel(name = "商品名称")
    private String goodsName;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /** 供应商
 */
    @Excel(name = "供应商 ")
    private String supplier;

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    /** 商品数量 */
    @Excel(name = "商品数量")
    private Long quantity;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal unitPrice;

//    /** 总金额 */
//    @Excel(name = "总金额")
//    private BigDecimal totalAmount;

    /** 付款方式 */
    @Excel(name = "付款方式")
    private String paymentTerms;

    /** 发货地 */
    @Excel(name = "发货地")
    private String shippingAddress;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 订单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderDate;

    /** 到货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date requiredDate;

    /** 备注 */
    @Excel(name = "备注")
    private String orderComments;

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }

    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }
    public void setUnitPrice(BigDecimal unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() 
    {
        return unitPrice;
    }
//    public void setTotalAmount(BigDecimal totalAmount)
//    {
//        this.totalAmount = totalAmount;
//    }

//    public BigDecimal getTotalAmount()
//    {
//        return totalAmount;
//    }
    public void setPaymentTerms(String paymentTerms)
    {
        this.paymentTerms = paymentTerms;
    }

    public String getPaymentTerms() 
    {
        return paymentTerms;
    }
    public void setShippingAddress(String shippingAddress) 
    {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingAddress() 
    {
        return shippingAddress;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setOrderDate(Date orderDate) 
    {
        this.orderDate = orderDate;
    }

    public Date getOrderDate() 
    {
        return orderDate;
    }
    public void setRequiredDate(Date requiredDate) 
    {
        this.requiredDate = requiredDate;
    }

    public Date getRequiredDate() 
    {
        return requiredDate;
    }
    public void setOrderComments(String orderComments) 
    {
        this.orderComments = orderComments;
    }

    public String getOrderComments() 
    {
        return orderComments;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("goodsId", getGoodsId())
            .append("supplierId", getSupplier())
            .append("quantity", getQuantity())
            .append("unitPrice", getUnitPrice())
//            .append("totalAmount", getTotalAmount())
            .append("paymentTerms", getPaymentTerms())
            .append("shippingAddress", getShippingAddress())
            .append("status", getStatus())
            .append("orderDate", getOrderDate())
            .append("requiredDate", getRequiredDate())
            .append("orderComments", getOrderComments())
            .toString();
    }
}
