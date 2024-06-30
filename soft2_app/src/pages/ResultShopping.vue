<template>
  <div style="  margin-top: 2%;">
    <van-nav-bar  left-text="返回" left-arrow @click-left="goBack">
      <template #right>
        <van-icon name="cart-o"  size="18"/>
      </template>
    </van-nav-bar>
  </div>

  <div class="shopping_box" >
    <template v-for="(favorable,index) in favorableList">
      <router-link :to="{ path: '/cart/shoppinggement/'+favorableList[index].id  }">
        <MerchandiseList :favorable="favorable"></MerchandiseList>
      </router-link>
    </template>
  </div>



</template>


<script >
import {goods} from "@/api/merchant";
import MerchandiseInfo from "@/components/Index/MerchandiseIfon.vue";
import MerchandiseList from "@/components/Index/MerchandiseList.vue";

export default {
  name: "ResultShopping",
  components: {MerchandiseList, MerchandiseInfo},
  props: {
    keyword: {
      type: String,
      required: true, // 确保 keyword 是必需的
      default: '', // 设置默认值，防止未传递时出现问题
    }
  },
  data(){
    return {
      favorableList:[],
    }
  },
  created() {
    this.selectGoods();
  },
  methods: {
    goBack() {
      // 返回上一菜单的逻辑，可以根据您的路由设置进行相应调整
      this.$router.go(-1); // 如果使用了 Vue Router
    },
    selectGoods(){
      goods(this.keyword).then(res=>{
        console.log( res.data.goodsName)
        this.favorableList = res.data.goodsName; // 将 API 返回的数据存储到 goodsList
      })
    }
  }
}
</script>

<style scoped>
.shopping_box{
  background-color: #ffffff;
  border-radius: 10px;
}

</style>
