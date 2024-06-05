<template>
<!--  <div class="nav_img">-->
<!--    <img src="@/assets/img/img.png" alt="">-->
<!--  </div>-->
  <div style="display: flex;justify-content: center;
    align-items: center;height: 300px">
     <div class="nav_img">
    <img src="@/assets/img/vegetables/vegetable_2.jpg" alt="">
  </div>
  </div>
  <p style="font-size: 28px;">生鲜绿源水果蔬菜超市</p>
  <div class="nav_bottom">
  <van-cell-group inset style="margin-bottom: 10px;margin-top: 30px;">
    <van-field
        v-model="user.username"
        label="电话号码"
        left-icon="phone-circle"
        style="background-color:rgb(214,223,211);height: 50px;display: flex;align-items: center;"
    />

  </van-cell-group>
  <van-cell-group inset style="margin-bottom: 10px">
  <van-field
      v-model="user.password"
      clearable
      type="password"
      label="密码"
      left-icon="lock"
      style="background-color:rgb(214,223,211);height: 50px;display: flex;align-items: center;"
  />
  </van-cell-group>
  <div style="display: flex;justify-content: space-around;
    align-items: center;">
    <van-radio-group  shape="square">
      <van-radio><p style="color: #afafaf">我同意平台的条款和条列</p></van-radio>
    </van-radio-group>
  </div>
  <div style="margin: 16px;">
<!--    -->
    <van-button round block  type="primary" color="rgb(47,135,112)" native-type="submit" @click="handleLogin">
      登录
    </van-button>
  </div>

  <div>
    <div style="display: flex;justify-content: center;">
      还没有注册？<router-link to="/adduser">
      <p style="color: green" >点这里</p>
    </router-link>
    </div>
  </div>
  </div>
</template>

<script>
    export default {
        // eslint-disable-next-line vue/multi-word-component-names
        name: "login",
        data(){
            return {
                user:{
                    username:"",
                    password:""
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
}
.nav_img img{
  border-radius: 3%;
  height: 300px;
}

.search-icon {
  font-size: 16px;
  line-height: inherit;
}
.nav_bottom{
  border-radius: 3%;
}
</style>
