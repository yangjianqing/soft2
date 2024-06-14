

<template>
  <div class="content_shop">
    <img src="https://fastly.jsdelivr.net/npm/@vant/assets/apple-1.jpeg" alt="">
    <p style=" width: 88px;  margin-left: 18px;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">南汇8424西时间发货发看风景我发</p>
    <div class="shop_cart" style="display:flex;justify-content: center;align-items: center;">
     <span style="color:rgb(255,32,35);">￥29.9</span>
      <span style="font-size: 10px; color: rgb(136, 134, 134)">/个</span>
      <van-icon style=";padding: 0  5px;"  name="cart-circle" size="18" color="rgb(0,178,255)" />
    </div>
  </div>

</template>


<script >

export default {
  name: "Merchandise",
  data() {
    return {

    };
  },
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
