package cn.lanqiao.system.domain;

import cn.lanqiao.common.annotation.Excel;
import cn.lanqiao.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 广告对象 f_address
 *
 * @author chuan
 * @date 2024-06-17
 */
public class Fadvertisement extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 广告ID */
    @Excel(name = "广告ID")
    private Long id;

    /** 广告名称 */
    @Excel(name = "广告名称")
    private String name;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private Long goodsId;

    /** 广告图片 */
    @Excel(name = "广告图片")
    private String img;

    /** 广告价格 */
    @Excel(name = "广告价格")
    private BigDecimal price;

    /** 商品数据 */
    @Excel(name = "商品数据")
    private FGoods fGoods;

    public Fadvertisement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public FGoods getfGoods() {
        return fGoods;
    }

    public void setfGoods(FGoods fGoods) {
        this.fGoods = fGoods;
    }

    @Override
    public String toString() {
        return "Fadvertisement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goodsId=" + goodsId +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", fGoods=" + fGoods +
                '}';
    }
}