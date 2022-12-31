<template>
<div>
  <el-container>
    <Aside></Aside>
    <el-container>
     <el-header> <Head></Head></el-header>
      <el-main>
        <router-view></router-view>
      </el-main>

    </el-container>
  </el-container>




</div>
</template>

<script>
import Aside from '../components/Aside'
import Head from "../components/Head";

export default {
  name:"Admin",
  components:{Aside,Head},

}
</script>

<style scoped>

.el-header{
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  line-height: 200px;
}

.el-main {
  display: block;
  margin-top: -20px;
  margin-left: 270px;

}

body > .el-container {
  height: 100%;
  width: 100%;
  position: fixed;

}



</style>

