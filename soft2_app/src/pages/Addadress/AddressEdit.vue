<template>
  <div >

  <div style="margin-bottom: 46px">
    <van-nav-bar
      title="地址管理"
      left-text="返回"
      right-text="删除"
      left-arrow
      @click-left="onClickLeft"
      @click-right="clickBot(this.delId)"
      class="fixed-navbar"
    >
    </van-nav-bar>
  </div>

    <div style="height: 90vh;">

      <van-address-list
        v-model="delId"
        :list="addressList"
        default-tag-text="默认"
        @add="onAdd"
        @edit="showBottomBox(this.delId)"
        @select="select"
      />
    </div>
  </div>
</template>
<script>
import {showConfirmDialog, showNotify} from 'vant';
import router from "@/router";
import {addressList, removeUsersAddress} from "@/api/merchant";

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
      delId:0,
    }
  },
  created() {
    //获取地址列表
    this.getAddressList();

  },
  methods: {
    //弹出层
    showBottomBox(delId){
      // 使用 router.push 跳转并携带参数
      this.$router.push({ name: 'AddressInfo', params: { id: delId } });
   },
    //获取地址 id

    select(data){
      if (data === undefined && data.id === null ) {
        // 如果没有数据或数据中没有id，提示用户添加地址
        showNotify({type: 'warning', message: '请先添加地址'});
      } else {

        this.delId = data.id;
      }
    },
    //获取地址列表
    getAddressList() {
      const userInfoString = localStorage.getItem("userInfo");
      // 将字符串解析回对象
      const userInfo = JSON.parse(userInfoString);
      // 现在可以访问对象中的属性了
      this.usersPhone = userInfo.usersPhone;
      addressList(this.usersPhone).then(res => {
        console.log(res.data)
        // 转换地址列表
        const transformedList = res.data.data.map((item, index) => ({
          id: item.addressId,
          name: item.addressName,
          tel: item.addressPhone,
          address: item.addressDetail,
          isDefault: index === 0 // 如果是第一个元素，则 isDefault 为 true
        }));
        this.addressList = transformedList;
        if (this.addressList.length > 0) {
          this.delId = this.addressList[0].id;
        }else {
          showNotify({type: 'warning', message: '请添加地址'});
        }

      })
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
}
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
