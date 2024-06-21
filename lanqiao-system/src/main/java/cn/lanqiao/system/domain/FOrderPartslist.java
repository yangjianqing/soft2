package cn.lanqiao.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.lanqiao.common.annotation.Excel;
import cn.lanqiao.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 订单明细对象 f_order_partslist
 * 
 * @author lanqiao
 * @date 2024-05-22
 */
public class FOrderPartslist extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 明细id */
    private Long id;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodsId;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /** 主键 */
    private Long goodsid;

    public Long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }

    /** 商品数量 */
    @Excel(name = "商品数量")
    private Long goodsNum;

    /** 商品数量 */
    @Excel(name = "商品对象")
    private FGoods goods;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String name;

    /** 商品规格 */
    @Excel(name = "商品规格")
    private String specification;

    /** 商品价格 */
    @Excel(name = "商品价格")
    private BigDecimal price;

    @Excel(name = "编码")
    private Long coding;

    /** 图片 */
    @Excel(name = "图片")
    private String image;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private Long ordersStatus;

    /** 订单支付状态 */
    @Excel(name = "订单支付状态")
    private Long ordersPayStatuds;

    public Long getOrdersPayStatuds() {
        return ordersPayStatuds;
    }

    public void setOrdersPayStatuds(Long ordersPayStatuds) {
        this.ordersPayStatuds = ordersPayStatuds;
    }

    public Long getOrdersStatus() {
        return ordersStatus;
    }

    public void setOrdersStatus(Long ordersStatus) {
        this.ordersStatus = ordersStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCoding() {
        return coding;
    }

    public void setCoding(Long coding) {
        this.coding = coding;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public FGoods getGoods() {
        return goods;
    }

    public void setGoods(FGoods goods) {
        this.goods = goods;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }

    public void setGoodsNum(Long goodsNum) 
    {
        this.goodsNum = goodsNum;
    }

    public Long getGoodsNum() 
    {
        return goodsNum;
    }

    public FOrderPartslist( Long goodsId, String orderId, Long goodsNum) {
        this.goodsId = goodsId;
        this.orderId = orderId;
        this.goodsNum = goodsNum;
    }

    public FOrderPartslist() {
    }

    public FOrderPartslist(Long id, Long goodsId, String orderId, Long goodsid, Long goodsNum, FGoods goods, String name, String specification, BigDecimal price, Long coding, String image, Long  ordersStatus) {
        this.id = id;
        this.goodsId = goodsId;
        this.orderId = orderId;
        this.goodsid = goodsid;
        this.goodsNum = goodsNum;
        this.goods = goods;
        this.name = name;
        this.specification = specification;
        this.price = price;
        this.coding = coding;
        this.image = image;
        this.ordersStatus = ordersStatus;
    }

    @Override
    public String toString() {
        return "FOrderPartslist{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", orderId='" + orderId + '\'' +
                ", goodsid=" + goodsid +
                ", goodsNum=" + goodsNum +
                ", goods=" + goods +
                ", name='" + name + '\'' +
                ", specification='" + specification + '\'' +
                ", price=" + price +
                ", coding=" + coding +
                ", image='" + image + '\'' +
                ", ordersStatus=" + ordersStatus +
                '}';
    }
}
