

<template>
  <!--顶部导航栏-->
  <!--定位信息-->

  <van-sticky >
  <van-nav-bar>
    <template #right>
      <div class="right_nav">
        <van-icon name="chat-o" badge="99+" size="20"/>
      </div>
    </template>
    <template #left>
      <van-icon name="location-o" size="20" />
      <span >
            泸州市
      </span>
    </template>
    <template #title>
      <!--搜索框-->
      <router-link to="/index/search">
        <van-search
            style="width: 100%;height: 30px"
            v-model="value"
            shape="round"
            placeholder="请输入搜索关键词"
        />
      </router-link>
    </template>
  </van-nav-bar>
  </van-sticky>

  <!--第一个模块-->
  <div class="row_swipe">
     <!--轮播图-->
    <van-swipe style="border-radius: 10px" :autoplay="3000" lazy-render>
      <van-swipe-item v-for="image in images" :key="image">
        <img class="van_swipe_item_img" :src="image" />
      </van-swipe-item>
    </van-swipe>
  </div>
  <div class="Classification">
    <router-link to="/classification">
    <van-swipe :show-indicators="false">
      <van-swipe-item >
          <van-grid  :border="true" :column-num="8">
          <van-grid-item >
            <img class="img_item" src="https://fastly.jsdelivr.net/npm/@vant/assets/apple-1.jpeg" >
            <p>果蔬鲜花</p>
          </van-grid-item>
          <van-grid-item>
            <img class="img_item" src="https://fastly.jsdelivr.net/npm/@vant/assets/apple-1.jpeg" >
            <p>果蔬鲜花</p>
          </van-grid-item>
          <van-grid-item >
            <img class="img_item" src="https://fastly.jsdelivr.net/npm/@vant/assets/apple-1.jpeg" >
            <p>果蔬鲜花</p>
          </van-grid-item>
          <van-grid-item >
            <img class="img_item" src="https://fastly.jsdelivr.net/npm/@vant/assets/apple-1.jpeg" >
            <p>果蔬鲜花</p>
          </van-grid-item>
          <van-grid-item >
            <img class="img_item" src="https://fastly.jsdelivr.net/npm/@vant/assets/apple-1.jpeg" >
            <p>果蔬鲜花</p>
          </van-grid-item>
        </van-grid>

      </van-swipe-item>
      <van-swipe-item>
        <van-grid  :border="true" :column-num="8">
          <van-grid-item >
            <img class="img_item" src="https://fastly.jsdelivr.net/npm/@vant/assets/apple-1.jpeg" >
            <p>果蔬鲜花</p>
          </van-grid-item>
          <van-grid-item>
            <img class="img_item" src="https://fastly.jsdelivr.net/npm/@vant/assets/apple-1.jpeg" >
            <p>果蔬鲜花</p>
          </van-grid-item>
          <van-grid-item >
            <img class="img_item" src="https://fastly.jsdelivr.net/npm/@vant/assets/apple-1.jpeg" >
            <p>果蔬鲜花</p>
          </van-grid-item>
          <van-grid-item >
            <img class="img_item" src="https://fastly.jsdelivr.net/npm/@vant/assets/apple-1.jpeg" >
            <p>果蔬鲜花</p>
          </van-grid-item>
          <van-grid-item >
            <img class="img_item" src="https://fastly.jsdelivr.net/npm/@vant/assets/apple-1.jpeg" >
            <p>果蔬鲜花</p>
          </van-grid-item>
        </van-grid>

      </van-swipe-item>
    </van-swipe>
    </router-link>

  </div>
<!-- 超合算 -->
  <router-link to="/index/DiscountPage">
<div class="bargain_div" >
    <div class="van_row_div1">
      <div class="box_icon">
        <van-icon style=" padding: 0  5px;"  name="fire-o" size="22" color="rgb(255,80,48)" />
        <span>超划算</span>
      </div>
        <div class="box_bot">
        <el-button style="color: rgb(153,153,153)">更多 ></el-button>
      </div>
    </div>
    <div class="van_row_div2">
      <Merchandise></Merchandise>
      <Merchandise></Merchandise>
      <Merchandise></Merchandise>
    </div>
