<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="可用金额(单位：分)" prop="availableAmount">
      <el-input v-model="dataForm.availableAmount" placeholder="可用金额(单位：分)"></el-input>
    </el-form-item>
    <el-form-item label="冻结金额(单位：分)" prop="freezeAmount">
      <el-input v-model="dataForm.freezeAmount" placeholder="冻结金额(单位：分)"></el-input>
    </el-form-item>
    <el-form-item label="待收利息(单位：分)" prop="interestPending">
      <el-input v-model="dataForm.interestPending" placeholder="待收利息(单位：分)"></el-input>
    </el-form-item>
    <el-form-item label="待收本金(单位：分)" prop="principalPending">
      <el-input v-model="dataForm.principalPending" placeholder="待收本金(单位：分)"></el-input>
    </el-form-item>
    <el-form-item label="待还金额(单位：分)" prop="repaidAmount">
      <el-input v-model="dataForm.repaidAmount" placeholder="待还金额(单位：分)"></el-input>
    </el-form-item>
    <el-form-item label="信用得分" prop="creditScore">
      <el-input v-model="dataForm.creditScore" placeholder="信用得分"></el-input>
    </el-form-item>
    <el-form-item label="授信额度(单位：分)" prop="creditLine">
      <el-input v-model="dataForm.creditLine" placeholder="授信额度(单位：分)"></el-input>
    </el-form-item>
    <el-form-item label="剩余授信额度(单位：分)" prop="residualCreditLine">
      <el-input v-model="dataForm.residualCreditLine" placeholder="剩余授信额度(单位：分)"></el-input>
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
          availableAmount: '',
          freezeAmount: '',
          interestPending: '',
          principalPending: '',
          repaidAmount: '',
          creditScore: '',
          creditLine: '',
          residualCreditLine: '',
          createTime: ''
        },
        dataRule: {
          availableAmount: [
            { required: true, message: '可用金额(单位：分)不能为空', trigger: 'blur' }
          ],
          freezeAmount: [
            { required: true, message: '冻结金额(单位：分)不能为空', trigger: 'blur' }
          ],
          interestPending: [
            { required: true, message: '待收利息(单位：分)不能为空', trigger: 'blur' }
          ],
          principalPending: [
            { required: true, message: '待收本金(单位：分)不能为空', trigger: 'blur' }
          ],
          repaidAmount: [
            { required: true, message: '待还金额(单位：分)不能为空', trigger: 'blur' }
          ],
          creditScore: [
            { required: true, message: '信用得分不能为空', trigger: 'blur' }
          ],
          creditLine: [
            { required: true, message: '授信额度(单位：分)不能为空', trigger: 'blur' }
          ],
          residualCreditLine: [
            { required: true, message: '剩余授信额度(单位：分)不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/user/userwallet/info/${this.dataForm.accountId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.availableAmount = data.userWallet.availableAmount
                this.dataForm.freezeAmount = data.userWallet.freezeAmount
                this.dataForm.interestPending = data.userWallet.interestPending
                this.dataForm.principalPending = data.userWallet.principalPending
                this.dataForm.repaidAmount = data.userWallet.repaidAmount
                this.dataForm.creditScore = data.userWallet.creditScore
                this.dataForm.creditLine = data.userWallet.creditLine
                this.dataForm.residualCreditLine = data.userWallet.residualCreditLine
                this.dataForm.createTime = data.userWallet.createTime
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
              url: this.$http.adornUrl(`/user/userwallet/${!this.dataForm.accountId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'accountId': this.dataForm.accountId || undefined,
                'availableAmount': this.dataForm.availableAmount,
                'freezeAmount': this.dataForm.freezeAmount,
                'interestPending': this.dataForm.interestPending,
                'principalPending': this.dataForm.principalPending,
                'repaidAmount': this.dataForm.repaidAmount,
                'creditScore': this.dataForm.creditScore,
                'creditLine': this.dataForm.creditLine,
                'residualCreditLine': this.dataForm.residualCreditLine,
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
