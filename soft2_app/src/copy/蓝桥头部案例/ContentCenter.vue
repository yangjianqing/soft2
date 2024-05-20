<template>
    <div class="center-warp">
        <h1>点赞数量 {{count}}</h1>
    </div>
</template>

<script>
    export default {
        name: "ContentCenter",
        props:["count"]
    }
</script>

<style scoped>
.center-warp{
    width: 1700px;
    height: 849px;
    background: #535353;
}
</style>