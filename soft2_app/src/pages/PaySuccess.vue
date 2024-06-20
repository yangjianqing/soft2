

<template>
  <div class="xtx-pay-page">
    <div class="container">
      <!-- 支付结果 -->
      <div class="pay-result">
        <van-icon name="checked" size="65" color="#1dc779" style="margin-bottom: 5px;" />
        <p class="tit">订单支付成功</p>
        <p class="tip">我们将尽快为您发货，收货期间请保持手机畅通</p>
        <p>支付方式：<span>支付宝支付</span></p>
        <p>支付金额：<span>¥1899.00</span></p>
        <div class="btn">

          <van-button @click="" type="success" style="margin-right:20px">查看订单</van-button>
          <van-button @click="" type="primary">进入首页</van-button>
        </div>
        <p class="alert">
          <van-icon name="bulb-o" />
          温馨提示：绿源鲜选不会以订单异常进行退款操作，保护资产、谨慎操作。
        </p>
      </div>
    </div>
  </div>
</template>


<style scoped>
.pay-result {
  padding: 100px 0;
  background: #fff;
  text-align: center;
}
.pay-result > .iconfont {
  font-size: 100px;
}

.pay-result .tit {
  font-size: 26px;
}
.pay-result .tip {
  color: #999;
}
.pay-result p {
  line-height: 40px;
  font-size: 15px;
}
.pay-result .btn {
  margin-top: 70px;
}
.pay-result .alert {
  font-size: 10px;
  color: #999;
  margin-top: 50px;
}
</style>


<script>
export default {
  name: "PaySuccess",
  created() {
    this.$route.query;
    console.log(this.$route.query)
    console.log(this.$route.query.out_trade_no)
    // 根据 out_trade_no 修改订单状态

  },
  methods(){

  }
}
</script>
