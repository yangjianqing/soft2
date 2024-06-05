
<template>
 <div>
   <van-nav-bar
       title="积分管理"
       left-text="返回"
       left-arrow
       @click-left="onClickLeft"
   />
   <PointsManagements>

   </PointsManagements>
 </div>
</template>

<script>


import PointsManagements from "@/components/pointsmanagement/PointsManagement.vue";

export default {
    name:"PointsManagement",
  components: {PointsManagements},
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
