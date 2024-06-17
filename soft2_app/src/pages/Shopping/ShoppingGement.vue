<template>
  <van-nav-bar
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
  >
    <template #right>
      <div class="right_nav">
        <van-icon name="cart-o" size="20"/>
      </div>
    </template>
  </van-nav-bar>

<!--  <MerchandiseInfo></MerchandiseInfo>-->
  <van-swipe :autoplay="3000" lazy-render>
    <van-swipe-item v-for="(goodsInfo,index) in imgUrls">
      <img style="width: 375px;height: 375px;" :src="this.baseUrl+goodsInfo" class="custom-image-size" alt=""/>
    </van-swipe-item>
  </van-swipe>


   <div  style="margin-top: 3%">
     <div style="display: flex; padding-left: 3%; font-size: 20px ;font-weight: bold;">
       <p>{{ selectedGoods.name }}</p>
     </div>
     <div style="display:flex;justify-content: space-between;align-items: center;">
       <span style="font-size: 16px ; color:#888686;margin-left: 2.5%">{{ selectedGoods.description }}</span>
       <div style="display: flex; margin-right: 2.5% ">
         <span style="color: red; font-size: 20px">￥{{selectedGoods.price}}</span>
         <span style=" font-size: 12px;margin-top: 13%">/{{ selectedGoods.unit }}</span>
       </div>
     </div>

   </div>






  <van-divider
      :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
  >
    猜你喜欢
  </van-divider>
  <div style="display: flex;flex-wrap: wrap;">
    <template v-for="(goodsInfo,index) in goodsList">
      <div class="total_box" v-if="index%2===1">
        <MerchandiseInfo :goodsInfo="goodsList[index-1]"></MerchandiseInfo>
        <MerchandiseInfo :goodsInfo="goodsList[index]"></MerchandiseInfo>
      </div>
    </template>
  </div>
  <van-action-bar>
    <van-action-bar-icon icon="cart-o" text="加入购物车" @click="onClickIcon" />
    <van-action-bar-button type="danger" text="立即购买" @click="onClickButton" />
  </van-action-bar>
</template>
<script>
import { showToast } from 'vant';
import MerchandiseInfo from "@/components/Index/MerchandiseIfon.vue";
import router from "@/router";
import {listShopping} from "@/api/merchant";
import {selectIdByInfo} from "@/api/merchant.js";

const onClickLeft = () => history.back();
const onClickIcon = () => showToast('已添加购物车');
const onClickButton = () => showToast('立即购买');
export default {
  components: {MerchandiseInfo},
  props:['goodsInfo'],
  data(){
    return{
      onClickLeft,
      images,
      onClickIcon,
      onClickButton,
      goodsList:[],
      selectedGoods:[],
      baseUrl:"",
      imgUrls:""
    }
  },
  methods:{
    Retrunshopping(){
      router.push({ path: '/cart/shoppinggement' });
    },
    getGoodsList(){
      listShopping().then(res=>{
        this.goodsList=res.data.rows;
      })
    },
    selectById(){
      selectIdByInfo(this.$route.params.id).then(res=>{
        console.log(res.data);
        this.selectedGoods = res.data.id;
        this.imgUrls = this.selectedGoods.image.split(',');

      })
    },
  },
  created() {
    //获取商品列表
    this.baseUrl=process.env.VUE_APP_BASE_API;
    this.getGoodsList();
    // 从路由参数中获取商品id
    this.selectById();

  },
}
const images = [

];

</script>
<style scoped>
.my-swipe .van-swipe-item {
  color: #fff;
  font-size: 20px;
  line-height: 150px;
  text-align: center;
  background-color: #39a9ed;
}
.custom-image-size {
  width: 100%; /* 设置图片宽度 */
  height: auto; /* 保持图片宽高比 */
}
.list-src{
  font-size: 0.9rem;
  background-color: rgb(242, 242, 242);;

  border-radius: 7px;
}
.list-ts{
  font-size: 0.9rem;
  color: #A6C9E2;
}

</style>
