<template>
  <div class="order">
    <router-link to="/cart/shoppinggement">
    <div class="title">
      <span>{{ userInfo.name }}</span>
      <span style="color: #FFB366">未支付</span>
    </div>
    <div class="content">
      <div class="img_info">
        <img :src="this.baseUrl+this.userInfo.image" alt="">
      </div>
      <div class="content_txt">
        <div style="height: 40px">
          <h4 style="font-size: 12px">{{ userInfo.description }}</h4>
        </div>
        <p>规格：一个</p>
        <p>单价：￥{{ userInfo.specification }}</p>
        <p>数量：{{userInfo.quantity}}个</p>
      </div>
    </div>
    </router-link>
      <p class="totals">金额:{{ userInfo.price }}元</p>
    <div class="bottom_btn">
      <div>
        <van-button plain hairline round  type="primary" size="mini" @add="addNum" >再次购买</van-button>
        <router-link to="/mine/ordermanagement">
          <van-button plain hairline round  type="danger" size="mini">评价</van-button>
        </router-link>
      </div>
    </div>
  </div>
</template>
<script >

import {getCarList, listShopping, listShoppings} from "@/api/merchant";

export default {
  name: "OrderInfo",
  data(){
    return{
      merchant:"",
      baseUrl:"",
      carList:[],
      userInfo:"",
      imgUrls:""
    }
  },
  methods:{
    //接口调用方法
    // getOrederInfo(){
    //   listShoppings().then(res=>{
    //     console.log(res)
    //   })
    // },
    addGoodsToCar(goods){
      let addNum=0;
      //加入的商品如果已经在购物列表中存在 则商品数量加一
      for(var i=0;i<this.carList.length;i++){
        if(goods.id===this.carList[i].id){
          this.carList[i].num++;
          addNum=1;
          break;
        }
      }
      if(addNum===0){
        //将子组件中传递出来的商品 添加到购物车列表
        goods.num=1;
        //接口书记
        this.carList.push(goods)
      }
    },
    addNum(goods){
      this.addGoodsToCar(goods);
    },
  },
  created() {
    this.baseUrl=process.env.VUE_APP_BASE_API;
    this.imgUrls = this.userInfo.image;
    console.log(this.imgUrls);
    // this.getOrederInfo();
    //取购物车的数据
    const userInfoString = localStorage.getItem("userInfo");
    // 将字符串解析回对象
    const userInfo = JSON.parse(userInfoString);

    // 现在可以访问对象中的属性了
    this.usersPhone = userInfo.usersPhone;
    getCarList(this.usersPhone).then(res =>{
      this.userInfo=res.data.data[0];
      console.log(res.data.data)
    })
  },
  // computed:{
  //   total(){
  //     //计算购物车的总结
  //     let total=0;
  //     for(var i=0;i<this.carList.length;i++){
  //       total+= this.carList[i].num*this.carList[i].price;
  //     }
  //     return total;
  //   }
  // }
}
</script>
<style scoped>
.order{
  height: 230px;
  background-color: #ffffff;
  margin: 1%;
  border-radius: 3%;
}
.title{
  height: 40px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-radius: 3%;
}
.title span{
  margin: 5%;
  font-size: 12px;
}
.content{
  height: 110px;
  display: flex;
  justify-content: left;
  align-items: center;

}
.content_txt{
  height: 100px;
  text-align: left;
  margin: 0 1%;
}
.content_txt p{
  font-size: 12px;
  color: #969696;
}
.img_info{
  width: 100px;
  height: 100px;
  margin: 0 2%;
}
.img_info img{
  width: 100px;
  height: 100px;
  border-radius: 3%;
}
.totals{
  text-align: right;
  margin-right: 6%;
}
.bottom_btn{
  text-align: right;
  margin: 2% 5%;
  display: flex;
  justify-content: flex-end;
}
</style>
