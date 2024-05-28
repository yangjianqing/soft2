
<template>
 <div>
   <van-nav-bar
       title="积分管理"
       left-text="返回"
       left-arrow
       @click-left="onClickLeft"
   />
   <van-cell-group v-for="list in 3">
     <van-cell title="单元格" value="内容" />
   </van-cell-group>
 </div>
</template>

<script>


export default {
    name:"PointsManagement",
    data(){
      return {
        onClickLeft,
      };
    }
}
const onClickLeft = () => history.back();
</script>
<style scoped>
>>> .van-cell__title {
  text-align: left !important;
}
</style>