<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-header style="text-align: right; font-size: 12px">
      <el-dropdown>
        <span class="el-dropdown-link">
          <i class="el-icon-user" style="margin-right: 8px;color: #fff;"></i>
        </span>
      </el-dropdown>
    </el-header>
    <el-container>
      <el-aside width="200px" style="background-color: rgb(238, 241, 246);height:100%;">
        <el-menu :router="true" :default-active="defaultActive" :unique-opened="true">
          <el-submenu index="1">
            <template slot="title">
              <i class="iconfont icon-setting"></i>系统设置</template>
            <el-menu-item index="1-1" route="/main/user">用户管理</el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="iconfont icon-equipment-set"></i>设备管理</template>
            <el-menu-item index="3-1" route="/main/device">设备列表</el-menu-item>
            <el-menu-item index="3-3" route="/main/device/arg">设备参数</el-menu-item>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title">
              <i class="iconfont icon-equipment-set"></i>规则引擎</template>
            <el-menu-item index="4-1" route="/main/ruleEngine/data">数据转发</el-menu-item>
            <el-menu-item index="4-2" route="/main/ruleEngine/equipment">设备联动</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <el-container>
          <el-header style="height:10px;" class="navBar">
            <el-breadcrumb separator="/">
              <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item>{{currentNav}}</el-breadcrumb-item>
            </el-breadcrumb>
          </el-header>
          <el-main>
            <router-view />
          </el-main>
        </el-container>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: 'Main',
  data () {
    return {
      currentNav: '',
      userInfo: {},
      centerDialogVisible: false,
      defaultActive: ''
    }
  },
  methods: {
  },
  computed: {
  },
  mounted () {
    this.currentNav = this.$route.meta.nav
    this.defaultActive = this.$route.meta.activeItem
    console.log(this.currentNav)
  }
}
</script>

<style>
.el-header {
  background-color: #00cc99;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
  flex-shrink: 0;
}
.navBar {
  background-color: #fff;
}
.intro {
  bottom: 10px;
  text-align: center;
  width: 100%;
  overflow-y: hidden;
}
.el-submenu__title {
  padding: 0 50px;
}
</style>
