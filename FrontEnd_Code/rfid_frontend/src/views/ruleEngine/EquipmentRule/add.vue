<template>
  <div class="form">
    <el-form :label-position="labelPosition" label-width="125px" :model="rule" :rules="rules" ref="device">
      <el-form-item label="规则名称" prop="name">
        <el-input v-model="rule.name" placeholder="规则首字母必须大写，否则无效"></el-input>
      </el-form-item>
      <el-form-item label="源设备ip" prop="ip">
        <el-input v-model="rule.ip" placeholder="127.0.0.1"></el-input>
      </el-form-item>
      <el-form-item label="执行条件" prop="condition">
        <el-input v-model="rule.condition.ip" placeholder="限定设备ip 格式：ip=?"></el-input>
        <el-input v-model="rule.condition.name" placeholder="限定设备名 格式：name=?">ip=?</el-input>
        <el-input v-model="rule.condition.state" placeholder="限定物流状态 格式 state=?">ip=?</el-input>
      </el-form-item>
      <el-form-item label="执行动作" style="text-align:left;" prop="action">
        <el-checkbox v-model="rule.action.reScan">重新扫描</el-checkbox>
        <el-checkbox v-model="rule.action.shutDown">关机</el-checkbox>
        <el-checkbox v-model="rule.action.alert">发出警报</el-checkbox>
      </el-form-item>
      <el-form-item style="text-align:left;margin-top:40px;">
        <el-button type="primary" @click="saveRuleInfo('rule')">保存设置</el-button>
        <el-button @click="back" >返回上级</el-button>
        <el-button v-if="!$route.params.devId" @click="clearForm('rule')" >重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'DataRuleAdd',
  mounted () {
    console.log(this.$route.params)
  },
  data () {
    return {
      labelPosition: 'right',
      rule: {
        name: 'NewRule',
        ip: '',
        condition: {
          ip: '',
          name: '',
          state: ''
        },
        action: {
          reScan: false,
          shutDown: false,
          alert: false
        }
      },
      rules: {
        name: [
          { required: true, message: '请输入规则名称', trigger: 'blur' },
          {
            min: 3,
            max: 10,
            trigger: 'blur,change'
          }
        ]
      }
    }
  },
  computed: {
  },
  methods: {
    back () {
      this.$router.back()
    },
    saveRuleInfo () {
      console.log(this.rule)

      let isA = false
      let c = ''
      if (this.rule.condition.ip!=''){
        isA = true
        c += this.rule.condition.ip
      }
      if(this.rule.condition.name != ''){
        if(isA)c+= ' && '
        else isA = true
        c += this.rule.condition.name
      }
      if(this.rule.condition.state != ''){
        if(isA)c+= ' && '
        else isA = true
        c += this.rule.condition.state
      }

      isA = false
      let a = ''
      if(this.rule.action.reScan){
        isA = true
        a += 'reScan'
      }
      if(this.rule.action.shutDown){
        if(isA)a+= ' && '
        else isA = true
        a += 'shutDown'
      }
      if(this.rule.action.alert){
        if(isA)a+= ' && '
        else isA = true
        a += 'alert'
      }

      const newRule = {
        name: this.rule.name,
        ip: this.rule.ip,
        condition: c,
        action: a
      }

      console.log(newRule)
      this.$http.post(this.$api.RuleEngine.addEquipmentRule, newRule)
      alert("添加成功")
      this.$router.push("/main/ruleEngine/equipment")
    },
    clearForm (form) {
      this.rule =  {
        name: '',
        ip: '',
        condition: {
          ip: '',
          name: '',
          state: ''
        },
        action: {
          database: false,
          log: false,
          alert: false
        }
      }
    }
  }
}
</script>

<style scoped>
.form {
  width: 52%;
  /* margin: 0 auto; */
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
