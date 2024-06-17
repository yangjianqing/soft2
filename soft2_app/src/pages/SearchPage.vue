<template>
  <div>
    <van-nav-bar left-text="返回" left-arrow @click-left="goBack">
      <template #title>
        <van-search style="height: 30px"
                    v-model="value"
                    shape="round"
                    placeholder="请输入搜索关键词"
                    @search="onSearch"
        />
      </template>
    </van-nav-bar>
    <div style="background-color: #FFFFFF">
      <div class="history_div">
        <div class="history_font"> 搜索历史</div>
        <van-icon name="delete-o" @click="clearHistory" />
      </div>
      <div style="display: flex; flex-wrap: wrap; padding: 0% 4%">
        <div v-for="(item, index) in displayedHistory" :key="index" class="history_box" @click="searchFromHistory(item)">
          {{ item }}
        </div>
      </div>
      <div v-if="showToggleButton" class="toggle_button" @click="toggleHistory">
        <span>{{ historyExpanded ? '隐藏' : '查看更多' }}</span>
      </div>
      <div class="find_div">
        <div class="find_font"> 搜索发现</div>
        <van-icon name="replay" />
      </div>
      <div style="display: flex; padding: 0% 4%">
        <div class="find_box">
          海鲜
        </div>
      </div>
    </div>
    <BottomNav />
  </div>
</template>

<script>
import BottomNav from "@/components/BottomNav.vue";

export default {
  components: { BottomNav },
  data() {
    return {
      value: '',
      searchHistory: [], // 定义搜索历史数组
      historyExpanded: false // 控制历史记录展开状态
    };
  },
  created() {
    this.loadHistory(); // 组件加载时获取搜索历史
  },
  computed: {
    displayedHistory() {
      return this.historyExpanded ? this.searchHistory : this.searchHistory.slice(0, 3); // 一行3个
    },
    showToggleButton() {
      return this.searchHistory.length > 3;
    }
  },
  methods: {
    goBack() {
      // 返回上一菜单的逻辑，可以根据您的路由设置进行相应调整
      this.$router.go(-1); // 如果使用了 Vue Router
    },
    onSearch() {
      if (this.value && !this.searchHistory.includes(this.value)) {
        this.searchHistory.unshift(this.value); // 将新搜索关键词添加到数组开头
        this.saveHistory();
      }

      // 跳转到搜索结果页面，并将搜索关键词作为查询参数传递
      this.$router.push({
        path: '/index/ResultShopping',
        query: { keyword: this.value }
      });
    },
    saveHistory() {
      localStorage.setItem('searchHistory', JSON.stringify(this.searchHistory)); // 将搜索历史保存到本地存储
    },
    loadHistory() {
      const history = localStorage.getItem('searchHistory');
      if (history) {
        this.searchHistory = JSON.parse(history); // 从本地存储加载搜索历史
      }
    },
    clearHistory() {
      this.searchHistory = []; // 清空搜索历史数组
      localStorage.removeItem('searchHistory'); // 移除本地存储中的搜索历史
    },
    searchFromHistory(keyword) {
      this.value = keyword; // 将搜索关键词赋值给输入框
      this.onSearch(); // 执行搜索逻辑
    },
    toggleHistory() {
      this.historyExpanded = !this.historyExpanded; // 切换历史记录展开状态
    }
  }
};
</script>

<style scoped>
.history_div {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 0% 5% 1% 4.5%;
  height: 30px;
}
.history_font {
  font-size: 14px;
  font-weight: bold;
}
.history_box {
  width: 75px;
  height: 25px;
  white-space: nowrap; /* 不换行 */
  overflow: hidden; /* 隐藏溢出部分 */
  text-overflow: ellipsis; /* 用省略号表示被截断部分 */
  background: rgb(245, 245, 245);
  border-radius: 15px;
  text-align: center;
  margin-right: 6%;
  margin-bottom: 10px;
  padding-top: 4px;
  padding-left:4px;
}
.toggle_button {
  width: 100%;
  text-align: center;
  margin-top: 10px;
}
.find_div {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 4.5% 5% 1% 4.5%;
  height: 30px;
}
.find_font {
  font-size: 14px;
  font-weight: bold;
}
.find_box {
  width: 75px;
  height: 25px;
  background: rgb(245, 245, 245);
  border-radius: 15px;
  align-items: center;
  margin-right: 6%;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
