<template>
  <div class="loginbody">

    <el-container>
      <el-header>
        <h1 class="title">Welcome</h1>
      </el-header>
      <el-main>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item  prop="loginName" placeholder="用户名">
            <el-input  placeholder="请输入账号" v-model="ruleForm.loginName"></el-input>
          </el-form-item>
          <el-form-item label="" prop="password" >
            <el-input placeholder="请输入密码" type="password" v-model="ruleForm.password"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button  class="login_btn" type="primary" @click="submitForm('ruleForm')">登录</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>

      </el-main>
    </el-container>

  </div>
</template>

<script>
import router from "@/router";
import Element from "element-ui";

export default {
  name:"Login",
  data() {
    return {

      ruleForm: {
        loginName: '',
        password: ''
      },
      rules: {
        loginName: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'change' }
        ]
      },

    };
  },
  methods: {
    openFullScreen2() {
      const loading = this.$loading({
        lock: true,
        text: '登录中>>>>',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      });
      setTimeout(() => {
        loading.close();
        Element.Message.success("登录成功",{duration :2*1000});
        this.$router.push("/admin")
      }, 2000);
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post('/user/login',this.ruleForm).then(res=>{
            this.openFullScreen2();
            //取后台数据储存起来
            const userInfo = res.data.data
            this.$store.commit("SET_USERINFO",userInfo)



          })



        } else {
          console.log('erro')

          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>
.loginbody {
  width: 100%;
  height: 100%;
  min-width: 1000px;
  background-image: url("../assets/images/background.jpeg");
  background-size: 100% 100%;
  background-position: center center;
  overflow: auto;
  background-repeat: no-repeat;
  position: fixed;
  line-height: 100%;
  padding-top: 150px;
}
.el-header{

  margin: 0 auto;
  width: 200px;
  color: #359fd0;
  text-align: center;
  line-height: 60px;

}
.login_btn {
  margin-left: 100px;
}

body > .el-container {
  margin-bottom: 40px;
}
.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}
.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.title {
margin-top: 0px;
  margin-left: 50px;
}
.demo-ruleForm {
  max-width: 500px;
  margin: 0 auto;
}
</style>
