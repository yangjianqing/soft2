
<template>
  <van-nav-bar

      title="地址管理"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
  />
  <van-address-edit
      :area-list="areaList"
      show-delete
      show-set-default
      show-search-result
      :search-result="searchResult"
      :area-columns-placeholder="['请选择', '请选择', '请选择']"
      @save="onSave"
      @delete="onDelete"
      @change-detail="onChangeDetail"
  />
</template>
<script>
import { ref } from 'vue';
import { showToast } from 'vant';
import {areaList} from "@vant/area-data";
export default {
  data(){
    return{
      name:"AddAddress",
      onClickLeft,
      onSave,
      onDelete,
      areaList,
      searchResult,
      onChangeDetail,
    }
  }
}
const onClickLeft = () => history.back();


const searchResult = ref([]);

const onSave = () => showToast('save');
const onDelete = () => showToast('delete');
const onChangeDetail = (val) => {
  if (val) {
    searchResult.value = [
      {
        name: '黄龙万科中心',
        address: '杭州市西湖区',
      },
    ];
  } else {
    searchResult.value = [];
  }
};

</script>
<style scoped lang="scss">

</style>