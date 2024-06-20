<template>
    <div>
      <van-sticky>
        <van-nav-bar   title="购物车"></van-nav-bar>
      </van-sticky>

      <div style="margin-bottom: 100px">
        <template  v-for="(goodsInfo,index) in goodsList" >
            <OrderInfo :goodsInfo="goodsInfo"></OrderInfo>
        </template>
      </div>
    </div>
  <van-action-bar  safe-area-inset-bottom style="bottom: 48px">
    <van-action-bar-button color="#be99ff" type="warning" :text="'总金额:'+getTotal()+'￥'" />
    <van-action-bar-button color="#7232dd" type="danger" text="去付款" @click="Retrunshoppings" />
  </van-action-bar>

</template>

<script>

import OrderInfo from "@/components/OrderInfo.vue";
import {getCarList,insertSettlement} from "@/api/merchant";
import {showToast} from "vant";


const PREFIX = '222';
const LENGTH = 12;
const usedOrderNumbers = new Set();
    export default {
        name: "CarPage",
        components: {OrderInfo},
        data(){
          return {
            userInfo:"",
            goodsList:[],

          }
        },

      methods:{

       generateOrderNumber() {
            let orderNumber;
            do {
              let randomNumber = PREFIX;
              for (let i = 2; i < LENGTH; i++) {
                randomNumber += Math.floor(Math.random() * 10); // 生成0到9之间的随机数字
              }
              orderNumber = randomNumber;
            } while (usedOrderNumbers.has(orderNumber)); // 如果订单号已存在，则重新生成

            usedOrderNumbers.add(orderNumber); // 添加到已使用的订单号集合中
            return orderNumber;
          },



        Retrunshoppings(){
            let orderNum = this.generateOrderNumber();
          insertSettlement({ordersNumber:orderNum,usersPhone:this.userInfo.usersPhone}).then(res=>{
            if (res.data.code===200){
              window.open(process.env.VUE_APP_BASE_API+"/api/alipay/pay?subject=绿源生鲜"+"&traceNo="+orderNum+"&totalAmount="+this.getTotal(),'_self')
            }else{
              showToast('订单添加失败');
            }
          });

        },
        getTotal(){
          let count=0;
          this.goodsList.forEach(e=>{
              count+=e.price * e.quantity
          })
          return count;
        }
      },
      created() {
        //取购物车的数据
        const userInfoString = localStorage.getItem("userInfo");
        // 将字符串解析回对象
         this.userInfo = JSON.parse(userInfoString);
        // 现在可以访问对象中的属性了
        getCarList(this.userInfo.usersPhone).then(res =>{
            console.log(res)
            this.goodsList=res.data.data;
        });

      },

    }

</script>

<style scoped>

</style>
