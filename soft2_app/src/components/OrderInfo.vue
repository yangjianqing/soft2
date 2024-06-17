<template>
  <div class="order">
    <router-link to="/cart/shoppinggement">
    <div class="title">
      <span>盒马生鲜</span>
      <span style="color: #FFB366">未支付</span>
    </div>
    <div class="content">

      <div class="img_info">
        <img src="@/assets/img/OIP-C.jpg" alt="">
      </div>

      <div class="content_txt">
        <div style="height: 40px">
          <h4 style="font-size: 12px">盒马购买成功南汇8424西瓜单果3kg-4kg</h4>
        </div>
        <p>规格：一个</p>
        <p>单价：￥29.90/个</p>
        <p>数量：1个</p>
      </div>
    </div>
    </router-link>
    <p class="totals">金额:334元</p>
    <div class="bottom_btn">
      <div>
        <van-button plain hairline round  type="primary" size="mini" @add="addNum" >再次购买</van-button>
        <van-button plain hairline round  type="danger" size="mini">评价</van-button>
      </div>
    </div>
  </div>
</template>
<script >

import {listShopping, listShoppings} from "@/api/merchant";

export default {
  name: "OrderInfo",
  data(){
    return{
      merchant:"",
      baseUrl:"",
      carList:[]
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
    // this.getOrederInfo();
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
