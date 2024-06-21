<template>

      <van-tabbar route v-model="active">
        <van-tabbar-item icon="home-o" to="/index"  >首页</van-tabbar-item>
        <van-tabbar-item icon="apps-o"  to="/classification">分类</van-tabbar-item>
        <van-tabbar-item icon="cart-o" :badge="this.dataCount" to="/cart" >购物车</van-tabbar-item>
        <van-tabbar-item icon="user-o"  to="/mine">我的</van-tabbar-item>
      </van-tabbar>


</template>

<script>

import {getCarList} from "@/api/merchant";

export default {
        name: "BottomNav"
    ,
    data() {
      return {
        active: 0, // 初始化 active 属性，以防止 v-model 绑定出错
        dataCount:0,
      };
    },

    created(){
      //取购物车的数据,取给购物车数量
      const userInfoString = localStorage.getItem("userInfo");
      // 将字符串解析回对象
      this.userInfo = JSON.parse(userInfoString);
      // 现在可以访问对象中的属性了
      getCarList(this.userInfo.usersPhone).then(res =>{
        // 将数据赋值给 goodsList
        this.goodsList = res.data.data;
        // 获取数据的数量
        // 获取数据的数量并保存到 dataCount
        this.dataCount = this.goodsList.length;
        // 打印数据数量到控制台
        console.log( this.dataCount)
      });
    }
}
</script>

<style scoped>
    .nav{
        width: 100%;
        height: 60px;
        /*将导航栏固定到底部*/
        position: fixed;
        bottom: 0;
        border-top: 1px solid #878787;
    }

    /*激活样式*/
    .router-link-active{
        color: #0381DD;
    }
    .active{
        color: #0381DD;

    }

</style>
