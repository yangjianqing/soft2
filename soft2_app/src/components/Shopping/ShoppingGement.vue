<template>
  <van-nav-bar
      title="商品名称"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
  />
  <van-swipe :autoplay="3000" lazy-render>
    <van-swipe-item v-for="image in images" :key="image">
      <img :src="image" class="custom-image-size"/>
    </van-swipe-item>
  </van-swipe>
  <van-cell-group>
    <van-cell title="东台8424西瓜 1个装（单果3kg起）" value="皮薄肉厚 汁水充盈" label="盒马量贩·基地优选·售后无忧" />
  </van-cell-group>
  <div style="display: flex;align-items: flex-end;">
    <h3 style="color: red;">￥38</h3>
    <p style="padding-left: 8px;
    padding-right: 8px;">/箱</p>
    <p style="color:gray;font-size: 10px"><del>￥46</del></p>
  </div>
  <van-cell class="list-src" color="#A6C9E2" title="商家24H发货，第三方物流配送，免运费" icon="guide-o" />
  <van-cell class="list-ts" color="#A6C9E2"  title="不支持7天无理由退换货" icon="clock-o" />
    <!-- 使用 right-icon 插槽来自定义右侧图标 -->
  <van-cell class="list-src"  title="已选：1个/箱" is-link url="https://github.com" />
  <van-divider
      :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
  >
    猜你喜欢
  </van-divider>
  <van-card
      num="2"
      tag="标签"
      price="2.00"
      desc="这是一个非常好的信息页面，让人非常高西违法我去二分请问父亲文革确认过热交易日热轧十大v的啊份的撒旦撒饿啊分为啊额我发安慰"
      title="商品这网上而我却扶额我去枪毙u"
      thumb="https://fastly.jsdelivr.net/npm/@vant/assets/ipad.jpeg"
      origin-price="10.00"
      v-for="list in 2"

  />
  <van-action-bar>
    <van-action-bar-icon icon="chat-o" text="客服" @click="onClickIcon" />
    <van-action-bar-icon icon="cart-o" text="购物车" @click="onClickIcon" />
    <van-action-bar-icon icon="shop-o" text="店铺" @click="onClickIcon" />
    <van-action-bar-button type="danger" text="立即购买" @click="onClickButton" />
  </van-action-bar>
</template>
<script>
import { showToast } from 'vant';
const onClickLeft = () => history.back();
const onClickIcon = () => showToast('点击图标');
const onClickButton = () => showToast('点击按钮');
export default {
  data(){
    return{
      onClickLeft,
      images,
      onClickIcon,
      onClickButton,
    }
  }
}
const images = [
  'https://fastly.jsdelivr.net/npm/@vant/assets/apple-1.jpeg',
  'https://fastly.jsdelivr.net/npm/@vant/assets/apple-2.jpeg',
];

</script>
<style scoped>
.my-swipe .van-swipe-item {
  color: #fff;
  font-size: 20px;
  line-height: 150px;
  text-align: center;
  background-color: #39a9ed;
}
.custom-image-size {
  width: 100%; /* 设置图片宽度 */
  height: auto; /* 保持图片宽高比 */
}
.list-src{
  font-size: 0.9rem;
  background-color: rgb(242, 242, 242);;

  border-radius: 7px;
}
.list-ts{
  font-size: 0.9rem;
  color: #A6C9E2;
}

</style>