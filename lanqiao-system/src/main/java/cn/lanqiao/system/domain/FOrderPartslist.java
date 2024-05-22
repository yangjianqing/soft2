package cn.lanqiao.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.lanqiao.common.annotation.Excel;
import cn.lanqiao.common.core.domain.BaseEntity;

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
    private Long orderId;

    /** 商品数量 */
    @Excel(name = "商品数量")
    private Long goodsNum;

    /** 商品数量 */
    @Excel(name = "商品对象")
    private FGoods goods;

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
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setGoodsNum(Long goodsNum) 
    {
        this.goodsNum = goodsNum;
    }

    public Long getGoodsNum() 
    {
        return goodsNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsId", getGoodsId())
            .append("orderId", getOrderId())
            .append("goodsNum", getGoodsNum())
            .toString();
    }
}
