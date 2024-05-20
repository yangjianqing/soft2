<template>
    <div>
        <h1>购物车列表</h1>
        <ul>
           <template v-for="goods in goodsList" :key="goods.id">
               <GoodsInfo :goods="goods">
                   <div class="item-control">
                       <button @click="catNum(goods)">-</button>
                       <h4>{{goods.num}}</h4>
                       <button @click="addNum(goods)">+</button>
                   </div>
               </GoodsInfo>
           </template>
        </ul>
    </div>
</template>

<script>
    // 1、在子组件中定义两个自定义事件  add   cat
    // 2、将 要操作的数据的id 从子组件传递到父组件中
    // 3、在父组件中完成 购物车数量的修改


    import GoodsInfo from "@/components/GoodsInfo";
    export default {
        name: "CarList",
        components: {GoodsInfo},
        props:["goodsList"],
        methods:{
            addNum(goods){
                this.$store.commit("addNum",goods);
            },
            catNum(goods){
                this.$store.commit("catNum",goods);

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
        width: 35px;
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