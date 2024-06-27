package cn.lanqiao.web.controller.common;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
 
/**
 * @Author 
 * @Date Chuan  2024/06/27 08:42
 * @DESCRIPTION:
 * @Version V1.0
 *
 */
@Component
//读取yml文件中alipay 开头的配置
@ConfigurationProperties(prefix = "alipay")
public class AliPayConfig {
   private String appId;
   private String appPrivateKey;
   private String alipayPublicKey;
   //异步通知回调地址（可选）
   private String notifyUrl;
   //支付成功后的回调地址
   private String returnUrl;
 
 
   @PostConstruct
   public void init() {
      // 设置参数（全局只需设置一次）
      Config config = new Config();
      config.protocol = "https";
      config.gatewayHost = "openapi.alipaydev.com";
      config.signType = "RSA2";
      config.appId = this.appId;
      config.merchantPrivateKey = this.appPrivateKey;
      config.alipayPublicKey = this.alipayPublicKey;
      config.notifyUrl = this.notifyUrl;
      Factory.setOptions(config);
      System.out.println("=======支付宝SDK初始化成功=======");
   }

   public String getAppId() {
      return appId;
   }

   public void setAppId(String appId) {
      this.appId = appId;
   }

   public String getAppPrivateKey() {
      return appPrivateKey;
   }

   public void setAppPrivateKey(String appPrivateKey) {
      this.appPrivateKey = appPrivateKey;
   }

   public String getAlipayPublicKey() {
      return alipayPublicKey;
   }

   public void setAlipayPublicKey(String alipayPublicKey) {
      this.alipayPublicKey = alipayPublicKey;
   }

   public String getNotifyUrl() {
      return notifyUrl;
   }

   public void setNotifyUrl(String notifyUrl) {
      this.notifyUrl = notifyUrl;
   }

   public String getReturnUrl() {
      return returnUrl;
   }

   public void setReturnUrl(String returnUrl) {
      this.returnUrl = returnUrl;
   }
}