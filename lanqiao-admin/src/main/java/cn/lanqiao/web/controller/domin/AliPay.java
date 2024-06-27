package cn.lanqiao.web.controller.domin;

/**
 * @Author
 * @Date Chuan  2024/06/27 08:42
 * @DESCRIPTION:
 * @Version V1.0
 *
 */
public class AliPay {
   //商家名称
   private String subject;
   //订单编号
   private String traceNo;
   //购物车总金额
   private double totalAmount;
   //订单追踪号，商户自己生成，可以不使用
//   private String alipayTraceNo;

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
}