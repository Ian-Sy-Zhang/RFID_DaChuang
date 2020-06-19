import axios from 'axios'
import { Message } from 'element-ui'

export default {
  /**
   * 封装get方法
   * @param url
   * @param params
   * @returns {Promise}
   */
  get (url, params = {}) {
    return new Promise((resolve, reject) => {
      axios.get(url, {
        params: params
      })
        .then(response => {
          if (response.data.success === true) {
            // 返回成功处理  这里传的啥 后续调用的时候 res就是啥
            resolve(response.data.content)// 我们后台所有数据都是放在返回的data里所以这里统一处理了
          } else {
            // 错误处理
            Message('error! ' + response.data)
          }
        })
        .catch(err => {
          reject(err)
          let message = '请求失败！请检查网络'
          // 错误返回
          if (err.response)message = err.response.data.message
          Message(message)
        })
    })
  },

  /**
   * 封装post请求
   * @param url
   * @param data
   * @returns {Promise}
   */

  post (url, data = {}) {
    return new Promise((resolve, reject) => {
      axios.post(url, data)
        .then(response => {
          if (response.data.success === true) {
            console.log(response)
            resolve(response.data.content)
          } else {
            Message(response.data.msg)
          }
        }, err => {
          reject(err)
          let message = '请求失败！请检查网络'
          if (err.response)message = err.response.data.message
          Message(message)
        })
    })
  },

  /**
   * 封装patch请求
   * @param url
   * @param data
   * @returns {Promise}
   */

  patch (url, data = {}) {
    return new Promise((resolve, reject) => {
      axios.patch(url, data)
        .then(response => {
          if (response.data.code === 200) {
            resolve(response.data.data)
          } else {
            Message(response.data.msg)
          }
        }, err => {
          reject(err)
          let message = '请求失败！请检查网络'
          if (err.response)message = err.response.data.message
          Message(message)
        })
    })
  },

  /**
   * 封装put请求
   * @param url
   * @param data
   * @returns {Promise}
   */
  put (url, data = {}) {
    return new Promise((resolve, reject) => {
      axios.put(url, data)
        .then(response => {
          if (response.data.code === 200) {
            resolve(response.data.data)
          } else {
            Message(response.data.msg)
          }
        }, err => {
          reject(err)
          let message = '请求失败！请检查网络'
          if (err.response)message = err.response.data.message
          Message(message)
        })
    })
  },

  del (url, data = {}) {
    return new Promise((resolve, reject) => {
      axios.delete(url, data)
        .then(response => {
          if (response.data.code === 200) {
            resolve(response.data.data)
          } else {
            Message(response.data.msg)
          }
        }, err => {
          reject(err)
          let message = '请求失败！请检查网络'
          if (err.response)message = err.response.data.message
          Message(message)
        })
    })
  }

}
