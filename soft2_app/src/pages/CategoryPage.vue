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
        @click-nav="queryCategoryInFo"
      >
        <template #content>
          <template v-for="goods in goodsList" :key="goods.id">
            <router-link :to="'/cart/shoppinggement/'+goods.id ">
            <shopping-cart  :Category="goods"></shopping-cart>
            </router-link>
          </template>
        </template>
      </van-tree-select>
    </div>
  </div>
</template>

<script>

import ShoppingCart from "@/components/Index/ShoppingCart.vue";
import {categoryList, goodsList2} from "@/api/merchant.js"

export default {
  components: {
    ShoppingCart,
  },
  data() {
    return {
      activeIndex:0,
      category:[],
      goodsList:"",
      items: [],
    };
  },
  created() {
    this.queryCategory();

  },
  methods: {
    queryCategory() {
      categoryList().then(res => {
        this.category = res.data.rows;
        this.category.forEach(e=>{
          this.items.push({ text: e.deptName,typeId: e.deptId})
        });
        // 分类数据加载完成后，加载默认分类的商品
        if (this.category.length > 0) {
          this.queryCategoryInFo(0);
        }
      });
    },
    queryCategoryInFo(e) {
      // 遍历每个 deptId 并调用 goodsList2
        goodsList2(this.category[e].deptId).then(res => {
          this.goodsList = res.data.deptId;
          console.log(this.goodsList)
        });
    },
  },

};
</script>
  <style scoped>

  </style>
