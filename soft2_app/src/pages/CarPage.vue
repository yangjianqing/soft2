<template>
    <div>
      <van-nav-bar   title="购物车"></van-nav-bar>

      <van-pull-refresh v-model="loading" @refresh="onRefresh">
        <div v-if="hasItemsInCart">
<!--          -->
<!--          <template  v-for="(goodsInfo,index) in goodsList" >-->
          <OrderInfo ></OrderInfo>
<!--          </template>-->
        </div>
        <div v-else>
          <NoShopping>

          </NoShopping>
        </div>

        <van-divider
            :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
        >
          猜你喜欢
        </van-divider>
      <div style="display: flex;flex-wrap: wrap;">
        <template v-for="(goodsInfo,index) in goodsList">
          <div class="total_box" v-if="index%2===1">
            <MerchandiseInfo @click="Retrunshopping" :goodsInfo="goodsList[index-1]"></MerchandiseInfo>
            <MerchandiseInfo @click="Retrunshopping" :goodsInfo="goodsList[index]"></MerchandiseInfo>
          </div>
        </template>
      </div>
      </van-pull-refresh>
    </div>
  <van-action-bar  safe-area-inset-bottom style="bottom: 48px">
    <van-action-bar-button color="#be99ff" type="warning" :text="'总净额:'+{total}" :click="addToCar" />
    <van-action-bar-button color="#7232dd" type="danger" text="去付款" @click="Retrunshoppings" />
  </van-action-bar>

</template>

<script>
import { ref } from 'vue';
import { showToast } from 'vant';
import OrderInfo from "@/components/OrderInfo.vue";
import NoShopping from "@/components/NoShopping/NoShopping.vue";
import MerchandiseInfo from "@/components/Index/MerchandiseIfon.vue";
import router from "@/router";
import {CarList, listShopping,getCarList} from "@/api/merchant";
import Goods from "@/components/good/goods.vue";

const count = ref(0);
const loading = ref(false);
const onRefresh = () => {
  setTimeout(() => {
    showToast('刷新成功');
    loading.value = false;
    count.value++;
  }, 1000);
};
let value = 1;
const hasItemsInCart = ref(value); // 假设这是从购物车服务获取的
  // fetchCartItems().then(items => {
  //   hasItemsInCart.value = items.length > 0;
  // });

    export default {
        name: "CarPage",
        components: {Goods, MerchandiseInfo, NoShopping, OrderInfo},
        props:['goods'],
        data(){
          return {
            count,
            loading,
            onRefresh,
            hasItemsInCart,
            goodsList:[],

          }
        },
      methods:{
        Retrunshopping(){
          router.push({ path: '/cart/shoppinggement' });
        },
        Retrunshoppings(){
          router.push({ path: '/mine/ordermanagement' });
        },
        //金额
        addToCar(){
            alert("你好")
        },
        //图片
        getGoodsList(){
          listShopping().then(res=>{
            console.log(res.data.rows);
            this.goodsList=res.data.rows;
          })
        },
        getCarInfoList(){
          CarList().then(res=>{
            console.log(res);
          })
        }
      },
      created() {

          this.getGoodsList();
          this.getCarInfoList();

      }
    }

</script>

<style scoped>

</style>
