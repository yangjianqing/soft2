<template>
    <div class="nav">
      <van-tabbar route v-model="active">
        <van-tabbar-item icon="home-o" to="/index"  >首页</van-tabbar-item>
        <van-tabbar-item icon="apps-o"  to="/classification">分类</van-tabbar-item>
        <van-tabbar-item icon="cart-o" badge="5" to="/cart" >购物车</van-tabbar-item>
        <van-tabbar-item icon="user-o" badge="20" to="/mine">我的</van-tabbar-item>
      </van-tabbar>
    </div>

</template>

<script>

export default {
        name: "BottomNav"
    ,
    data() {
      return {

      };
    },
    }
</script>

<style scoped>
    .nav{
        width: 100%;
        height: 60px;
        /*将导航栏固定到底部*/
        position: fixed;
        bottom: 0;
    }

    /*激活样式*/
    .router-link-active{
        color: #0381DD;
    }
    .active{
        color: #0381DD;

    }

</style>