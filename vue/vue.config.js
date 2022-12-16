const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  publicPath: './',
  outputDir: 'dist',
  assetsDir: "./static",
  indexPath: './index.html',
  transpileDependencies: true,
})
module.exports={
  lintOnSave:false,
  devServer: {
    open: false,//open 在devServer启动且第一次构建完成时，自动用我们的系统的默认浏览器去打开要开发的网页
    host: '0.0.0.0',//默认是 localhost。如果你希望服务器外部可访问，指定如下 host: '0.0.0.0'，设置之后之后可以访问ip地址
    port: 8080,
    hot: true,//hot配置是否启用模块的热替换功能，devServer的默认行为是在发现源代码被变更后，通过自动刷新整个页面来做到事实预览，开启hot后，将在不刷新整个页面的情况下通过新模块替换老模块来做到实时预览。
    https: false,
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:8086/',
        ws: true,
        changeOrigin: true,
        secure: false,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  },
  pwa: {
    iconPaths: {
      favicon32: 'favicon32.ico',
      favicon16: 'favicon32.ico',
      appleTouchIcon: 'apple-touch-icon.ico',
      maskIcon: 'favicon32.ico',
      msTileImage: 'favicon32.ico'
    }
  }
}
