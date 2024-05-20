<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="收入摘要" prop="financialIncome">
        <el-input
          v-model="queryParams.financialIncome"
          placeholder="请输入收入摘要"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="费用摘要" prop="financialExpense">
        <el-input
          v-model="queryParams.financialExpense"
          placeholder="请输入费用摘要"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="利润摘要" prop="financialProfit">
        <el-input
          v-model="queryParams.financialProfit"
          placeholder="请输入利润摘要"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="financialCreatetime">
        <el-date-picker clearable
                        v-model="queryParams.financialCreatetime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择创建时间">
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
          v-hasPermi="['system:financial:add']"
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
          v-hasPermi="['system:financial:edit']"
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
          v-hasPermi="['system:financial:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:financial:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="financialList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="财务id" align="center" prop="financialId" />
      <el-table-column label="收入摘要" align="center" prop="financialIncome" />
      <el-table-column label="费用摘要" align="center" prop="financialExpense" />
      <el-table-column label="利润摘要" align="center" prop="financialProfit" />
      <el-table-column label="创建时间" align="center" prop="financialCreatetime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.financialCreatetime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:financial:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:financial:remove']"
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

    <!-- 添加或修改财务报表管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="收入摘要" prop="financialIncome">
          <el-input v-model="form.financialIncome" placeholder="请输入收入摘要" />
        </el-form-item>
        <el-form-item label="费用摘要" prop="financialExpense">
          <el-input v-model="form.financialExpense" placeholder="请输入费用摘要" />
        </el-form-item>
        <el-form-item label="利润摘要" prop="financialProfit">
          <el-input v-model="form.financialProfit" placeholder="请输入利润摘要" />
        </el-form-item>
        <el-form-item label="创建时间" prop="financialCreatetime">
          <el-date-picker clearable
                          v-model="form.financialCreatetime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择创建时间">
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
import { listFinancial, getFinancial, delFinancial, addFinancial, updateFinancial } from "@/api/system/financial";

export default {
  name: "Financial",
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
      // 财务报表管理表格数据
      financialList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        financialIncome: null,
        financialExpense: null,
        financialProfit: null,
        financialCreatetime: null
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
  },
  methods: {
    /** 查询财务报表管理列表 */
    getList() {
      this.loading = true;
      listFinancial(this.queryParams).then(response => {
        this.financialList = response.rows;
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
        financialId: null,
        financialIncome: null,
        financialExpense: null,
        financialProfit: null,
        financialCreatetime: null
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
      this.ids = selection.map(item => item.financialId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加财务报表管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const financialId = row.financialId || this.ids
      getFinancial(financialId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改财务报表管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.financialId != null) {
            updateFinancial(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFinancial(this.form).then(response => {
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
      const financialIds = row.financialId || this.ids;
      this.$modal.confirm('是否确认删除财务报表管理编号为"' + financialIds + '"的数据项？').then(function() {
        return delFinancial(financialIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/financial/export', {
        ...this.queryParams
      }, `financial_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
