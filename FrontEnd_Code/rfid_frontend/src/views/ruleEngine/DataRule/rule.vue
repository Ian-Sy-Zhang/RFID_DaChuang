<template>
  <div class="search">
    <el-form :inline="true" class="form-inline">

      <el-form-item>
        <el-button type="success" @click="$router.push('/main/ruleEngine/data/add')">新增规则</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)" stripe style="width: 100%;" :highlight-current-row="true">
      <el-table-column prop="name" label="数据转发规则" sortable align="center"></el-table-column>
      <el-table-column prop="sql" label="数据过滤sql" align="center"></el-table-column>
      <el-table-column prop="condition" label="执行条件" align="center"></el-table-column>
      <el-table-column prop="action" label="执行动作" align="center"></el-table-column>
      <el-table-column prop="state" label="状态" align="center"></el-table-column>

      <el-table-column label="操作" width="500" align="center">
        <template slot-scope="scope">
          <el-button @click="goToModify(scope.row)" type="text" size="small">
            <i class="iconfont icon-details"></i>修改</el-button>
          <el-button @click="startRule(scope.row)" type="text" size="small">
            <i class="iconfont icon-delete"></i>启动</el-button>
          <el-button @click="stopRule(scope.row)" type="text" size="small">
            <i class="iconfont icon-delete"></i>停止</el-button>
          <el-button @click="removeRule(scope.row)" type="text" size="small">
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
  name: 'DataRuleList',
  data () {
    return {
      tableData: [
        {
        }
      ],
      currentPage: 1,
      pageSize: 5
    }
  },
  mounted () {
    if (!this.$store.state.haveDataRuleList) {
      this.initial()
    } else {
      this.tableData = this.$store.state.dataRuleList
    }
  },
  methods: {
    initial(){
      this.$http.get(this.$api.RuleEngine.getDataRules)
        .then(res => {
          this.tableData = []
          this.$store.commit('clearDataRule')
          let counter = 1
          for (const ele of res) {
            const temp = {
              id: counter,
              name: ele.name,
              sql: ele.sql,
              condition: ele.condition,
              action: ele.action,
              state: ele.state=='start'?'运行中':'未运行'
            }
            counter++
            console.log(temp)
            this.tableData.push(temp)
            this.$store.commit('dataRulePush', temp)
            this.$store.commit('getDataRuleList')
          }
        })
        .catch(err => {
          this.$message.error(err.message)
        })
    },
    back () {
      this.$router.back()
    },
    goToModify (row) {
      if(row.state == '运行中'){
        alert("无法修改已启动的规则")
        return
      }
      this.$router.push({ name: 'ModifyDataRule', params: row })
    },
    startRule (row) {
      if(row.state == '运行中'){
        alert("此规则已启动")
        return
      }
      this.$http.get(this.$api.RuleEngine.startDataRule + row.name)
        .then(res => {
          alert("启动成功！")
          this.initial()
        })
        .catch(err => {
            this.$message.error(err.message)
        })
    },
    stopRule (row) {
      if (row.name == 'Transport'){
        alert("此规则无法被停止")
        return
      }
      if(row.state == '未运行'){
        alert("此规则已停止")
        return
      }
      this.$http.get(this.$api.RuleEngine.stopDataRule + row.name)
        .then(res => {
          alert("停止成功！")
          this.initial()
        })
        .catch(err => {
            this.$message.error(err.message)
        })
    },
    removeRule (row) {
      if (row.name == 'Transport'){
        alert("此规则无法被删除")
        return
      }
      if(row.state == '运行中'){
        alert("无法删除正在运行的规则")
        return
      }
      this.$http.get(this.$api.RuleEngine.removeDataRule + row.name)
        .then(res => {
            alert("删除成功！")
            this.initial()
        })
        .catch(err => {
            this.$message.error(err.message)
        })
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
