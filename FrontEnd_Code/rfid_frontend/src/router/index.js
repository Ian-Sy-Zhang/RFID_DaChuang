import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/main',
    name: '',
    meta: {
      nav: '用户管理',
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
      // {
      //   path: 'device/type',
      //   component: () => import('../views/device/devType/List.vue'),
      //   name: 'DevTypeList',
      //   meta: {
      //     nav: '设备类别',
      //     activeItem: '3-2'
      //   }
      // },
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
