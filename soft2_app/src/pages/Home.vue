<template>
    <div>
<!--        二级路由显示区域-->
        <router-view></router-view>
        <BottomNav></BottomNav>
    </div>
</template>

<script>
    import BottomNav from "@/components/BottomNav";
    export default {
        name: "Home",
        components: {BottomNav},
        // mounted() {
        //     //发起请求
        //     this.$http.get("/member?pageNum=1&pageSize=10").then((res)=>{
        //         //获取请求中的值
        //         console.log(res.data);
        //     }).catch((error)=>{
        //         console.log(error)
        //     })
        // }
    }
</script>

<style scoped>

</style>