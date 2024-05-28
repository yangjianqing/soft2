<template>
    <div>
      <van-nav-bar   title="购物车" left-text="返回" left-arrow @click-left="onClickLeft"></van-nav-bar>
      <van-pull-refresh v-model="loading" @refresh="onRefresh">
      <router-link to="/cart/shoppinggement">
        <OrderInfo></OrderInfo>
      </router-link>


        <OrderInfo></OrderInfo>
        <OrderInfo></OrderInfo>
        <OrderInfo></OrderInfo>
        <OrderInfo></OrderInfo>
      </van-pull-refresh>

    </div>
</template>

<script>
import { ref } from 'vue';
import { showToast } from 'vant';
import OrderInfo from "@/components/OrderInfo.vue";

const count = ref(0);
const loading = ref(false);
const onRefresh = () => {
  setTimeout(() => {
    showToast('刷新成功');
    loading.value = false;
    count.value++;
  }, 1000);
};
const onClickLeft = () => history.back();
    export default {
        name: "CarPage",
      components: {OrderInfo},
        data(){
          return {
            count,
            loading,
            onRefresh,
            onClickLeft
          }
        },
      methods:{

      },
    }

</script>

<style scoped>

</style>