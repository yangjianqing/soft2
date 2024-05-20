<template>
    <li class="phone-item">
        <div><img :src="goods.imgUrl"/> </div>
        <div>{{goods.name}} </div>
        <div>{{goods.price}} </div>
        <div>
            <!--  占位符-->
            <slot>
<!--                默认信息-->
                <span>插槽区域未输入内容</span>
            </slot>
        </div>
    </li>
</template>

<script>
    export default {
        name: "GoodsInfo",
        //接收使用者传入的商品信息
        props:["goods"]
    }
</script>

<style scoped>
    .phone-item{
        display: flex;
        flex-direction: row;
        border:solid 1px #aaa;
        padding: 5px;
    }
    .phone-item div img{
        width: 100px;
    }
    .phone-item div{
        flex: 1;
        height: 100px;
        line-height: 100px;
    }
    .phone-item button{
        height: 30px;
        margin-top: 35px;
        width: 35px;
    }

</style>