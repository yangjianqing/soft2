<template>
  <h3>感谢你对本产品的使用</h3>
  <h3>记得给个五星好评哦！亲</h3>
  <van-rate v-model="value" />
</template>
<script>
import { ref } from 'vue';
const value = ref(3);
    export default {
      name:"AssessMent",
      data(){
        return { value };
      }
    }
</script>
<style scoped>

</style>