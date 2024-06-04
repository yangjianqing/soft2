const { defineConfig } = require('@vue/cli-service')
const port = process.env.port || process.env.npm_config_port || 8082 // 端口

module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  // webpack-dev-server 相关配置
  devServer: {
    host: '0.0.0.0',
    port: port,
    open: true,
    proxy: {
      // detail: https://cli.vuejs.org/config/#devserver-proxy
      [process.env.VUE_APP_BASE_API]: {
        target: `http://localhost:8089/`,
        changeOrigin: true,
        pathRewrite: {
          ['^' + process.env.VUE_APP_BASE_API]: ''
        }
      }
    }
    // disableHostCheck:true
  },
})
