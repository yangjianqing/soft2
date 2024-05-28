<template>
    <div>
      <!--二级路由显示区域-->
      <router-view/>
      <BottomNav></BottomNav>
    </div>
</template>

<script>
    import  BottomNav from "@/components/BottomNav";
    import { ref } from 'vue';
    import { showToast } from 'vant';

    export default {
        name: "Home",
        components: {BottomNav},

    }
</script>

<style scoped>
.nav_bar{
    display: flex;
    justify-content: space-around;
}

</style>