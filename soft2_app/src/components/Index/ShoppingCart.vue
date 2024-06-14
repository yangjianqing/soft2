
<template>
  <div class="shoppingCart_box">
    <div class="shopBox_left">
      <img :src="this.baseUrl+imgUrls[0]" alt="">
    </div>
    <div class="shopBox_right">
      <p >{{Category.name}}</p>
      <div class="Description_box">
        <span>{{ Category.description}}</span>
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
          <span class="money_span">￥{{Category.price}}</span>
          <span class="unit_span">/{{Category.unit}}</span>
        </div>
        <van-icon style=" padding: 0  5px;"  name="cart-circle" size="22" color=" rgb(0,178,255)" />
      </div>


    </div>

  </div>

</template>


<script >


export default {
  name: "ShoppingCart",
  props:['Category'],
  data() {
    return {
      baseUrl:"",
      imgUrls:""
    };
  },
  created() {
      this.baseUrl=process.env.VUE_APP_BASE_API;
    this.imgUrls = this.Category.image.split(',');
  },
  methods:{

  }

}
</script>


<style scoped>
.shoppingCart_box{
  width: 100%;
  height: 110px;
  display: flex;
}
.shoppingCart_box .shopBox_left{
  width: 35%;
  height: 110px;


}
.shoppingCart_box .shopBox_left img{
  width: 80%;
  height: 70%;
  margin: 10% 0;
  border-radius: 5px;
}
.shoppingCart_box .shopBox_right{
  width: 65%;
  height: 110px;
  border-bottom: 2px solid  rgb(249,249,249);
}
.shopBox_right p {
  margin: 4% 3% 4% 0;
  font-size: 13px;
  font-weight: bold;
  text-align: left;
}
.dis_box {
  text-align: left;
  color: rgb(0,171,241);
  margin: 2px 0  0;
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
}
.line_box{
  margin: 0 2%;
  text-align: center;
}


</style>
