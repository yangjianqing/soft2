<template>
    <div>
      <van-nav-bar title="标题" left-text="返回" left-arrow>
        <template #right>
          <van-icon name="search" size="18" />
        </template>
      </van-nav-bar>
        <GoodsList :goodsList="goodsList"></GoodsList>
   </div>
</template>

<script>
    import GoodsList from "@/components/GoodsList";
    export default {
        name: "HomePage",
        components: {GoodsList},
        data(){
            return {
                goodsList:[
                    {id:0,num:1,name:"荣耀8X",price:1399,imgUrl:"https://labfile.oss.aliyuncs.com/courses/5428/8x.jpg"},
                    {id:1,num:1,name:"荣耀10GT",price:2199,imgUrl:"https://labfile.oss.aliyuncs.com/courses/5428/honor10.jpg"},
                    {id:2,num:1,name:"nova4",price:3399,imgUrl:"https://labfile.oss.aliyuncs.com/courses/5428/nova4.jpg"},
                    {id:3,num:1,name:"Magic2",price:3799,imgUrl:"https://labfile.oss.aliyuncs.com/courses/5428/honor2.jpg"},
                    {id:4,num:1,name:"荣耀10",price:1399,imgUrl:"https://labfile.oss.aliyuncs.com/courses/5428/young.jpg"},
                ],
            }
        },
        methods:{
            updateNum(){
              //调用vuex中的setNum方法
                this.$store.commit("setNum",10);
            }
        }
    }
</script>

<style scoped>

</style>