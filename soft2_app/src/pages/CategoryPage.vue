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
    <van-tree-select style="width: 100%;height: 100%;"
        v-model:main-active-index="activeIndex"
        :items="items"
    >
    <template #content>
        <div  v-if="activeIndex==0">
        <ShoppingCart></ShoppingCart>
        <ShoppingCart></ShoppingCart>
        <ShoppingCart></ShoppingCart>
        <ShoppingCart></ShoppingCart>
        <ShoppingCart></ShoppingCart>
        <ShoppingCart></ShoppingCart>
        <ShoppingCart></ShoppingCart>
        <ShoppingCart></ShoppingCart>
        <ShoppingCart></ShoppingCart>
        <ShoppingCart></ShoppingCart>
        <ShoppingCart></ShoppingCart>
        <ShoppingCart></ShoppingCart>
        <ShoppingCart></ShoppingCart>
        <ShoppingCart></ShoppingCart>
      </div>
      <div v-if="activeIndex==1">
        <ShoppingCart></ShoppingCart>
        <ShoppingCart></ShoppingCart>
        <ShoppingCart></ShoppingCart>

        <ShoppingCart></ShoppingCart>
      </div>
    </template>
  </van-tree-select>


  </div>


</div>
</template>

<script>

import { ref } from 'vue';
import ShoppingCart from "@/components/Index/ShoppingCart.vue";


export default {
  components: {
    ShoppingCart,
  },
  setup() {
    const activeIndex = ref(0);
    return {
      activeIndex,
      items: [{ text: '果蔬鲜花' }, { text: '肉禽视频' },{ text: '乳品烘培' }, { text: '粮油面点' },{ text: '家电百货' }, { text: '服装饰品' }],
    };
  },
};


</script>

<style scoped>


</style>
