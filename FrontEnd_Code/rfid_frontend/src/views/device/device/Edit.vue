<template>
  <div class="form">
    <el-form :label-position="labelPosition" label-width="125px" :model="device" :rules="rules" ref="device">
      <el-form-item label="设备名称" prop="name">
        <el-input v-model="device.name"></el-input>
      </el-form-item>
      <el-form-item label="公网地址" prop="pubNetAddr">
        <el-input v-model="device.pubNetAddr"></el-input>
      </el-form-item>
      <el-form-item label="设备型号" prop="model">
        <el-input v-model="device.model"></el-input>
      </el-form-item>
      <el-form-item label="设备状态" style="text-align:left;" prop="status">
        <el-radio-group v-model="device.status">
          <el-radio label="online">在线</el-radio>
          <el-radio label="offline">离线</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="连接类型" style="text-align:left;" prop="connection">
        <el-radio-group v-model="device.connection">
          <el-radio label="http">http</el-radio>
          <el-radio label="mqtt">mqtt</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="设备说明" prop="desc">
        <el-input type="textarea" :rows="4" placeholder="请输入描述内容" v-model="device.desc">
        </el-input>
      </el-form-item>
      <el-form-item style="text-align:left;margin-top:40px;">
        <el-button type="primary" @click="saveDeviceInfo('device')">保存设置</el-button>
        <el-button @click="back" >返回上级</el-button>
        <el-button v-if="!$route.params.devId" @click="clearForm('device')" >重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
// import {
//   getDeviceDetail,
//   updateDeviceInfo,
//   addDevice
// } from '../../http/device'
// import { getDevTypeList } from '../../http/devType'
// import { getDevArgList } from '../../http/devArg'
// import { getUserList } from '../../http/user'
// import { mapState } from 'vuex'
export default {
  name: 'DeviceEdit',
  mounted () {
    // getDevTypeList()
    //   .then(res => {
    //     this.devTypes = res.rows
    //   })
    //   .catch(err => {
    //     this.$message.error(err.message)
    //   })
    // getUserList()
    //   .then(res => {
    //     this.users = res.rows
    //   })
    //   .catch(err => {
    //     this.$message.error(err.message)
    //   })
    // // 加载设备信息
    // if (!this.$route.params.devId) return
    // getDeviceDetail(this.$route.params.devId)
    //   .then(res => {
    //     getDevArgList({ deviceId: res.id })
    //       .then(argRes => {
    //         this.device = Object.assign({}, res, { args: argRes.rows })
    //       })
    //       .catch(err => {
    //         this.$message.error(err.message)
    //       })
    //   })
    //   .catch(err => {
    //     this.$message.error(err.message)
    //   })
  },
  data () {
    return {
      users: [],
      devTypes: [],
      labelPosition: 'right',
      device: {
        args: [{}],
        status: 'online',
        connection: 'http'
      },
      rules: {
        name: [
          { required: true, message: '请输入设备名称', trigger: 'blur' },
          {
            min: 3,
            max: 10,
            message: '长度在 3 到 10 个字符',
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
    // handleAvatarSuccess (res, file) {
    //   this.device.pic = '/' + res.url
    //   // this.imageUrl = URL.createObjectURL(file.raw);
    // },
    // beforeAvatarUpload (file) {
    //   const isJPG = file.type === 'image/jpeg'
    //   const isLt2M = file.size / 1024 / 1024 < 2
    //
    //   if (!isJPG) {
    //     this.$message.error('上传头像图片只能是 JPG 格式!')
    //   }
    //   if (!isLt2M) {
    //     this.$message.error('上传头像图片大小不能超过 2MB!')
    //   }
    //   return isJPG && isLt2M
    // },
    // saveDeviceInfo (formName) {
    //   this.$refs[formName].validate(valid => {
    //     if (valid) {
    //       this.device.code += ''
    //       if (this.device.id) {
    //         // 修改保存
    //         updateDeviceInfo(this.device)
    //           .then(res => {
    //             this.$router.back()
    //           })
    //           .catch(err => {
    //             console.log(err)
    //             this.$message.error(err.message)
    //           })
    //       } else {
    //         // 新增保存
    //         addDevice(this.device)
    //           .then(res => {
    //             this.$message({
    //               type: 'success',
    //               message: '新增成功!'
    //             })
    //             this.$router.back()
    //           })
    //           .catch(err => {
    //             this.$message.error(err.message)
    //           })
    //       }
    //     }
    //   })
    // },
    removeDevArg (devArg, index) {
      this.device.args.splice(index, 1)
    },
    addDevArg () {
      this.device.args.push({})
    },
    clearForm (form) {
      this.device.args = this.device.args.map(item => {
        item.desc = ''
        return item
      })
      this.$refs[form].resetFields()
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
