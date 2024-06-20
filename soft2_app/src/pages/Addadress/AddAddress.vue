<template>
  <van-nav-bar

    title="地址管理"
    left-text="返回"
    left-arrow
    @click-left="onClickLeft"
  />
  <van-address-edit
    :area-list="areaList"
    show-set-default
    show-search-result
    :search-result="searchResult"
    :area-columns-placeholder="['请选择', '请选择', '请选择']"
    @save="onSave"
  />
</template>
<script>
import {ref} from 'vue';
import {showNotify, showToast} from 'vant';
import {areaList} from "@vant/area-data";
import {addAddress, addressList, editUsersAddress, removeUsersAddress} from "@/api/merchant";

export default {
  data() {
    return {
      name: "AddAddress",
      onClickLeft,
      areaList,
      searchResult,
      address: [],
    }
  },
  created() {
    this.getAddress();
  },
  methods: {
    //保存/修改地址
    onSave(formData) {
      console.log('表单：', formData);
      const userInfoString = localStorage.getItem("userInfo");
      // 将字符串解析回对象
      const userInfo = JSON.parse(userInfoString);
      const address = {
        adderssUsersId: userInfo.usersId,
        addressName: formData.name,
        addressPhone: formData.tel,
        addressDetail: formData.province + formData.city + formData.county + formData.addressDetail,
        addressSort: formData.isDefault ? 0 : 1,
      }
        addAddress(address).then(res => {
          if (res.data.code === 200) {
            showNotify({type: 'success', message: '地址添加成功'});
            history.back();
          } else {
            showNotify({type: 'danger', message: '地址添加失败'});
          }
        })
    },
    getAddress() {
      const userInfoString = localStorage.getItem("userInfo");
      // 将字符串解析回对象
      const userInfo = JSON.parse(userInfoString);
      // 现在可以访问对象中的属性了
      this.usersPhone = userInfo.usersPhone;
      addressList(this.usersPhone).then(res => {
        this.address = res.data.data[0];
        console.log(this.address)
      })
    }
  }
}
const onClickLeft = () => history.back();


const searchResult = ref([]);

// const onSave = () => showToast('save');


</script>
<style scoped lang="scss">

</style>
