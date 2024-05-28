<template>
  <van-nav-bar
      title="设置"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
  />
  <van-cell title="支付设置" is-link />
  <van-cell title="隐私设置" is-link />
  <van-cell title="关于我们" is-link />
  <van-cell title="客服" is-link />
  <van-cell title="其他设置" is-link />

</template>
<script>
export default {
  data(){
    return{
      onClickLeft
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