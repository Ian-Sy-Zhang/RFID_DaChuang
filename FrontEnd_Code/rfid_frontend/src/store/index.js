import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
export const deviceList = state => state.deviceList

export default new Vuex.Store({
  state: {
    deviceList: [],
    haveDeviceList: false
  },
  mutations: {
    devicePush (state, element) {
      state.deviceList.push(element)
    },
    getList (state) {
      state.haveDeviceList = true
    },
    reGetList (state) {
      state.haveDeviceList = false
      state.deviceList = []
    },
    removeDevice (state, deviceIndex) {
      delete state.deviceList[deviceIndex]
    },
    freshDeviceList (state, deviceList) {
      state.deviceList = deviceList
    }
  },
  actions: {
  },
  modules: {
  },
  getters: {
    deviceList (state) {
      return state.deviceList
    }
  }
})
