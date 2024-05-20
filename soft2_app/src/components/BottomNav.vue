<template>
    <div class="nav">
        <ul>
            <li>
                <router-link to="/index">
                    <p><span class="icon iconfont icon-home"></span></p>
                    <p>首页</p>
                </router-link>
            </li>
            <li>
                <router-link to="/car">
                    <p><span class="icon iconfont icon-cart"></span></p>
                    <p>购物车({{this.$store.getters.allGoodsNum}})</p>
                </router-link>
            </li>

            <li>
                <router-link to="/memberList">
                    <p><span class="icon iconfont icon-category"></span></p>
                    <p>会员管理</p>
                </router-link>
            </li>
            <li>
                <router-link to="/my">
                    <p><span class="icon iconfont icon-my"></span></p>
                    <p>我的</p>
                </router-link>
            </li>
        </ul>
    </div>

</template>

<script>
    export default {
        name: "BottomNav"
    }
</script>

<style scoped>
    .nav{
        width: 100%;
        height: 60px;
        /*将导航栏固定到底部*/
        position: fixed;
        bottom: 0;
        border-top: 1px solid #878787;
    }
    .nav ul{
        display: flex;
        height: 60px;
        justify-content: space-between;
        align-items: center;
    }
    .nav ul li{
        padding: 0 10px;
    }
    .nav ul li p span{
        font-size: 25px;
    }
    /*激活样式*/
    .router-link-active{
        color: #0381DD;
    }
    .active{
        color: #0381DD;

    }

</style>