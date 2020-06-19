<template>
  <div class="form">
    <el-form :label-position="labelPosition" label-width="125px" :model="connection" :rules="rules" ref="connection">
      <el-form-item label="服务器地址" prop="ip">
        <el-input v-model="connection.name"></el-input>
      </el-form-item>
      <el-form-item label="订阅主题" prop="topic">
        <el-input v-model="connection.pubNetAddr"></el-input>
      </el-form-item>
      <el-form-item label="消息质量" style="text-align:left;" prop="connection">
        <el-radio-group v-model="connection.connectionQuality">
          <el-radio label="0">不再次发送</el-radio>
          <el-radio label="1">再次发送直到成功</el-radio>
          <el-radio label="2">确保受到</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item style="text-align:left;margin-top:40px;">
        <el-button type="primary" @click="saveConnectionInfo('connection')">保存设置</el-button>
        <el-button @click="back" >返回上级</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'DeviceEdit',
  mounted () {

  },
  data () {
    return {
      labelPosition: 'right',
      connection: {
        args: [{}],
        connectionQuality: '0'
      },
      rules: {
        // ip: [
        //   { required: true, message: '请输入设备名称', trigger: 'blur' },
        //   {
        //     min: 3,
        //     max: 12,
        //     message: '长度在 3 到 12 ',
        //     trigger: 'blur,change'
        //   }
        // ],
        // topic: [
        //   { required: true }
        //
        // ]
      }
    }
  },
  computed: {
  },
  methods: {
    back () {
      this.$router.back()
    },
    saveConnectionInfo () {
      console.log(this.connection)
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
