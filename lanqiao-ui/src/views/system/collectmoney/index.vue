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
            <el-table-column prop="price" label="商品价格" />
            <el-table-column prop="quantity" label="商品数量">
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
          <p>总金额：{{ this.calculateTotalPrice() }}</p>
          <el-button type="success" size="medium" @click="SettleAccount">结账</el-button>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="pay_img">
          <img src="@/assets/images/collectmoney/goods.png" alt="新增商品"  @click="handleAdds" />
          <img src="@/assets/images/collectmoney/addusers.png" alt="新增会员"  @click="handleAdd" />
          <img src="@/assets/images/collectmoney/wechat.png" alt="微信支付" @click="showQRCode('wechat')" />
          <img src="@/assets/images/collectmoney/alipay.png" alt="支付宝支付" @click="Alipay" />
          <img src="@/assets/images/collectmoney/cash.png" alt="现金支付" @click="SettleAccount" />
          <img src="@/assets/images/collectmoney/print.png" alt="打印小票" @click="printReceipt" />
        </div>
      </el-col>
      <el-dialog :visible.sync="showQR" title="扫描二维码支付">
        <div class="code_img_div">
        <img :src="currentQRCode" alt="支付二维码"  class="code_img"/>
        </div>
      </el-dialog>
    </el-row>
    <!-- 添加或修改会员管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="会员姓名" prop="usersName">
          <el-input v-model="form.usersName" placeholder="请输入会员姓名" />
        </el-form-item>
        <el-form-item label="会员性别" prop="usersSex">
          <el-select v-model="form.usersSex" placeholder="请选择会员性别">
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联系方式" prop="usersPhone">
          <el-input v-model="form.usersPhone" placeholder="请输入联系方式" prefix-icon="el-icon-phone-outline" />
        </el-form-item>
        <el-form-item label="会员头像">
          <image-upload  v-model="form.usersAvatar"/>
        </el-form-item>
        <el-form-item label="会员积分" prop="memberTotal">
          <el-input v-model="form.memberTotal" placeholder="请输入会员积分" />
        </el-form-item>
        <el-form-item label="会员级别" prop="memberGrade">
          <el-select v-model="form.memberGrade" placeholder="请选择会员级别">
            <el-option
              v-for="dict in dict.type.f_membership_grade"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 添加或修改商品对话框 -->
    <el-dialog :title="titles" :visible.sync="opens" width="500px" append-to-body>
      <el-form ref="forms" :model="forms" :rules="ruless" label-width="80px">
        <el-form-item label="商品名称" prop="name">
          <el-input v-model="forms.name" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商品价格" prop="price">
          <el-input v-model="forms.price" placeholder="请输入商品价格" />
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-input v-model="forms.unit" placeholder="请输入单位" />
        </el-form-item>
        <el-form-item label="规格" prop="specification">
          <el-input v-model="forms.specification" placeholder="请输入单位" />
        </el-form-item>
        <el-form-item label="编码" prop="coding">
          <el-input v-model="forms.coding" placeholder="请输入编码" />
        </el-form-item>
        <el-form-item label="图片">
          <image-upload v-model="forms.image"/>
        </el-form-item>
        <el-form-item label="描述信息" prop="description">
          <el-input v-model="forms.description" placeholder="请输入描述信息" />
        </el-form-item>
        <el-form-item label="库存" prop="num">
          <el-input v-model="forms.num" placeholder="请输入库存" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="forms.status">
            <el-radio
              v-for="dict in dict.type.f_goods"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="类别" prop="cateId">
          <treeselect v-model="forms.categoryId" :options="cateOptions" :show-count="true" placeholder="请选择类别"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForms">确 定</el-button>
        <el-button @click="cancels">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getGoods, addGoodsList, selectMemberName, addUsers, cateTreeSelect,addGoods } from "@/api/system/collectmoney";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import printJS from "print-js";

