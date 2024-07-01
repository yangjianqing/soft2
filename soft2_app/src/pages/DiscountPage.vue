<template>

  <div>
    <van-nav-bar left-text="返回" left-arrow @click-left="goBack" class="fixed-nav-bar">

      <!-- 中间标题 -->
      <template #title>
        <div>超划算</div>
      </template>

      <!-- 右侧购物车图标 -->
      <template #right>
        <router-link :to="{ path: '/cart' }">
            <van-icon name="cart-o" size="18"  :badge="dataCount !== 0 ? dataCount : ''"/>
        </router-link>
      </template>

    </van-nav-bar>
    <div>

<!--      留白-->
    <div style="height: 2.5rem;"></div>

    <template v-for="(favorable,index) in favorableList">
      <router-link :to="{ path: '/cart/shoppinggement/'+favorableList[index].id  }">
      <MerchandiseList :favorable="favorable"></MerchandiseList>
      </router-link>
    </template>
    </div>
  </div>


</template>


<script >
import MerchandiseList from "@/components/Index/MerchandiseList.vue";
import {getCarList, selectFGoodsByFavorable} from "@/api/merchant";

export default {
  components: {
    MerchandiseList
  },
  data() {
    return {
      favorableList:[],
      cartCount: 3, // 示例购物车商品数量
      dataCount:0,
    };
  },
  created() {
    //获取超盒算列表
    this.getFavorableList();
    //取购物车的数据,取给购物车数量
    const userInfoString = localStorage.getItem("userInfo");
    // 将字符串解析回对象
    this.userInfo = JSON.parse(userInfoString);
    // 现在可以访问对象中的属性了
    getCarList(this.userInfo.usersPhone).then(res => {
      if ( res.data.data.length > 0 ) {
        this.goodsList = res.data.data;
        this.dataCount = this.goodsList.length;
      } else {
        // 处理数据为空或者不是预期的数组情况
        console.error('购物车没有任何商品：', res.data);
      }
    }).catch(error => {
      console.error('购物车没有任何商品：', error);
    });
  },

  methods: {
    goBack() {
      // 返回上一菜单的逻辑，可以根据您的路由设置进行相应调整
      this.$router.go(-1); // 如果使用了 Vue Router
    },
    getFavorableList(){
      selectFGoodsByFavorable().then(res=>{
        this.favorableList=res.data.rows;
      })
    },
  }
}


</script>

<style scoped>
.fixed-nav-bar {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 1000; /* 确保导航栏位于其他内容的上层 */
}

.cart-badge {
  position: relative;
}

.cart-badge .van-badge__content {
  position: absolute;
  top: -8px;
  right: -8px;
}
</style>
