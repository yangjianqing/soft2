<template>
  <div>
    <van-nav-bar  left-text="返回" left-arrow @click-left="goBack">
      <template #title>
        <div>超划算</div>
      </template>
    </van-nav-bar>
  </div>


</template>


<script >
export default {
  methods: {
    goBack() {
      // 返回上一菜单的逻辑，可以根据您的路由设置进行相应调整
      this.$router.go(-1); // 如果使用了 Vue Router
    }
  }
}


</script>

<style scoped>

</style>
