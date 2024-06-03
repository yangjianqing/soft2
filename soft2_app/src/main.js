import { createApp } from 'vue';
import App from './App.vue';
//导入 index.js导出的路由对象
import router  from "@/router";
// 导入状态管理器
import store from "@/store";
//导入axios配置文件
import request from "@/axios/request"
import { AddressEdit } from 'vant';
//1.引入vant
import Vant  from 'vant';
//引入FloatingPanel 浮动面板
import { FloatingPanel } from 'vant'
// 2. 引入组件样式
import 'vant/lib/index.css';

import "./assets/css/main.css";
import "./assets/iconfont/iconfont.css"


const  app =createApp(App);


app.config.globalProperties.$http = request //全局挂载 axios
//在vue中使用路由
app.use(router);
//在vue中使用状态管理器
app.use(store);

// 方式一. 通过 app.use 注册
app.use(Vant);
app.mount('#app');
//地址管理器
app.use(AddressEdit);
//引入FloatingPanel 浮动面板
app.use(FloatingPanel);