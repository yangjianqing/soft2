

<template>
  <div class="big_box">
    <div class="shopping_img">
      <img :src="this.baseUrl+imgUrls[0]" alt="">
    </div >
      <p>{{goodsInfo.name}}</p>
    <div class="Description_box">
        <span>{{ goodsInfo.description}}</span>
    </div>
    <div class="dis_box">
      <div class="span_box1">
        <span class="span1">自 营</span>
      </div>
      <div class="span_box2">
        <span class="span2">24H发 货</span>
      </div>
      <span class="span_box3">月1100+人已下单</span>
    </div>
    <div class="money_box">
      <div>
        <span class="money_span">￥{{goodsInfo.price}}</span>
        <span class="unit_span">/{{goodsInfo.unit}}</span>
      </div>
      <van-icon style=" padding: 0  5px;"  name="cart-circle" size="22" color=" rgb(0,178,255)" />
    </div>
  </div>

</template>


<script >
export default {
  name: "MerchandiseInfo",
  data() {
    return {
      baseUrl:"",
      imgUrls:""
    };
  },
  props:['goodsInfo'],
  created() {
    this.baseUrl=process.env.VUE_APP_BASE_API;
    this.imgUrls = this.goodsInfo.image.split(',');
  },
  methods:{

  },

}

</script>

<style scoped>
.big_box{
  width: 100%;
  height: 260px;
  background-color: #ffffff;
  margin:3% 3% 3% 3%;
  border-radius: 10px;
}

.shopping_img{
  padding: 3%;
}

.shopping_img img{
  width: 150px;
  height: 150px;
}

.big_box p {
  margin: 1% 4%;
  font-size: 13px;
  font-weight: bold;
  text-align: left;
}
.dis_box {
  text-align: left;
  color: rgb(0,171,241);
  padding: 0% 3%;
  display: flex;
}

.span_box1 {
  border: 1px solid rgb(184,218,227);
  width: 18%;
  height: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 10px;
  border-radius: 3px 0 0 3px; /* 设置左半部分为圆角，右半部分为直角 */
  background-color: rgb(227,249,255);
}

.span_box2 {
  border: 1px solid rgb(184,218,227);
  width: 30%;
  height: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 10px;
  border-radius: 0 3px 3px 0; /* 设置左半部分为圆角，右半部分为直角 */

}
.money_box{
  margin: 5% 2%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.span_box3{
  margin: 1% 3px;
  font-size:9px ;
  font-weight: bold;
  color: rgb(208,153,57);
}
.money_box .money_span{
  font-size: 20px;
  font-weight: bold;
  color: rgb(255,32,35);

}
.unit_span{
  color: rgb(136, 134, 134);
  font-size: 12px;

}

.Description_box {
  display: flex;
  align-items: center;
  color: rgb(136, 134, 134);
  font-size: 11px;
  font-weight: bold;
  margin: 1% 4%;
}
</style>
