
<template>
  <div class="custom-cell">
    <van-cell class="payinfo" is-link size="large">
      <!-- 这里只显示地址或其他内容 -->
      <div class="address-content">{{ labels.tel }}</div>
      <div class="address-content">{{ labels.address }}</div>

    </van-cell>
  </div>
  <OrderInfo style="margin-top: 10px">

  </OrderInfo>
  <van-cell title="单元格" class="payinfo" icon="alipay" color="blue" is-link>
    <template #title>
      <span class="custom-title">支付宝</span>
      <van-tag type="primary">推荐</van-tag>
    </template>
  </van-cell>
  <van-cell title="单元格" class="payinfo" icon="wechat"  color="agree" is-link>
    <template #title>
      <span class="custom-title">微信</span>
    </template>
  </van-cell>

  <van-submit-bar :price="3050" button-text="提交订单" @submit="onSubmit" />
</template>


<script>

import OrderInfo from "@/components/OrderInfo.vue";
import {showToast} from "vant";
const onSubmit = () => showToast('点击按钮');
export default {
  name:"PayInfo",
  components: {OrderInfo},
  data(){
    return{
      onSubmit,
      labels: {
        id: '1',
        name: '张三',
        tel: '13000000000',
        address: '浙江省杭州市西湖区文三路 138 号东方通信大厦 7 楼 501 室',
        isDefault: true,
      },
    }
  },
  methods:{

  }
}

</script>


<style scoped>
.custom-cell {
  margin-top: 10px;
  display: flex;
  align-items: center; /* 垂直居中 */
}

.left-label {
  /* 左侧标签的样式，可以根据需要调整 */
  flex: 0 0 auto; /* 不伸缩 */
  margin-right: 10px; /* 右侧间距 */
  font-weight: bold; /* 可选：加粗 */
}

.address-content {
  /* 地址内容的样式，可以根据需要调整 */

display: flex;
}

.payinfo {
  /* 如果需要，可以覆盖 van-cell 的默认样式 */
}
</style>
