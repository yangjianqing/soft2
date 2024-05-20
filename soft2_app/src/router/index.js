// 1. 从依赖包中导入路由函数 createRouter 和 createWebHashHistory。
import { createRouter, createWebHashHistory } from "vue-router";

// 2. 从其他文件导入路由组件。

//一次性全部加载
// import HomePage from "@/pages/HomePage";
// import MyInfo from "@/pages/MyInfo";
// import CarPage from "@/pages/CarPage";

//懒加载
const HomePage = () => import("@/pages/HomePage")
const MyInfo = () => import("@/pages/MyInfo")
const CarPage = () => import("@/pages/CarPage")
const Login = () => import("@/pages/Login")
const Home = () => import("@/pages/Home")
const MemberList=()=>import("@/pages/MemberList")

// 3. 定义一些路由。
// 每个路由都需要映射到一个组件。
const routes = [
    //一级路由
    {
        path:"/",
        component:Home,
        //二级路由
        children: [
            // 空嵌套路由
            {
                path:"/index",
                component:HomePage,
                name:"index",
                meta: {
                    title: "首页",
                },
            },
            {
                path:"/my",
                component:MyInfo,
                meta: {
                    title: "个人中心",
                },
            },
            {
                path:"/car",
                component:CarPage,
                meta: {
                    title: "购物车",
                },
            },
            {
                path: "/memberList",
                component:MemberList,
                meta:
                    {
                        title:"会员管理"
                    }
            }
        ]
    },
    {
        path:"/login",
        component:Login,
        name:"login",
        meta: {
            title: "登录",
        },
    },

];

// 4. 使用 createRouter 方法创建一个路由实例 router
const router = createRouter({
    // 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
    history: createWebHashHistory(),
    // linkActiveClass: "active", //配置激活路由样式
    routes, // `routes: routes` 的缩写
});

//路由导航前置守卫：在路由跳转之前完成某些操作
router.beforeEach((to,form,next)=>{

    let totken = localStorage.getItem("token");
    // 如果进入页面时没有登录标识  则 跳转到登录页 如果有登录标识则放行
    if((totken!=null && totken!=undefined && totken!="") || to.path=="/login"){  //如果进入的页面时登录页面叶放行
        next();
    }else{
        // 跳转到登录页
     // alert("你没有登录")
        next({name:"login"})
    }
});

router.afterEach((to, from)=>{
    console.log(to)
                    //标题
  document.title=  to.meta.title;
})





// 5. 导出路由对象。
export default router;