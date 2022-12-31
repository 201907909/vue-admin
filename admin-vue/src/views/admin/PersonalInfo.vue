<template>
  <div class="personal">
    <el-card shadow="hover" class="card_set">
      <el-descriptions class="margin-top" title="超级管理员" :column="3" border>
        <template slot="extra">
          <el-button type="primary" icon="el-icon-edit" circle @click="dialogFormVisible = true;edit()">编辑</el-button>
          <el-dialog title="信息修改" :visible.sync="dialogFormVisible" :modal="false">
            <el-form :model="form" :ref="form" :rules="rules">
              <el-form-item label="登录名" :label-width="formLabelWidth">
                <el-input disabled v-model="form.loginName" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item prop="email" label="电子邮箱" :label-width="formLabelWidth">
                <el-input v-model="form.email" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="真实姓名" :label-width="formLabelWidth">
                <el-input v-model="form.name" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item prop="mobilePhone" label="手机号" :label-width="formLabelWidth">
                <el-input v-model.number="form.mobilePhone" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="地址" :label-width="formLabelWidth">
                <el-input v-model="form.address" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="性别" :label-width="formLabelWidth">
                <el-select v-model="form.sex" placeholder="请选择性别">
                  <el-option label="男" value="1"></el-option>
                  <el-option label="女" value="0"></el-option>
                </el-select>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="dialogFormVisible = false;confirmUpdate()">确 定</el-button>
            </div>
          </el-dialog>

        </template>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            登录名
          </template>
       <el-tag type="danger">{{user.loginName}}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-finance"></i>
            电子邮箱
          </template>
         <el-tag type="success">{{user.email}}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user-solid"></i>
            真实姓名
          </template>
          <el-tag type="warning">{{user.name}}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-mobile-phone"></i>
            手机号
          </template>
          <el-tag color="#00FFFF">{{user.mobilePhone}}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-location-outline"></i>
            地址
          </template>
         <el-tag type="success">{{user.address}}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-attract"></i>
            性别
          </template>
          <el-tag size="small"><span v-if="user.sex==1">男</span><span v-else-if="user.sex==0">女</span></el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-loading"></i>
            上次修改时间
          </template>
          <el-tag color="#FFFACD">{{user.updateTime}}</el-tag>
        </el-descriptions-item>
      </el-descriptions>

    </el-card>

  </div>

</template>
<script>
import Element from "element-ui";

export default {
  name: "PersonalInfo",
  data() {
    return {

      user: {
        loginName: '',
        name:'',
        sex: '',
        address:'',
        updateTime:'',
        email:'',
        mobilePhone:'',
        id:''
      },

      dialogFormVisible: false,
      form: {
        loginName: '',
        name:'',
        sex:'',
        address:'',
        updateTime:'',
        email:'',
        mobilePhone:'',
        id:''
      },
      rules: {

        mobilePhone:[{
          required: true,type: 'number',message: '必填且是数字类型',trigger: 'blur'
        }],
        email:[  { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }]
      },
      formLabelWidth: '120px',
    }


  },methods:{

    edit(){
      const userId = this.user.id
        this.$axios.get("/user/getUser/",{
          params:{
            id:userId
          }
        }).then(res=>{
          const userInfo = res.data.data;
          this.form.id=userInfo.id;
          this.form.loginName = userInfo.loginName;
          this.form.name = userInfo.name;
          this.form.email = userInfo.email;
          this.form.sex = (userInfo.sex).toString();
          this.form.address = userInfo.address;
          this.form.updateTime = '';
          this.form.mobilePhone = userInfo.mobilePhone;

        })


    },
    confirmUpdate() {
      this.form.updateTime='';
      this.$axios.post("/user/updateUser",this.form).then(res=> {
        const newInfo = res.data.data

        this.user=newInfo;


      })


    }
  }

  ,created() {
    const x = this.$store.getters.getUser
    const userId = x.id;
    this.$axios.get("/user/getUser/",{
      params:{
        id:userId
      }
    }).then(res=>{
      const userInfo = res.data.data;
      this.user.id=userInfo.id;
      this.user.loginName = userInfo.loginName;
      this.user.name = userInfo.name;
      this.user.email = userInfo.email;
      this.user.sex = (userInfo.sex).toString();
      this.user.address = userInfo.address;
      this.user.updateTime = userInfo.updateTime;
      this.user.mobilePhone = userInfo.mobilePhone;


    })

  }
};
</script>

<style scoped>
.personal {

  width: 70%;
  height: 800px;
  position: fixed;

}
.card_set {
 height:100%;

}




</style>
