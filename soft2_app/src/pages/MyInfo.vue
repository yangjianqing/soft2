<template>
<div class="body">
  <van-nav-bar
      title="个人中心"
  />
  <div class="image-wrapper">
    <div class="image-wrappers">
      <div style="padding-left: 12px;padding-top: 12px">
        <p>{{ userInfo.usersName }}</p>
      </div>
      <van-image
          round
          width="3rem"
          height="3rem"
          position="contain"
          src="https://fastly.jsdelivr.net/npm/@vant/assets/cat.jpeg"
          event: MouseEvent
      />
    </div>
    <div class="management-box">
      <div style="width: 100px"><p style="font-size: 10px"></p>
        <p style="text-align:center;font-size: 12px;color: #808080">积分:{{ userInfo.memberTotal }}</p>
      </div>
      <p style="text-align:center;font-size: 12px;color: #808080" >会员等级:{{ userInfo.memberGrade?'高级会员':'普通会员' }}</p>
    </div>
  </div>


  <van-cell-group style="margin-top: 13px;margin-bottom: 13px"  inset>
    <van-cell v-on:click="ReturnOrMa" title="我的订单" value="查看全部" />
    <div style="display: flex; justify-content: space-around; align-items: center;padding-top: 10px">
      <div style="display: flex; flex-direction: column;" @click="ReturnOrMa">
      <van-icon  name="card" color="#1989fa" size="30px" to=""  />
        <p style="font-size: 13px; color: #808080">待付款</p>
      </div>
      <div style="display: flex; flex-direction: column;" @click="ReturnOrMa">
      <van-icon name="shopping-cart"  size="30px" color="#D8BFD8"/>
        <p style="font-size: 13px;color: #808080">待发货</p>
      </div>
      <div style="display: flex; flex-direction: column;" @click="ReturnOrMa">
      <van-icon name="cart" size="30px" color="#9932CC" />
      <p style="font-size: 13px;color: #808080">待收货</p>
    </div>
      <div style="display: flex; flex-direction: column;" @click="ReturnOrMa">
      <van-icon name="comment"  size="30px" color="#228B22"/>
      <p style="font-size: 13px;color: #808080">已完成</p>
    </div>
    </div>

    <van-cell  />
  </van-cell-group>
  <van-cell-group style="margin-top: 13px;margin-bottom: 13px"  inset>
    <van-cell />
    <div style="display: flex;justify-content: space-around; ">
      <div style="display: flex; align-items: center;  padding-top: 10px;  flex-direction: column;">
      <van-icon size="30px" name="setting" color="#1989fa" v-on:click="ReturnSetting"/>
      <p style="font-size: 13px;color: #808080;flex-direction: column;">设置</p>
    </div>
     <router-link to="/mine/address">
       <div style="display: flex; align-items: center;  padding-top: 10px;  flex-direction: column;">
         <van-icon size="30px" name="location" color="#1989fa"/>
         <p style="font-size: 13px;color: #808080;flex-direction: column;">收货地址</p>
       </div>
     </router-link>
      <router-link to="/mine/">
        <div style="display: flex; align-items: center; padding-top: 10px;   flex-direction: column;" @click="showPopup">
          <van-icon size="30px" name="phone-circle" color="#1989fa"/>
          <p style="font-size: 13px;color: #808080;flex-direction: column;">
            联系电话</p>
        </div>
      </router-link>
    </div>
    <van-cell />
  </van-cell-group>
  <van-action-sheet v-model:show="show" @select="onSelect" />
  <van-cell-group style="margin-top: 13px;margin-bottom: 13px"  inset>
    <van-cell title="消息通知" is-link  @click="InfoUser"/>
    <van-cell  title="退出登录" is-link  v-on:click="showConfirmDialog()"/>
  </van-cell-group>
    <van-divider
        :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
    >
      猜你喜欢
    </van-divider>
  <van-list
    v-model:loading="loading"
    :finished="finished"
    finished-text="没有数据了"
    @load="onLoad"
  >


   <template v-for="(goodsInfo,index) in goodsList">
     {{goodsList.id}}
     <router-link :to="'/cart/shoppinggement/' +goodsList.id ">
     <div class="total_box"  v-if="index%2===1" >
       <MerchandiseInfo :goodsInfo="goodsList[index-1]" v-on:click="ReturnOrShopping"></MerchandiseInfo>
       <MerchandiseInfo :goodsInfo="goodsList[index]" v-on:click="ReturnOrShopping"></MerchandiseInfo>
     </div>
     </router-link>
   </template>

<!-- </div>-->

  </van-list>
</div>
  <van-popup
    v-model:show="showBottom"
    position="bottom"
    round
    :style="{ height: '20%' }"
  >
    <div class="phone-call-popup">
      <p class="phone-number">客服电话:19950886351</p>
      <div @click="callPhone()" class="phone_call">
        <van-icon name="phone-o" color="#1dc779" size="20" style="margin: 10px 2px 10px 40vw" />
        <p style="margin: 10px 2px 10px 2px" >呼叫</p>
      </div>

      <!-- 取消按钮 -->
      <div  @click="cancel" style="margin: 10px 0px">取消</div>
    </div>
  </van-popup>


