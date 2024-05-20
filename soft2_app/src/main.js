import { createApp } from 'vue';
import App from './App.vue';
//导入 index.js导出的路由对象
import router  from "@/router";
// 导入状态管理器
import store from "@/store";
//导入axios配置文件
import request from "@/axios/request"


import "./assets/css/main.css";
import "./assets/iconfont/iconfont.css"
const  app =createApp(App);


app.config.globalProperties.$http = request //全局挂载 axios

//在vue中使用路由
app.use(router);
//在vue中使用状态管理器
app.use(store);
app.mount('#app');


