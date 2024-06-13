
<template>

  <div>
    <van-nav-bar  left-text="返回" left-arrow @click-left="goBack">
      <template #title>
        <van-search style="height: 30px"
                    v-model="value"
                    shape="round"
                    placeholder="请输入搜索关键词"
        />
      </template>
    </van-nav-bar>
  </div>
  <div style="background-color: #FFFFFF">
    <div class="history_div">
      <div class="history_font"> 搜索历史</div>
      <van-icon name="delete-o" />
    </div>
    <div style="display: flex ;padding:0% 4% ">
      <div class="history_box">
        海鲜
      </div>
      <div class="history_box">
        海鲜
      </div>
      <div class="history_box">
        海鲜
      </div>

    </div>
    <div class="find_div">
      <div class="find_font"> 搜索发现</div>
      <van-icon name="replay" />
    </div >
    <div style="display: flex ;padding:0% 4% ">
      <div class="find_box">
        海鲜
      </div>
    </div>
  </div>




</template>

<script >
import BottomNav from "@/components/BottomNav.vue";

export default {
  components: {BottomNav},
  setup() {
    return {
    };
  },
  methods: {
    goBack() {
      // 返回上一菜单的逻辑，可以根据您的路由设置进行相应调整
      this.$router.go(-1); // 如果使用了 Vue Router
    }
  }
};

</script>

<style scoped>
.history_div{
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin:0% 5% 1% 4.5%;
  height: 30px;
}
.history_font{
  font-size: 14px;
  font-weight: bold;
}
.history_box{
  width: 75px;
  height: 25px;
  background: rgb(245,245,245);
  border-radius: 15px;
  text-align: center;
  margin-right: 6%;
}
.find_div{
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin:4.5% 5% 1% 4.5%;
  height: 30px;
}
.find_font{
  font-size: 14px;
  font-weight: bold;
}
.find_box{
  width: 75px;
  height: 25px;
  background: rgb(245,245,245);
  border-radius: 15px;
  align-items: center;
  margin-right: 6%;
}
</style>

