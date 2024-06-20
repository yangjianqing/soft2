

<template>
  <div>支付成</div>
</template>

<style scoped>

</style>
<script>
export default {
  name: "PaySuccess",
  created() {
    this.$route.query;
    console.log(this.$route.query)
    console.log(this.$route.query.out_trade_no)
    // 根据 out_trade_no 修改订单状态


  }
}
</script>
