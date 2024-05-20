<template>
    <div class="contain">
        <div class="box">
            <h4>欢迎</h4>
            <input class="login hw" v-model="user.username"  type="text" value="登录">
            <input class="password hw" v-model="user.password"  type="text" value="密码">
            <div class="in hw" v-on:click="login">立即登录</div>
            <div class="other"><a href="#">忘记密码？丨</a><a href="#"> 立即注册</a></div>
        </div>
    </div>

</template>

<script>
    export default {
        // eslint-disable-next-line vue/multi-word-component-names
        name: "login",
        data(){
            return {
                user:{
                    username:"",
                    password:""
                }
            }
        },
        methods:{
            login(){
                // 1、校验用户名和密码是否合规
                // 2、登录服务器
                //登录完成后服务器给的登录凭证
                var token="";
                //将登录token 保存到服务器
                localStorage.setItem("token",token);
                // 3、完成页面跳转
                this.$router.push("/index/"+this.user.username);//不传参数
                // this.$router.push({name:"index",params:{username:this.user.username}})


                //query传参
                // this.$router.push({path:"/index",query:{username:this.user.username}})

            }
        }
    }
</script>

<style scoped>
    *{
        color: #ffffff;
        box-sizing: border-box;
    }
    .contain {
        height: 844px;
        width: 100%;
        background-image: linear-gradient(to right bottom, #6dcab7, #3979ca);
        display: flex;
        align-items: center;
    }

    .contain h4{
        margin: 0 auto;
        font-size: 45px;
    }
    .hw{
        width: 235px;
        height: 38px;
        margin-bottom: 15px;
        padding-left: 8px;
        color: #ffffff;
        font-size: 13px;
        line-height: 38px;
        border-radius: 18px;
        border: solid 1px #ffffff;
        background-color: transparent;
    }
    .box {
        margin: 0 auto;
        width: 235px;
    }
    .box .in{
        text-align: center;
        background-color: rgb(86, 179, 245);
    }
    .other{
        text-align: center;
    }
    .other a{
        text-decoration: none;
        font: normal 400 10px '微软雅黑';
    }
</style>