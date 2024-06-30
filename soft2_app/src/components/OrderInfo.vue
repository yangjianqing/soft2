<template>
    <div class="order">
        <div class="title">
          <span style="font-size:16px ;font-weight: bold">{{ goodsInfo.name }}</span>
          {{}}
          <span style="color: #FFB366">未支付</span>
        </div>
        <div class="content">

          <div class="img_info">
            <img :src="this.baseUrl+imgUrl[0]" alt="">
          </div>
          <div class="content_txt">
            <div style="margin-top: 10px;margin-bottom: 5px">
              <h4 style="font-size: 12px">{{ goodsInfo.description }}</h4>
            </div>
            <p>规格：{{ goodsInfo.specification }}</p>
            <p>单价：{{ goodsInfo.price }}￥</p>
            <p>数量：{{goodsInfo.quantity}}{{goodsInfo.unit}}</p>
          </div>
        </div>
      <p class="totals">金额:{{ (goodsInfo.price * goodsInfo.quantity).toFixed(2)}}元</p>
    </div>
</template>
<script >

import { showConfirmDialog } from 'vant';
import {deleteCarList} from "@/api/merchant";

export default {
  name: "OrderInfo",
  props:["goodsInfo"],
  data(){
    return{
      baseUrl:"",
      imgUrl:""
    }
  },
  methods: {

  },
  created() {
    console.log(this.goodsInfo)
    this.baseUrl=process.env.VUE_APP_BASE_API;
    this.imgUrl = this.goodsInfo.image.split(",");
  },

}
</script>
<style scoped>
.order{
  height: 180px;
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
  height: 100px;
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
  text-align: right;
  margin-right: 12%;
  margin-bottom: 1%;
}
.bottom_btn{
  text-align: right;
  margin: 2% 5%;
  display: flex;
  justify-content: flex-end;
}
</style>
