<template>
  <van-nav-bar
    title="地址管理"
    left-text="返回"
    left-arrow
    @click-left="onClickLeft"
    class="fixed-navbar"
  />
  <div style="height: 3rem"></div>
  <template v-for="(address,index) in addressList">
    <van-swipe-cell>

    <div class="address-container">
      <!--    左侧单选按钮-->
      <div class="address-line">
        <input type="radio" :name="'address'+index" :value="0" v-model.number="address.addressSort" @change="selectAddress(index)">
      </div>
      <!--右侧-->
      <div class="address-management">
        <!-- 第一行：地址渲染 -->
        <div class="address-line">
          <p class="address-detail">{{ address.addressDetail }}</p>
        </div>
        <!-- 第三行：用户名、电话号码和地址标签渲染 -->
        <div class="address-line">
          <p>{{ address.addressName }}</p>
        </div>
        <div class="address-line">
          <p>{{ address.addressPhone }}</p>
        </div>
      </div>
    </div>
    <template #right>
      <van-button @click="clickBot(address.addressId)" style="width: 20px;height: 100%" icon="delete-o"  type="danger" class="delete-button" />
    </template>
  </van-swipe-cell>
  </template>
  <button @click="onAdd" class="add-address-button">新增地址</button>
  <div style="height: 4rem"></div>
</template>
<script>
import {ref} from 'vue';
import {showConfirmDialog, showNotify, showToast} from 'vant';
import router from "@/router";
import {addressList, editUsersAddress, removeUsersAddress} from "@/api/merchant";

const onClickLeft = () => history.back();
const onAdd = () => router.push({path: '/mine/addadres'});
export default {
  name: "AddressEdit",
  data() {
    return {
      onAdd,
      onClickLeft,
      usersPhone: "",
      addressList: [],
    }
  },
  created() {
    //获取地址列表
    this.getAddressList();
  },
  methods: {
    //获取地址列表
    getAddressList() {
      const userInfoString = localStorage.getItem("userInfo");
      // 将字符串解析回对象
      const userInfo = JSON.parse(userInfoString);
      // 现在可以访问对象中的属性了
      this.usersPhone = userInfo.usersPhone;
      addressList(this.usersPhone).then(res => {
        this.addressList = res.data.data;
        console.log(this.addressList)
      })
    },
    selectAddress(selectedIndex) {
      this.addressList.forEach((address, index) => {
        address.addressSort = (index === selectedIndex) ? 0 : 1;
      });
    },
    clickBot(coding){
      showConfirmDialog({
        message:
          '将这个地址删除',
      })
        .then(() => {
          removeUsersAddress(coding).then(res=>{
            if (res.data.code === 200) {
              showNotify({type: 'success', message: '地址删除成功'});
            } else {
              showNotify({type: 'danger', message: '地址删除失败'});
            }
            // 刷新页面
            location.reload();
          })
        })
        .catch(() => {
          // on cancel
        });
    },
  },

}

</script>
<style scoped>

.address-container {
  border-top: 1px solid #ccc; /* 上边框 */
  border-bottom: 1px solid #ccc; /* 下边框 */
  display: flex; /* 使用 flexbox 布局 */
  align-items: center; /* 垂直居中对齐 */
}

.fixed-navbar {
  position: fixed;
  top: 0;
  width: 100%;
  z-index: 1000; /* 可以根据需要设置 z-index */
}

.add-address-button {
  margin-bottom: 0px; /* 适当调整按钮与上方内容的间距 */
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
}

.address-management {
  padding: 10px;
  margin-bottom: 10px;
  width: 90%;
}

.address-line {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 5px 10px;
}

.address-detail {
  white-space: nowrap; /* 防止文本换行 */
  overflow: hidden; /* 隐藏溢出的内容 */
  text-overflow: ellipsis; /* 显示省略号 */
  max-width: 80%; /* 根据需要设置最大宽度 */
}

button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
