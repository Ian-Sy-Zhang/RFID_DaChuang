import axios from 'axios'
import { Message } from 'element-ui'

// 请求拦截
axios.interceptors.request.use(config => {
  // 1. 这个位置就请求前最后的配置
  // 2. 当然你也可以在这个位置 加入你的后端需要的用户授权信息
  return config
}, error => {
  Message.error({ message: '请求超时!' })
  return Promise.reject(error)
})

axios.interceptors.response.use(response => {
  // 请求成功
  // 1. 根据自己项目需求定制自己的拦截
  // 2. 然后返回数据
  return response
}, error => {
  // 请求失败
  if (error && error.response) {
    switch (error.response.status) {
      case 400:
        Message.error({ message: '400 error' })
        break
      case 401:
        Message.error({ message: '401 error' })
        break
      default:
        return Promise.reject(error)
    }
  }
})
