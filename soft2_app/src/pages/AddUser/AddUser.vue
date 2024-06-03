<template>
  <div style="display: flex;flex-direction: column;
    align-items: center;
    justify-content: center;height: 200px">
    <h1>欢迎</h1>
    <p style="font-size: 28px;">创建你的个人账号或者登录</p>
  </div>
  <van-cell-group inset style="margin-bottom: 10px;margin-top: 30px;">
    <van-field
        v-model="user.username"
        label="用户名"
        left-icon="smile-o"
        placeholder="请输入用户名"
        style="background-color:rgb(208,224,212);height: 50px;display: flex;align-items: center;"
    />

  </van-cell-group>
  <van-cell-group inset style="margin-bottom: 10px">
    <van-field
        v-model="user.password"
        clearable
        type="password"
        label="密码"
        left-icon="lock"
        placeholder="请输入密码"
        style="background-color:rgb(208,224,212);height: 50px;display: flex;align-items: center;"
    />
  </van-cell-group>
  <van-cell-group inset style="margin-bottom: 10px">
    <van-field
        v-model="user.password"
        clearable
        type="password"
        label="电话号码"
        left-icon="lock"
        placeholder="请输入电话号码"
        style="background-color:rgb(208,224,212);height: 50px;display: flex;align-items: center;"
    />
  </van-cell-group>
  <van-cell-group inset style="margin-bottom: 10px">
    <van-field
        v-model="user.phone"
        clearable
        type="password"
        label="密码"
        left-icon="lock"
        placeholder="请再次输入密码"
        style="background-color:rgb(208,224,212);height: 50px;display: flex;align-items: center;"
    />
  </van-cell-group>
  <van-cell-group inset style="margin-bottom: 10px">
    <van-field
        v-model="user.password"
        clearable
        type="password"
        label="验证码"
        left-icon="chat"
        placeholder="请输入验证码"
        style="background-color:rgb(208,224,212);height: 50px;display: flex;align-items: center;"
    >
    <template #button>
      <van-button size="small" type="primary">发送验证码</van-button>
    </template>
    </van-field>
  </van-cell-group>
  <div style="display: flex;justify-content: space-around;
    align-items: center;">
    <van-radio-group  shape="square">
      <van-radio><p style="color: #afafaf">我同意平台的条款和条列</p></van-radio>
    </van-radio-group>
  </div>
  <div style="margin: 16px;">
    <!--    -->
    <van-button round block  type="primary" color="rgb(47,135,112)" native-type="submit">
      注册
    </van-button>
  </div>

  <div>
    <div style="display: flex;justify-content: center;">
     已经有账号去
      <router-link to="/login"><p style="color: green" >登录</p></router-link>
    </div>
  </div>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "AddUser",
  data(){
    return {
      user:{
        username:"",
        password:"",
        phone:"",
        yzm:""
      }
    }
  },
  methods:{

  }
}
</script>

<style scoped>
.nav_img{
  border-radius: 3%;
  height: 83px;
}
.nav_img img{
  border-radius: 3%;
  height: 92px;
  width: 106px;
}

.search-icon {
  font-size: 16px;
  line-height: inherit;
}
</style>