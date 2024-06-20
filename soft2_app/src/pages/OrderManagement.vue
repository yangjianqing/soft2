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
        <van-swipe-cell v-for="list in 3">
<!--          <OrderInfo>-->

<!--          </OrderInfo>-->
        </van-swipe-cell>
        <h6 style="padding-top:15px;padding-bottom: 10px">猜你喜欢</h6>

        <div style="display: flex;flex-wrap: wrap;">
          <template v-for="(goodsInfo,index) in goodsList">
            <div class="total_box" v-if="index%2===1">
              <MerchandiseInfo :goodsInfo="goodsList[index-1]"></MerchandiseInfo>
              <MerchandiseInfo :goodsInfo="goodsList[index]"></MerchandiseInfo>
            </div>
          </template>
        </div>
      </van-tab>
      <van-tab title="待付款">

        <van-swipe-cell v-for="list in 3">
<!--          <OrderInfo>-->

<!--          </OrderInfo>-->
        </van-swipe-cell>
        <h6 style="padding-top:15px;padding-bottom: 10px">猜你喜欢</h6>
        <div style="display: flex;flex-wrap: wrap;">
          <template v-for="(goodsInfo,index) in goodsList">
            <div class="total_box" v-if="index%2===1">
              <MerchandiseInfo :goodsInfo="goodsList[index-1]"></MerchandiseInfo>
              <MerchandiseInfo :goodsInfo="goodsList[index]"></MerchandiseInfo>
            </div>
            <van-action-bar  safe-area-inset-bottom>
              <van-action-bar-button color="#be99ff" type="warning" :text="'总净额:'+{total}" />
              <van-action-bar-button color="#7232dd" type="danger" text="付款" @click="showPopup" />
            </van-action-bar>
          </template>

        </div>

      </van-tab>

      <van-tab title="待发货">
        <AddressGement>

        </AddressGement>
      </van-tab>
      <van-tab title="待收货">
        <van-swipe-cell>
<!--          <OrderInfo> </OrderInfo>-->
        </van-swipe-cell>
        <h6 style="padding-top:15px;padding-bottom: 10px">猜你喜欢</h6>
        <div style="display: flex;flex-wrap: wrap;">
          <template v-for="(goodsInfo,index) in goodsList">
            <div class="total_box" v-if="index%2===1">
              <MerchandiseInfo :goodsInfo="goodsList[index-1]"></MerchandiseInfo>
              <MerchandiseInfo :goodsInfo="goodsList[index]"></MerchandiseInfo>
            </div>
          </template>
        </div>
      </van-tab>
      <van-tab title="评价">
      <AssessMent>

      </AssessMent>
        <h6 style="padding-top:15px;padding-bottom: 10px">猜你喜欢</h6>
        <div style="display: flex;flex-wrap: wrap;">
          <template v-for="(goodsInfo,index) in goodsList">
            <div class="total_box" v-if="index%2===1">
              <MerchandiseInfo :goodsInfo="goodsList[index-1]"></MerchandiseInfo>
              <MerchandiseInfo :goodsInfo="goodsList[index]"></MerchandiseInfo>
            </div>
          </template>
        </div>
      </van-tab>
    </van-tabs>
  </van-pull-refresh>
  <van-popup
    v-model:show="show"
    position="bottom"
    :style="{ height: '70%' }"
    class="bottoms"
  >
  <PayInfo></PayInfo>
  </van-popup>
</template>
<!-- 底部弹出 -->

<script>

import AssessMent from "@/components/Assessment/AssessMent.vue";

const show = ref(false);
const showPopup = () => {
  show.value = true;
};
export default {
  name:"OrderManagement",
  components: {PayInfo, MerchandiseInfo, OrderInfo, ShoppingCard, AddressGement, AssessMent},
  data(){
    return{
      count,
      loading,
      onRefresh,
      onClickLeft,
      active,
      actives,
      goodsList:[],
      show,
      showPopup,
    }
  },
  methods:{
    getGoodsList(){
      listShopping().then(res=>{
        console.log(res.data.rows);
        this.goodsList=res.data.rows;
      })
    },

  },
  created() {
    //获取商品列表
    this.getGoodsList();
  },
}
import {ref} from "vue";
import {showToast} from "vant";
import AddressGement from "@/components/address/AddressGement.vue";
import ShoppingCard from "@/components/card/ShoppingCard.vue";
import OrderInfo from "@/components/OrderInfo.vue";
import MerchandiseInfo from "@/components/Index/MerchandiseIfon.vue";
import {listShopping} from "@/api/merchant";
import router from "@/router";
import PayInfo from "@/pages/PayInfo/PayInfo.vue";
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
.bottoms{
  background-color: #e3e3e3;
}
</style>
