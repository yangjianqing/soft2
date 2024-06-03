
<template>
 <div>
   <van-nav-bar
       title="积分管理"
       left-text="返回"
       left-arrow
       @click-left="onClickLeft"
   />
   <van-cell-group v-for="list in 3">
     <van-cell title="{{list.name}}" value="{{list.valuephone}}" />
   </van-cell-group>
 </div>
</template>

<script>



export default {
    name:"PointsManagement",
     computed: {  },
    data(){
      return {
        list: {
        name: "在2024年6月3号，办理了会员",
        valuephone: "+3",
      },
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