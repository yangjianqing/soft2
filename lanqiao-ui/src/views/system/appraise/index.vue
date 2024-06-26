<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:appraise:add']"
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
          v-hasPermi="['system:appraise:edit']"
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
          v-hasPermi="['system:appraise:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:appraise:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="appraiseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="评价id" align="center" prop="reviewid" />
      <el-table-column label="商品名称" align="center" prop="goodsName" />
      <el-table-column label="用户名称" align="center" prop="userName" />
      <el-table-column label="评分" align="center" prop="rating">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.evaluation_score" :value="scope.row.rating"/>
        </template>
      </el-table-column>
      <el-table-column label="匿名" align="center" prop="starts">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.anonymous_statu" :value="scope.row.starts"/>
        </template>
      </el-table-column>
      <el-table-column label="评价内容" align="center" prop="reviewText" class-name="goods-reviewText":show-overflow-tooltip="true">
        <template slot-scope="scope">
          <span :title="scope.row.reviewText">
            {{scope.row.reviewText}}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="评价时间" align="center" prop="reviewDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reviewDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:appraise:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:appraise:remove']"
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

    <!-- 添加或修改评价管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品id" prop="goodsid">
          <el-input v-model="form.goodsid" placeholder="请输入商品id" />
        </el-form-item>
        <el-form-item label="用户id" prop="userid">
          <el-input v-model="form.userid" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="评分" prop="rating">
          <el-select v-model="form.rating" placeholder="请选择评分">
            <el-option
              v-for="dict in dict.type.evaluation_score"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="匿名" prop="starts">
          <el-select v-model="form.starts" placeholder="请选择匿名">
            <el-option
              v-for="dict in dict.type.anonymous_statu"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="评价内容" prop="reviewText">
          <el-input v-model="form.reviewText" placeholder="请输入评价内容" />
        </el-form-item>
        <el-form-item label="评价时间" prop="reviewDate">
          <el-date-picker clearable
            v-model="form.reviewDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择评价时间">
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
<style scoped>
.address-cell {
  white-space: nowrap; /* 文本不换行 */
  overflow: hidden; /* 溢出部分隐藏 */
  text-overflow: ellipsis; /* 超出部分显示省略号 */
}
</style>
<script>
import { listAppraise, getAppraise, delAppraise, addAppraise, updateAppraise } from "@/api/system/appraise";

export default {
  name: "Appraise",
  dicts: ['anonymous_statu', 'evaluation_score'],
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
      // 评价管理表格数据
      appraiseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsid: this.$route.query.id,
        userid: null,
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
    const goodsid = this.$route.query.id;
    console.log(goodsid);
  },
  methods: {
    /** 查询评价管理列表 */
    getList() {
      this.loading = true;
      listAppraise(this.queryParams).then(response => {
        this.appraiseList = response.rows;
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
        reviewid: null,
        goodsid: null,
        userid: null,
        rating: null,
        starts: null,
        reviewText: null,
        reviewDate: null
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
      this.ids = selection.map(item => item.reviewid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加评价管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const reviewid = row.reviewid || this.ids
      getAppraise(reviewid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改评价管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.reviewid != null) {
            updateAppraise(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAppraise(this.form).then(response => {
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
      const reviewids = row.reviewid || this.ids;
      this.$modal.confirm('是否确认删除评价管理编号为"' + reviewids + '"的数据项？').then(function() {
        return delAppraise(reviewids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/appraise/export', {
        ...this.queryParams
      }, `appraise_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
