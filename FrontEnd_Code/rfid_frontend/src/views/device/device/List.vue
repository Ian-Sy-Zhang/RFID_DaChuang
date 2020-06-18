<template>
  <div class="search">
    <el-form :inline="true" :model="formInline" class="form-inline">

      <el-form-item>
        <el-button type="success" @click="$router.push('/main/device/edit')">新增设备</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)" stripe style="width: 100%;" :highlight-current-row="true">
      <el-table-column prop="id" label="编号" sortable align="center"></el-table-column>
      <el-table-column prop="name" label="设备名称" align="center"></el-table-column>
      <el-table-column prop="code" label="设备编号" align="center"></el-table-column>
      <el-table-column prop="pubNetAddr" label="公网地址 ip" align="center"></el-table-column>
      <el-table-column prop="model" label="设备型号" align="center"></el-table-column>
      <el-table-column prop="status" label="设备状态" align="center"></el-table-column>
      <el-table-column prop="type" label="连接类型(http/mqtt)" align="center"></el-table-column>

      <el-table-column label="操作" width="180" align="center">
        <template slot-scope="scope">
          <el-button @click="goToArgs(scope.row)" type="text" size="small">
            <i class="iconfont icon-details"></i>查看</el-button>
          <el-button @click="handleDelete(scope.row)" type="text" size="small">
            <i class="iconfont icon-delete"></i>删除</el-button>
          <el-button @click="connect(scope.row)" type="text" size="small">
            <i class="iconfont icon-delete"></i>连接</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="page">
      <el-pagination layout="prev, pager, next, jumper, ->, sizes, total" :total="tableData.length" :background="true" @current-change="pageChange" @size-change="pageSizeChange" :current-page="currentPage" :page-size="pageSize" :page-sizes="[5, 10, 20, 50, 100]"></el-pagination>
    </div>
  </div>
</template>

<script>
// import { getDeviceList, deleteDeviceById } from '../../http/device'
export default {
  name: 'DeviceList',
  data () {
    return {
      tableData: [
        {
          id: 2,
          name: 'test1',
          code: 'qwwwq',
          pubNetAddr: '222.222.333.222',
          model: 'ww-1s',
          status: 'online',
          type: 'http'
        }
      ],
      currentPage: 1,
      pageSize: 5
    }
  },
  mounted () {
    if (!this.$store.state.haveDeviceList) {
      this.$http.get(this.$api.Device.getAllDevice)
        .then(res => {
          this.tableData = []
          let counter = 1
          for (const ele of res) {
            const temp = {
              id: counter,
              name: ele.name,
              connectionType: 'http',
              code: ele.id,
              pubNetAddr: ele.ip,
              model: 'standard',
              status: ele.status,
              type: 'http',
              abs: ele.abs
            }
            counter++
            console.log(temp)
            this.tableData.push(temp)
            this.$store.commit('devicePush', temp)
            this.$store.commit('getList')
          }
        })
        .catch(err => {
          this.$message.error(err.message)
        })
    } else {
      this.tableData = this.$store.state.deviceList
    }
  },
  methods: {
    onSubmit () {
      const keys = Object.keys(this.formInline)
      const query = {}
      keys.forEach(key => {
        this.formInline[key] &&
          (query[key] = (this.formInline[key] + '').trim())
      })
    },
    handleDelete (row) { // 删除
      console.log('row:', row)
      this.$confirm('此操作将永久删除该设备, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          // deleteDeviceById(row.id)
          //   .then(res => {
          //     this.$message({
          //       type: 'success',
          //       message: '删除成功!'
          //     })
          //     this.tableData = this.tableData.filter(
          //       item => item.id !== row.id
          //     )
          //   })
          //   .catch(err => {
          //     this.$message.error(err.message)
          //   })
        })
        .catch(err => {
          console.log(err)
        })
    },
    connect (row) {
      this.$router.push({ name: 'DeviceConnect', params: row })
    },
    goToArgs (row) {
      this.$router.push({ name: 'DevArgList', params: row })
    },
    pageChange (currentPage) {
      console.log('当前页：', currentPage)
      this.currentPage = currentPage
    },
    pageSizeChange (pageSize) {
      this.pageSize = pageSize
    }
  }
}
</script>

<style scoped>
.search {
  width: 100%;
  height: 100%;
  text-align: left;
}
.page {
  text-align: center;
  margin-top: 20px;
}
</style>
