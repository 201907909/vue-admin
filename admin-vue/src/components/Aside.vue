<template>
<div>
  <el-aside width="290px">
    <el-row class="tac">
      <el-col :span="12">
        <el-menu
            :default-active="$route.path"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @select="handleSelect"
            router
            background-color="#abd3ea"
            @close="handleClose">
          <span class="side_title"><h1>管理面板</h1></span>


         <router-link to="/admin/home"class="a"><el-menu-item index="/admin/home" class="a">
           <i class="el-icon-s-home"></i>
           <span slot="title">首页</span>
         </el-menu-item></router-link>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-s-tools"></i>
              <span>权限管理</span>
            </template>
           <router-link to="/admin/menuManager"class="a"><el-menu-item index="/admin/menuManager" >  <i class="el-icon-menu"></i>菜单管理</el-menu-item></router-link>
           <router-link to="/admin/roleManager"class="a"><el-menu-item index="/admin/roleManager"> <i class="el-icon-s-custom"></i>角色管理</el-menu-item></router-link>
           <router-link to="/admin/userManager"class="a"><el-menu-item index="/admin/userManager" ><i class="el-icon-user-solid"></i>用户管理</el-menu-item></router-link>

          </el-submenu>
          <router-link to="/admin/personalInfo" class="a"><el-menu-item index="/admin/personalInfo">
            <i class="el-icon-camera-solid"></i>
            <span slot="title">个人中心</span>
          </el-menu-item></router-link>

        </el-menu>
      </el-col>
    </el-row>
  </el-aside>


</div>
</template>

<script>

export default {
  name: "Aside",
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    handleSelect(val){
      this.$store.commit("SET_PATH",val)
    },


  },



}


</script>

<style  scoped>
.el-menu-item {
  padding:0;
}

.side_title {
  display: block;
  width: 100px;
  color: #fcfcfc;
  margin-left: 50px;
}
.el-aside {
  background-color: #D3DCE6;
  color: #333;
  width: 100%;
  position: fixed;
  height: 100%;


}
.light {

}
.el-menu {
  width: 100%;
  position: absolute;
}

.a{
  text-decoration: none;
}



</style>
