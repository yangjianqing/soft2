
<template>

    <div>
      <van-nav-bar  left-text="返回" left-arrow>
        <template #title>
          <van-search style="height: 40px"
              v-model="value"
              shape="round"
              placeholder="请输入搜索关键词"
          />
        </template>
      </van-nav-bar>

    </div>



</template>

<script >
export default {
  setup() {
    const onClickLeft = () => history.back();
    return {
      onClickLeft,
    };
  },
};

</script>

<style scoped>



</style>