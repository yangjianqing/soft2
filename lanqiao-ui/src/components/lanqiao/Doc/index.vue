<template>
  <div>
    <svg-icon icon-class="question" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'lanqiaoDoc',
  data() {
    return {
      url: 'http://doc.www.lanqiao.cn/lanqiao-vue'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
