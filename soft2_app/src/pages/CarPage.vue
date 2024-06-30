<template>
    <div>
      <van-sticky>
        <van-nav-bar   title="购物车"></van-nav-bar>
      </van-sticky>

      <div style="margin-bottom: 100px">
        <template  v-for="(goodsInfo,index) in goodsList" >
            <OrderInfo :goodsInfo="goodsInfo"></OrderInfo>
        </template>
      </div>
    </div>
  <van-action-bar  safe-area-inset-bottom style="bottom: 48px">
    <van-action-bar-button color="#be99ff" type="warning" :text="'总金额:'+getTotal()+'￥'" />
    <van-action-bar-button color="#7232dd" type="danger" text="结算" @click="RouterShopping" />
  </van-action-bar>

</template>

<script>

import OrderInfo from "@/components/OrderInfo.vue";
import {getCarList} from "@/api/merchant";
import router from "@/router";



  export default {
      name: "CarPage",
      components: {OrderInfo},
      data(){
        return {
          userInfo:"",
          goodsList:[],

        }
      },

    methods:{
      //跳转结算页面
      RouterShopping(){
        router.push("/PayInfo");
      },
      getTotal() {
        let count = 0;
        // 检查 this.goodsList 是否定义且是数组
        if (this.goodsList && Array.isArray(this.goodsList)) {
          this.goodsList.forEach(e => {
            count += e.price * e.quantity;
          });
        } else {
          console.error('"查无购物车数据"');
        }
        return count.toFixed(2);
      }
    },

    created() {
      //取购物车的数据
      const userInfoString = localStorage.getItem("userInfo");
      // 将字符串解析回对象
       this.userInfo = JSON.parse(userInfoString);
      // 现在可以访问对象中的属性了
      getCarList(this.userInfo.usersPhone).then(res =>{
          this.goodsList=res.data.data;
      });

    },

  }

</script>

<style scoped>

</style>
