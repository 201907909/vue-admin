<template>
<div class="head_site">

  <div class="bar_set">
    <div class="line"></div>
    <el-menu
        :default-active="$route.path"
        class="el-menu-demo"
        mode="horizontal"
        router
        @select="handleSelect"
        background-color="#ea7575"
        text-color="#fff"
        active-text-color="#fff8f8">
      <el-menu-item index="/admin/home">首页</el-menu-item>
      <el-menu-item index="/admin/menuManager">菜单管理</el-menu-item>
      <el-menu-item index="/admin/roleManager">角色管理</el-menu-item>
      <el-menu-item index="/admin/userManager">用户管理</el-menu-item>
      <el-menu-item index="/admin/personalInfo">个人中心</el-menu-item>
    </el-menu>

  </div>



  <router-link to="/admin/personalInfo"><span class="user"> <el-link  type="primary" :underline="false"><el-tag type="danger"><i class="el-icon-user
"></i>{{user.loginName}}</el-tag></el-link></span></router-link>
  <span class="exit_but"><el-button type="danger"  @click="open">退出</el-button></span>


</div>

</template>

<script>

import Element from "element-ui";

export default {
  name: "Head",
  data() {
    return {
      user: {
        loginName: ''
      },
      path:'',
      id:this.$store.getters.getUser.id

    }
  }
  ,methods: {

    logout() {

        this.$axios.get("/user/logout?id="+this.id).then(res => {
          this.$store.commit("REMOVE_INFO")
          this.$router.push("/login")
        })

    },

    handleSelect(val){

    },
    open() {
      this.$confirm('此操作将安全退出登录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        this.$message({
          type: 'success',
          message: '退出成功!',

        }, this.logout())

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消退出'
        });
      });
    }
  },



  created() {
    if (this.$store.getters.getUser.loginName) {
      this.user.loginName = this.$store.getters.getUser.loginName;

    }
  }
}
</script>

<style scoped>
.head_site {
  position: absolute;
  left: 300px;
  top: 20px;
}

.el-button {
  height: 30px;
line-height: 1px;
}
.exit_but  {
  display: block;
  position: absolute;
  left: 1000px;
  top: -20px;

}
.user{
  display: block;

  position: absolute;
  left: 900px;
  top: -20px;
  width: 80px;
  height: 30px;
}
.bar_set {
  margin-top: -20px;
  margin-left: -10px;
  background: #ea7575;
}
</style>
