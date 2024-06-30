<template>
  <van-nav-bar
      title="订单信息"
      left-text="返回"
      left-arrow
      @click-left=" this.$router.go(-1)"
  />
    <van-tabs v-model:active="active" style="margin-bottom: 40px">
      <van-tab title="全部订单">
        <template  v-for="(goodsInfo,index) in reversedGoodsList" >
          <OrderListInfo :goodsInfo="goodsInfo"> </OrderListInfo>
        </template>
      </van-tab>
      <van-tab title="待付款">
        <template  v-for="(goodsInfo,index) in reversedGoodsLists  " >
          <OrderPayInfo :goodsInfo="goodsInfo"> </OrderPayInfo>
        </template>

      </van-tab>
      <van-tab title="待发货">
        <template  v-for="(goodsInfo,index) in reversedFilterType(1)" >
          <OrderListInfo :goodsInfo="goodsInfo"> </OrderListInfo>
        </template>
      </van-tab>
      <van-tab title="待收货">
        <template  v-for="(goodsInfo,index) in reversedFilterType(2)" >
          <OrderConfirmInfo :goodsInfo="goodsInfo"> </OrderConfirmInfo>
        </template>
      </van-tab>
      <van-tab title="已完成">
        <template  v-for="(goodsInfo,index) in reversedFilterType(3)" >
          <OrderListInfo :goodsInfo="goodsInfo"> </OrderListInfo>
        </template>
      </van-tab>


    </van-tabs>

</template>
<!-- 底部弹出 -->

<script>

// 导入 vue-router 中的 router 对象
import router from "@/router";
import {getCarList, selectOrders, selectShopingData} from "@/api/merchant";
import OrderListInfo from "@/components/OrderlistInfo.vue";
import OrderPayInfo from "@/components/OrderPayInfo.vue";
import OrderConfirmInfo from "@/components/OrderConfirmInfo.vue";
export default {
  name:"OrderManagement",
  components: {OrderConfirmInfo, OrderPayInfo, OrderListInfo},
  data(){
    return{
      active:0,
      goodsList:[],
      goodsLists:[],
      goodsPayList:[],
      goodsListInfo:""
    }
  },
  computed: {
    reversedGoodsList() {
      return [...this.goodsList].reverse();
    },
    reversedGoodsLists() {
      return [...this.goodsLists].reverse();
    },
  },
  methods: {


    //筛选订单状态
    filterType(status) {
      return this.goodsList.filter(obj => obj.ordersStatus === status);
    },
    //筛选未支付
    reversedFilterType(status) {
      const filteredGood = this.filterType(status);
      return filteredGood.reverse();
    },
  },
  created() {
    //取购物车的数据
    const userInfoString = localStorage.getItem("userInfo");
    // 将字符串解析回对象
    this.userInfo = JSON.parse(userInfoString);
    // 现在可以访问对象中的属性了
    //查询
    selectOrders(this.userInfo.usersPhone).then(res =>{
      this.goodsList=res.data.data;
    }).catch(err =>{
      console.log(err)
    });
    selectShopingData(this.userInfo.usersPhone).then(res =>{
      this.goodsLists = res.data.data;
    }).catch(err =>{
      console.log(err)
    });
  }

}





</script>
<style scoped>
:root {
  --van-font-size-lg: 30px; /* 定义一个较大的字体大小 */
}


.bottoms{
  background-color: #e3e3e3;
}
</style>
