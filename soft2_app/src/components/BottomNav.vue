<template>
  <van-tabbar route v-model="active">
    <van-tabbar-item icon="home-o" to="/index">首页</van-tabbar-item>
    <van-tabbar-item icon="apps-o" to="/classification">分类</van-tabbar-item>
    <van-tabbar-item icon="cart-o" :badge="dataCount !== 0 ? dataCount : ''" to="/cart">购物车</van-tabbar-item>
    <van-tabbar-item icon="user-o" to="/mine">我的</van-tabbar-item>
  </van-tabbar>
</template>

<script>
import { getCarList } from "@/api/merchant";

export default {
  name: "BottomNav",
  data() {
    return {
      active: 0,    // 初始化 active 属性，确保与实际选项对应
      dataCount: 0, // 初始化购物车数据数量
      userInfo: {}, // 初始化用户信息对象，以避免未定义错误
      goodsList: [] // 初始化购物车商品列表
    };
  },
  created() {
    // 从本地存储获取用户信息
    const userInfoString = localStorage.getItem("userInfo");
    this.userInfo = JSON.parse(userInfoString) || {}; // 解析用户信息，注意处理可能的空字符串情况

    // 获取购物车数据并更新数量
    if (this.userInfo.usersPhone) {
      getCarList(this.userInfo.usersPhone).then(res => {
        this.goodsList = res.data.data || []; // 将返回的商品数据保存到 goodsList 中
        this.dataCount = this.goodsList.length; // 更新购物车数据数量
        console.log("购物车商品数量:", this.dataCount);
      }).catch(error => {
        console.error('获取购物车数据失败:', error);
      });
    }
  }
};
</script>

<style scoped>
.nav {
  width: 100%;
  height: 60px;
  position: fixed;
  bottom: 0;
  border-top: 1px solid #878787;
}

.router-link-active {
  color: #0381DD;
}

.active {
  color: #0381DD;
}
</style>
