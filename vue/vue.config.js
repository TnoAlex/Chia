const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
})
module.exports={
  lintOnSave:false,
  devServer:{
    proxy:{
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
