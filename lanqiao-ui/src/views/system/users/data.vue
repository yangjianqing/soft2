<template>
  <div class="app-container">
<!--    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">-->
<!--      <el-form-item label="收货人" prop="addressName">-->
<!--        <el-input-->
<!--          v-model="queryParams.addressName"-->
<!--          placeholder="请输入收货人"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="手机号" prop="addressPhone">-->
<!--        <el-input-->
<!--          v-model="queryParams.addressPhone"-->
<!--          placeholder="请输入手机号"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="地址标签" prop="addressLabel">-->
<!--        <el-select v-model="queryParams.addressLabel" placeholder="请选择地址标签" clearable>-->
<!--          <el-option-->
<!--            v-for="dict in dict.type.f_address_label"-->
<!--            :key="dict.value"-->
<!--            :label="dict.label"-->
<!--            :value="dict.value"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>-->
<!--        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:address:add']"
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
          v-hasPermi="['system:address:edit']"
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
          v-hasPermi="['system:address:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:address:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="addressIdList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="地址id" align="center" prop="addressId" />
      <el-table-column label="地址排序" align="center" prop="addressSort" />
      <el-table-column label="收货人" align="center" prop="addressName" />
      <el-table-column label="性别" align="center" prop="addressSex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.addressSex"/>
        </template>
      </el-table-column>
      <el-table-column label="联系方式" align="center" prop="addressPhone" />
      <el-table-column label="地址标签" align="center" prop="addressLabel">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.f_address_label" :value="scope.row.addressLabel"/>
        </template>
      </el-table-column>
      <el-table-column label="地址经度" align="center" prop="addressTude" />
      <el-table-column label="地址纬度" align="center" prop="addressLatit" />
      <el-table-column label="详细地址" align="center" prop="addressDetail" class-name="address-column" :show-overflow-tooltip="true" >
        <template slot-scope="scope">
          <div class="address-cell">{{ scope.row.addressDetail }}</div>
        </template>
      </el-table-column>
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['system:address:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['system:address:remove']"-->
<!--          >删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改地址管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户姓名" prop="addressUsersId">
          <el-input v-model="usersName" disabled/>
        </el-form-item>
        <el-form-item label="地址排序" prop="addressSort">
          <el-input-number v-model="form.addressSort" controls-position="right" :min="0" />
        </el-form-item>
        <el-form-item label="收货人" prop="addressName">
          <el-input v-model="form.addressName" placeholder="请输入收货人" />
        </el-form-item>
        <el-form-item label="性别" prop="addressSex">
          <el-select v-model="form.addressSex" placeholder="请选择性别">
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联系方式" prop="addressPhone">
          <el-input v-model="form.addressPhone" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="地址标签" prop="addressLabel">
          <el-select v-model="form.addressLabel" placeholder="请选择地址标签">
            <el-option
              v-for="dict in dict.type.f_address_label"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="地址经度" prop="addressTude">
          <el-input v-model="form.addressTude" placeholder="请输入地址经度" />
        </el-form-item>
        <el-form-item label="地址纬度" prop="addressLatit">
          <el-input v-model="form.addressLatit" placeholder="请输入地址纬度" />
        </el-form-item>
        <el-form-item label="详细地址" prop="addressDetail">
          <el-input v-model="form.addressDetail" type="textarea" placeholder="请输入内容" />
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
import { listAddress, getAddress, delAddress, addAddress, updateAddress, getType, getUsers } from "@/api/system/users/data";

export default {
  name: "Address",
  dicts: ['f_address_label', 'sys_user_sex'],
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
      //当前页面的用户名称
      usersName: '',
      // 地址管理表格数据
      addressList: [],
      // 根据id查询地址详情数据
      addressIdList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        addressName: null,
        addressPhone: null,
        addressLabel: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        addressName: [
          { required: true, message: "收货人不能为空", trigger: "blur" }
        ],
        addressPhone: [
          { required: true, message: "手机号不能为空", trigger: "blur" }
        ],
        addressTude: [
          { required: true, message: "地址经度不能为空", trigger: "blur" }
        ],
        addressLatit: [
          { required: true, message: "地址纬度不能为空", trigger: "blur" }
        ],
        addressDetail: [
          { required: true, message: "详细地址不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    // 使用 query 传参
    const usersId = this.$route.query.usersId;
    // 根据 usersId 查询地址详情
    this.getType(usersId);
    this.getUsers(usersId);
    // // 获取地址列表
    // this.getList();
  },
  methods: {
    /** 根据usersId查询地址详情 */
    getType(usersId) {
      this.loading = true;
      getType(usersId).then(response => {
        this.addressIdList = response.data;
        this.loading = false;
      });
    },
    /** 获取当前用户名称 */
    getUsers(usersId) {
      getUsers(usersId).then(response => {
        this.usersName = response.usersList.usersName;
      });
    },
    /** 查询地址管理列表 */
    getList() {
      this.loading = true;
      listAddress(this.queryParams).then(response => {
        this.addressList = response.rows;
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
        addressId: null,
        adderssUsersId: null,
        addressSort: null,
        addressName: null,
        addressSex: null,
        addressPhone: null,
        addressLabel: null,
        addressTude: null,
        addressLatit: null,
        addressDetail: null
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
      this.ids = selection.map(item => item.addressId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加地址管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const addressId = row.addressId || this.ids
      getAddress(addressId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改地址管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.addressId != null) {
            updateAddress(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAddress(this.form).then(response => {
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
      const addressIds = row.addressId || this.ids;
      this.$modal.confirm('是否确认删除地址管理编号为"' + addressIds + '"的数据项？').then(function() {
        return delAddress(addressIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/address/export', {
        ...this.queryParams
      }, `address_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
