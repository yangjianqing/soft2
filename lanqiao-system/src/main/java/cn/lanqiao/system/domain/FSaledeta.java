package cn.lanqiao.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.lanqiao.common.annotation.Excel;
import cn.lanqiao.common.core.domain.BaseEntity;

/**
 * 销售明细管理对象 f_saledeta
 * 
 * @author chuan
 * @date 2024-05-16
 */
public class FSaledeta extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 销售明细id */
    @Excel(name = "销售明细id")
    private Long saledetaId;

    /** 销售记录 */
    @Excel(name = "销售记录")
    private Long salesId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private Long goodsId;

    /** 商品数量 */
    @Excel(name = "商品数量")
    private Long saledetaTity;

    /** 商品单价 */
    @Excel(name = "商品单价")
    private BigDecimal saledetaPrice;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date saledetaCreationtime;

    public void setSaledetaId(Long saledetaId) 
    {
        this.saledetaId = saledetaId;
    }

    public Long getSaledetaId() 
    {
        return saledetaId;
    }
    public void setSalesId(Long salesId) 
    {
        this.salesId = salesId;
    }

    public Long getSalesId() 
    {
        return salesId;
    }
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setSaledetaTity(Long saledetaTity) 
    {
        this.saledetaTity = saledetaTity;
    }

    public Long getSaledetaTity() 
    {
        return saledetaTity;
    }
    public void setSaledetaPrice(BigDecimal saledetaPrice) 
    {
        this.saledetaPrice = saledetaPrice;
    }

    public BigDecimal getSaledetaPrice() 
    {
        return saledetaPrice;
    }
    public void setSaledetaCreationtime(Date saledetaCreationtime) 
    {
        this.saledetaCreationtime = saledetaCreationtime;
    }

    public Date getSaledetaCreationtime() 
    {
        return saledetaCreationtime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("saledetaId", getSaledetaId())
            .append("salesId", getSalesId())
            .append("goodsId", getGoodsId())
            .append("saledetaTity", getSaledetaTity())
            .append("saledetaPrice", getSaledetaPrice())
            .append("saledetaCreationtime", getSaledetaCreationtime())
            .toString();
    }
}
