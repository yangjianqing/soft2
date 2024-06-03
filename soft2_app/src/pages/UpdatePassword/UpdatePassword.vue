<template>
  <van-nav-bar
      title="修改密码"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
  />
  <div style="display: flex;flex-direction: column;
    align-items: center;
    justify-content: center;height: 200px">
  </div>

  <van-cell-group inset style="margin-bottom: 10px">
    <van-field
        v-model="user.password"
        clearable
        type="password"
        label="旧密码"
        left-icon="lock"
        style="background-color:rgb(214,245,255);height: 50px;display: flex;align-items: center;"
    />
  </van-cell-group>
  <van-cell-group inset style="margin-bottom: 10px">
    <van-field
        v-model="user.pass"
        clearable
        type="password"
        label="新密码"
        left-icon="lock"
        style="background-color:rgb(214,245,255);height: 50px;display: flex;align-items: center;"
    />
  </van-cell-group>
  <van-cell-group inset style="margin-bottom: 10px">
    <van-field
        v-model="user.newpassword"
        clearable
        type="password"
        label="确认新密码"
        left-icon="lock"
        style="background-color:rgb(214,245,255);height: 50px;display: flex;align-items: center;"
    />
  </van-cell-group>
  <div style="margin: 16px;">
    <!--    -->
    <van-button round block  type="primary" color="rgb(47,135,112)" native-type="submit">
      确认修改
    </van-button>
  </div>

</template>

<script>
const onClickLeft = () => history.back();
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "AddUser",
  data(){
    return {
      onClickLeft,
      user:{
        pass:"",
        password:"",
        newpassword:""
      }
    }
  },
  methods:{

  }
}
</script>

<style scoped>
.nav_img{
  border-radius: 3%;
  height: 83px;
}
.nav_img img{
  border-radius: 3%;
  height: 92px;
  width: 106px;
}

.search-icon {
  font-size: 16px;
  line-height: inherit;
}
</style>