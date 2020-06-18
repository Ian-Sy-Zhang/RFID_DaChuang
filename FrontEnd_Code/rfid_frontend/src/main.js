import Vue from 'vue'
import App from './App.vue'
import router from './router'
import api from './http/api'
import http from './http/http'
import './http/axios'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.config.productionTip = false
Vue.use(ElementUI)
// 对后端接口 进行全局注册
Vue.prototype.$api = api
// 对请求方式 进行全局注册
Vue.prototype.$http = http

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