export default {
  name: "collectmoney",
  components: {Treeselect},
  dicts: ['f_membership_grade', 'sys_user_sex','f_goods'],
  data() {
    return {
      MerchantName: 'John Doe',
      UnitPrice: 'john@doe.com',
      MerQuantity: '111-111-1111',
      // 新增会员对话框相关数据
      title: '新增会员',
      titles: '新增商品',
      //分类树选项
      cateOptions: undefined,
      open: false,
      opens: false,
      showPassword: false,
      ordersPayMethod: '',//订单支付方式
      CashPayment: '2',
      AlipayPayment: '0',
      barcode: '', //条形码
      productsInCart: [], //购物车
      showQR: false,// 是否显示对话框
      currentQRCode: '', //二维码地址
      //会员显示数据
      member: {
        memberPhone: '', // 会员账号
        memberName: '', // 会员名称
        memberTotal: '', //会员积分
        memberJian: '', //抵扣积分
      },
      // 新增会员表单参数
      form: {
        usersId: '',
        usersName: '',
        usersSex: '',
        usersPhone: '',
        usersAvatar: '',
        memberGrade: '',
        memberTotal: '',
        createTime: ''
      },
      // 会员表单校验
      rules: {
        usersName: [
          { required: true, message: "会员姓名不能为空", trigger: "blur" },
          { pattern: /^[\u4e00-\u9fa5a-zA-Z0-9]{2,10}$/, message: "2-10位中文、英文、英文数字组合", trigger: "blur" }
        ],
        usersPhone: [
          { required: true, message: "联系方式不能为空", trigger: "blur" },
          { pattern: /^((0\d{2,3}-\d{7,8})|(1[34578]\d{9}))$/, message: "电话号码无效", trigger: "blur" }
        ],
        memberTotal: [
          { required: true, message: "会员积分不能为空", trigger: "blur" },
          { pattern: /^(0|[1-9]\d*)$/, message: "请输入非负整数", trigger: "blur" }
        ]
      },
      // 新增商品表单参数
      forms: {
        name: '',
        price: '',
        unit: '',
        specification: '',
        coding: '',
        image: '',
        description: '',
        num: '',
        status: '',
        categoryId: ''
      },
      // 商品表单校验
      ruless: {
        name: [
          { required: true, message: "商品名称不能为空", trigger: "blur" },
          { pattern: /^[\u4e00-\u9fa5]+$/, message: "商品名称必须为中文", trigger: "blur" }
        ],
        categoryId: [
          { required: true, message: "类别不能为空", trigger: "change" }
        ],
        price: [
          { required: true, message: "商品价格不能为空", trigger: "blur" },
          { pattern: /(^[\d]|^[1-9][\d]*)($|[\.][\d]{0,2}$)/, message: '商品价格必须为数字', trigger: 'blur' }
        ],
        image: [
          { required: true, message: "图片不能为空", trigger: "blur" }
        ],
        description: [
          { required: true, message: "描述信息不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "blur" }
        ],
        coding: [
          { required: true, message: "编码不能为空", trigger: "blur" },
          { pattern:/^[1-9]\d{12}$/, message: '编码必须为13位数字', trigger: 'blur' }
        ],
        num: [
          { required: true, message: "库存不能为空", trigger: "blur" },
          { pattern: /^[1-9]\d*$/, message: '库存必须为正整数', trigger: 'blur' }
        ],
      }
    };
  },
  watch: {
    // 根据名称筛选部门树
    catName(val) {
      this.$refs.tree.filter(val);
    },
    'form.memberTotal': function(value) {
      if (value == null || value < 100) {
        this.form.memberGrade = 0;
      } else if (value >= 100) {
        this.form.memberGrade = 1;
      }
    }
  },
  created() {
    this.getCateTree();
  },
  methods: {
    printReceipt() {
        const productsInCart = localStorage.getItem('productsInCart');// 获取购物车数据
        if (productsInCart != null) {
          printJS({
            printable: JSON.parse(productsInCart),
            properties: [
              {field: 'name', displayName: '商品名称'},
              {field: 'quantity', displayName: '商品数量'},
              {field: 'price', displayName: '商品价格'}
            ],
            header: '<h3 class="custom-h3">绿源鲜选超市发票</h3>',
            style:
            `.custom-h3 {
              color: #000;
              text-align: center;
              margin-bottom: 10px;
            }
            table {
              margin: 10px auto;
              border-collapse: collapse;
              width: 100%;
            }
            th, td {
              border: 1px solid #000;
              padding: 8px;
              text-align: center;
            }
            th {
              background-color: #f2f2f2;
            }`,
            documentSize: 'Statement',
            type: 'json'
          });
        }
    },
    showQRCode(paymentMethod) {//传参
      if (this.productsInCart.length !== 0) {//判断购物车长度是否为0
        // 根据不同的支付方式设置相应的二维码路径
        switch(paymentMethod) {//利用switch控制语句进行判断传入的参数值与谁匹配
          case 'wechat':
            this.currentQRCode = require('@/assets/images/collectmoney/weChatCode.png');
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
      // 设置延迟时间为 100 毫秒
      this.timer = setTimeout(() => {
      if (this.barcode !== '') {//判断用户输入商品编码是否为null
        getGoods(this.barcode).then(response => {//发送商品编码查询数据
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
          }
        }).catch(error => {
          this.barcode = ''; // 清空条形码输入
        });
      }
      }, 100); // 设置延迟时间为 100 毫秒
    },
    //会员查询功能
    handleMemberAccountInput() {
      // 在此处处理会员账号输入框输入事件，可以进行实时校验、查询等操作
      clearTimeout(this.timer);// 清除之前的定时器
      // 设置延迟时间为 1000 毫秒
      this.timer = setTimeout(() => {
          if (this.member.memberPhone !== '') {//判断用户输入会员账号是否为null
            selectMemberName(this.member.memberPhone).then(response => {
              if (response.code === 200) {
                this.member.memberName = response.data.usersName;
                this.member.memberTotal = response.data.memberTotal;
              }
            }).catch(error => {
              this.clearMembers();
            });
          } else {
            this.clearMembers();
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
            this.$message.error("抵扣积分超过会员积分");
            this.member.memberJian='';
          }
        }
        }, 1000);
    },
    //生成订单编号
    generateOrderNumber() {
      const PREFIX = '222';
      const LENGTH = 12;
      const usedOrderNumbers = new Set();
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
    //结算功能
    checkout() {
      if (this.productsInCart.length !== 0) {//判断购物车是否为null
        const formData = {
          // 获取计算属性的值
          memberPhone: this.member.memberPhone,
          totalPrice: this.calculateTotalPrice()/10,
          memberJian: this.member.memberJian/10,
          ordersPayMethod: this.ordersPayMethod,
          productsInCart: this.productsInCart
        };
        addGoodsList(formData).then(response => {//发送购物车数据给后端
          // 实现结账逻辑，新增后返回状态码进行判断
          if (response.code === 200) {
            sessionStorage.removeItem('productsInCart');// 删除上次存储的购物车数据
            localStorage.setItem('productsInCart',JSON.stringify(formData.productsInCart));//存储购物车数据
            this.$message.success(response.msg);//结账成功
          } else if (response.code === 500) {
            this.$message.error(response.msg);//系统异常
          } else {
            this.$message.error("网络异常");
          }
        });
        this.clearCartMembers(); // 调用清空购物车+会员方法
      }
    },
    //购物车页面结算功能
    SettleAccount() {
      this.ordersPayMethod = this.CashPayment;
      //购物车结算
      this.checkout();
    },
    //生成支付宝页面+结算功能
    Alipay() {// 前端路径参数格式?subject=xxx&traceNo=xxx&totalAmount=xxx
      if (this.productsInCart.length !== 0) {//判断购物车是否为null
        const subject = "绿源鲜选超市";//超市名称
        const OrderNumber = this.generateOrderNumber();//订单编号
        const totalAmount = this.calculateTotalPrice();//总金额
        this.ordersPayMethod = this.AlipayPayment;//支付方式
        this.checkout();//购物车结算
        clearTimeout(this.timer);// 清除之前的定时器
        // 设置延迟时间为 500 毫秒
        this.timer = setTimeout(() => {
          const path = "http://localhost:8088/api/system/alipay/pay?subject=" + subject + "&traceNo=" + OrderNumber + "&totalAmount=" + totalAmount;
          window.open(path, '_self'); //发送请求给后端支付宝接口,生成支付宝收款页面
        },500);
      }
    },
    //新增会员用户功能
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "新增会员";
    },
    // 新增会员提交表单方法
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
            addUsers(this.form).then(response => {
              this.$modal.msgSuccess("新增会员成功");
              this.open = false;
              this.reset();
            });
        }
      });
    },
    // 新增会员取消按钮
    cancel() {
      this.$refs["form"].resetFields(); // 重置表单验证状态
      this.open = false;
      this.reset();
    },
    // 新增会员表单重置
    reset() {
      this.form = {
        usersId: null,
        usersName: null,
        usersSex: null,
        usersPhone: null,
        usersAvatar: null,
        memberGrade: 0,
        memberTotal: null,
        createTime: null
      };
      this.resetForm("form");
    },
    //新增商品功能
    /** 新增按钮操作 */
    handleAdds() {
      this.resets();
      this.opens = true;
      this.titles = "添加商品";
    },
    /** 查询部门下拉树结构 */
    getCateTree() {
      cateTreeSelect().then(response => {
        this.cateOptions = response.data;
      });
    },
    // 新增商品提交表单方法
    submitForms() {
      this.$refs["forms"].validate(valid => {
        if (valid) {
          addGoods(this.forms).then(response => {
            this.$modal.msgSuccess("新增商品成功");
            this.opens = false;
            this.resets();
          });
        }
      });
    },
    // 商品取消按钮
    cancels() {
      this.$refs["forms"].resetFields(); // 重置表单验证状态
      this.opens = false;
      this.resets();
    },
    // 商品表单重置
    resets() {
      this.forms = {
        name: null,
        price: null,
        unit: null,
        specification: null,
        coding: null,
        image: null,
        description: null,
        status: 0,
        categoryId: null
      };
    },
    //清空购物车+会员
    clearCartMembers() {
      this.productsInCart = []; //重置购物车数据
      this.member= {//重置会员数据
          memberPhone: '', // 会员账号
          memberName: '', // 会员名称
          memberTotal: '', //会员积分
          memberJian: '', //抵扣积分
        };
    },
    //清空会员数据
    clearMembers() {
      this.member= {//重置会员数据
        memberPhone: '', // 会员账号
        memberName: '', // 会员名称
        memberTotal: '', //会员积分
        memberJian: '', //抵扣积分
      };
    }
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
  margin-top: 6rem;
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

.pay_img img {
  cursor: pointer; /* 更改光标样式以指示可点击 */
  transition: opacity 0.3s ease; /* 添加过渡效果 */
  opacity: 1; /* 默认不透明 */
  width: 50px;
  height: 50px;
}

.pay_img img:hover {
  opacity: 0.7; /* 鼠标悬停时降低不透明度 */
}
</style>
