<template>
  <div class="order">
    <router-link to="/cart/shoppinggement">
    <div class="title">
      <span>{{ goodsInfo.name }}</span>
      <span style="color: #FFB366">未支付</span>
    </div>
    <div class="content">

      <div class="img_info">
        <img :src="this.baseUrl+imgUrl[0]" alt="">
      </div>
      <div class="content_txt">
        <div style="height: 40px">
          <h4 style="font-size: 12px">{{ goodsInfo.description }}</h4>
        </div>
        <p>规格：一个</p>
        <p>单价：￥{{ goodsInfo.specification }}</p>
        <p>数量：{{goodsInfo.quantity}}个</p>
      </div>
    </div>
    </router-link>
      <p class="totals">金额:{{ goodsInfo.price }}元</p>
    <div class="bottom_btn">
      <div>
        <van-button plain hairline round  type="primary" size="mini" @add="addNum" >再次购买</van-button>
        <router-link to="/mine/ordermanagement">
          <van-button plain hairline round  type="danger" size="mini">评价</van-button>
        </router-link>
      </div>
    </div>
  </div>
</template>
<script >


export default {
  name: "OrderInfo",
  props:["goodsInfo"],
  data(){
    return{
      baseUrl:"",
      imgUrl:""
    }
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
  height: 230px;
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
  margin-right: 6%;
}
.bottom_btn{
  text-align: right;
  margin: 2% 5%;
  display: flex;
  justify-content: flex-end;
}
</style>
