/**
 * Created by MingzhengGu on 2020/6/17.
 * 封装的网络请求模块
 */

import axios from 'axios'

export function request (config) {
  const instance = axios.create({
    baseURL: 'http://localhost:8090/',
    timeout: 60000
  })
  return instance(config)
}
