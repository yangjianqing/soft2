<template>
  <div class="order">
    <div class="title">
      <span style="font-size:16px ;font-weight: bold">{{ goodsInfo.name }}</span>
      <span style="color: #FFB366">{{ paymentStatus }}</span>
    </div>
    <div class="content">

      <div class="img_info">
        <img :src="this.baseUrl+imgUrl[0]" alt="">
      </div>
      <div class="content_txt">
        <div style="margin-top: 10px;margin-bottom: 5px">
          <h4 style="font-size: 12px">{{ goodsInfo.description }}</h4>
        </div>
        <p style="margin-bottom: 3px">规格：{{ goodsInfo.specification }}</p>
        <p style="margin-bottom: 3px">单价：{{ goodsInfo.price }}￥</p>
        <p style="margin-bottom: 3px">数量：{{goodsInfo.goodsNum}}{{goodsInfo.unit}}</p>
      </div>
      <span class="totals" style=" margin-left: -18px ;font-size:14px;font-weight: bold ">￥{{ (goodsInfo.price * goodsInfo.goodsNum).toFixed(2)}}</span>
    </div>

  </div>
</template>
<script>
export default {
  name: "OrderListInfo",
  props:["goodsInfo"],
  data(){
    return{
      baseUrl:"",
      imgUrl:"",
      paymentStatus:0,
    }
  },
  computed: {
    paymentStatus() {
      if (this.goodsInfo.ordersPayStatuds === 0) {
        return '待付款';
      } else {
        switch (this.goodsInfo.ordersStatus) {
          case 1:
            return '待发货';
          case 2:
            return '待收货';
          case 3:
            return '已完成';
        }
      }
    },
  },
  methods:{


  },
  created() {
    this.baseUrl=process.env.VUE_APP_BASE_API;
    this.imgUrl = this.goodsInfo.image.split(",");

  },

}
</script>
<style scoped>
.order{
  height: 160px;
  background-color: #ffffff;
  margin: 1%;
  border-radius: 3%;
}
.title{
  height: 40px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-radius: 3%;
}
.title span{
  margin: 5%;
  font-size: 12px;
}
.content{
  height: 110px;
  display: flex;
  justify-content: left;
  align-items: center;

}
.content_txt{
  height: 123px;
  width: 200px;
  text-align: left;
  margin: 0 1%;
}
.content_txt p{
  font-size: 12px;
  color: #969696;
}
.img_info{
  width: 100px;
  height: 100px;
  margin: 0 2%;
}
.img_info img{
  width: 100px;
  height: 100px;
  border-radius: 3%;
}
.totals{
  margin-left: -14%;
  margin-top: 24%;
}
.bottom_btn{
  text-align: right;
  margin: 2% 5%;
  display: flex;
  justify-content: flex-end;
}
</style>
