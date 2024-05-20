<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户姓名" prop="usersName">
        <el-input
          v-model="queryParams.usersName"
          placeholder="请输入用户姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系方式" prop="usersPhone">
        <el-input
          v-model="queryParams.usersPhone"
          placeholder="请输入联系方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会员级别" prop="memberGrade">
        <el-select v-model="queryParams.memberGrade" placeholder="请选择会员级别" clearable>
          <el-option
            v-for="dict in dict.type.f_membership_grade"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable
                        v-model="queryParams.createTime"
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
          v-hasPermi="['system:users:add']"
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
          v-hasPermi="['system:users:edit']"
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
          v-hasPermi="['system:users:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:users:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="usersList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户id" align="center" prop="usersId" />
      <el-table-column label="用户姓名" align="center" prop="usersName" />
      <el-table-column label="用户性别" align="center" prop="usersSex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.usersSex"/>
        </template>
      </el-table-column>
      <el-table-column label="联系方式" align="center" prop="usersPhone" />
      <el-table-column label="用户密码" align="center" prop="usersPassword" />
      <el-table-column label="用户头像" align="center" prop="usersAvatar" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.usersAvatar" :width="50" :height="50"/>
        </template>
      </el-table-column>

      <el-table-column label="用户地址" align="center" prop="userAddress" class-name="address-column" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <router-link :to="'/system/users-data/index?usersId='+ scope.row.usersId" class="link-type">
            <div class="address-cell">{{ scope.row.userAddress }}</div>
          </router-link>
        </template>
      </el-table-column>

      <el-table-column label="会员级别" align="center" prop="memberGrade">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.f_membership_grade" :value="scope.row.memberGrade"/>
        </template>
      </el-table-column>
      <el-table-column label="会员积分" align="center" prop="memberTotal" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:users:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:users:remove']"
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

    <!-- 添加或修改用户管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户姓名" prop="usersName">
          <el-input v-model="form.usersName" placeholder="请输入用户姓名" />
        </el-form-item>
        <el-form-item label="用户性别" prop="usersSex">
          <el-select v-model="form.usersSex" placeholder="请选择用户性别">
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联系方式" prop="usersPhone">
          <el-input v-model="form.usersPhone" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="用户密码" prop="usersPassword">
          <el-input v-model="form.usersPassword" placeholder="请输入用户密码" />
        </el-form-item>
        <el-form-item label="用户头像">
          <image-upload v-model="form.usersAvatar"/>
        </el-form-item>
        <el-form-item label="用户地址" prop="userAddress">
          <el-input v-model="form.userAddress" type="textarea" placeholder="请输入内容" />
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
import { listUsers, getUsers, delUsers, addUsers, updateUsers } from "@/api/system/users/users";

export default {
  name: "Users",
  dicts: ['f_membership_grade', 'sys_user_sex'],
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
      // 用户管理表格数据
      usersList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        usersName: null,
        usersPhone: null,
        memberGrade: null,
        createTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        usersName: [
          { required: true, message: "用户姓名不能为空", trigger: "blur" }
        ],
        usersSex: [
          { required: true, message: "用户性别不能为空", trigger: "change" }
        ],
        usersPhone: [
          { required: true, message: "联系方式不能为空", trigger: "blur" }
        ],
        usersPassword: [
          { required: true, message: "用户密码不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询用户管理列表 */
    getList() {
      this.loading = true;
      listUsers(this.queryParams).then(response => {
        this.usersList = response.rows;
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
        usersId: null,
        usersName: null,
        usersSex: null,
        usersPhone: null,
        usersPassword: null,
        usersAvatar: null,
        userAddress: null,
        memberGrade: null,
        memberTotal: null,
        createTime: null
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
      this.ids = selection.map(item => item.usersId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用户管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const usersId = row.usersId || this.ids
      getUsers(usersId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.usersId != null) {
            updateUsers(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUsers(this.form).then(response => {
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
      const usersIds = row.usersId || this.ids;
      this.$modal.confirm('是否确认删除用户管理编号为"' + usersIds + '"的数据项？').then(function() {
        return delUsers(usersIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/users/export', {
        ...this.queryParams
      }, `users_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