</div>
  </router-link>
<!--  推荐栏-->
<div>

  <van-tabs line-height="10px" color="rgb(0,195,255)" background="rgb(245,245,245)" v-model:active="activeName">
    <van-tab title="推   荐"  name="a">
      <template v-for="(goodsInfo,index) in goodsList">
        <div class="total_box" v-if="index%2===1">
          <router-link
            :to="{ path: '/cart/shoppinggement/'+goodsList[index-1].id }"
          >
            <MerchandiseInfo :goodsInfo="goodsList[index-1]"></MerchandiseInfo>
          </router-link>
          <router-link
            :to="{ path: '/cart/shoppinggement/'+goodsList[index].id  }"
          >
            <MerchandiseInfo :goodsInfo="goodsList[index]"></MerchandiseInfo>
          </router-link>
        </div>
      </template>
    </van-tab>
    <van-tab title="生鲜自营" name="b">

    </van-tab>
    <van-tab title="进口超市" name="c">

    </van-tab>
    <van-tab title="10元 店"  name="d">
    </van-tab>
  </van-tabs>
</div>


</template>
<script>
import MerchandiseInfo from "@/components/Index/MerchandiseIfon.vue";
import Merchandise from "@/components/Index/Merchandise.vue";
import {listShopping} from "@/api/merchant.js"

export default {
  name: "IndexPage",
  components: {
    Merchandise,
    MerchandiseInfo
  },
  data() {
    return {
      gridItems: [],
      goodsList:[],
    };
  },
  created() {
    //获取商品列表
    this.getGoodsList();
  },
  methods:{
    getGoodsList(){
      listShopping().then(res=>{
        this.goodsList=res.data.rows;
      })
    }
  },
  setup() {
    const images = [
      'https://fastly.jsdelivr.net/npm/@vant/assets/apple-1.jpeg',
      'https://fastly.jsdelivr.net/npm/@vant/assets/apple-2.jpeg',
      'https://fastly.jsdelivr.net/npm/@vant/assets/apple-1.jpeg',
      'https://fastly.jsdelivr.net/npm/@vant/assets/apple-2.jpeg',
    ];
    return { images };

  },

}
</script>
<style >

.row_swipe{
  margin: 2% 2%;
  width: 96%;

}
.van_swipe_item_img{
  width: 100%;
  height:150px;
  border-radius: 5px;
}
.Classification{
  height: 100%;
}
.van-grid .van-grid-item p{
  font-size: 10px;
}
.van-grid .van-grid-item{
  margin: 1.2%;

}
.van-grid .van-grid-item .img_item{
  width: 50px;
  height: 50px;
  border-radius: 5px;
}
.bargain_div{
  width: 96%;
  margin: 1% 2%;
  height: 186px;
  background-color: #ffffff;
  border-radius: 5px;
}

.bargain_div .van_row_div1{
  height: 44px;
  width: 100%;
  display: flex;
  justify-content: flex-start;
  align-items: center;

}

.van_row_div1 .box_bot{
  height: 44px;
  width: 50%;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin: 0 3%;
}

.van_row_div1  .box_icon{
  height: 44px;
  width: 50%;
  display: flex;
  justify-content: flex-start;
  align-items: center;

}
.box_icon span{
  font-size: 16px;
  font-weight: bold;
}

.bargain_div .van_row_div2{
  height: 142px;
  display: flex;

}

.total_box{
  width: 100%;
  height: 100%;
  display: flex;
  background-color:rgb(245,245,245) ;
  border-radius: 10px;
}

.total_box a{
  width: 100%;
  height: 100%;
  display: flex;
  background-color:rgb(245,245,245) ;
  border-radius: 10px;
}
</style>