</template>
<script>
    // 这里需要什么其他功能可以查询文档
    import { ref } from 'vue';
    import {showConfirmDialog, showToast} from 'vant';
    import router from "@/router";
    import ShoppingCard from "@/components/card/ShoppingCard.vue";
    import NavTitle from "@/components/Navtitle/NavTitle.vue";
    import {listShopping} from "@/api/merchant.js"
    import MerchantInfo from "@/components/MerchantInfo.vue";
    import MerchandiseInfo from "@/components/Index/MerchandiseIfon.vue";

    export default {
      name: "MyInfo",
      components: {MerchandiseInfo, MerchantInfo, NavTitle, ShoppingCard},
      data() {
        const showBottom = ref(false);
        const showPopup = () => {
          showBottom.value = true;
        };
        const cancel  = () => {
          showBottom.value = false;
        };
        return{
          location: null,
          show,
          onSelect,
          cancel,
          count,
          onRefresh,
          showPopup,
          showBottom,
          goodsList:[],
          finished:false,//false还有数据  true 已经是最后一条数据了
          loading:false, //是否显示加载中
          merList:[],
          queryParams: {
            pageNum: 1,
            pageSize: 5,
            name: null,
            phone: null,
            serveType: null,
            minimumCharge: null,
            userInfo:"",
          },

        }
      },
      methods:{
        InfoUser(){
          showConfirmDialog({
            title: '关闭消息通知吗？',
            message:
              '确定要关闭消息通知',
          })
            .then(() => {

              // on confirm
            })
            .catch(() => {
              showConfirmDialog({
                title: '开启消息通知吗？',
                message:
                  '开启要关闭消息通知',
              }).then(() => {

              })
                .catch(() => {
                  // on cancel
                });
              // on cancel
            });
        },
        callPhone(){
          window.location.href = 'tel://19950886351'
        },
        showConfirmDialog(){
          showConfirmDialog({
            title: '退出登录',
            message:
              '确认要退出登录吗？',
          })
            .then(() => {
              // on confirm
                // 清除本地存储的用户数据（如JWT令牌）
                localStorage.removeItem('userToken');
                // 重定向到登录页面
                window.location.href = 'http://localhost:8082/#/login';
            })
            .catch(() => {
              // on cancel
            });
        },
        //加载名字
        ReturnPoMa(){
          router.push({ path: '/mine/poma' });
        },
        ReturnOrMa(){
          router.push({ path: '/mine/ordermanagement' });
        },
        ReturnOrShopping(){
          router.push({ path: '/cart/shoppinggement/' });
        }
        ,
        ReturnSetting(){
          router.push({ path: '/mine/setting' });
        },
        //退出登录
        getGoodsList(){
          listShopping().then(res=>{
            console.log(res.data.rows);
            this.goodsList=res.data.rows;
          })
        },
      },
      created() {
        //获取商品列表
        this.getGoodsList();
        //根据电话查询个人信息
        //根据电话查询个人信息
        this.userInfo=JSON.parse(localStorage.getItem("userInfo"))
      },
    };
    // 导入插件样式返回
    const show = ref(false);
    const onSelect = (item) => {
      // 默认情况下点击选项时不会自动收起
      // 可以通过 close-on-click-action 属性开启自动收起
      show.value = false;
      showToast(item.name);
    };
    const count = ref(0);
    const loading = ref(false);
    const onRefresh = () => {
      setTimeout(() => {
        showToast('刷新成功');
        loading.value = false;
        count.value++;
      }, 1000);
    };
</script>
<style scoped>
.body {
  margin: 0;
  height: 100%;
  font-family: var(--van-base-font);
  background-color: #F0F0F0;
}
>>> .van-cell__title {
  text-align: left !important;
}

.image-wrapper {
  display: flex;
  flex-direction: column;
  padding: 16px;
  justify-content: flex-end;
  align-items: center;
  background-color: rgb(214, 245, 255);
}
.image-wrappers{
  display: flex;
  flex-direction: row-reverse;
  width: 100%;
  justify-content: flex-end;
}
.management-box{
  display: flex;
  width: 100%;
  justify-content: space-between;
  padding-top: 11px;
}
.goods-card__title {
  order: 5; /* 将标题移至最后 */
}
.goods-card__price {
  order: 3; /* 将价格移至上方 */
}
.goods-card__desc {
  order: 0; /* 将描述保持在中间 */
}
.sousaphone{
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 30px;
  border-radius: 30px;
}

.total_box{
  width: 100%;
  height: 100%;
  display: flex;
  background-color:rgb(245,245,245) ;
  border-radius: 10px;
}


.phone-call-popup {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.phone-number {
  width: 100%;
  height: 25px;
  margin: 10px 0px;
  border-bottom: 1px solid #eeeeee;
  padding-bottom: 8px;
}
.phone_call{
  width: 100%;
  height: 30px;
  display: flex;
  align-items: flex-end;
  border-bottom: 1px solid #eeeeee;
}
</style>
