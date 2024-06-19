

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
            {{ address || '获取位置中...' }}
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
    <van-swipe style="border-radius: 10px" :autoplay="3000" lazy-render>
      <van-swipe-item v-for="Advertising in selectFadvertisement.data" >
        <router-link :to="'/cart/shoppinggement/' +Advertising.goodsId ">
          <img class="van_swipe_item_img" :src="this.baseUrl + Advertising.fadvertisementImg" />
        </router-link>
      </van-swipe-item>
    </van-swipe>
  </div>
<!-- 分类导航栏-->
  <div class="Classification">
    <van-swipe :show-indicators="false">
      <van-swipe-item>
        <van-grid  :border="true" :column-num="8">
          <template v-for="(category, index) in categoryInFo">
            <router-link style="margin: 2px 1vw" to="/classification" v-if="index%2==0">
              <van-grid-item >
                <img class="img_item" :src="baseUrl+category.picture" >
                <p style="font-size: 12px;padding-top: 6px">{{ category.deptName }}</p>
              </van-grid-item>
            </router-link>
          </template>
        </van-grid>
      </van-swipe-item>
      <van-swipe-item>
        <van-grid :border="true" :column-num="8" style="justify-content: space-around;">
          <template v-for="(category, index) in categoryInFo">
            <router-link style="margin: 2px 1vw" to="/classification" v-if="index%2!=0">
              <van-grid-item >
                <img class="img_item" :src="baseUrl+category.picture" >
                <p style="font-size: 12px;padding-top: 6px">{{ category.deptName }}</p>
              </van-grid-item>
            </router-link>
          </template>
        </van-grid>
      </van-swipe-item>
    </van-swipe>


  </div>
<!-- 超合算 -->
<div class="bargain_div" >
  <router-link to="/index/DiscountPage">
    <div class="van_row_div1">
      <div class="box_icon">
        <van-icon style=" padding: 0  5px;"  name="fire-o" size="18" color="rgb(255,80,48)" />
        <span>超划算</span>
      </div>
        <div class="box_bot">
        <el-button style="color: rgb(153,153,153)">更多 ></el-button>
      </div>
    </div>
  </router-link>

    <div class="van_row_div2">
      <template v-for="(favorable, index) in favorableList.slice(0,3)" :key="index">
        <Merchandise :favorable="favorable" ></Merchandise>
      </template>
    </div>
</div>
<!--  推荐栏-->
<div style="margin-bottom: 50px;">

  <van-tabs line-height="10px" color="rgb(0,195,255)" animated background="rgb(245,245,245)" v-model:active="activeName">
    <van-tab title="推   荐"  name="a">
      <template v-for="(goodsInfo,index) in goodsList1">
        <div class="total_box" v-if="index%2===1">
          <router-link
            :to="{ path: '/cart/shoppinggement/'+goodsList1[index-1].id }"
          >
            <MerchandiseInfo :goodsInfo="goodsList1[index-1]"></MerchandiseInfo>
          </router-link>
          <router-link
            :to="{ path: '/cart/shoppinggement/'+goodsList1[index].id  }"
          >
            <MerchandiseInfo :goodsInfo="goodsList1[index]"></MerchandiseInfo>
          </router-link>
        </div>
      </template>
    </van-tab>
    <van-tab title="生鲜自营" name="b">
      <template v-for="(goodsInfo,index) in goodsList2">
        <div class="total_box" v-if="index%2===1">
          <router-link
            :to="{ path: '/cart/shoppinggement/'+goodsList2[index-1].id }"
          >
            <MerchandiseInfo :goodsInfo="goodsList2[index-1]"></MerchandiseInfo>
          </router-link>
          <router-link
            :to="{ path: '/cart/shoppinggement/'+goodsList2[index].id  }"
          >
            <MerchandiseInfo :goodsInfo="goodsList2[index]"></MerchandiseInfo>
          </router-link>
        </div>
      </template>

    </van-tab>
    <van-tab title="日常用品" name="c">
      <template v-for="(goodsInfo,index) in goodsList3">
        <div class="total_box" v-if="index%2===1">
          <router-link
            :to="{ path: '/cart/shoppinggement/'+goodsList3[index-1].id }"
          >
            <MerchandiseInfo :goodsInfo="goodsList3[index-1]"></MerchandiseInfo>
          </router-link>
          <router-link
            :to="{ path: '/cart/shoppinggement/'+goodsList3[index].id  }"
          >
            <MerchandiseInfo :goodsInfo="goodsList3[index]"></MerchandiseInfo>
          </router-link>
        </div>
      </template>

    </van-tab>
    <van-tab title="10元 店"  name="d">
      <template v-for="(goodsInfo,index) in goodsList4">
        <div class="total_box" v-if="index%2===1">
          <router-link
            :to="{ path: '/cart/shoppinggement/'+goodsList4[index-1].id }"
          >
            <MerchandiseInfo :goodsInfo="goodsList4[index-1]"></MerchandiseInfo>
          </router-link>
          <router-link
            :to="{ path: '/cart/shoppinggement/'+goodsList4[index].id  }"
          >
            <MerchandiseInfo :goodsInfo="goodsList4[index]"></MerchandiseInfo>
          </router-link>
        </div>
      </template>
    </van-tab>
  </van-tabs>
