<template>
  <div class="app-container">
    <el-row :gutter="20" class="mb8">
<!--     分类数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
            v-model="catName"
            placeholder="请输入部门名称"
            clearable
            size="small"
            prefix-icon="el-icon-search"
            style="margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree
            :data="cateOptions"
            :props="defaultProps"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            ref="tree"
            :default-expand-all="false"
            highlight-current
            @node-click="handleNodeClick"
          />
        </div>
      </el-col>
    <el-col :span="20" :xs="24">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="商品名称" prop="name">
          <el-input
            v-model="queryParams.name"
            placeholder="请输入商品名称"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
            <el-option
              v-for="dict in dict.type.f_goods"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
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
            v-hasPermi="['system:goods:add']"
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
            v-hasPermi="['system:goods:edit']"
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
            v-hasPermi="['system:goods:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:goods:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
      <el-table v-loading="loading" :data="goodsList" @selection-change="handleSelectionChange">
<!--        <el-table-column type="selection" width="55" align="center" />-->
        <el-table-column label="商品ID" align="center" prop="id" width="60px"/>
        <el-table-column label="商品名称" align="center" prop="name"  class-name="goods-name":show-overflow-tooltip="true" >
          <template slot-scope="scope">
          <span :title="scope.row.name">
            {{scope.row.name}}
          </span>
          </template>
        </el-table-column>
        <el-table-column label="类别" align="center" prop="categoryName" />
        <el-table-column label="商品价格" align="center" prop="price" />
        <el-table-column label="单位" align="unit" prop="unit" />
        <el-table-column label="编码" align="coding" prop="coding" class-name="goods-coding":show-overflow-tooltip="true" >
          <template slot-scope="scope">
          <span :title="scope.row.coding">
            {{scope.row.coding}}
          </span>
          </template>
        </el-table-column>
        <el-table-column label="图片" align="center" prop="image" width="100">
          <template slot-scope="scope">
            <image-preview :src="scope.row.image" :width="50" :height="50"/>
          </template>
        </el-table-column>
        <el-table-column label="库存" align="center" prop="num" />
        <el-table-column label="描述" align="center" prop="description" class-name="goods-description":show-overflow-tooltip="true" >
          <template slot-scope="scope">
          <span :title="scope.row.description">
            {{scope.row.description}}
          </span>
          </template>
        </el-table-column>
        <el-table-column label="评价" align="center" prop="appraise" >
          <template slot-scope="scope">
            <router-link :to="'/system/goods-data/index?id='+ scope.row.id" class="link-type">
              <div class="address-cell">评价</div>
            </router-link>
          </template>
        </el-table-column>

        <el-table-column label="状态" align="center" prop="status">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.f_goods" :value="scope.row.status"/>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:goods:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:goods:remove']"
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
      </el-col>
    </el-row>

    <!-- 添加或修改商品对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商品价格" prop="price">
              <el-input v-model="form.price" placeholder="请输入商品价格" />
        </el-form-item>
        <el-form-item label="单位" prop="unit">
              <el-input v-model="form.unit" placeholder="请输入单位" />
        </el-form-item>
        <el-form-item label="规格" prop="specification">
              <el-input v-model="form.specification" placeholder="请输入单位" />
        </el-form-item>
        <el-form-item label="编码" prop="coding">
              <el-input v-model="form.coding" placeholder="请输入编码" />
        </el-form-item>
        <el-form-item label="图片">
          <image-upload v-model="form.image"/>
        </el-form-item>
        <el-form-item label="描述信息" prop="description">
          <el-input v-model="form.description" placeholder="请输入描述信息" />
        </el-form-item>
        <el-form-item label="库存" prop="num">
          <el-input v-model="form.num" placeholder="请输入库存" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.f_goods"
              :key="dict.value"
:label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
<!--        <el-form-item label="创建人" prop="createUser">-->
<!--          <el-input v-model="form.createUser" placeholder="请输入创建人" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="修改人" prop="updateUser">-->
<!--          <el-input v-model="form.updateUser" placeholder="请输入修改人" />-->
<!--        </el-form-item>-->
          <el-form-item label="类别" prop="cateId">
            <treeselect v-model="form.categoryId" :options="cateOptions" :show-count="true" placeholder="请选择类别" />
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
import {listGoods, getGoods, delGoods, addGoods, updateGoods, cateTreeSelect} from "@/api/system/goods";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Goods",
  components: {Treeselect},
  dicts: ['f_goods'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedCategory: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 商品表格数据
      goodsList: [],
      // 商品分类表格数据
      categoryList: [],
      // 弹出层标题
      title: "",
      //分类树选项
      cateOptions:undefined,
      // 是否显示弹出层
      open: false,
      //分类名称
      catName:undefined,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        categoryId: null,
        status: null,
      },
      // 表单参数
      form: {},
      defaultProps: {
        children: "children",
        label: "label"
      },
      // 表单校验
      rules: {
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
        // num: [
        //   { required: true, message: "库存不能为空", trigger: "blur" },
        //   { pattern: /^[1-9]\d*$/, message: '库存必须为正整数', trigger: 'blur' }
        // ],
      }
    };
  },
  watch: {
    // 根据名称筛选部门树
    catName(val) {
      this.$refs.tree.filter(val);
    }
  },
  created() {
    this.getList();
    this.getCateTree();
  },
  methods: {
    /** 查询商品列表 */
    getList() {
      this.loading = true;
      listGoods(this.queryParams).then(response => {
        this.goodsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询部门下拉树结构 */
    getCateTree() {
      cateTreeSelect().then(response => {
        this.cateOptions = response.data;
      });
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.categoryId = data.id;
      this.handleQuery();
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        categoryId: null,
        price: null,
        unit: null,
        specification: null,
        coding: null,
        image: null,
        description: null,
        status: 0,
        createTime: null,
        updateTime: null,
        createUser: null,
        updateUser: null
      };

      this.categoryList = [];
      this.resetForm("form");
    },
    onDescriptionInput(event) {
      // 当输入的字符数超过100时，阻止继续输入
      if (this.description.length >= 6) {
        event.preventDefault();
        // 可以在这里显示一个警告提示框
        alert('描述不能超过100个字符！');
      }
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加商品";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getGoods(id).then(response => {
        this.form = response.data;
        this.categoryList = response.data.categoryList;
        this.open = true;
        this.title = "修改商品";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.categoryList = this.categoryList;
          if (this.form.id != null) {
            updateGoods(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGoods(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除商品编号为"' + ids + '"的数据项？').then(function() {
        return delGoods(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 商品分类序号 */
    rowCategoryIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },

    /** 商品分类添加按钮操作 */
    handleAddCategory() {
      let obj = {};
      obj.parentId = "";
      obj.ancestors = "";
      obj.deptName = "";
      obj.orderNum = "";
      this.categoryList.push(obj);
    },
    /** 商品分类删除按钮操作 */
    handleDeleteCategory() {
      if (this.checkedCategory.length == 0) {
        this.$modal.msgError("请先选择要删除的商品分类数据");
      } else {
        const categoryList = this.categoryList;
        const checkedCategory = this.checkedCategory;
        this.categoryList = categoryList.filter(function(item) {
          return checkedCategory.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleCategorySelectionChange(selection) {
      this.checkedCategory = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/goods/export', {
        ...this.queryParams
      }, `goods_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
