<template>
  <div class="nav_img">
 <div>
   <div style="display: flex;flex-direction: column;
    align-items: center;
    justify-content: center;height: 200px">
     <h1 style="color: white">欢迎</h1>
     <p style="font-size: 28px;color: white">创建你的个人账号或者登录</p>
   </div>
   <van-cell-group inset style="margin-bottom: 10px;margin-top: 5px;">
     <van-field
       v-model="user.username"
       left-icon="smile-o"
       placeholder="请输入用户名"
       style="height: 50px;display: flex;align-items: center;"
     />

   </van-cell-group>
   <van-cell-group inset style="margin-bottom: 10px">
     <van-field
       v-model="user.userPassword"
       clearable
       left-icon="lock"
       placeholder="请输入密码"
       style="height: 50px;display: flex;align-items: center;"
     />
   </van-cell-group>
   <van-cell-group inset style="margin-bottom: 10px">
     <van-field
       v-model="user.confirmPassword"
       clearable
       left-icon="lock"
       placeholder="请再输入密码"
       prop="confirmPassword"
       style="height: 50px;display: flex;align-items: center;"
     />
   </van-cell-group>
   <van-cell-group inset style="margin-bottom: 10px">
     <van-field
       v-model="user.userPhone"
       clearable
       left-icon="phone-o"
       placeholder="请输入电话号码"
       style="height: 50px;display: flex;align-items: center;"
     />
   </van-cell-group>
   <van-cell-group inset style="margin-bottom: 10px">
     <van-field
       v-model="user.verificationCode"
       clearable
       left-icon="chat"
       placeholder="请输入验证码"
       style="height: 50px;display: flex;align-items: center;"
     >
       <template #button>
         <van-button size="small" type="primary"  @click="sendVerificationCode">发送验证码</van-button>
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
     <van-button
       round
       block
       type="primary"
       color="rgb(47,135,112)"
       native-type="submit"
       v-on:click="registerUser(user)"
     >
       注册
     </van-button>
   </div>

   <div>
     <div style="display: flex;justify-content: center;color: white">
       <router-link to="/login"><p style="color: white" >已经有账号去登录</p></router-link>
     </div>
   </div>
 </div>
  </div>
</template>

<script>
import {register, sendCode} from "@/api/login";
import {showNotify} from "vant";
import { showToast } from 'vant';
export default {

  // eslint-disable-next-line vue/multi-word-component-names
  name: "AddUser",
  data(){
    return {
      user:{
        username:"",
        userPassword:"",
        confirmPassword:"",
        userPhone:"",
        verificationCode:"",
        uuid:"",
      },
    }
  },

  methods:{
    //验证码
    sendVerificationCode(verificationCode) {
      // showToast({
      //   message: '验证码发送成功，请在30秒内填写',
      //   position: 'top',
      // });
      // 这里实现发送验证码的逻辑
      // 通常会调用另一个 API 方法并处理响应
      sendCode(this.user.verificationCode).then(res=>{
        if (res.data.code===200) {
          this.user.uuid=res.data.uuid;
          console.log(res.data.uuid);
          // 这里可以更新UI，显示“验证码已发送”等消息
        } else {
          console.error('验证码发送失败:');
          // 这里可以更新UI，显示错误信息
        }

      })
    },

    //注册
    registerUser(user){
      if (!this.user.userPassword===this.user.confirmPassword){
        showToast({
          message: '两次密码不一致',
          position: 'top',
        });
      }else{

      }
      register(this.user.username,this.user.userPassword,this.user.userPhone,this.user.uuid).then(res=>{
        if (res.data.code ===200){
          localStorage.setItem("shoppingInfo",JSON.stringify(res.data.data));
          //存储用户信息和token
          localStorage.setItem("userInfo",JSON.stringify(res.data.data));
          this.$router.push('/login');
        }else {
          showNotify({ type: 'danger', message: '注册失败，请检查您的网络连接或稍后再试' });
        }
      }).catch(error => {
          // 处理注册失败的逻辑
          showNotify({ type: 'danger', message: '注册时发生错误，请稍后再试' });
          // 可以显示错误信息给用户等
        });
    },

  },

}
</script>

<style scoped>
.nav_img{
  border-radius: 1%;
  height: 932px;
  background-image: url("@/assets/img/login.jpg");
  background-size: 141% 100%; /* 这将设置图像的宽度和高度 */
  background-position: center; /* 这将确保图像在容器中居中 */
  background-repeat: no-repeat; /* 这将确保图像不重复 */
}

.search-icon {
  font-size: 16px;
  line-height: inherit;
}
</style>
