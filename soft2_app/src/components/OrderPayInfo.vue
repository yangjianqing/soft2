<template>
  <van-swipe-cell>
  <div class="order">
    <div class="title">
      <span style="font-size:16px ;font-weight: bold">{{ goodsInfo.name }}</span>
      <span style="color: #FFB366">待支付</span>
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
        <p style="margin-bottom: 3px">数量：{{goodsInfo.quantity }}{{goodsInfo.unit}}</p>

      </div>
       <div style="margin-top: 56px">
         <span  style="margin-top: 10px;font-size: 14px;font-weight: bold">￥{{ (goodsInfo.price * goodsInfo.quantity).toFixed(2)}}</span>
         <van-button @click="clickPayGoods" type="primary" size="mini" >继续支付</van-button>
       </div>
    </div>
  </div>
  <template #right>
    <van-button @click="clickBot(goodsInfo.coding)" style="width: 20px;height: 100%" icon="delete-o"  type="danger" class="delete-button" />
  </template>
  </van-swipe-cell>
</template>
<script>

import router from "@/router";
import {showConfirmDialog} from "vant";
import {deleteCarList} from "@/api/merchant";
export default {
  name: "OrderPayInfo",
  props:["goodsInfo"],
  data(){
    return{
      baseUrl:"",
      imgUrl:"",
      paymentStatus:0,
    }
  },
  methods:{
    clickPayGoods(){
      router.push("/PayInfoList/"+this.goodsInfo.coding)
    },
    clickBot(coding){
      showConfirmDialog({
        message:
          '将此款商品删除',
      })
        .then(() => {
          // on confirm
          const userInfoString = localStorage.getItem("userInfo");
          // 将字符串解析回对象
          const userInfo = JSON.parse(userInfoString);
          //购物车的数据
          this.deleteShopping(userInfo.usersPhone,coding);
        })
        .catch(() => {
          // on cancel
        });
      },
    deleteShopping(userInfo,coding){
      deleteCarList(userInfo,coding).then(res=>{
        this.$router.go(0)
      })
    }
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

.bottom_btn{
  text-align: right;
  margin: 2% 5%;
  display: flex;
  justify-content: flex-end;
}
</style>
