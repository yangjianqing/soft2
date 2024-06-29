<template>
  <div style="height: 100vh;">
    <van-nav-bar
      title="我的地址"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />
    <van-address-edit
      :area-list="areaList"
      show-set-default
      show-search-result
      :address-info="addressInfo"
      :area-columns-placeholder="['请选择', '请选择', '请选择']"
      @save="onSave"
    />
  </div>
</template>
<script>
import {showNotify, showToast} from 'vant';
import {areaList} from "@vant/area-data";
import { editUsersAddress, selectUsersAddress} from "@/api/merchant";
import router from "@/router";

export default {
  data() {
    return {
      name: "AddressInfo",
      onClickLeft,
      areaList,
      addressInfo:{},
      address: [],
    }
  },
  created() {
    this.selectAddressInfo();
  },
  methods: {
    selectAddressInfo(){
      selectUsersAddress(this.$route.params.id).then(res=>{
        console.log(res.data.data)
        const addressData = res.data.data;
        this.addressInfo = {
          name: addressData.addressName,
          tel: addressData.addressPhone,
          addressDetail: addressData.addressDetails,
          province: addressData.province,
          city: addressData.city,
          county: addressData.county,
        };

      })

    },
    //保存/修改地址
    onSave(formData) {
      console.log('表单：', formData);
      const userInfoString = localStorage.getItem("userInfo");
      // 将字符串解析回对象
      const userInfo = JSON.parse(userInfoString);
      const address = {
        addressId:this.$route.params.id,
        adderssUsersId: userInfo.usersId,
        addressName: formData.name,
        addressPhone: formData.tel,
        addressDetail: formData.province + formData.city+ formData.county + formData.addressDetail,
      }
      editUsersAddress(address).then(res => {
        if (res.data.code==200){
          router.push("/mine/address");
          showToast('加入成功');
        }
      })

    },

  }
}
const onClickLeft = () => history.back();




</script>
<style scoped lang="scss">

</style>
