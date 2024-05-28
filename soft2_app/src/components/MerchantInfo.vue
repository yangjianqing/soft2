<template>
  <div class="box">
    <div class="merchant_info">
      <div class="head_img">
  <!-- //this.baseUrl+merchantInfo.headUrl-->
        <img src="@/assets/img/OIP-C.jpg" alt="">
      </div>
      <div class="text_info">
        <div class="title info">
          <h3>新鲜水果王</h3>
          <van-icon style="color: #ee9900" name="award" />
        </div>

        <div class="pingfen info">
          <div class="left">
            <h4>4.8分</h4>
            <span>月售100+</span>
            <span>起送￥{{merchantInfo.minimumCharge}}</span>
          </div>
          <div class="right">
            <span>25分钟</span>
            <span>1.7公里</span>
          </div>
        </div>
        <div class="tag info" >
          <p>高折扣</p>
          <p>热销脆皮鸡</p>
          <p>刚刚有用户看过</p>

        </div>
        <div class="youhui info">
          <p>反2元卷</p>
          <p>新客减1元</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MerchantInfo",
  props:["merchantInfo"],
  data(){
    return{
      baseUrl:"",
    }
  },
  created() {
    this.baseUrl=process.env.VUE_APP_BASE_API;
  }

}
</script>

<style scoped>
.box{
  padding: 0 5px;
  background: white;
  margin: 5px 0;
}
.merchant_info{
  width: 100%;
  height: 120px;
  background: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.head_img img{
  width: 100px;
  height: 100px;
  border-radius: 10px;
}
.merchant_info .head_img{
  padding-right: 10px;
}
.info{
  margin: 3px 0;
}

.text_info{
  width: 100%;
  height: 100px;
}

.text_info .info{
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.pingfen{
  font-size: 12px;
  color: #7f7f7f;
}
.pingfen h4{
  font-size: 18px;
  color: #fcb765;
}
.pingfen .left{
  width: 70%;
  display: flex;
  justify-content: left;
  align-items: center;
}
.pingfen  span,h4{
  margin: 0 2px;
}
.tag.info{
  display: flex;
  align-items: center;
  justify-content: left;
}
.tag p{
  font-size: 12px;
  background: #f8dcae;
  color: #ee9900;
  line-height: 18px;
  height: 18px;
  padding: 0 3px;
  margin-right: 5px;
  border-radius: 3px;
}
.youhui.info{
  display: flex;
  align-items: center;
  justify-content: left;
}
.youhui p{
  font-size: 12px;
  border: 1px solid red;
  color: #ee0030;
  line-height: 18px;
  height: 18px;
  padding: 0 3px;
  margin-right: 5px;
  border-radius: 3px;
}

</style>
