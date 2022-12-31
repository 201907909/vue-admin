<template>
  <div class="home">

    <el-card shadow="hover" class="card_set">
      <el-empty :image-size="200"></el-empty>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Home",


}
</script>

<style scoped>
.home {
  width: 100%;
  position: fixed;
  height: 600px;
}
.card_set {
  height:100%;
  width: 75%;


}


</style>
