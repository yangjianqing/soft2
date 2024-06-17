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

    /** 广告图片 */
    @Excel(name = "广告图片")
    private String fadvertisementImg;

    /** 广告费用 */
    @Excel(name = "广告费用")
    private BigDecimal fadvertisementprice;

    public Fadvertisement() {
    }

    public Fadvertisement(Long fadvertisementId, String fadvertisementName, String fadvertisementImg, BigDecimal fadvertisementprice) {
        this.fadvertisementId = fadvertisementId;
        this.fadvertisementName = fadvertisementName;
        this.fadvertisementImg = fadvertisementImg;
        this.fadvertisementprice = fadvertisementprice;
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

    @Override
    public String toString() {
        return "Fadvertisement{" +
                "fadvertisementId=" + fadvertisementId +
                ", fadvertisementName='" + fadvertisementName + '\'' +
                ", fadvertisementImg='" + fadvertisementImg + '\'' +
                ", fadvertisementprice=" + fadvertisementprice +
                '}';
    }
}
