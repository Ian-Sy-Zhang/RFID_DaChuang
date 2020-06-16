import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: 'main'
  },
  {
    path: '/main',
    name: '',
    meta: {
      nav: '',
      activeItem: '1-1'
    },
    component: () => import('../views/Main'),
    children: [
      {
        path: 'device',
        component: () => import('../views/device/device/List.vue'),
        name: 'Device',
        meta: {
          nav: '设备列表',
          activeItem: '3-1'
        }
      }, {
        path: 'device/edit/:devId?',
        component: () => import('../views/device/device/Edit.vue'),
        name: 'DeviceEdit',
        meta: {
          nav: '设备编辑',
          activeItem: '3-1'
        }
      },
      {
        path: 'device/arg',
        name: 'DevArgList',
        component: () => import('../views/device/devArg/List.vue'),
        meta: {
          nav: '设备参数',
          activeItem: '3-3'
        }
      }

    ]
  }

]

const router = new VueRouter({
  routes
})

export default router
