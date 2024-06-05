<template>
    <div>
      <van-nav-bar   title="购物车"></van-nav-bar>
      <van-pull-refresh v-model="loading" @refresh="onRefresh">
        <div v-if="hasItemsInCart">
          <router-link to="/cart/shoppinggement">
          <OrderInfo ></OrderInfo>
      </router-link>
        </div>
        <div v-else>
          <NoShopping >

          </NoShopping>
        </div>
        <van-divider
            :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
        >
          猜你喜欢
        </van-divider>


      <div style="display: flex;flex-wrap: wrap;">
        <MerchandiseInfo v-for="list in 10" v-on:click="Retrunshopping">

        </MerchandiseInfo>
      </div>
      </van-pull-refresh>
    </div>
  <van-action-bar safe-area-inset-bottom style="bottom: 48px">
    <van-action-bar-icon icon="cart-o" text="加入购物车" @click="onClickIcon" />
    <van-action-bar-button type="danger" text="立即购买" @click="onClickButton" />
  </van-action-bar>
</template>

<script>
import { ref } from 'vue';
import { showToast } from 'vant';
import OrderInfo from "@/components/OrderInfo.vue";
import NoShopping from "@/components/NoShopping/NoShopping.vue";
import MerchandiseInfo from "@/components/Index/MerchandiseIfon.vue";
import router from "@/router";

const count = ref(0);
const loading = ref(false);
const onRefresh = () => {
  setTimeout(() => {
    showToast('刷新成功');
    loading.value = false;
    count.value++;
  }, 1000);
};
let value = 1;
const hasItemsInCart = ref(value); // 假设这是从购物车服务获取的
  // fetchCartItems().then(items => {
  //   hasItemsInCart.value = items.length > 0;
  // });

    export default {
        name: "CarPage",
        components: {MerchandiseInfo, NoShopping, OrderInfo},
        data(){
          return {
            count,
            loading,
            onRefresh,
            hasItemsInCart
          }
        },
      methods:{
        Retrunshopping(){
          router.push({ path: '/cart/shoppinggement' });
        },
      },
    }

</script>

<style scoped>

</style>
