<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="供应商" prop="supplier">
        <el-input
          v-model="queryParams.supplier"
          placeholder="请输入供应商"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="付款方式" prop="paymentTerms">
        <el-select v-model="queryParams.paymentTerms" placeholder="请选择付款方式" clearable>
          <el-option
            v-for="dict in dict.type.f_sales_way"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.f_sales_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="订单日期" prop="orderDate">
        <el-date-picker clearable
                        v-model="queryParams.orderDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择订单日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:orders:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:orders:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:orders:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ordersList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="采购ID" align="center" prop="orderId" />
      <el-table-column label="商品名称" align="center" prop="goodsName"  class-name="goods-shippingAddress":show-overflow-tooltip="true">
        <template slot-scope="scope">
          <span :title="scope.row.goodsName">
            {{scope.row.goodsName}}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="供应商" align="center" prop="supplier" />
      <el-table-column label="数量" align="center" prop="quantity" />
      <el-table-column label="单价" align="center" prop="unitPrice" />
      <el-table-column label="总金额" align="center" >
        <template slot-scope="scope">
          <span>{{ scope.row.quantity*scope.row.unitPrice}}</span>
        </template>
      </el-table-column>
      <el-table-column label="付款方式" align="center" prop="paymentTerms">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.f_sales_way" :value="scope.row.paymentTerms"/>
        </template>
      </el-table-column>
      <el-table-column label="发货地" align="center" prop="shippingAddress" class-name="goods-shippingAddress":show-overflow-tooltip="true" >
        <template slot-scope="scope">
          <span :title="scope.row.shippingAddress">
            {{scope.row.shippingAddress}}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.f_sales_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="订单日期" align="center" prop="orderDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="到货日期" align="center" prop="requiredDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.requiredDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="orderComments" class-name="goods-orderComments":show-overflow-tooltip="true" >
        <template slot-scope="scope">
          <span :title="scope.row.orderComments">
            {{scope.row.orderComments}}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:orders:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:orders:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改采购信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品id" prop="goodsId">
          <el-input disabled v-model="form.goodsId" placeholder="默认值" />
        </el-form-item>
        <el-form-item label="商品名称">
          <el-autocomplete
            class="inline-input"
            v-model="state"
            :fetch-suggestions="querySearch"
            placeholder="请输入内容"
            :trigger-on-focus="false"
            @select="handleSelect"
          >
            <template slot-scope="{ item }">
              <div class="name">{{ item.name }}</div>
<!--              <span class="addr">{{ item.id }}</span>-->
            </template>
          </el-autocomplete>

        </el-form-item>
        <el-form-item label="供应商" prop="supplier">
          <el-input v-model="form.supplier" placeholder="请输入供应商" />
        </el-form-item>
        <el-form-item label="商品数量" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入商品数量" />
        </el-form-item>
        <el-form-item label="单价" prop="unitPrice">
          <el-input v-model="form.unitPrice" placeholder="请输入单价" />
        </el-form-item>
        <el-form-item label="总金额" prop="totalAmount">
          <el-input v-model="calculatedTotalAmount" placeholder="请输入总金额"  />
        </el-form-item>
        <el-form-item label="付款方式" prop="paymentTerms">
          <el-select v-model="form.paymentTerms" placeholder="请选择付款方式">
            <el-option
              v-for="dict in dict.type.f_sales_way"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="发货地" prop="shippingAddress">
          <el-input v-model="form.shippingAddress" placeholder="请输入发货地" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态">
            <el-option
              v-for="dict in dict.type.f_sales_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="订单日期" prop="orderDate">
          <el-date-picker clearable
                          v-model="form.orderDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择订单日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="到货日期" prop="requiredDate">
          <el-date-picker clearable
                          v-model="form.requiredDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择到货日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="orderComments">
          <el-input v-model="form.orderComments" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<!--样式-->
<style scoped>
.address-cell {
  white-space: nowrap; /* 文本不换行 */
  overflow: hidden; /* 溢出部分隐藏 */
  text-overflow: ellipsis; /* 超出部分显示省略号 */
}
</style>

