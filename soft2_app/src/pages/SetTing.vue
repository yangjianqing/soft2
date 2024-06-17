<template>
  <van-nav-bar
      title="设置"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
  />
  <van-cell title="用户协议" is-link @click="showPopup" />
  <!-- 底部弹出 -->
  <van-popup
    v-model:show="show"
    position="bottom"
    :style="{ height: '80%' }"
  >
    <p>用户协议
      <br>
      一、前言
      <br>
      本用户协议（以下简称“本协议”）由[生鲜]（以下简称“本公司”）与用户（以下简称“您”或“用户”）就[服务名称]的使用所达成的法律协议。
      在使用[服务名称]之前，请您务必仔细阅读本协议的所有条款。如您不同意本协议中的任何条款，请立即停止使用[服务名称]。
      二、服务内容
      <br>
      本公司提供的服务包括但不限于：[具体服务内容1]，[具体服务内容2]，以及未来可能增加的其他服务。
      用户在使用本公司服务时，应遵守国家相关法律法规和本协议的规定。
      三、用户权利与义务
      <br>
      用户权利
      合法使用本公司服务的权利。
      享有协议约定的其他权利。
      用户义务
      提供真实、准确、完整的个人信息，并及时更新。
      对自己的账号和密码的保密负全部责任。
      不将本公司服务用于非法活动，不干扰他人正常使用。
      不发布、传播违法、违规或侵犯他人合法权益的内容。
      四、使用规则
      用户可以：
      根据本协议条款使用本公司服务。
      复制和备份本公司服务的相关内容，但仅限于个人使用，并不得用于商业目的。
      在符合法律法规和本协议规定的前提下，分享本公司服务的内容。
      用户不可以：
      对本公司服务进行逆向工程、反汇编、解体拆卸或任何试图发现源代码的行为。
      未经本公司书面许可，出售、租赁或以其他方式商业化使用本公司服务。
      发布、传播违法、违规或侵犯他人权益的内容，包括但不限于侵犯他人知识产权、隐私权、名誉权等。
      五、费用与支付
      <br>
      用户使用本公司收费服务时，需要按照本公司公布的费用标准和支付方式进行支付。
      用户有权了解本公司的收费方式和标准，并选择是否使用收费服务。
      六、安全与隐私
      <br>
      本公司将采取合理的技术和管理措施，保护用户的个人信息安全。
      用户应妥善保管个人信息，不得将个人信息泄露给第三方。
      <br>
      七、免责条款

      用户使用本公司服务时，应自行承担风险。本公司对因网络传输故障、硬件设备问题、不可抗力等原因导致服务中断、延迟或数据丢失等情况不承担责任。
      八、协议变更与终止
      <br>
      本公司有权根据业务需要和法律法规的变化，修改本协议。修改后的协议将通过适当的方式通知用户。
      如用户违反本协议规定，本公司有权单方面终止本协议，并停止向用户提供服务。
      九、法律适用与争议解决
      <br>
      本协议的订立、效力、解释、履行和争议解决均适用中华人民共和国法律。
      如双方就本协议发生争议，应首先通过友好协商解决；协商不成的，任何一方均有权向本公司所在地人民法院提起诉讼。
    </p>
  </van-popup>
  <van-cell title="隐私设置" is-link />
  <van-cell title="关于我们" is-link @click="showPopups" />
  <van-cell title="客服" is-link  />
  <van-popup
    v-model:show="shows"
    position="bottom"
    :style="{ height: '30%' }"
    style="display: flex;justify-content: center;align-items: center"
  >
    <p style="font-size: 30px;">我们用最好的页面和最好的功能给用户使用，如果有什么不好，还请原谅,您还可以写建议，我们还会按照您提的建议来修改</p>
  </van-popup>
  <van-cell title="其他设置" is-link />

</template>
<script>
import { ref } from 'vue';
const show = ref(false);
const showPopup = () => {
  show.value = true;
};
const shows = ref(false);
const showPopups = () => {
  shows.value = true;
};
export default {
  data(){
    return{
      onClickLeft,
      show,
      showPopup,
      showPopups,
      shows
    }
  },
  methods:{

  }
}
const onClickLeft = () => history.back();

</script>


<style scoped>
>>> .van-cell__title {
  text-align: left !important;
}
</style>
