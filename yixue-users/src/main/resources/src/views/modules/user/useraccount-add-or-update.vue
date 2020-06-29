<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户名" prop="username">
      <el-input v-model="dataForm.username" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="dataForm.password" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item label="状态( 0:禁用, 1:正常 )" prop="accountStatus">
      <el-input v-model="dataForm.accountStatus" placeholder="状态( 0:禁用, 1:正常 )"></el-input>
    </el-form-item>
    <el-form-item label="账户类型( 1:普通用户, 2:运营人员 )" prop="accountType">
      <el-input v-model="dataForm.accountType" placeholder="账户类型( 1:普通用户, 2:运营人员 )"></el-input>
    </el-form-item>
    <el-form-item label="是否完善个人信息( 0:未完善, 1:已完善 )" prop="fillUserinfo">
      <el-input v-model="dataForm.fillUserinfo" placeholder="是否完善个人信息( 0:未完善, 1:已完善 )"></el-input>
    </el-form-item>
    <el-form-item label="最后登录时间" prop="lastLoginTime">
      <el-input v-model="dataForm.lastLoginTime" placeholder="最后登录时间"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          username: '',
          password: '',
          accountStatus: '',
          accountType: '',
          fillUserinfo: '',
          lastLoginTime: '',
          createTime: ''
        },
        dataRule: {
          username: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          accountStatus: [
            { required: true, message: '状态( 0:禁用, 1:正常 )不能为空', trigger: 'blur' }
          ],
          accountType: [
            { required: true, message: '账户类型( 1:普通用户, 2:运营人员 )不能为空', trigger: 'blur' }
          ],
          fillUserinfo: [
            { required: true, message: '是否完善个人信息( 0:未完善, 1:已完善 )不能为空', trigger: 'blur' }
          ],
          lastLoginTime: [
            { required: true, message: '最后登录时间不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/user/useraccount/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.username = data.userAccount.username
                this.dataForm.password = data.userAccount.password
                this.dataForm.accountStatus = data.userAccount.accountStatus
                this.dataForm.accountType = data.userAccount.accountType
                this.dataForm.fillUserinfo = data.userAccount.fillUserinfo
                this.dataForm.lastLoginTime = data.userAccount.lastLoginTime
                this.dataForm.createTime = data.userAccount.createTime
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/user/useraccount/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'username': this.dataForm.username,
                'password': this.dataForm.password,
                'accountStatus': this.dataForm.accountStatus,
                'accountType': this.dataForm.accountType,
                'fillUserinfo': this.dataForm.fillUserinfo,
                'lastLoginTime': this.dataForm.lastLoginTime,
                'createTime': this.dataForm.createTime
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
