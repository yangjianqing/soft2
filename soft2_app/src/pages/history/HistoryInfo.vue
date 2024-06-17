
<template>
  <div>
    <van-nav-bar
        title="历史记录"
        left-text="返回"
        left-arrow
        @click-left="onClickLeft"
    />
    <router-link to="/mine/address">
    <van-cell class="address" :title="text" icon="location-o" is-link/>

    </router-link>

    <OrderInfo></OrderInfo>
  </div>
</template>

<script>



import OrderInfo from "@/components/OrderInfo.vue";

export default {
  name:"HistoryInfo",
  components: {OrderInfo},
  computed: {  },
  data() {
    return {
      list: {
        name: "在2024年6月3号，办理了会员",
        valuephone: "+3",
      },
      text:"地址管理",
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
.address{
  margin-top: 6px;
}
</style>
