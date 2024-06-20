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
        <template  v-for="(goodsInfo,index) in goodsList" >
          <OrderListInfo :goodsInfo="goodsInfo"> </OrderListInfo>
        </template>
      </van-tab>
      <van-tab title="待付款">
        <template  v-for="(goodsInfo,index) in filterType(1)" >
          <OrderListInfo :goodsInfo="goodsInfo"> </OrderListInfo>
        </template>
      </van-tab>
      <van-tab title="待发货">
        <template  v-for="(goodsInfo,index) in filterType(1)" >
          <OrderListInfo :goodsInfo="goodsInfo"> </OrderListInfo>
        </template>
      </van-tab>

      <van-tab title="待收货">
        <template  v-for="(goodsInfo,index) in filterType(1)" >
          <OrderListInfo :goodsInfo="goodsInfo"> </OrderListInfo>
        </template>
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
import {ref} from "vue";
import {showToast} from "vant";
import AddressGement from "@/components/address/AddressGement.vue";
import ShoppingCard from "@/components/card/ShoppingCard.vue";
import OrderInfo from "@/components/OrderInfo.vue";
import MerchandiseInfo from "@/components/Index/MerchandiseIfon.vue";
import {selectOrders} from "@/api/merchant";
import router from "@/router";
import PayInfo from "@/pages/PayInfo/PayInfo.vue";
import OrderListInfo from "@/components/OrderlistInfo.vue";
const show = ref(false);
const showPopup = () => {
  show.value = true;
};
export default {
  name:"OrderManagement",
  components: {OrderListInfo, PayInfo, MerchandiseInfo, OrderInfo, ShoppingCard, AddressGement, AssessMent},
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
      goodsListInfo:""
    }
  },
  methods:{

    getTotal(){
      let count=0;
      this.goodsList.forEach(e=>{
        count+=e.price * e.quantity
      })
      return count;
    },
    filterType(status){
      return this.goodsList.filter(obj=>obj.ordersStatus===status);
    }

  },
  created() {
    //取购物车的数据
      const userInfoString = localStorage.getItem("userInfo");
      // 将字符串解析回对象
      this.userInfo = JSON.parse(userInfoString);
      // 现在可以访问对象中的属性了
    selectOrders(this.userInfo.usersPhone).then(res =>{
        console.log(res)
        this.goodsList=res.data.data;
      })
  },
}

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


.bottoms{
  background-color: #e3e3e3;
}
</style>
