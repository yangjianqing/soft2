<template>
  <div>
    <van-sticky>
      <div>
        <van-search
          v-model="value"
          shape="round"
          placeholder="请输入搜索关键词"
        />
      </div>
    </van-sticky>
    <div style="width: 100%;height: 85vh;">
      <van-tree-select
        style="width: 100%;height: 100%;"
        v-model:main-active-index="activeIndex"
        :items="items"
      >
        <template #content v-for="(category, index) in Category" :key="index">
          <div v-if="activeIndex === index">
            <template v-for="(categoryInFo, index) in CategoryInFo">
              <ShoppingCart :Category="categoryInFo"/>
            </template>
          </div>
        </template>
      </van-tree-select>
    </div>
  </div>
</template>

<script>

import {ref} from 'vue';
import ShoppingCart from "@/components/Index/ShoppingCart.vue";
import {categoryList, goodsList2} from "@/api/merchant.js"

export default {
  components: {
    ShoppingCart,
  },
  data() {
    return {
      Category:[],
      CategoryInFo:[],

    };
  },
  setup() {
    const activeIndex = ref(0);
    return {
      activeIndex,
      items: [],
    };
  },
  created() {
    this.queryCategory();
  },
  methods: {
    queryCategory() {
      categoryList().then(res => {
        this.Category = res.data.rows;
        this.deptNames = res.data.rows.map(category => category.deptName);
        // 创建新的 items 数组，使用 this.deptNames 的元素
        this.items = this.deptNames.map(deptName => ({ text: deptName }));
        //获取大分类的对应商品的id
        this.deptIds = res.data.rows.map(category => category.deptId);
        // 在这里调用 queryCategoryInFo 方法并传递所有大分类的 deptId
        this.queryCategoryInFo(this.deptIds);
      });
    },
    queryCategoryInFo(deptIds) {
      // 遍历每个 deptId 并调用 goodsList2
      deptIds.forEach(deptId => {
        goodsList2(deptId).then(res => {
          console.log( res.data.deptId);
          this.CategoryInFo = res.data.deptId;
        });
      });
    }
  }
};
</script>
  <style scoped>

  </style>
