<template>
  <van-nav-bar
      title="地址管理"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
  />
  <van-address-list
      v-model="chosenAddressId"
      :list="list"

      @add="onAdd"
      @edit="onEdit"
  />
</template>
<script>
import { ref } from 'vue';
import { showToast } from 'vant';
import router from "@/router";
const chosenAddressId = ref('1');
const list = [
  {
    id: '1',
    name: '张三',
    tel: '13000000000',
    address: '浙江省杭州市西湖区文三路 138 号东方通信大厦 7 楼 501 室',
    isDefault: true,
  },
  {
    id: '2',
    name: '李四',
    tel: '1310000000',
    address: '浙江省杭州市拱墅区莫干山路 50 号',
  },
];
const disabledList = [
  {
    id: '3',
    name: '王五',
    tel: '1320000000',
    address: '浙江省杭州市滨江区江南大道 15 号',
  },
];
const onClickLeft = () => history.back();
const onAdd = () => router.push({ path: '/mine/addadres' });
const onEdit = (item, index) => showToast('编辑地址:' + index);
export default {
  name:"AddressEdit",
  data(){
    return{
      list,
      onAdd,
      onEdit,
      disabledList,
      chosenAddressId,
      onClickLeft
    }
  },
  methods:{

  }

}

</script>
<style scoped>

</style>