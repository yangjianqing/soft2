<template>
  <van-nav-bar
      title="商品名称"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
  />
<!--  <MerchandiseInfo></MerchandiseInfo>-->
  <van-swipe :autoplay="3000" lazy-render>
    <van-swipe-item v-for="(goodsInfo,index) in goodsList">
      <img :src="this.baseUrl+goodsInfo.image" class="custom-image-size" alt=""/>
    </van-swipe-item>
  </van-swipe>
  <van-cell-group>
    <van-cell title="东台8424西瓜 1个装（单果3kg起）" value="皮薄肉厚 汁水充盈" label="盒马量贩·基地优选·售后无忧" />
  </van-cell-group>
  <div style="display: flex;align-items: flex-end;">
    <h3 style="color: red;">￥38</h3>
    <p style="padding-left: 8px;
    padding-right: 8px;">/箱</p>
  </div>
  <van-cell class="list-src" color="#A6C9E2" title="商家24H发货，第三方物流配送，免运费" icon="guide-o" />
    <!-- 使用 right-icon 插槽来自定义右侧图标 -->
  <van-cell class="list-src"  title="已选：1个/箱" is-link url="https://github.com" />
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
      baseUrl:"",
    }
  },
  methods:{
    Retrunshopping(){
      router.push({ path: '/cart/shoppinggement' });
    },
    getGoodsList(){
      listShopping().then(res=>{
        console.log(res.data.rows);
        this.goodsList=res.data.rows;
      })
    },
  },
  created() {
    //获取商品列表
    this.baseUrl=process.env.VUE_APP_BASE_API;
    this.getGoodsList();

  },
}
const images = [
  'https://fastly.jsdelivr.net/npm/@vant/assets/apple-1.jpeg',
  'https://fastly.jsdelivr.net/npm/@vant/assets/apple-2.jpeg',
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
