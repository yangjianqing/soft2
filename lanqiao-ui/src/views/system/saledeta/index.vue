<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="销售记录" prop="salesId">
        <el-input
          v-model="queryParams.salesId"
          placeholder="请输入销售记录"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品名称" prop="goodsId">
        <el-input
          v-model="queryParams.goodsId"
          placeholder="请输入商品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品数量" prop="saledetaTity">
        <el-input
          v-model="queryParams.saledetaTity"
          placeholder="请输入商品数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="saledetaCreationtime">
        <el-date-picker clearable
          v-model="queryParams.saledetaCreationtime"
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
          v-hasPermi="['system:saledeta:add']"
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
          v-hasPermi="['system:saledeta:edit']"
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
          v-hasPermi="['system:saledeta:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:saledeta:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="saledetaList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="销售明细id" align="center" prop="saledetaId" />
      <el-table-column label="销售记录" align="center" prop="salesId" />
      <el-table-column label="商品名称" align="center" prop="goodsId" />
      <el-table-column label="商品数量" align="center" prop="saledetaTity" />
      <el-table-column label="商品单价" align="center" prop="saledetaPrice" />
      <el-table-column label="创建时间" align="center" prop="saledetaCreationtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.saledetaCreationtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:saledeta:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:saledeta:remove']"
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

    <!-- 添加或修改销售明细管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="销售记录" prop="salesId">
          <el-input v-model="form.salesId" placeholder="请输入销售记录" />
        </el-form-item>
        <el-form-item label="商品名称" prop="goodsId">
          <el-input v-model="form.goodsId" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商品数量" prop="saledetaTity">
          <el-input v-model="form.saledetaTity" placeholder="请输入商品数量" />
        </el-form-item>
        <el-form-item label="商品单价" prop="saledetaPrice">
          <el-input v-model="form.saledetaPrice" placeholder="请输入商品单价" />
        </el-form-item>
        <el-form-item label="创建时间" prop="saledetaCreationtime">
          <el-date-picker clearable
            v-model="form.saledetaCreationtime"
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
import { listSaledeta, getSaledeta, delSaledeta, addSaledeta, updateSaledeta } from "@/api/system/saledeta";

export default {
  name: "Saledeta",
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
      // 销售明细管理表格数据
      saledetaList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        salesId: null,
        goodsId: null,
        saledetaTity: null,
        saledetaCreationtime: null
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
    /** 查询销售明细管理列表 */
    getList() {
      this.loading = true;
      listSaledeta(this.queryParams).then(response => {
        this.saledetaList = response.rows;
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
        saledetaId: null,
        salesId: null,
        goodsId: null,
        saledetaTity: null,
        saledetaPrice: null,
        saledetaCreationtime: null
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
      this.ids = selection.map(item => item.saledetaId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加销售明细管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const saledetaId = row.saledetaId || this.ids
      getSaledeta(saledetaId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改销售明细管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.saledetaId != null) {
            updateSaledeta(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSaledeta(this.form).then(response => {
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
      const saledetaIds = row.saledetaId || this.ids;
      this.$modal.confirm('是否确认删除销售明细管理编号为"' + saledetaIds + '"的数据项？').then(function() {
        return delSaledeta(saledetaIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/saledeta/export', {
        ...this.queryParams
      }, `saledeta_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
