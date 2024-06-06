<template>
  <div class="warp">
    <el-row :gutter="5" align="middle">
      <el-col :span="18">
        <h1>收银台</h1>
        <div class="warp_info">
          <el-row :gutter="3" align="middle">
            <el-col :span="2">
              <p style="height: 40px;line-height: 40px">
                商品条码：
              </p>
            </el-col>
            <el-col :span="4">
              <p style="height: 40px;line-height: 40px">
                <el-input
                  v-model="barcode"
                  placeholder="请扫描商品条形码"
                  @input="handleBarcodeInput"
                  @keyup.enter.native="checkout"
                ></el-input>
              </p>
            </el-col>
            <el-col :span="2">
              <p style="height: 40px;line-height: 40px;">
                会员账号：
              </p>
            </el-col>
            <el-col :span="4">
              <p style="height: 40px;line-height: 40px">
                <el-input
                  v-model="member.memberPhone"
                  placeholder="请输入会员账号"
                  @input="handleMemberAccountInput"
                ></el-input>
              </p>
            </el-col>
            <el-col :span="3">
              <div v-if="member.memberName !== undefined && member.memberName !== null" class="member-info">
                <p style="height: 40px;line-height: 40px">会员名称：{{ member.memberName }}</p>
              </div>
            </el-col>
            <el-col :span="3">
              <div v-if="member.memberTotal !== undefined && member.memberTotal !== null" class="member-info">
                <p style="height: 40px;line-height: 40px">会员积分：{{ member.memberTotal }}</p>
              </div>
            </el-col>
            <el-col :span="2">
              <p style="height: 40px;line-height: 40px">
                抵扣积分：
              </p>
            </el-col>
            <el-col :span="4">
              <p style="height: 40px;line-height: 40px">
                <el-input
                  v-model="member.memberJian"
                  placeholder="请输入抵扣积分"
                  @input="handleMemberTotal"
                ></el-input>
              </p>
            </el-col>
          </el-row>
        </div>
        <div class="warp_info wrap_table" >
          <el-table :data="productsInCart" style="width: 100%" >
            <el-table-column prop="name" label="商品名称" />
            <el-table-column prop="price" label="价格" />
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
          <p>总价：{{ this.calculateTotalPrice() }}</p>
          <el-button type="success" size="medium" @click="checkout">结账</el-button>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="pay_img">
          <img src="@/assets/images/collectmoney/wechat.png" alt="微信支付" @click="showQRCode('wechat')" />
          <img src="@/assets/images/collectmoney/alipay.png" alt="支付宝支付" @click="showQRCode('alipay')" />
          <img src="@/assets/images/collectmoney/cash.png" alt="现金支付" @click="checkout" />
        </div>
      </el-col>
      <el-dialog :visible.sync="showQR" title="扫描二维码支付">
        <div class="code_img_div">
        <img :src="currentQRCode" alt="支付二维码"  class="code_img"/>
        </div>
      </el-dialog>
    </el-row>
  </div>
</template>

<script>
import { getGoodsList, addGoodsList, selectMemberName } from "@/api/system/collectmoney";

export default {

  data() {
    return {
      barcode: '', //条形码
      productsInCart: [], //购物车
      showQR: false,// 是否显示对话框
      currentQRCode: '', //二维码地址
      member: {
        memberPhone: '', // 会员账号
        memberName: '', // 会员名称
        memberTotal: '', //会员积分
        memberJian: '', //抵扣积分
      },
    };
  },
  created() {

  },
  methods: {
    showQRCode(paymentMethod) {//传参
      if (this.productsInCart.length !== 0) {//判断购物车长度是否为0
        // 根据不同的支付方式设置相应的二维码路径
        switch(paymentMethod) {//利用switch控制语句进行判断传入的参数值与谁匹配
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
      }
    },
    removeFromCart(index){
      this.productsInCart.splice(index,1);
    },
    handleIncrease(index) {
      this.productsInCart[index].quantity++;
      this.calculateTotalPrice();
    },
    //计算总金额
    calculateTotalPrice() {
      const total = this.productsInCart.reduce((sum, item) => sum + item.price * item.quantity, 0);
      return total.toFixed(2); // 保留两位小数
    },
    handleDecrease(index) {
      if (this.productsInCart[index].quantity > 1) {
        this.productsInCart[index].quantity--;
        this.calculateTotalPrice();
      }
    },
    //购物车功能
    handleBarcodeInput() {
      clearTimeout(this.timer);// 清除之前的定时器
      // 设置延迟时间为 500 毫秒
      this.timer = setTimeout(() => {
      if (this.barcode !== '') {//判断用户输入订单编号是否为null
        getGoodsList(this.barcode).then(response => {//发送订单编号查询数据
          var product = response.GoodsList;
          //判断商品数据是否查询为null
          if (product) {
            const existingProduct = this.productsInCart.find(item => item.id === product.coding);
            if (existingProduct) {
              //商品已加入购物车
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
            this.$message.error('未找到该商品');
          }
        });
      }
      }, 500); // 设置延迟时间为 500 毫秒
    },

    //会员功能
    handleMemberAccountInput() {
      // 在此处处理会员账号输入框输入事件，可以进行实时校验、查询等操作
      clearTimeout(this.timer);// 清除之前的定时器
      // 设置延迟时间为 500 毫秒
      this.timer = setTimeout(() => {
          if (this.member.memberPhone !== '') {//判断用户输入会员账号是否为null
            selectMemberName(this.member.memberPhone).then(response => {
              console.log(response);
              if (response.code === 200) {
                this.member.memberName = response.data.usersName;
                this.member.memberTotal = response.data.memberTotal;
              } else {
                this.$message.success("网络异常");
              }
            });
          } else {
            this.member.memberName = '';
            this.member.memberTotal= '';
            this.member.memberJian= '';
          }
      }, 1000);
    },

    //抵扣积分判断功能
    handleMemberTotal() {
      // 在此处处理会员账号输入框输入事件，可以进行实时校验、查询等操作
      clearTimeout(this.timer);// 清除之前的定时器
      // 设置延迟时间为 500 毫秒
      this.timer = setTimeout(() => {
        if (this.member.memberJian !== '') {
          if (this.member.memberJian > this.member.memberTotal && this.member.memberTotal !=='') {
            this.$message.error("抵扣积分不能大于会员积分");
          }
        }
        }, 1000);
    },

    //结算功能
    checkout() {
      if (this.productsInCart.length !== 0) {//判断购物车是否为null
        const formData = {
          // 获取计算属性的值
          memberPhone: this.member.memberPhone,
          totalPrice: this.calculateTotalPrice()/10,
          memberJian: this.member.memberJian/10,
          productsInCart: this.productsInCart
        };
        addGoodsList(formData).then(response => {//发送购物车数据给后端
          // 实现结账逻辑，新增后返回状态码进行判断
          if (response.code === 200) {
            this.$message.success(response.msg);//结账成功
          } else if (response.code === 500) {
            this.$message.error(response.msg);//系统异常
          } else {
            this.$message.error("网络异常");
          }
        });
        this.clearCart(); // 调用清空购物车+会员方法
      }
    },
    //清空购物车+会员
    clearCart() {
      this.productsInCart = []; //重置购物车数据
      this.member= {}; //重置会员数据
    },
  },
  computed: {

  }
};
</script>

<style scoped>
h1 {
  font-family: '黑体', '微软雅黑', sans-serif; /* 使用黑体或微软雅黑字体，如果找不到则使用无衬线字体 */
  font-size: 55px; /* 设置字体大小 */
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
  width: 100%;
  height: 100%;
  margin: 0 9rem;
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
