<template>
    <div>
        <ul>
           <template v-for="goods in goodsList" :key="goods.id">
               <GoodsInfo :goods="goods">
                   <button v-on:click="addToCar(goods)">加入购物车</button>
               </GoodsInfo>
           </template>
        </ul>
    </div>
</template>

<script>
    import GoodsInfo from "@/components/GoodsInfo";
    export default {
        name: "GoodsList",
        components: {GoodsInfo},
        props:["goodsList"],
        emits:["addGoodsToCar"],
        methods:{
            addToCar(goods){
                //获取到点击事件对应的商品
                console.log("子组件获取到商品"+goods)
                //将商品传递到父组件中
                this.$emit("addGoodsToCar",goods)
            }
        }
    }
</script>

<style scoped>
    body, p, ul, li {
        margin:0;
        padding:0;
    }
    ul, li {
        list-style-type:none;
    }

    #app{
        width: 600px;
        margin: 0 auto;
        text-align: center;
    }
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
    }


    .cart-item, .item-control{
        display: flex;
        flex-direction: row;
    }
    .cart-item{
        border:solid 1px #aaa;
        padding: 5px;
    }

    .cart-item div{
        flex: 1;
        height: 30px;
        line-height: 30px;
    }
    .cart-item div img{
        width: 30px;
        height: 30px;
    }
    .cart-item button{
        height: 30px;
        width: 50px;
    }
    .item-control h4{
        width: 60px;
        margin-top: 0px;
    }

    .order-page{
        text-align: left;
    }
    .btn{
        width: 120px;
        height: 30px;
        margin-top: 10px;
    }

    .order-item{
        padding: 10px;
        border-bottom:solid 1px #aaa;
    }
</style>