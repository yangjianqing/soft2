<template>
    <div>
        <h1>商品列表</h1>
        <GoodsList v-on:addGoodsToCar="addGoodsToCar" :goodsList="goodsList"></GoodsList>
        <h1>购物车</h1>
        <CarList @add="addNum"  @cat="catNum" :goodsList="carList" class="carList"></CarList>
        <div class="total">
            <p>总价:</p>
            <p>{{total}}元</p>
        </div>
    </div>
</template>
<script>
import GoodsList from "@/components/GoodsList";
import CarList from "@/components/CarList";
export default {
      name: 'App',
    data(){
          return {
              goodsList:[
                  {id:0,num:1,name:"荣耀8X",price:1399,imgUrl:"https://labfile.oss.aliyuncs.com/courses/5428/8x.jpg"},
                  {id:1,num:1,name:"荣耀10GT",price:2199,imgUrl:"https://labfile.oss.aliyuncs.com/courses/5428/honor10.jpg"},
                  {id:2,num:1,name:"HUAWEI nova4",price:3399,imgUrl:"https://labfile.oss.aliyuncs.com/courses/5428/nova4.jpg"},
                  {id:3,num:1,name:"荣耀Magic2",price:3799,imgUrl:"https://labfile.oss.aliyuncs.com/courses/5428/honor2.jpg"},
                  {id:4,num:1,name:"荣耀10",price:1399,imgUrl:"https://labfile.oss.aliyuncs.com/courses/5428/young.jpg"},
              ],
              carList:[]
          }
        },
      components: {
          CarList,
          GoodsList
      },
    methods:{
          //增加购物车商品
      addGoodsToCar(goods){
          let addNum=0;
          //加入的商品如果已经在购物列表中存在 则商品数量加一
            for(var i=0;i<this.carList.length;i++){
                if(goods.id===this.carList[i].id){
                    this.carList[i].num++;
                    addNum=1;
                    break;
                }
            }
        if(addNum===0){
            //将子组件中传递出来的商品 添加到购物车列表
            goods.num=1;
            this.carList.push(goods)
        }
      },
        //加商品数量
        addNum(goods){
            this.addGoodsToCar(goods);
        },
        //减商品数量
        catNum(goods){
            //当购物车商品的数量变成0的时候要将该商品移除
            for(var i=0;i<this.carList.length;i++){
                if(goods.id===this.carList[i].id){ //找到要操作的商品 商品数量减1
                    this.carList[i].num--; //商品数量减1
                    if(this.carList[i].num===0){  // 如果商品数量==0 移除商品
                        this.carList.splice(i,1);//根据下标删除商品
                    }
                    break;
                }
            }
        }
    },
    //使用计算属性 统计购物车的总和
    computed:{
        total(){
            //计算购物车的总结
            let total=0;
            for(var i=0;i<this.carList.length;i++){
                total+= this.carList[i].num*this.carList[i].price;
            }
            return total;
        }
    }
}

</script>
<style>
    #app {
      font-family: Avenir, Helvetica, Arial, sans-serif;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      text-align: center;
      color: #2c3e50;
        height: 100%;
    }
    *{
        margin: 0;
        padding: 0;
    }
    .total{
        width: 100%;
        height: 60px;
        background: #aaaaaa;
        /*固定定位 距离底部0px*/
        position: fixed;
        bottom: 0;
        display: flex;
        justify-content:space-around;
    }
    .carList{
        margin-bottom: 60px;
    }
    .total p{
        height: 60px;
        line-height: 60px;
    }
</style>
