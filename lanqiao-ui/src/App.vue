<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default  {
  name:  'App',
    metaInfo() {
        return {
            title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
            titleTemplate: title => {
                return title ? `${title} - ${process.env.VUE_APP_TITLE}` : process.env.VUE_APP_TITLE
            }
        }
    },


  data() {
    return {
      // qrcodeInfo: JSON.parse(localStorage.getItem('qrcodeInfo')),
      socket: '',
      uId: 0,
    };
  },
  components: {},
  methods: {
    initWebSocket(uid) {
      const websocket = new WebSocket(`ws://127.0.0.1:8089/api/webSocket/${uid}`);
      websocket.onopen = (event) => {
        console.log(this.qrcodeInfo.uid);
        console.log(uid);
        console.log('建立连接');
        websocket.send('Hello WebSockets!');
      };
      websocket.onclose = function (event) {
        console.log('连接关闭');
        // this.reconnect();
        //尝试重连websocket
      }.bind(this); // 将 this 绑定到回调函数中
      //建立通信后，监听到后端的数据传递
      websocket.onmessage = (event) => {
        let data = event.data;
        // 业务处理....
        console.log(data);
        router.replace('/' + data);
      };
      websocket.onerror = function () {
        console.warn('websocket通信发生错误！');
        this.initWebSocket();
      }.bind(this); // 将 this 绑定到回调函数中
      window.onbeforeunload = () => {
        websocket.close();
      };
      this.socket = websocket;
    },
    reconnect() {
      console.log("正在重连");
      // 进行重连
      setTimeout(() => {
        this.initWebSocket()
      }, 1000);
    }
  },
  watch: {
    'qrcodeInfo.uid': function (newUid, oldUid) {
      if (newUid !== oldUid) {
        this.initWebSocket();
      } else {
        // 停止 initWebSocket() 方法
        if (this.socket) {
          this.socket.close();
        }
      }
    },
  },
  created() {
    this.initWebSocket = this.initWebSocket(1234);
  },
}
</script>
