package cn.lanqiao.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.lanqiao.common.annotation.Excel;
import cn.lanqiao.common.core.domain.BaseEntity;

/**
 * 财务报表管理对象 f_financial
 * 
 * @author chuan
 * @date 2024-05-15
 */
public class FFinancial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 财务id */
    @Excel(name = "财务id")
    private Long financialId;

    /** 收入摘要 */
    @Excel(name = "收入摘要")
    private String financialIncome;

    /** 费用摘要 */
    @Excel(name = "费用摘要")
    private String financialExpense;

    /** 利润摘要 */
    @Excel(name = "利润摘要")
    private String financialProfit;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date financialCreatetime;

    public void setFinancialId(Long financialId) 
    {
        this.financialId = financialId;
    }

    public Long getFinancialId() 
    {
        return financialId;
    }
    public void setFinancialIncome(String financialIncome) 
    {
        this.financialIncome = financialIncome;
    }

    public String getFinancialIncome() 
    {
        return financialIncome;
    }
    public void setFinancialExpense(String financialExpense) 
    {
        this.financialExpense = financialExpense;
    }

    public String getFinancialExpense() 
    {
        return financialExpense;
    }
    public void setFinancialProfit(String financialProfit) 
    {
        this.financialProfit = financialProfit;
    }

    public String getFinancialProfit() 
    {
        return financialProfit;
    }
    public void setFinancialCreatetime(Date financialCreatetime) 
    {
        this.financialCreatetime = financialCreatetime;
    }

    public Date getFinancialCreatetime() 
    {
        return financialCreatetime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("financialId", getFinancialId())
            .append("financialIncome", getFinancialIncome())
            .append("financialExpense", getFinancialExpense())
            .append("financialProfit", getFinancialProfit())
            .append("financialCreatetime", getFinancialCreatetime())
            .toString();
    }
}
