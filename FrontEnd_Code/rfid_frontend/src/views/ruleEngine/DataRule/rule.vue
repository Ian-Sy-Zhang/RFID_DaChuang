<template>
  <div class="search">
    <el-form :inline="true" :model="formInline" class="form-inline">

      <el-form-item>
        <el-button type="success" @click="$router.push('/main/ruleEngine/DataRule/modify.vue')">新增规则</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)" stripe style="width: 100%;" :highlight-current-row="true">
      <el-table-column prop="name" label="数据转发规则" sortable align="center"></el-table-column>
      <el-table-column prop="sql" label="数据过滤sql" align="center"></el-table-column>
      <el-table-column prop="condition" label="执行条件" align="center"></el-table-column>
      <el-table-column prop="action" label="执行动作" align="center"></el-table-column>

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
          name: 'test',
          sql: 'select * from 127.0.0.1',
          condition: 'num > 20',
          action: 'database'
        }
      ]
    }
  },
  mounted () {

  },
  methods: {
    back () {
      this.$router.back()
    },
    goToModify (row) {
      this.$router.push({ name: 'ModifyDataRule', params: row })
    },
    startRule (row) {
      this.$http.get(this.$api.RuleEngine.startDataRule, this.$data.name)
    },
    stopRule (row) {
      this.$http.get(this.$api.RuleEngine.stopDataRule, this.$data.name)
    },
    removeRule (row) {
      this.$http.get(this.$api.RuleEngine.removeDataRule, this.$data.name)
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
