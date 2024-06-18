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
              v-model="form.verificationCode"
              clearable
              left-icon="chat"
              placeholder="请输入验证码"
              style="height: 50px;display: flex;align-items: center;"
            >
              <template #button>
                <van-button size="small" type="primary" :disabled="isButtonDisabled" @click="sendVerificationCode">
                  {{ buttonText }}
                </van-button>
              </template>
            </van-field>
          </van-cell-group>
          <!--           <van-cell-group inset style="margin-bottom: 10px">-->
          <!--             <van-field-->
          <!--               v-model="form.usersPassword"-->
          <!--               clearable-->
          <!--               type="password"-->
          <!--               placeholder="密码"-->
          <!--               left-icon="lock"-->
          <!--               style="height: 50px;display: flex;align-items: center;"-->
          <!--             />-->
          <!--           </van-cell-group>-->
          <div style="display: flex;justify-content: space-around;
    align-items: center;">
            <!--             v-model="checked"-->
            <van-radio-group shape="square">
              <van-radio><p style="color: #afafaf">我同意平台的条款和条列</p></van-radio>
            </van-radio-group>
          </div>
          <div style="margin: 16px;">
            <!--    -->
            <van-button round block type="primary" color="rgb(47,135,112)" native-type="submit"
                        @click="submitForm(form)">
              登录
            </van-button>
            <!--             <router-link to="/adduser">-->
            <!--             <van-button-->
            <!--               plain-->
            <!--               hairline-->
            <!--               round-->
            <!--               block-->
            <!--               type="success"-->
            <!--               color="rgb(47,135,112)"-->
            <!--               native-type="submit"-->
            <!--               style="margin-top: 30px">-->
            <!--               注册-->
            <!--             </van-button>-->
            <!--             </router-link>-->
          </div>

        </div>
      </div>
    </div>
  </div>

</template>

<script>
import {login, sendCode} from "@/api/login";
import addUser from "@/pages/AddUser/AddUser.vue";
import {showNotify} from "vant";

const TOKEN_KEY = "X-Token";
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "login",
  computed: {},
  data() {
    return {
      form: {
        usersPhone: "",
        verificationCode: "",
        uuid: "",
      },
      isButtonDisabled: false, // 标志按钮是否禁用
      buttonText: '发送验证码', // 按钮文本
      timer: null, // 定时器引用
    }
  },
  methods: {
    submitForm(form) {
      console.log(this.form.uuid)
      const user = {
        usersPhone: form.usersPhone,
        code: form.verificationCode,
        uuid: this.form.uuid
      };
      // 假设 login 函数返回一个 Promise
      login(user).then(res => {
        console.log(res.data.code)
        console.log(res.data)
        // 检查登录是否成功（这取决于您的后端API返回的数据结构）
        if (res.data.code === 200) {
          localStorage.setItem("shoppingInfo", JSON.stringify(res.data.data));
          //存储用户信息和token
          localStorage.setItem("userInfo", JSON.stringify(res.data.data));
          this.$router.push('/index');
        } else {
          showNotify({type: 'danger', message: '登录失败，请检查您的网络连接或稍后再试'});
        }
      }).catch(error => {
        // 处理网络错误或其他异常
        showNotify({type: 'danger', message: '登录时发生错误，请稍后再试'});
      });
    },
    //正则校验手机号码
    validatePhoneNumber() {
      const phoneNumber = this.form.usersPhone.trim();
      // 使用正则表达式验证手机号码
      const regex = /^(0\d{2,3}-\d{7,8}|1[34578]\d{9})$/;
      return regex.test(phoneNumber);
    },
    //验证码
    sendVerificationCode(verificationCode) {
      //发送验证码前校验手机号
      if (this.validatePhoneNumber()) {
        // showToast({
        //   message: '验证码发送成功，请在30秒内填写',
        //   position: 'top',
        // });
        // 这里实现发送验证码的逻辑
        // 通常会调用另一个 API 方法并处理响应
        sendCode(this.form.verificationCode).then(res => {
          if (res.data.code === 200) {
            this.form.uuid = res.data.uuid;
            // 这里可以更新UI，显示“验证码已发送”等消息
            showNotify({type: 'success', message: '验证码发送成功'});
            this.startCountdown();
          } else {
            console.error('验证码发送失败:');
            // 这里可以更新UI，显示错误信息
            showNotify({type: 'danger', message: '验证码发送失败'});
          }
        })
      }else {
        showNotify({type: 'danger', message: '请输入正确的手机号'});
      }
    },
    //验证码重发计时器
    startCountdown() {
      this.isButtonDisabled = true;
      let countdown = 60;
      this.buttonText = `${countdown}秒后重新发送验证码`;

      this.timer = setInterval(() => {
        countdown--;
        if (countdown > 0) {
          this.buttonText = `${countdown}秒后重新发送验证码`;
        } else {
          this.buttonText = '发送验证码';
          this.isButtonDisabled = false;
          clearInterval(this.timer);
        }
      }, 1000);
    },
    beforeDestroy() {
      // 清除定时器以防内存泄漏
      if (this.timer) {
        clearInterval(this.timer);
      }
    }
  }
}
</script>

<style scoped>
.nav_img {
  border-radius: 1%;
  height: 932px;
  background-image: url("../assets/img/login.jpg");
  background-size: 125% 100%; /* 这将设置图像的宽度和高度 */
  background-position: center; /* 这将确保图像在容器中居中 */
  background-repeat: no-repeat; /* 这将确保图像不重复 */
}

.introverted_picture {

}

.search-icon {
  font-size: 16px;
  line-height: inherit;
}

.nav_bottom {
  border-radius: 3%;
}

.image {
  width: 50%;
  height: 56%;
}
</style>
