<template>
<div class="body">
  <van-nav-bar
      title="个人中心"

  />
  <div class="image-wrapper">
    <div class="image-wrappers">
      <div style="padding-left: 12px;padding-top: 12px">
        <p>生鲜绿源</p>
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
      <div style="width: 100px" @click="ReturnPoMa"><p style="font-size: 10px">532</p>
        <p style="text-align:center;font-size: 12px;color: #808080">积分</p></div>
      <router-link to="/mine/history">
        <div style="width: 100px"><p style="font-size: 10px">32</p>
          <p style="text-align:center;font-size: 12px;color: #808080">历史记录</p>
        </div>
      </router-link>
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
      <p style="font-size: 13px;color: #808080">待评价</p>
    </div>
    </div>

    <van-cell  />
  </van-cell-group>
  <van-cell-group style="margin-top: 13px;margin-bottom: 13px"  inset>
    <van-cell />
    <div style="display: flex;justify-content: space-around;">
      <div style="display: flex; align-items: center;    flex-direction: column;">
      <van-icon size="30px" name="setting" color="#1989fa" v-on:click="ReturnSetting"/>
      <p style="font-size: 13px;color: #808080;flex-direction: column;">设置</p>
    </div>
     <router-link to="/mine/address">
       <div style="display: flex; align-items: center;    flex-direction: column;">
         <van-icon size="30px" name="location" color="#1989fa"/>
         <p style="font-size: 13px;color: #808080;flex-direction: column;">收货地址</p>
       </div>
     </router-link>
      <router-link to="/mine/">
        <div style="display: flex; align-items: center;    flex-direction: column;">
          <van-icon size="30px" name="phone-circle" color="#1989fa"/>
          <p style="font-size: 13px;color: #808080;flex-direction: column;">
            联系电话</p>
        </div>
      </router-link>
    </div>
    <van-cell />
  </van-cell-group>
  <van-action-sheet v-model:show="show" :actions="actions" @select="onSelect" />

  <van-cell-group style="margin-top: 13px;margin-bottom: 13px"  inset>
    <router-link to="/mine/UpdatePassword"><van-cell title="修改密码" is-link /></router-link>

  <van-cell  title="退出登录" is-link/>
  </van-cell-group>
    <van-divider
        :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
    >
      猜你喜欢
    </van-divider>
  <div style="display: flex;flex-wrap: wrap;">
      <MerchandiseInfo v-for="list in 10" v-on:click="Retrunshopping">

      </MerchandiseInfo>
  </div>

  <div v-for="goods in 9">
    <van-list
        v-model:loading="loading"
        :finished="finished"
        finished-text="没有数据了"
        @load="onLoad"
    >
      <!--        @load:满足加载条件时执行-->

      <template v-for="item in merList">
        <!-- 商家组件-->
        <MerchandiseInfo :merchantInfo="item"></MerchandiseInfo>
      </template>
    </van-list>

  </div>


</div>
</template>
<script>
    // 这里需要什么其他功能可以查询文档
    import { ref } from 'vue';
    import { showToast } from 'vant';
    import router from "@/router";
    import axios from "axios";
    import ShoppingCard from "@/components/card/ShoppingCard.vue";
    import NavTitle from "@/components/Navtitle/NavTitle.vue";
    import {listMembers} from "@/api/members";
    import MerchantList from "@/components/MerchantInfo.vue";
    import MerchantInfo from "@/components/MerchantInfo.vue";
    import MerchandiseInfo from "@/components/Index/MerchandiseIfon.vue";

    export default {
      name: "MyInfo",
      components: {MerchandiseInfo, MerchantInfo, NavTitle, ShoppingCard},
      data() {
        return{

          show,
          actions,
          onSelect,
          count,
          onRefresh,
          finished:false,//false还有数据  true 已经是最后一条数据了
          loading:false, //是否显示加载中
          merList:[
          ],
          queryParams: {
            pageNum: 1,
            pageSize: 5,
            name: null,
            phone: null,
            serveType: null,
            minimumCharge: null,
          },
        }
      },
      methods:{
        //加载名字

        ReturnPoMa(){
          router.push({ path: '/mine/poma' });
        },
        ReturnOrMa(){
          router.push({ path: '/mine/ordermanagement' });
        }
        ,
        ReturnSetting(){
          router.push({ path: '/mine/setting' });
        },
        Retrunshopping(){
          router.push({ path: '/cart/shoppinggement' });
        },
        onLoad(){
          // 开启loading
          this.loading = true;
          //加载商家列表的数据
          listMembers(this.queryParams).then(res=>{
            var rows = res.data.rows;
            if(rows){ //判断列表中是否有数据
              rows.forEach(data=>{ //循环将数据添加到 merList列表中
                this.merList.push(data);
              })
            }else{
              //接口中已经没有数据
              this.finished=true;
            }
            //当total的数值等于 merList的长度的时候标识数据加载完了
            if(res.data.total===this.merList.length){
              this.finished=true;
            }

            this.loading = false;
          }).catch(error=>{
            console.log(error)
          })
          this.queryParams.pageNum++;
        }
      },
      created() {

      }
    };
    // 导入插件样式返回

    const show = ref(false);
    const actions = [
      { name: '选项一' },
      { name: '选项二' },
      { name: '选项三' },
    ];
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
</style>