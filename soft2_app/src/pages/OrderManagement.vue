<template>
  <van-nav-bar
      title="订单信息"
      left-text="返回"
      left-arrow
      @click-left=" this.$router.go(-1)"
  />
  <van-pull-refresh v-model="loading" >
    <van-tabs v-model:active="active">
      <van-tab title="全部订单">
        <template  v-for="(goodsInfo,index) in goodsList" >
          <OrderListInfo :goodsInfo="goodsInfo"> </OrderListInfo>
        </template>
      </van-tab>
<!--      <van-tab title="待付款">-->
<!--        <template  v-for="(goodsInfo,index) in filterType(1)" >-->
<!--          <OrderListInfo :goodsInfo="goodsInfo"> </OrderListInfo>-->
<!--        </template>-->
<!--      </van-tab>-->
<!--      <van-tab title="待发货">-->
<!--        <template  v-for="(goodsInfo,index) in filterType(2)" >-->
<!--          <OrderListInfo :goodsInfo="goodsInfo"> </OrderListInfo>-->
<!--        </template>-->
<!--      </van-tab>-->

<!--      <van-tab title="待收货">-->
<!--        <template  v-for="(goodsInfo,index) in filterType(3)" >-->
<!--          <OrderListInfo :goodsInfo="goodsInfo"> </OrderListInfo>-->
<!--        </template>-->
<!--      </van-tab>-->

    </van-tabs>
  </van-pull-refresh>

</template>
<!-- 底部弹出 -->

<script>


import {selectOrders} from "@/api/merchant";
import OrderListInfo from "@/components/OrderlistInfo.vue";
export default {
  name:"OrderManagement",
  components: {OrderListInfo},
  data(){
    return{
      loading:false,
      active:0,
      goodsList:[],
      goodsListInfo:""
    }
  },
  methods:{
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
