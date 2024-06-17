<template>
<div style="display: flex;justify-content: space-between;padding-top: 13px" v-for="list in 5">
  <div style="display: flex;" >
    <div style="display: flex;flex-direction: column;justify-content: space-evenly;">
      <p>购买商品</p>
      <p style=" text-align: left;">2024-06-16</p>
    </div>
  </div>
  <div>
      <p>+2积分</p>
  </div>
</div>

</template>


<script>
export default {
  name:"PointsManagements"
}
</script>
<style scoped>
.div_img{
  width: 50PX;
  height: 50PX;
}
.div_img img{
  width: 50PX;
  height: 50PX;
}
</style>
