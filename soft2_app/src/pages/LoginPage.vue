<template>
<!--  <div class="nav_img">-->
<!--    <img src="@/assets/img/img.png" alt="">-->
<!--  </div>-->

  <div style="">
     <div class="nav_img">
       <div style="height: 374px;
    display: flex;
    align-items: flex-end;
    justify-content: center;">
         <img class="image" src="@/assets/img/img.png" alt="">
       </div>
       <div class="introverted_picture">
         <p style="font-size: 28px;color: white">生鲜绿源水果蔬菜超市</p>
         <div class="nav_bottom">
           <van-cell-group inset style="margin-bottom: 10px;margin-top: 30px;">
             <van-field
               v-model="form.usersPhone"
               name="电话号码"
               placeholder="电话号码"
               left-icon="phone-circle"
               style="height: 50px;display: flex;align-items: center;"
             />

           </van-cell-group>
           <van-cell-group inset style="margin-bottom: 10px">
             <van-field
               v-model="form.usersPassword"
               clearable
               type="password"
               placeholder="密码"
               left-icon="lock"
               style="height: 50px;display: flex;align-items: center;"
             />
           </van-cell-group>
           <div style="display: flex;justify-content: space-around;
    align-items: center;">
            <!--             v-model="checked"-->
             <van-radio-group   shape="square">
               <van-radio><p  style="color: #afafaf">我同意平台的条款和条列</p></van-radio>
             </van-radio-group>
           </div>
           <div style="margin: 16px;">
             <!--    -->
             <van-button round block  type="primary" color="rgb(47,135,112)" native-type="submit" @click="submitForm(form)">
               登录
             </van-button>
             <router-link to="/adduser">
             <van-button
               plain
               hairline
               round
               block
               type="success"
               color="rgb(47,135,112)"
               native-type="submit"
               style="margin-top: 30px">
               注册
             </van-button>
             </router-link>
           </div>

         </div>
       </div>
  </div>
  </div>

</template>

<script>
import {login} from "@/api/login";
import addUser from "@/pages/AddUser/AddUser.vue";
import {showNotify} from "vant";
const TOKEN_KEY = "X-Token";
export default {
        // eslint-disable-next-line vue/multi-word-component-names
        name: "login",
        computed: {
        },
        data(){
            return {
              form: {
                usersPhone: "",
                usersPassword: ""
              },
        }
        },
        methods:{
          submitForm(form){
            // 假设 login 函数返回一个 Promise
            login(form.usersPhone, form.usersPassword).then(res => {
              // 检查登录是否成功（这取决于您的后端API返回的数据结构）
              if (res.data.code ===200) {
                localStorage.setItem("shoppingInfo",JSON.stringify(res.data.data));
                //存储用户信息和token
                localStorage.setItem("userInfo",JSON.stringify(res.data.data));
                this.$router.push('/index');
              } else {
                showNotify({ type: 'danger', message: '登录失败，请检查您的网络连接或稍后再试' });
              }
            }).catch(error => {
              // 处理网络错误或其他异常
              showNotify({ type: 'danger', message: '登录时发生错误，请稍后再试' });
            });
          },
        }
}
</script>

<style scoped>
.nav_img{
  border-radius: 1%;
  height: 932px;
  background-image: url("../assets/img/login.jpg");
  background-size: 125% 100%; /* 这将设置图像的宽度和高度 */
  background-position: center; /* 这将确保图像在容器中居中 */
  background-repeat: no-repeat; /* 这将确保图像不重复 */
}

.introverted_picture{

}
.search-icon {
  font-size: 16px;
  line-height: inherit;
}
.nav_bottom{
  border-radius: 3%;
}
.image{
  width: 50%;
  height: 56%;
}
</style>