</div>


</template>
<script>
import MerchandiseInfo from "@/components/Index/MerchandiseIfon.vue";
import Merchandise from "@/components/Index/Merchandise.vue";
import {recommendedList, selectFadvertisementList, selectPicture} from "@/api/merchant.js"
import {selectFGoodsByFavorable} from "@/api/merchant.js"
const axios = require('axios'); // 确保您已经安装了axios库

export default {
  name: "IndexPage",
  components: {
    Merchandise,
    MerchandiseInfo
  },
  data() {
    return {
      activeName: 'a',
      gridItems: [],
      favorableList:[],
      goodsList1:[],
      goodsList2:[],
      goodsList3:[],
      goodsList4:[],
      categoryInFo: [],
      baseUrl: '' ,// 初始化 baseUrl
      selectFadvertisement:[],
      address: '', // 确保定义了address
      location: [], // 确保定义了location，最好是一个对象
    };
  },
  created() {
    //获取超盒算列表
      this.getFavorableList();
      this.queryCategory();
      this.baseUrl=process.env.VUE_APP_BASE_API;
      this.onTabChange();
      this.selectRotationalList();
    // 调用获取位置和地址的函数
      this.getLocationAndAddress();
  },
  methods:{
    async getLocationAndAddress() {
      try {
        const location = await this.getLocation();
        if (location) {
          await this.getAddress(location.latitude, location.longitude);
        } else {
          console.error('无法获取位置信息');
        }
      } catch (error) {
        console.error('获取位置或地址时出错:', error);
      }
    },
    //获取浏览器所在经纬度
    getLocation() {
      return new Promise((resolve, reject) => {
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(
            (position) => {
              const location = {
                latitude: position.coords.latitude,
                longitude: position.coords.longitude,
              };
              console.log('Latitude:', position.coords.latitude);
              console.log('Longitude:', position.coords.longitude);
              resolve(location); // 返回位置信息
            },
            (error) => {
              console.error('Error getting location:', error);
              reject(error);
            }
          );
        } else {
          console.error('该浏览器不支持 Geolocation');
          reject(new Error('Geolocation not supported'));
        }
      });
    },
    async getAddress(latitude, longitude) {
      try {
        const url = `https://api.map.baidu.com/reverse_geocoding/v3/?ak=QHdL52NTXrQT5iABEjB9htutCDt4l8dC&location=${latitude},${longitude}&output=json&coordtype=wgs84`;
        // 注意：将YOUR_APP_KEY替换为您自己的App Key
        // location参数是纬度和经度的组合，格式为"纬度,经度"
        // output参数指定返回的数据格式，这里使用json
        // coordtype参数指定输入坐标的类型，这里使用wgs84（GPS坐标）
        const response = await axios.get(url);
        const data = response.data;
        // 处理返回的数据...
        console.log(data);

      } catch (error) {
        console.error("请求失败:", error);
      }
    },



    //根据tab栏状态判断
    onTabChange() {
      // 调用方法获取对应的商品数据
      this.queryRecommendation(1);
      this.queryRecommendation(2);
      this.queryRecommendation(3);
      this.queryRecommendation(4);
    },
    //查询tab栏对应的商品信息
    queryRecommendation(sortNum){
      recommendedList(sortNum).then(res =>{
        // 这里可以处理获取到的推荐商品数据
        // 更新 goodsList 为新的数据
        switch (sortNum) {
          case 1:
            this.goodsList1 = res.data.rows;
            break;
          case 2:
            this.goodsList2 = res.data.rows;
            break;
          case 3:
            this.goodsList3 = res.data.rows;
            break;
          case 4:
            this.goodsList4 = res.data.rows;
            break;
        }
      })
    },
    //查询分类导航栏及其图片
    queryCategory(){
      selectPicture().then(res =>{
        this.categoryInFo = res.data.rows;
      })
    },
    getFavorableList(){
      selectFGoodsByFavorable().then(res=>{
        this.favorableList=res.data.rows;
      })
    },
    //首页轮播图显示
    selectRotationalList(){
      selectFadvertisementList().then(res=>{
        this.selectFadvertisement  = res.data;
      })

    },
  },
  mounted() {
    // 初始加载默认的tab数据
    this.queryRecommendation(1);
    this.getLocation();
  },

}
</script>
<style >
.van-grid {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}
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

