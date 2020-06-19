<template>
  <div class="search">
    <h1 style="font-size: x-large">危险化学品清单</h1>
    <p style="margin-top: 13px">下面这个列表展示了当前较为危险的化学品的清单及详细信息:</p>
    <el-table
      :data="dangerousChems"
      style="width: 40%;margin-top: 8px">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="化学品 CAS_ID">
              <span>{{ props.row.cas_id }}</span>
            </el-form-item>
            <el-form-item label="化学品名称">
              <span>{{ props.row.name }}</span>
            </el-form-item>
            <el-form-item label="存在状态">
              <span>{{ props.row.existType }}</span>
            </el-form-item>
            <el-form-item label="熔点">
              <span>{{ props.row.fusionPoint }}</span>
            </el-form-item>
            <el-form-item label="沸点">
              <span>{{ props.row.boilingPoint }}</span>
            </el-form-item>
            <el-form-item label="氧化性">
              <span>{{ props.row.oxidation }}</span>
            </el-form-item>
            <el-form-item label="还原性">
              <span>{{ props.row.reducibility }}</span>
            </el-form-item>
            <el-form-item label="有机性">
              <span>{{ props.row.isOrganic }}</span>
            </el-form-item>
            <el-form-item label="可燃性">
              <span>{{ props.row.inflammability }}</span>
            </el-form-item>
            <el-form-item label="易爆性">
              <span>{{ props.row.explosion }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        label="化学品 CAS_ID"
        prop="cas_id">
      </el-table-column>
      <el-table-column
        label="化学品名称"
        prop="name">
      </el-table-column>
    </el-table>
    <h1 style="font-size: x-large;margin-top:25px">危险批次清单</h1>
    <p style="margin-top: 13px">下面这个列表展示了当前较为危险的化学品批次的清单:</p>
    <el-table
      :data="dangerousBatches"
      style="width: 40%;margin-top: 8px"
      :row-class-name="tableRowClassName">
      <el-table-column
        prop="batchId"
        label="批次 ID">
      </el-table-column>
      <el-table-column
        prop="dangerousTimes"
        label="危险程度">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { request } from '../../network/request'
export default {
  name: 'Analysis',
  data () {
    return {
      dangerousChems: [
        {
          cas_id: '0',
          name: 'H2',
          existType: 'gas',
          fusionPoint: '-20.0℃',
          boilingPoint: '-10.0℃',
          oxidation: 'high',
          reducibility: 'high',
          isOrganic: 'no',
          inflammability: 'high',
          explosion: 'high'
        },
        {
          cas_id: '1',
          name: 'H2O',
          existType: 'Liquid',
          fusionPoint: '-20.0℃',
          boilingPoint: '-10.0℃',
          oxidation: 'high',
          reducibility: 'high',
          isOrganic: 'no',
          inflammability: 'high',
          explosion: 'high'
        }
      ],
      dangerousBatches: [
        {
          batchId: '1',
          dangerousTimes: '4'
        },
        {
          batchId: '2',
          dangerousTimes: '4'
        },
        {
          batchId: '3',
          dangerousTimes: '3'
        }
      ]
    }
  },
  mounted: function () {
    request({
      url: '/dataAnalysis/getDangerousChemList'
    }).then(res => {
      this.dangerousChems = res.data.content
      request({
        url: '/dataAnalysis/getDangerousBatchList'
      }).then(res => {
        this.dangerousBatches = res.data.content
      }).catch(err => {
        console.log(err)
      })
    }).catch(err => {
      console.log(err)
    })
  },
  methods: {
    tableRowClassName ({ row, rowIndex }) {
      const degree = this.dangerousBatches[rowIndex].dangerousTimes
      if (parseInt(degree) > 3) {
        return 'danger-row'
      } else {
        return 'warning-row'
      }
    }
  }
}
</script>

<style>
  .search {
    width: 100%;
    height: 100%;
    text-align: left;
  }
  .page {
    text-align: center;
    margin-top: 20px;
  }
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
  .el-table .warning-row {
    background: oldlace;
  }
  .el-table .danger-row {
    background: #FED7D7;
  }
</style>
