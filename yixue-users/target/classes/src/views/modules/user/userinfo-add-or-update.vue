<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="肖像图片" prop="avatar">
      <el-input v-model="dataForm.avatar" placeholder="肖像图片"></el-input>
    </el-form-item>
    <el-form-item label="真实姓名" prop="realname">
      <el-input v-model="dataForm.realname" placeholder="真实姓名"></el-input>
    </el-form-item>
    <el-form-item label="身份证号码" prop="idCardNumber">
      <el-input v-model="dataForm.idCardNumber" placeholder="身份证号码"></el-input>
    </el-form-item>
    <el-form-item label="手机号码" prop="phoneNumber">
      <el-input v-model="dataForm.phoneNumber" placeholder="手机号码"></el-input>
    </el-form-item>
    <el-form-item label="收入等级id" prop="incomeLevelId">
      <el-input v-model="dataForm.incomeLevelId" placeholder="收入等级id"></el-input>
    </el-form-item>
    <el-form-item label="婚姻状况id" prop="marriageId">
      <el-input v-model="dataForm.marriageId" placeholder="婚姻状况id"></el-input>
    </el-form-item>
    <el-form-item label="教育背景id" prop="eduBackgroundId">
      <el-input v-model="dataForm.eduBackgroundId" placeholder="教育背景id"></el-input>
    </el-form-item>
    <el-form-item label="住房情况id" prop="houseConditionId">
      <el-input v-model="dataForm.houseConditionId" placeholder="住房情况id"></el-input>
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
          accountId: 0,
          avatar: '',
          realname: '',
          idCardNumber: '',
          phoneNumber: '',
          incomeLevelId: '',
          marriageId: '',
          eduBackgroundId: '',
          houseConditionId: '',
          createTime: ''
        },
        dataRule: {
          avatar: [
            { required: true, message: '肖像图片不能为空', trigger: 'blur' }
          ],
          realname: [
            { required: true, message: '真实姓名不能为空', trigger: 'blur' }
          ],
          idCardNumber: [
            { required: true, message: '身份证号码不能为空', trigger: 'blur' }
          ],
          phoneNumber: [
            { required: true, message: '手机号码不能为空', trigger: 'blur' }
          ],
          incomeLevelId: [
            { required: true, message: '收入等级id不能为空', trigger: 'blur' }
          ],
          marriageId: [
            { required: true, message: '婚姻状况id不能为空', trigger: 'blur' }
          ],
          eduBackgroundId: [
            { required: true, message: '教育背景id不能为空', trigger: 'blur' }
          ],
          houseConditionId: [
            { required: true, message: '住房情况id不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.accountId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.accountId) {
            this.$http({
              url: this.$http.adornUrl(`/user/userinfo/info/${this.dataForm.accountId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.avatar = data.userInfo.avatar
                this.dataForm.realname = data.userInfo.realname
                this.dataForm.idCardNumber = data.userInfo.idCardNumber
                this.dataForm.phoneNumber = data.userInfo.phoneNumber
                this.dataForm.incomeLevelId = data.userInfo.incomeLevelId
                this.dataForm.marriageId = data.userInfo.marriageId
                this.dataForm.eduBackgroundId = data.userInfo.eduBackgroundId
                this.dataForm.houseConditionId = data.userInfo.houseConditionId
                this.dataForm.createTime = data.userInfo.createTime
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
              url: this.$http.adornUrl(`/user/userinfo/${!this.dataForm.accountId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'accountId': this.dataForm.accountId || undefined,
                'avatar': this.dataForm.avatar,
                'realname': this.dataForm.realname,
                'idCardNumber': this.dataForm.idCardNumber,
                'phoneNumber': this.dataForm.phoneNumber,
                'incomeLevelId': this.dataForm.incomeLevelId,
                'marriageId': this.dataForm.marriageId,
                'eduBackgroundId': this.dataForm.eduBackgroundId,
                'houseConditionId': this.dataForm.houseConditionId,
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
