<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="用户姓名" prop="usersId">
        <el-select v-model="queryParams.usersId" :data="users" placeholder="请输入用户姓名" clearable @keyup.enter.native="handleQuery">
          <el-option
            v-for="item in users"
            :key="item.usersId"
            :label="item.usersName"
            :value="item.usersId"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="付款方式" prop="salesWay">
        <el-select v-model="queryParams.salesWay" placeholder="请选择付款方式" clearable>
          <el-option
            v-for="dict in dict.type.f_sales_way"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="订单状态" prop="salesStatus">
        <el-select v-model="queryParams.salesStatus" placeholder="请选择订单状态" clearable>
          <el-option
            v-for="dict in dict.type.f_sales_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="销售日期" prop="salesCreationtime">
        <el-date-picker clearable
          v-model="queryParams.salesCreationtime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择销售日期">
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
          v-hasPermi="['system:sales:add']"
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
          v-hasPermi="['system:sales:edit']"
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
          v-hasPermi="['system:sales:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:sales:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="salesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="销售记录id" align="center" prop="salesId" />
      <el-table-column label="用户姓名" align="center" prop="usersName" />
      <el-table-column label="总金额" align="center" prop="salesAllamount" />
      <el-table-column label="付款方式" align="center" prop="salesWay">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.f_sales_way" :value="scope.row.salesWay"/>
        </template>
      </el-table-column>
      <el-table-column label="订单状态" align="center" prop="salesStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.f_sales_status" :value="scope.row.salesStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="销售日期" align="center" prop="salesCreationtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.salesCreationtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:sales:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:sales:remove']"
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

    <!-- 添加或修改销售记录管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="用户姓名" prop="usersId">
          <el-select v-model="form.usersId" :data="users" placeholder="请输入用户姓名" clearable>
            <el-option
              v-for="item in users"
              :key="item.usersId"
              :label="item.usersName"
              :value="item.usersId"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="总金额" prop="salesAllamount">
          <el-input v-model="form.salesAllamount" placeholder="请输入总金额" />
        </el-form-item>
        <el-form-item label="付款方式" prop="salesWay">
          <el-select v-model="form.salesWay" placeholder="请选择付款方式">
            <el-option
              v-for="dict in dict.type.f_sales_way"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="订单状态" prop="salesStatus">
          <el-select v-model="form.salesStatus" placeholder="请选择订单状态">
            <el-option
              v-for="dict in dict.type.f_sales_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="销售日期" prop="salesCreationtime">
          <el-date-picker clearable
            v-model="form.salesCreationtime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择销售日期">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSales, getSales, delSales, addSales, updateSales, initUsers } from "@/api/system/sales";

export default {
  name: "Sales",
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
      // 销售记录管理表格数据
      salesList: [],
      // 弹出层标题
      title: "",
      users: [],
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        usersId: null,
        salesWay: null,
        salesStatus: null,
        salesCreationtime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.init();
    this.getList();
  },
  methods: {
    // 页面加载初始化数据
    init(){
      initUsers().then(response => {
        this.users = response.users;
        console.log(this.users)
      });
    },
    /** 查询销售记录管理列表 */
    getList() {
      this.loading = true;
      listSales(this.queryParams).then(response => {
        this.salesList = response.rows;
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
        salesId: null,
        usersId: null,
        salesAllamount: null,
        salesWay: null,
        salesStatus: null,
        salesCreationtime: null
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
      this.ids = selection.map(item => item.salesId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加销售记录管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const salesId = row.salesId || this.ids
      getSales(salesId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改销售记录管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.salesId != null) {
            updateSales(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSales(this.form).then(response => {
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
      const salesIds = row.salesId || this.ids;
      this.$modal.confirm('是否确认删除销售记录管理编号为"' + salesIds + '"的数据项？').then(function() {
        return delSales(salesIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/sales/export', {
        ...this.queryParams
      }, `sales_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
