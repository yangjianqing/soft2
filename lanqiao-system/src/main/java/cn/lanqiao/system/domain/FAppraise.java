package cn.lanqiao.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.lanqiao.common.annotation.Excel;
import cn.lanqiao.common.core.domain.BaseEntity;

/**
 * 评价管理对象 f_appraise
 * 
 * @author lanqiao
 * @date 2024-05-21
 */
public class FAppraise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评价id */
    private Long reviewid;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodsid;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** 商品名称 */
    @Excel(name = "商品id")
    private String goodsName;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userid;

    /** 用户名称 */
    @Excel(name = "用户id")
    private String userName;

    /** 评分 */
    @Excel(name = "评分")
    private String rating;

    /** 匿名 */
    @Excel(name = "匿名")
    private String starts;

    /** 评价内容 */
    @Excel(name = "评价内容")
    private String reviewText;

    /** 评价时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "评价时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reviewDate;

    public void setReviewid(Long reviewid) 
    {
        this.reviewid = reviewid;
    }

    public Long getReviewid() 
    {
        return reviewid;
    }
    public void setGoodsid(Long goodsid) 
    {
        this.goodsid = goodsid;
    }

    public Long getGoodsid() 
    {
        return goodsid;
    }
    public void setUserid(Long userid) 
    {
        this.userid = userid;
    }

    public Long getUserid() 
    {
        return userid;
    }
    public void setRating(String rating) 
    {
        this.rating = rating;
    }

    public String getRating() 
    {
        return rating;
    }
    public void setStarts(String starts) 
    {
        this.starts = starts;
    }

    public String getStarts() 
    {
        return starts;
    }
    public void setReviewText(String reviewText) 
    {
        this.reviewText = reviewText;
    }

    public String getReviewText() 
    {
        return reviewText;
    }
    public void setReviewDate(Date reviewDate) 
    {
        this.reviewDate = reviewDate;
    }

    public Date getReviewDate() 
    {
        return reviewDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reviewid", getReviewid())
            .append("goodsid", getGoodsid())
            .append("userid", getUserid())
            .append("rating", getRating())
            .append("starts", getStarts())
            .append("reviewText", getReviewText())
            .append("reviewDate", getReviewDate())
            .toString();
    }
}
