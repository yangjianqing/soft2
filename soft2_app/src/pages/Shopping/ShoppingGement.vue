<template>
  <van-nav-bar
    left-text="返回"
    left-arrow
    @click-left="onClickLeft"
  >
    <template #right>
      <router-link to="/cart">
      <div class="right_nav">
        <van-icon name="cart-o" size="18"  :badge="this.dataCount"/>
      </div>
      </router-link>
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
import {listShopping, addCarList, selectIdByInfo, getCarList} from "@/api/merchant";

const onClickLeft = () => history.back();
export default {
  components: {MerchandiseInfo},
  props:['goodsInfo'],
  data(){
    return{
      onClickLeft,
      goodsList:[],
      selectedGoods:[],
      baseUrl:"",
      imgUrls:"",
      usersPhone:"",
      dataCount:0,
    }
  },
  methods:{
    //点击加入购物车
    onClickIcon(){
      const userInfoString = localStorage.getItem("userInfo");
      // 将字符串解析回对象
      const userInfo = JSON.parse(userInfoString);

      // 现在可以访问对象中的属性了
      this.usersPhone = userInfo.usersPhone; // 假设属性名为 phone
      addCarList(this.usersPhone,this.selectedGoods.coding).then(res=>{
          if ( res.data.msg === '加入购物车成功'){
            showToast('加入成功'); // 在成功时显示提示
            this.$router.go(0)
          }
      })
    },
    //点击立即购买
    onClickButton(){
      const userInfoString = localStorage.getItem("userInfo");
      // 将字符串解析回对象
      const userInfo = JSON.parse(userInfoString);
      // 现在可以访问对象中的属性了
      this.usersPhone = userInfo.usersPhone; // 假设属性名为 phone
      addCarList(this.usersPhone,this.selectedGoods.coding).then(res=>{
        if ( res.data.msg === '加入购物车成功'){
          router.push("/mine/ordermanagement");
        }
      })
    },
    getGoodsList() {
      listShopping().then(res => {
        this.goodsList = res.data.rows;
      })
    },
    selectById(){
      selectIdByInfo(this.$route.params.id).then(res=>{
        this.selectedGoods = res.data.id;
        this.imgUrls = this.selectedGoods.image.split(',');

      })
    },
  },
  created() {
    //获取商品列表
    this.baseUrl = process.env.VUE_APP_BASE_API;
    this.getGoodsList();
    // 从路由参数中获取商品id
    this.selectById();
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
.list-src {
  font-size: 0.9rem;
  background-color: rgb(242, 242, 242);;

  border-radius: 7px;
}
.list-ts{
  font-size: 0.9rem;
  color: #A6C9E2;
}

.total_box{
  width: 100%;
  height: 100%;
  display: flex;
  background-color:rgb(245,245,245) ;
  border-radius: 10px;
}
</style>
