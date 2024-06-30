
<template>
  <div style="width: 100%;height: 100vh">
    <van-nav-bar
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    >
    </van-nav-bar>
    <div class="custom-cell">
      <router-link style="width: 600px;" to="/mine/address">
        <van-cell class="payinfo" style="margin-bottom: 5px; border-radius: 5px;" is-link size="large">
          <template v-if="addressList && addressList.length > 0">
            <div style="display: flex;justify-content: flex-start;">
              <div class="address-content" style="margin-right: 10px">{{ addressList[0]?.addressName }}</div>
              <div class="address-content">{{ addressList[0]?.addressPhone }}</div>
            </div>
            <div class="address-content">{{ addressList[0]?.addressDetail }}</div>
          </template>
          <template v-else>
            <div class="address-content">请新增地址</div>
          </template>
        </van-cell>
      </router-link>
    </div>
    <template  v-for="(goodsInfo,index) in goodsList" >
      {{goodsList}}
      <OrderInfo :goodsInfo="goodsInfo">

      </OrderInfo>
    </template>
    <div style="padding-left: -5px;padding-right: -5px;">
      <van-cell title="单元格" class="payinfo" icon="alipay" color="blue" style="margin-top: 5px;border-radius: 5px;"  is-link>
        <template #title>
          <span class="custom-title">支付宝</span>
          <van-tag type="primary">推荐</van-tag>
        </template>
      </van-cell>
    </div>



    <van-submit-bar :price="formattedTotal()*100" button-text="提交订单" @submit="onSubmit" />
  </div>

</template>


<script>

import OrderInfo from "@/components/OrderInfo.vue";
import {showToast} from "vant";
import {addressList, insertSettlement, selectSho} from "@/api/merchant.js";

const PREFIX = '222';
const LENGTH = 12;
const usedOrderNumbers = new Set();
const onClickLeft = () => history.back();
export default {
  name:"PayInfoList",
  components: {OrderInfo},
  data(){
    return{
      onClickLeft,
      addressList:[],
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
    onSubmit(){
      //取购物车的数据
      const userInfoString = localStorage.getItem("userInfo");
      // 将字符串解析回对象
      this.userInfo = JSON.parse(userInfoString);
      let orderNum = this.generateOrderNumber();
      insertSettlement({usersPhone:this.userInfo.usersPhone,ordersPayMethod:0,ordersPayStatuds:0,coDings:[this.$route.params.coding]}).then(res=>{
        console.log(res.data)
        if (res.data.code===200){
          window.open(process.env.VUE_APP_BASE_API+"/api/alipay/pay?subject=绿源生鲜"+"&traceNo="+orderNum+"&totalAmount="+this.getTotal(),'_self')
        }else{
          showToast('订单添加失败');
        }
      });
    },
    //总金额
    getTotal() {
      let count = 0;
      // 检查 this.goodsList 是否定义且是数组
      if (this.goodsList && Array.isArray(this.goodsList)) {
        this.goodsList.forEach(e => {
          count += e.price * e.quantity;
        });
      } else {
        console.error('"查无购物车数据"');
      }
      return count.toFixed(2);
    },
    formattedTotal() {
      // 在这里可以将字符串转换为数字
      return parseFloat(this.getTotal()); // 正确示例，转换为数字
    },
    //获取地址列表
    getAddressList() {
      const userInfoString = localStorage.getItem("userInfo");
      // 将字符串解析回对象
      const userInfo = JSON.parse(userInfoString);
      // 现在可以访问对象中的属性了
      this.usersPhone = userInfo.usersPhone;
      //获取地址列表
      addressList(this.usersPhone).then(res => {
        this.addressList = res.data.data;
      })
    },

  },
  created() {
    //取购物车的数据
    const userInfoString = localStorage.getItem("userInfo");
    // 将字符串解析回对象
    this.userInfo = JSON.parse(userInfoString);

    // 现在可以访问对象中的属性了
    //获取结算订单信息
    selectSho(this.userInfo.usersPhone,this.$route.params.coding).then(res =>{
      this.goodsList=res.data.data;
    });
    this.getAddressList();

  },
}

</script>


<style scoped>
.custom-cell {
  margin-top: 10px;
  display: flex;
  align-items: center; /* 垂直居中 */
}

.left-label {
  /* 左侧标签的样式，可以根据需要调整 */
  flex: 0 0 auto; /* 不伸缩 */
  margin-right: 10px; /* 右侧间距 */
  font-weight: bold; /* 可选：加粗 */
}

.address-content {
  /* 地址内容的样式，可以根据需要调整 */
  display: flex;
}

.payinfo {
  /* 如果需要，可以覆盖 van-cell 的默认样式 */

}

</style>
