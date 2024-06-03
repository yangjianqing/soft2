<template>
  <div>
    <van-nav-bar
        title="历史记录"
        left-text="返回"
        left-arrow
        @click-left="onClickLeft"
    />
    <van-cell-group v-for="list in 3">
      <div class="info">
<!--        <p>{{list.name}}</p>-->
<!--        <p>{{list.value}}</p>-->
      </div>
    </van-cell-group>
  </div>
</template>

<script>
  export default {
    name:"HistoryInfo",
    data(){
      return {
        list: {
          name: "在2024年6月3号，办理了会员",
          value: "+3",
        },
        onClickLeft,
      };
    }
  }
</script>



<style scoped>
  .info{
    display: flex;

  }
</style>