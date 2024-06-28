

<template>
  <div class="xtx-pay-page">
    <div class="container">
      <!-- 支付结果 -->
      <div class="pay-result">
        <van-icon name="checked" size="65" color="#1dc779" style="margin-bottom: 5px;" />
        <p class="tit">订单支付成功</p>
        <p class="tip">我们将尽快为您发货，收货期间请保持手机畅通</p>
        <p>支付方式：<span>支付宝支付</span></p>
        <p>支付金额：<span>¥{{this.totalAmount}}</span></p>
        <div class="btn">

          <van-button @click="ViewOrder()" type="success" style="margin-right:20px">查看订单</van-button>
          <van-button @click="EnterIndex()" type="primary">进入首页</van-button>
        </div>
        <p class="alert">
          <van-icon name="bulb-o" />
          温馨提示：绿源鲜选不会以订单异常进行退款操作，保护资产、谨慎操作。
        </p>
      </div>
    </div>
  </div>
</template>


<script>

import {updateSettlement} from "@/api/merchant";

export default {
  name: "PaySuccess",
  data(){
    return{
      totalAmount:0,
    }
  },
  created() {
    //获取订单金额
    this.totalAmount = this.$route.query.total_amount;
    // 根据 out_trade_no 修改订单状态
    //获取用户登陆信息
    const userInfoString = localStorage.getItem("userInfo");
    // 将用户信息字符串解析回对象
    this.userInfo = JSON.parse(userInfoString);
    updateSettlement({usersPhone:this.userInfo.usersPhone,ordersNumber:this.$route.query.out_trade_no}).then(res=>{
         if (res.data.msg ===200){
           console.log("修改成功")
         }
    })
    this.sendMessage();
  },
  methods:{
    ViewOrder() {
      this.total = this.$router.push('/mine/ordermanagement');
    },
    EnterIndex() {
      this.$router.push('/index');
    },
    async sendMessage() {
      try {
        // 处理点击事件的逻辑
        const apiUrl = `http://127.0.0.1:8089/api/sendMessage/1234`;
        await axios.get(apiUrl);
      } catch (error) {
        console.log(error);
      }
    },
  }


}
</script>


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

