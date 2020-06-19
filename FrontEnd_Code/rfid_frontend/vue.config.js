module.exports = {
  lintOnSave: false,
  outputDir: process.env.outputDir,
  devServer: {
    // 所有的接口请求代理
    proxy: {
      '/api': {
        target: 'http://localhost:8090/',
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  },
  // 设置路径
  assetsDir: 'static'
}
