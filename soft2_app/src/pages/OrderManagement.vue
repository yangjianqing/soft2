<template>
  <van-nav-bar
      title="订单信息"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
  />
  <van-pull-refresh v-model="loading" :onclick="AddProduct" @refresh="onRefresh">
    <van-tabs v-model:active="active">
      <van-tab title="全部订单">
        <van-cell  title="用户名"/>

        <van-swipe-cell v-for="list in 3">
          <OrderInfo>

          </OrderInfo>
        </van-swipe-cell>
        <h6 style="padding-top:15px;padding-bottom: 10px">猜你喜欢</h6>

        <div style="display: flex;flex-wrap: wrap;">
          <MerchandiseInfo v-for="list in 9"></MerchandiseInfo>
        </div>
      </van-tab>
      <van-tab title="待付款"> <van-cell  title="用户名"/>
        <van-swipe-cell v-for="list in 3">
          <OrderInfo>

          </OrderInfo>
        </van-swipe-cell>
        <h6 style="padding-top:15px;padding-bottom: 10px">猜你喜欢</h6>
        <div style="display: flex;flex-wrap: wrap;">
          <MerchandiseInfo v-for="list in 9"></MerchandiseInfo>
        </div>
      </van-tab>
      <van-tab title="待发货">
        <AddressGement>

        </AddressGement>
      </van-tab>
      <van-tab title="待收货">
        <van-cell  title="用户名"/>

        <van-swipe-cell v-for="list in 3">
          <OrderInfo>

          </OrderInfo>
        </van-swipe-cell>
        <h6 style="padding-top:15px;padding-bottom: 10px">猜你喜欢</h6>
        <div style="display: flex;flex-wrap: wrap;">
          <MerchandiseInfo v-for="list in 9"></MerchandiseInfo>
        </div>
      </van-tab>
      <van-tab title="评价">
      <AssessMent>

      </AssessMent>
      </van-tab>
    </van-tabs>

  </van-pull-refresh>
</template>

<script>

import AssessMent from "@/components/Assessment/AssessMent.vue";

export default {
  name:"OrderManagement",
  components: {MerchandiseInfo, OrderInfo, ShoppingCard, AddressGement, AssessMent},
  data(){
    return{
      count,
      loading,
      onRefresh,
      onClickLeft,
      active,
      actives
    }
  }, methods:{

  },

}
import {ref} from "vue";
import {showToast} from "vant";
import AddressGement from "@/components/address/AddressGement.vue";
import ShoppingCard from "@/components/card/ShoppingCard.vue";
import OrderInfo from "@/components/OrderInfo.vue";
import MerchandiseInfo from "@/components/Index/MerchandiseIfon.vue";
const actives = ref(1);
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

const active = ref(0);

</script>
<style scoped>
:root {
  --van-font-size-lg: 30px; /* 定义一个较大的字体大小 */
}
.goods-card {
  margin: 0;
  background-color: @white;
}

.delete-button {
  height: 100%;
}
>>> .van-cell__title {
  text-align: left !important;
}
.goods-card {
  display: flex;
  flex-direction: column;
}

.goods-card__title {
  order: 3; /* 将标题移至最后 */
}

.goods-card__price {
  order: 1; /* 将价格移至上方 */
}

.goods-card__desc {
  order: 2; /* 将描述保持在中间 */
}
</style>