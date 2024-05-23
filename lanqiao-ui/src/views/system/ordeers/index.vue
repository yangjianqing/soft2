<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单编号" prop="ordersNumber">
        <el-input
          v-model="queryParams.ordersNumber"
          placeholder="请输入订单编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="买家姓名" prop="ordersUsersId">
        <el-select v-model="queryParams.ordersUsersId" :data="ordeersList" clearable placeholder="请输入买家姓名" @keyup.enter.native="handleQuery" filterable>
          <el-option
            v-for="item in ordeersList"
            :key="item.address.addressId"
            :label="item.address.addressName"
            :value="item.address.addressId"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="配送员" prop="ordersSysuserId">
        <el-select v-model="queryParams.ordersSysuserId" :data="Delivery" clearable placeholder="请输入配送员" @keyup.enter.native="handleQuery" filterable>
          <el-option
            v-for="item in Delivery"
            :key="item.deptId"
            :label="item.userName"
            :value="item.deptId"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="订单状态" prop="ordersStatus">
        <el-select v-model="queryParams.ordersStatus" placeholder="请选择订单状态" clearable>
          <el-option
            v-for="dict in dict.type.f_sales_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="下单时间" prop="ordersCreattime">
        <el-date-picker clearable
          v-model="queryParams.ordersCreattime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择下单时间">
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
          v-hasPermi="['system:ordeers:add']"
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
          v-hasPermi="['system:ordeers:edit']"
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
          v-hasPermi="['system:ordeers:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:ordeers:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ordeersList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--<el-table-column label="订单id" align="center" prop="ordersId" />-->
      <!--<el-table-column label="订单编号" align="center" prop="ordersNumber" />-->

      <el-table-column label="订单编号" align="center" prop="ordersNumber">
        <template slot-scope="scope">
          <router-link :to="'/system/ordeers-data/index?ordersNumber='+ scope.row.ordersNumber" class="link-type">
            <div class="address-cell">{{ scope.row.ordersNumber }}</div>
          </router-link>
        </template>
      </el-table-column>

      <el-table-column label="买家姓名" align="center" prop="address.addressName" />
      <el-table-column label="联系方式" align="center" prop="address.addressPhone" />
      <el-table-column label="配送员" align="center" prop="ordersSysuserName" />
      <el-table-column label="支付方式" align="center" prop="ordersPayMethod">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_model_pay" :value="scope.row.ordersPayMethod"/>
        </template>
      </el-table-column>
      <el-table-column label="支付状态" align="center" prop="ordersPayStatuds">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_status_typ" :value="scope.row.ordersPayStatuds"/>
        </template>
      </el-table-column>
      <el-table-column label="订单状态" align="center" prop="ordersStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.f_sales_status" :value="scope.row.ordersStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="收货地址" align="center" prop="address.addressDetail" class-name="address-column" :show-overflow-tooltip="true" >
        <template slot-scope="scope">
          <div class="address-cell">{{ scope.row.address.addressDetail }}</div>
        </template>
      </el-table-column>
      <el-table-column label="下单时间" align="center" prop="ordersCreattime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.ordersCreattime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注信息" align="center" prop="ordersRemark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:ordeers:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:ordeers:remove']"
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

    <!-- 添加或修改订单管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="买家姓名" prop="ordersUsersId">
          <el-input v-model="form.ordersUsersId" placeholder="请输入买家姓名" />
        </el-form-item>
        <el-form-item label="配送员" prop="ordersSysuserId">
          <el-input v-model="form.ordersSysuserId" placeholder="请输入配送员" />
        </el-form-item>
        <el-form-item label="支付方式" prop="ordersPayMethod">
          <el-select v-model="form.ordersPayMethod" placeholder="请选择支付方式">
            <el-option
              v-for="dict in dict.type.sys_model_pay"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="支付状态" prop="ordersPayStatuds">
          <el-select v-model="form.ordersPayStatuds" placeholder="请选择支付状态">
            <el-option
              v-for="dict in dict.type.sys_status_typ"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="订单状态" prop="ordersStatus">
          <el-select v-model="form.ordersStatus" placeholder="请选择订单状态">
            <el-option
              v-for="dict in dict.type.f_sales_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注信息" prop="ordersRemark">
          <el-input v-model="form.ordersRemark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<style scoped>
.address-cell {
  white-space: nowrap; /* 文本不换行 */
  overflow: hidden; /* 溢出部分隐藏 */
  text-overflow: ellipsis; /* 超出部分显示省略号 */
}
</style>

<script>
import {listOrdeers, getOrdeers, delOrdeers, addOrdeers, updateOrdeers, listDelivery} from "@/api/system/ordeers";

export default {
  name: "Ordeers",
  dicts: ['sys_model_pay', 'sys_status_typ', 'f_sales_status'],
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
      //配送员信息
      Delivery: [],
      // 订单管理表格数据
      ordeersList: [],
      // 订单管理用户ids
      ordeersListIds: [],
      // 订单管理表格数据
      addressList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ordersNumber: null,
        ordersUsersId: null,
        ordersSysuserId: null,
        ordersStatus: null,
        ordersCreattime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getDelivery();
  },
  methods: {
    // 页面加载初始化数据
    getDelivery(){
      listDelivery().then(response => {
        this.Delivery = response.Delivery;
      });
    },
    /** 查询订单管理列表 */
    getList() {
      this.loading = true;
      listOrdeers(this.queryParams).then(response => {
        this.ordeersList = response.rows;
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
        ordersId: null,
        ordersNumber: null,
        ordersUsersId: null,
        ordersSysuserId: null,
        ordersPayMethod: null,
        ordersPayStatuds: null,
        ordersStatus: null,
        ordersCreattime: null,
        ordersRemark: null
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
      this.ids = selection.map(item => item.ordersId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加订单管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ordersId = row.ordersId || this.ids
      getOrdeers(ordersId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改订单管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.ordersId != null) {
            updateOrdeers(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrdeers(this.form).then(response => {
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
      const ordersIds = row.ordersId || this.ids;
      this.$modal.confirm('是否确认删除订单管理编号为"' + ordersIds + '"的数据项？').then(function() {
        return delOrdeers(ordersIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/ordeers/export', {
        ...this.queryParams
      }, `ordeers_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
