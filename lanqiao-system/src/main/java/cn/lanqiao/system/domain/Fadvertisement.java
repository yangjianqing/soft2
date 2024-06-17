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
    /** 广告id */
    @Excel(name = "广告id")
    private Long fadvertisementId;

    /** 广告名称 */
    @Excel(name = "广告名称")
    private String fadvertisementName;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodsId;

    /** 广告图片 */
    @Excel(name = "广告图片")
    private String fadvertisementImg;

    /** 广告费用 */
    @Excel(name = "广告费用")
    private BigDecimal fadvertisementprice;

    /** 商品数据 */
    @Excel(name = "商品数据")
    private FGoods fGoods;

    public Fadvertisement() {
    }

    public Long getFadvertisementId() {
        return fadvertisementId;
    }

    public void setFadvertisementId(Long fadvertisementId) {
        this.fadvertisementId = fadvertisementId;
    }

    public String getFadvertisementName() {
        return fadvertisementName;
    }

    public void setFadvertisementName(String fadvertisementName) {
        this.fadvertisementName = fadvertisementName;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getFadvertisementImg() {
        return fadvertisementImg;
    }

    public void setFadvertisementImg(String fadvertisementImg) {
        this.fadvertisementImg = fadvertisementImg;
    }

    public BigDecimal getFadvertisementprice() {
        return fadvertisementprice;
    }

    public void setFadvertisementprice(BigDecimal fadvertisementprice) {
        this.fadvertisementprice = fadvertisementprice;
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
                "fadvertisementId=" + fadvertisementId +
                ", fadvertisementName='" + fadvertisementName + '\'' +
                ", goodsId=" + goodsId +
                ", fadvertisementImg='" + fadvertisementImg + '\'' +
                ", fadvertisementprice=" + fadvertisementprice +
                ", fGoods=" + fGoods +
                '}';
    }
}