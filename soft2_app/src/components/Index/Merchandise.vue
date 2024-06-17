

<template>
  <div class="content_shop">
    <img :src="this.baseUrl+imgUrls[0]" alt="">
    <p style=" width: 88px;  margin-left: 18px;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
      {{ favorable.name }}</p>
    <div class="shop_cart" style="display:flex;justify-content: center;align-items: center;">
     <span style="color:rgb(255,32,35);">￥{{ favorable.price }}</span>
      <span style="font-size: 10px;">/{{favorable.unit}}</span>
      <van-icon style=";padding: 0  5px;"  name="cart-circle" size="22" color="rgb(0,178,255)" />
    </div>
  </div>

</template>


<script >

export default {
  name: "Merchandise",
  data() {
    return {
      baseUrl:"",
      imgUrls:""
    };
  },
  props:['favorable'],
  created() {
    this.baseUrl=process.env.VUE_APP_BASE_API;
    this.imgUrls = this.favorable.image.split(',');
  }
}
</script>

<style scoped>

.content_shop{
  width: 33%;
  height: 136px;
}

.content_shop img{
  width: 70%;
  height: 60%;
}

</style>
