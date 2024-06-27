package cn.lanqiao.system.domain.argument;
 

/**
 * @Author 
 * @Date Created in  2024/1/13 15:26
 * @DESCRIPTION: alipay接口参数
 * @Version V1.0
 */

public class AliPay {
   //订单编号
   private String traceNo;
   //商品金额
   private double totalAmount;
   //商品名称
   private String subject;
   //订单追踪号，商户自己生成，可已不使用
   private String alipayTraceNo;

   public String getTraceNo() {
      return traceNo;
   }

   public void setTraceNo(String traceNo) {
      this.traceNo = traceNo;
   }

   public double getTotalAmount() {
      return totalAmount;
   }

   public void setTotalAmount(double totalAmount) {
      this.totalAmount = totalAmount;
   }

   public String getSubject() {
      return subject;
   }

   public void setSubject(String subject) {
      this.subject = subject;
   }

   public String getAlipayTraceNo() {
      return alipayTraceNo;
   }

   public void setAlipayTraceNo(String alipayTraceNo) {
      this.alipayTraceNo = alipayTraceNo;
   }
}