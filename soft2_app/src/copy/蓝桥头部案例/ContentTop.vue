<template>
  <div class="top-warp">
      <div class="left">
          <img :src="laoshi.schoolImg" alt="">
          <div class="leftTitle">
              <h2>大数据教学管理系统</h2>
              <p>{{laoshi.schoolName}}</p>
          </div>
          <div>
              <button v-on:click="dianzhang">点赞</button>
              <p>点赞{{count}}</p>
          </div>
      </div>
    <div class="right">
        <a href="">教师工作台</a>
        <img :src="laoshi.headImg" alt="">
        <p>{{laoshi.teacherName}} {{age}}岁</p>
    </div>

  </div>
</template>

<script>
    export default {
        name: "ContentTop",
        data(){
            return{
                teacher:{},
                count:0
            }
        },
        emits:["my-event"], //自定义一个事件名称
        methods:{
            dianzhang(){

                console.log("top组件中点击事件")
                this.count++;
                //将自定义事件暴露代用该组件的父组件
                this.$emit("my-event",this.count);
            }
        },
        props: {
            laoshi: Object,
            age: {
                type: String,
                required: true
            }
        },
    }
</script>

<style scoped>
    /*flex布局  样式位置 父盒子里*/
    .top-warp{
        width: 100%;
        height: 60px;
        border-top: 1px solid #EFEFEF;
        border-bottom:  1px solid #EFEFEF;
        display: flex;
        justify-content: space-between;
    }
    .top-warp img{
        width: 40px;
        height: 40px;
        border-radius: 50px;

    }
    .left{
        width: 250px;
        height: 60px;
        display: flex;
        justify-content: left;
        align-items: center;
        padding-left: 15px;

    }
    .leftTitle{
        padding-left: 20px;
    }
    .leftTitle h2{
        font-size: 14px;
    }
    .leftTitle p{
        font-size: 12px;
        color: #535353;
        text-align: left;
    }
    .right{
        width: 210px;
        height: 60px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding-right: 15px;
    }
    .right p{
        font-size: 12px;
        color: #535353;

    }
    .right a{
        text-decoration: none;
        color: #2D7AE6;

    }

</style>