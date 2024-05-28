

<template>
  <div class="content_shop">
    <img src="https://fastly.jsdelivr.net/npm/@vant/assets/apple-1.jpeg" alt="">
    <p>南汇8424西...</p>
    <div class="shop_cart">
     <span>￥29.9</span>
      <span>个</span>
      <van-icon style=" padding: 0  5px;"  name="cart-circle" size="22" color="rgb(255,80,48)" />
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