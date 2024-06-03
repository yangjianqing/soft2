<template>
  <div v-if="hasItemsInCart">
    <router-link to="/cart/shoppinggement">
      <OrderInfo ></OrderInfo>
    </router-link>
  </div>
  <div v-else>
    <NoShopping >

    </NoShopping>
  </div>
  <h3>感谢你对本产品的使用</h3>
  <h3>记得给个五星好评哦！亲</h3>
  <van-rate v-model="value" />
</template>
<script>
import { ref } from 'vue';
import OrderInfo from "@/components/OrderInfo.vue";
import NoShopping from "@/components/NoShopping/NoShopping.vue";
const value = ref(3);
let values = 0;
const hasItemsInCart = ref(values);
    export default {
      name:"AssessMent",
      components: {NoShopping, OrderInfo},
      data(){
        return { value,hasItemsInCart };
      }
    }
</script>
<style scoped>

</style>