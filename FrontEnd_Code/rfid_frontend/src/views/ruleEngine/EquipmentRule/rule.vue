<template>
  <div class="search">

    <el-form :inline="true" :model="formInline" class="form-inline">
      <el-form-item>
        <el-button type="success" @click="$router.push('/main/device/edit')">新增规则</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)" stripe style="width: 100%;" :highlight-current-row="true">
      <el-table-column prop="name" label="设备联动规则" sortable align="center"></el-table-column>
      <el-table-column prop="ip" label="设备ip" align="center"></el-table-column>
      <el-table-column prop="condition" label="执行条件" align="center"></el-table-column>
      <el-table-column prop="action" label="执行动作" align="center"></el-table-column>

      <el-table-column label="操作" width="500" align="center">
        <template slot-scope="scope">
          <el-button @click="$router.push('/main/device/edit/' + scope.row.id)" type="text" size="small">
            <i class="iconfont icon-details"></i>修改</el-button>
          <el-button @click="handleDelete(scope.row)" type="text" size="small">
            <i class="iconfont icon-delete"></i>启动</el-button>
          <el-button @click="handleDelete(scope.row)" type="text" size="small">
            <i class="iconfont icon-delete"></i>停止</el-button>
          <el-button @click="handleDelete(scope.row)" type="text" size="small">
            <i class="iconfont icon-delete"></i>删除</el-button>
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
      formInline: {
        name: '',
        email: ''
      },
      tableData: [],
      currentPage: 1,
      pageSize: 5
    }
  },
  mounted () {
    // getDeviceList()
    //   .then(res => {
    //     this.tableData = res.rows
    //   })
    //   .catch(err => {
    //     this.$message.error(err.message)
    //   })
  },
  methods: {
    onSubmit () {
      const keys = Object.keys(this.formInline)
      const query = {}
      keys.forEach(key => {
        this.formInline[key] &&
          (query[key] = (this.formInline[key] + '').trim())
      })
      // getDeviceList(query)
      //   .then(res => {
      //     this.tableData = res.rows
      //   })
      //   .catch(err => {
      //     this.$message.error(err.message)
      //   })
    },
    formatIsAdmin (row, column, cellValue) {
      return cellValue ? '是' : '否'
    },
    formatStatus (row, column, cellValue) {
      return cellValue === 'ok' ? '正常' : '已禁用'
    },
    // handleDelete (row) { // 删除
    //   console.log('row:', row)
    //   this.$confirm('此操作将永久删除该设备, 是否继续?', '提示', {
    //     confirmButtonText: '确定',
    //     cancelButtonText: '取消',
    //     type: 'warning'
    //   })
    //     .then(() => {
    //       deleteDeviceById(row.id)
    //         .then(res => {
    //           this.$message({
    //             type: 'success',
    //             message: '删除成功!'
    //           })
    //           this.tableData = this.tableData.filter(
    //             item => item.id !== row.id
    //           )
    //         })
    //         .catch(err => {
    //           this.$message.error(err.message)
    //         })
    //     })
    //     .catch(err => {
    //       console.log(err)
    //     })
    // },
    addDevice () {
      // 添加设备
      this.$router.push('/main/device/setting')
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
