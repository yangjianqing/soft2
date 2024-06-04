<template>
  <div class="warp">
    <el-row :gutter="5" align="middle">
      <el-col :span="18">
        <h1>收银台</h1>
        <div class="warp_info">
          <el-row :gutter="5" align="middle">
            <el-col :span="4">
              <p style="height: 40px;line-height: 40px">
                商品条码：
              </p>
            </el-col>
            <el-col :span="18">
              <p style="height: 40px;line-height: 40px">
                <el-input
                  v-model="barcode"
                  placeholder="请扫描商品条形码"
                  @input="handleBarcodeInput"
                  @keyup.enter.native="checkout"
                ></el-input>
              </p>
            </el-col>
          </el-row>
        </div>
        <div class="warp_info wrap_table" >
          <el-table :data="productsInCart" style="width: 100%"  >
            <el-table-column prop="name" label="商品名称"></el-table-column>
            <el-table-column prop="price" label="价格"></el-table-column>
            <el-table-column prop="quantity" label="数量">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  icon="el-icon-minus"
                  type="primary"
                  circle
                  @click="handleDecrease(scope.$index)"
                  :disabled="scope.row.quantity === 1"
                ></el-button>
                <span>&nbsp;&nbsp;{{ scope.row.quantity }}&nbsp;&nbsp;</span>
                <el-button
                  size="mini"
                  type="primary"
                  icon="el-icon-plus"
                  circle
                  @click="handleIncrease(scope.$index)"
                ></el-button>
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button icon="el-icon-delete" type="danger" circle @click="removeFromCart(scope.$index)"></el-button>
              </template>
            </el-table-column>
          </el-table>
        </div >
        <div class="warp_info warp_bottom">
          <p>总价：{{ calculateTotalPrice }}</p>
          <el-button type="success" size="medium" @click="checkout">结账</el-button>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="pay_img">
          <img src="@/assets/images/collectmoney/wechat.png" alt="微信支付" @click="showQRCode('wechat')" />
          <img src="@/assets/images/collectmoney/alipay.png" alt="支付宝支付" @click="showQRCode('alipay')" />
          <img src="@/assets/images/collectmoney/cash.png" alt="现金支付" />
        </div>
      </el-col>
      <el-dialog :visible.sync="showQR" title="扫描二维码支付">
        <div class="code_img_div">
        <img :src="currentQRCode" alt="支付二维码" class="code_img"/>
        </div>
      </el-dialog>
    </el-row>
  </div>
</template>

<script>
import { getGoodsList, addGoodsList } from "@/api/system/collectmoney";

export default {

  data() {
    return {
      //条码
      barcode: '',
      productsInCart: [],
      showQR: false,
      currentQRCode: ''
    };
  },
  created() {

  },
  methods: {
    showQRCode(paymentMethod) {
      // 根据不同的支付方式设置相应的二维码路径
      switch(paymentMethod) {
        case 'wechat':
          this.currentQRCode = require('@/assets/images/collectmoney/weChatCode.jpg');
          break;
        case 'alipay':
          this.currentQRCode = require('@/assets/images/collectmoney/alipayCode.jpg');
          break;
        default:
          this.currentQRCode = '';
      }
      this.showQR = true; // 显示对话框
    },
    removeFromCart(index){
      this.productsInCart.splice(index,1);
    },
    handleIncrease(index) {
      this.productsInCart[index].quantity++;
      this.calculateTotalPrice();
    },
    handleDecrease(index) {
      if (this.productsInCart[index].quantity > 1) {
        this.productsInCart[index].quantity--;
        this.calculateTotalPrice();
      }
    },
    handleBarcodeInput() {
      getGoodsList(this.barcode).then(response => {
        var product = response.GoodsList;
        if (product) {
          //商品已加入购物车
          const existingProduct = this.productsInCart.find(item => item.id === product.coding);
          if (existingProduct) {
            existingProduct.quantity++;
          } else {
            //商品未加入购物车
            this.productsInCart.push({
              id:product.coding,
              name: product.name,
              price:product.price,
              quantity: 1
            });
          }
          this.barcode = ''; // 清空条形码输入
        } else {
          this.$message.error(response.msg);
        }
      });
    },
    checkout() {
      // 实现结账逻辑，比如调用后端API进行支付
      this.$message.success('结账成功');
      addGoodsList(this.productsInCart);
      this.clearCart(); // 调用清空购物车方法
    },
    clearCart() {
      this.productsInCart = [];
    }
  },
  computed:{
    calculateTotalPrice(){
      return  this.productsInCart.reduce((sum, item) => sum + item.price * item.quantity, 0);
    }
  },
};
</script>

<style scoped>
h1 {
  font-family: '黑体', '微软雅黑', sans-serif; /* 使用黑体或微软雅黑字体，如果找不到则使用无衬线字体 */
  font-size: 48px; /* 设置字体大小 */
  color: #0099ff; /* 设置字体颜色为红色 */
  text-align: center; /* 文本居中显示 */
  text-shadow: 2px 2px 4px rgba(173, 115, 53, 0.5); /* 设置文本阴影 */
  margin-bottom: 20px; /* 设置标题下方的外边距 */
}

/* 样式可以根据需要进行自定义 */
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}

.warp{
  width: 80%;
  height: 600px;
  margin: 0 16rem;
}

.warp_info{
  margin: 10px 15px;
  background: #f1efef;
  border-radius: 15px;
  padding: 0 15px;
}

.wrap_table{
  padding: 15px 15px;

}

.warp_bottom{
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.pay_img {
  margin-top: 12rem;
  width: 64px;
  height: 64px;
}

.code_img {
  width: 500px;
  height: 500px;
  margin: 0 auto;
}

.code_img_div {
  width: 500px;
  height: 600px;
  margin: 0 auto;
}
</style>
