<template>
<div style="display: flex;justify-content: space-between;padding-top: 13px" v-for="list in 5">
  <div style="display: flex;" >
    <div class="div_img">
      <img src="@/assets/img/xiaomage.jpg" alt="">
    </div>
    <div style="display: flex;flex-direction: column;justify-content: space-evenly;">
      <p>拼多多平台用户</p>
      <p>5月26日 09:30</p>
    </div>
  </div>
  <div>
      <p>-159.00</p>
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