<script>
import { listOrders, getOrders, delOrders, addOrders, updateOrders ,goodsListAll } from "@/api/system/orders";
import alert from "element-ui/packages/alert";
export default {
  name: "Orders",
  dicts: ['f_sales_way', 'f_sales_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 采购信息表格数据
      ordersList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        supplier: null,
        paymentTerms: null,
        shippingAddress: null,
        status: null,
        orderDate: null,
      },
      // 表单参数
      form: {
        quantity:0,
        unitPrice:0
      },
      // 表单校验
      rules: {
        goodsId: [
          { required: true, message: "商品名称不能为空", trigger: "blur" },
          // { pattern: /^[\u4e00-\u9fa5]+$/, message: "商品名称必须为中文", trigger: "blur" }
        ],
        supplier: [
          { required: true, message: "供应商不能为空", trigger: "blur" },
          { pattern: /^[\u4e00-\u9fa5]+$/, message: "供应商必须为中文", trigger: "blur" }
        ],
        quantity: [
          { required: true, message: "数量不能为空", trigger: "blur" },
          { pattern: /^[1-9]\d*$/, message: '数量必须为正整数', trigger: 'blur' }
        ],
        unitPrice: [
          { required: true, message: "单价不能为空", trigger: "blur" },
          { pattern: /(^[\d]|^[1-9][\d]*)($|[\.][\d]{0,2}$)/, message: '单价必须为数字', trigger: 'blur' }
        ],
        paymentTerms: [
          { required: true, message: "付款方式不能为空", trigger: "blur" },
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "blur" },
        ],
      },
      state: ''
    };
  },
  created() {
    this.getList();
  },

  methods: {
    querySearch(queryString, cb) {
      goodsListAll({name:queryString}).then(res=>{
        cb(res);
      })
    },



    handleSelect(item) {
      this.form.goodsId=item.id
      console.log(item);
      this.state=item.name;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加采购信息";
    },
    /** 查询采购信息列表 */
    getList() {
      this.loading = true;
      listOrders(this.queryParams).then(response => {
        this.ordersList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        orderId: null,
        goodsId: null,
        supplier: null,
        quantity: null,
        unitPrice: null,
        totalAmount: null,
        paymentTerms: null,
        shippingAddress: null,
        status: null,
        orderDate: null,
        requiredDate: null,
        orderComments: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.orderId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const orderId = row.orderId || this.ids
      getOrders(orderId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改采购信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.orderId != null) {
            updateOrders(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrders(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const orderIds = row.orderId || this.ids;
      this.$modal.confirm('是否确认删除采购信息编号为"' + orderIds + '"的数据项？').then(function () {
        return delOrders(orderIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/orders/export', {
        ...this.queryParams
      }, `orders_${new Date().getTime()}.xlsx`)
    }
  },
  computed: {
    calculatedTotalAmount() {
      return this.form.quantity * this.form.unitPrice;
    }
  },
  watch:{
    "form.netType": function (val){
      //付款方式
      if (val=="1"){
        this.showPaymentTerms=true;
        this.showPaymentTerms=false;
        Object.assign(this.rules,{
          paymentTerms
        });
        //点击提交按钮触发
        //   this.$refs.form.validateField(["paymentTerms"]]);
        //   this.$refs.form.clearValidate(["paymentTerms"]]);
        //添加验证，立即触发校验
        setTimeout(()=>{
          this.$refs.form.validateField(["paymentTerms"]);
          this.$refs.form.cleaValidate(["paymentTerms"]);
        },1);
      };
      //状态
      if (val=="1"){
        this.showStatus=true;
        this.showStatus=false;
        Object.assign(this.rules,{
          paymentTerms
        });
        //点击提交按钮触发
        //   this.$refs.form.validateField(["status"]]);
        //   this.$refs.form.clearValidate(["status"]]);
        //添加验证，立即触发校验
        setTimeout(()=>{
          this.$refs.form.validateField(["status"]);
          this.$refs.form.cleaValidate(["status"]);
        },1);
      };
    }
  },
};
</script>
