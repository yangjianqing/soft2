// 1. 从依赖包中导入路由函数 createRouter 和 createWebHashHistory。
import { createRouter, createWebHashHistory } from "vue-router";
import loginPage from "@/pages/LoginPage.vue";

// 2. 从其他文件导入路由组件。

//一次性全部加载
// import HomePage from "@/pages/HomePage";
// import MyInfo from "@/pages/MyInfo";
// import CarPage from "@/pages/CarPage";

//懒加载

const CategoryPage = () => import("@/pages/CategoryPage.vue")
const CartPage = () => import("@/pages/CartPage.vue")
const LoginPage = () => import("@/pages/LoginPage.vue")
const Home = () => import("@/pages/HomePage.vue")
const MinePage=()=>import("@/pages/MinePage.vue")
const IndexPage = () => import("@/pages/IndexPage.vue")

// 3. 定义一些路由。
// 每个路由都需要映射到一个组件。
const routes = [
    //一级路由
    {
        path:"/",
        component:Home ,
        //二级路由
        children: [
            // 空嵌套路由
            {
                path:"/index",
                component:IndexPage,
                name:"index",
                meta: {
                    title: "首页",
                },
            },
            {
                path:"/classification",
                component:CategoryPage,
                meta: {
                    title: "分类",
                },
            },
            {
                path:"/cart",
                component:CartPage,
                meta: {
                    title: "购物车",
                },
            },
            {
                path: "/mine",
                component:MinePage,
                meta:
                    {
                        title:"我的"
                    }
            }
        ]
    },
    {
        path:"/login",
        component:LoginPage,
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