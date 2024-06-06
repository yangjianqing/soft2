<template>
  <!--  头部外边框-->
  <div class="head_warp">
    <!--  图标区域-->
    <div class="icon_warp">
      <div class="icon">
        <!--      //返回上一级路由 this.$router.go(-1)-->
        <van-icon name="arrow-left" v-on:click="this.$router.go(-1)" size="25"/>
      </div>
      <div class="icon">
        <van-icon style="margin: 0 20px"  name="search" size="25"/>
        <van-icon v-if="!isChoose" v-on:click="this.isChoose=!this.isChoose" name="like-o" size="25"/>
        <van-icon v-if="isChoose"  v-on:click="this.isChoose=!this.isChoose" style="color: red" name="like" size="25"/>
      </div>
    </div>
    <div class="mer_warp">
      <div class="top_wrap">

        <div class="left_info">
          <h3>{{merchant.name}}</h3>
          <p>
            <span style="font-size: 16px;font-weight: 800;color: #ee9900">3.5分</span>
            <span>起送{{merchant.minimumCharge}}</span>
            <span>约30分钟</span>
            <span>月售60</span>
          </p>
        </div>
        <div class="right_img">
          <img :src="baseUrl+merchant.headUrl" alt="">
        </div>
      </div>
      <div class="bottom_warp">
        公告： {{merchant.disc}}
      </div>
    </div>
  </div>

</template>

<script>
// 商家详情页
/**
 * 进入当前页面以后  根据商家Id再加载商家信息
 * 1、获取到商家id
 * 2、根据商家ID获取到商家的数据
 * 3、将数据显示到页面
 */
import {listShopping} from "@/api/merchant"
export default {
  name: "MerchantProduct",
  data(){
    return{
      merchant:"",
      isChoose:true,
      baseUrl:"",
    }
  },
  created() {
    this.baseUrl=process.env.VUE_APP_BASE_API

    // 1、获取到商家id
    this.getMerInfo();
  },
  methods:{
    getMerInfo(){
      listShopping(this.$route.params.merId).then(res=>{
        this.merchant=res.data.rows;
      })
    }
  }
}
</script>

<style scoped>
.head_warp{
  width: 100%;
  height: 240px;
  background-image: url("../assets/img/merchant.jpg");
  background-size: 100% 60%;
  background-repeat: no-repeat;
}
.icon_warp{
  padding-top: 10px;
  height: 80px;
  width: 100%;
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
}
.icon{
  width: 45%;
  display: flex;
}
.mer_warp{
  width: 94%;
  background: white;
  height: 140px;
  border-radius: 15px;
  margin: 0 3%;
  padding-top: 5px;
}
.top_wrap{
  height: 70px;
  width: 96%;
  margin: 5px 2%;
  display: flex;
  justify-content: space-between;
  /*background: red;*/
}
.bottom_warp{
  height: 60px;
  width: 96%;
  margin: 5px 2%;
  /*background: red;*/
  text-align: left;
  white-space: nowrap; /* 文本不换行 */
  overflow: hidden; /* 超出部分隐藏 */
  text-overflow: ellipsis; /* 使用省略号代替 */
  font-size: 12px;
  color: #848484;
}
.left_info{
  width: 75%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: flex-start;

}
.right_img{
  width: 25%;
  height:60px;
  /*background: red;*/
}
.right_img img{
  width: 75%;
  height:65px;

  border-radius: 15px;
}
.left_info p{
  font-size: 12px;
  color: #848484;
}
.left_info p span{
  margin: 0 5px;
}
</style>
