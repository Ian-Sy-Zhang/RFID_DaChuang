<template>
  <div class="search">

    <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)" stripe style="width: 100%;" :highlight-current-row="true">
      <el-table-column prop="name" label="设备参数名称" align="center"></el-table-column>
      <el-table-column prop="desc" label="设备参数描述" align="center"></el-table-column>
      <el-table-column prop="deviceName" label="所属设备" align="center"></el-table-column>
    </el-table>
    <div class="page">
      <el-pagination layout="prev, pager, next, jumper, ->, sizes, total" :total="tableData.length" :background="true" @current-change="pageChange" @size-change="pageSizeChange" :current-page="currentPage" :page-size="pageSize" :page-sizes="[5, 10, 20, 50, 100]"></el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: 'DevArgList',
  data () {
    return {
      formInline: {
        name: '',
        email: ''
      },
      tableData: [],
      currentPage: 1,
      pageSize: 5,
      centerDialogVisible: false,
      currentDevArg: {
        device: {
          name: '',
          id: ''
        }
      },
      devices: [],
      rules: {
        name: [
          {
            required: true,
            message: '参数名称不能为空',
            trigger: 'blur, change'
          }
        ],
        desc: [
          {
            required: true,
            message: '参数描述不能为空',
            trigger: 'blur, change'
          }
        ]
      }
    }
  },
  mounted () {
    const row = this.$route.params
    this.currentDevArg.device.id = row.id
    this.currentDevArg.device.name = row.name
    console.log(row.code)

    if (row.code !== undefined) {
      this.tableData = [{ name: 'code', desc: row.code, deviceName: row.name }, { name: 'pubNetAddr', desc: row.pubNetAddr, deviceName: row.name },
        { name: 'model', desc: row.model, deviceName: row.name }, { name: 'status', desc: row.status, deviceName: row.name },
        { name: 'connectionType', desc: row.type, deviceName: row.name }]
    }
  },
  methods: {
    pageChange (currentPage) {
      console.log('当前页：', currentPage)
      this.currentPage = currentPage
    },
    pageSizeChange (pageSize) {
      this.pageSize = pageSize
    },
    clearForm () {
      this.currentDevArg = {
        device: {
          id: '',
          name: ''
        }
      }
    },
    addLocalData (newData) {
      this.tableData.push(newData)
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
