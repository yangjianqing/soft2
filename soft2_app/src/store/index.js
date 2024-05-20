import { createStore } from "vuex"; // 引入 createStore

//存储共享数据
const state = function () {
    return {
        carList:[]
    };
};
//修改共享数据
const mutations = {
    //加商品数量
    addNum(state,goods){
        //判断加入购物车的商品是否已存在
        if(!state.carList.includes(goods)){
            // 在商品对象中添加一个 num属性用于记录商品的数量
            goods.num=1;
            state.carList.push(goods);
        }else{
            goods.num++;
        }
        return state;
    },

    //减商品数量
    catNum(state,goods){
        //当购物车商品的数量变成0的时候要将该商品移除
        goods.num--; //商品数量减1
        if(goods.num===0){
            //删除数组中的对象
            for(var i=0;i<state.carList.length;i++){
                if(state.carList[i].id===goods.id){
                    state.carList.splice(i,1);
                }
            }
        }
        return state;
    }
};

//当共享数据需要进行计算的时候使用
const getters = {
    //计算购物车列表商品的数量
    allGoodsNum(state){
        let temp=0
        // state.carList.forEach(element => {
        //     temp+= element.num;
        // });
        for(var i=0;i<state.carList.length;i++){
            temp+=state.carList[i].num;
        }

        return temp;
    },
};

const actions = {};




//创建 store对象
const store = createStore({
    state,
    getters,
    mutations,
    actions,
});
// 导出stored对象
export default store;