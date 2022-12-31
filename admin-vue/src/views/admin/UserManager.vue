<template>
  <div class="user">
    <el-card class="card_set" shadow="hover">
      <el-button type="danger"   @click="open" :disabled="flag==0"><i class="el-icon-minus"></i>
        批量删除
      </el-button>
      <el-button type="success"   @click="dialogFormVisible = true"> <i class="el-icon-plus"></i>
        <span>
      新增
    </span></el-button>
      <el-button type="warning"  @click="addRole();bindVisible=true" size="medium" v-if="flag==1"> <i class="el-icon-plus"></i>
        <span>
      单个所选用户绑定角色
    </span></el-button>
      <el-dialog title="下列是未绑定的角色" :visible.sync="bindVisible" :modal="false" center="true">
        <h1 v-if="roles.length==0">所有角色都已经绑定完了</h1>
        <template>
          <el-checkbox-group v-model="checkedRoles" @change="handleCheckedRolesChange">
            <el-checkbox v-for="role in roles" :label="role" :key="role">{{role.name}}</el-checkbox>
          </el-checkbox-group>
        </template>
        <div slot="footer" class="dialog-footer">
          <el-button @click="bindVisible = false">取 消</el-button>
          <el-button type="primary" :disabled="checkedRoles==0" @click="bindVisible = false;confirmBind()">确 定</el-button>
        </div>
      </el-dialog>
      <el-button style="margin-left: 10px" type="info" size="medium" v-if="flag==1"  @click="deleteRole();unbindVisible = true"> <i class="el-icon-minus"></i>
        <span>
      单个所选用户解绑角色
    </span></el-button>
      <el-dialog title="下列是已绑定的角色" :visible.sync="unbindVisible" :modal="false" center="center">
        <h1 v-if="roles.length==0">该用户还没有绑定角色</h1>
        <template>
          <el-checkbox-group v-model="checkedRoles" @change="handleCheckedRolesChange">
            <el-checkbox v-for="role in roles" :label="role" :key="role">{{role.name}}</el-checkbox>
          </el-checkbox-group>
        </template>
        <div slot="footer" class="dialog-footer">
          <el-button @click="unbindVisible = false">取 消</el-button>
          <el-button type="primary" :disabled=" checkedRoles==0" @click="unbindVisible = false;confirmUnbind()">确 定</el-button>
        </div>
      </el-dialog>

      <div class="select"> <el-input placeholder="用户名关键字" v-model:value="searchName"></el-input></div>
      <div class="select_but"> <!--自定义searchHandler函数-->
        <el-button type="primary" @click="searchHandler(1)"  icon="el-icon-search">查询</el-button> </div>

      <template>
        <el-table
            :data="tableData"
            style="width: 100%"
            @selection-change="handleSelectionChange">
          <el-table-column
              :selectable="checkSelect"
              align="center"
              type="selection"
              width="55">
          </el-table-column>
          <el-table-column
              label="用户名"
              prop="name"
          width="90">
          </el-table-column>
          <el-table-column
              label="性别"
              prop="sex"
          width="70">
            <template slot-scope="scope">
              <span v-if="scope.row.sex == 1">男</span>
              <span v-if="scope.row.sex == 0">女</span>
            </template>
          </el-table-column>
          <el-table-column
              label="登录名"
              width="100"
              prop="loginName">
          </el-table-column>
          <el-table-column
              label="密码"
              prop="password">
          </el-table-column>
          <el-table-column
              label="地址"
              prop="address">
          </el-table-column>
          <el-table-column
              label="邮箱"
              prop="email">
          </el-table-column>
          <el-table-column
              label="手机号码"
              prop="mobilePhone">
          </el-table-column>
          <el-table-column
              label="版本"
              prop="version"
          width="50">
          </el-table-column>
          <el-table-column
              label="状态"
              prop="status"
              width="50">
            <template slot-scope="scope">
              <span v-if="scope.row.status== 1"><span style="background: #15f131">在线</span></span>
              <span v-if="scope.row.status== 0 "><span style="background: #e5c4c4">离线</span></span>
            </template>
          </el-table-column>
          <el-table-column
              align="right"
          width="200">
            <template slot-scope="scope">
              <el-button
                  type="info" icon="el-icon-view" round
                  size="mini"
                  @click="handleSeen(scope.$index, scope.row)">查看</el-button>
              <el-dialog
              title="该用户拥有的角色"
             :center="true"
              :modal="false"
              :visible.sync="table"
              width="30%">
                <el-descriptions :column="1"
                                 v-for="(item,index) in roleNums"
                                 :key="index"
                ><el-descriptions-item label="拥有角色">
                <span>{{item.name}}</span>
                  </el-descriptions-item>
                </el-descriptions>
              <span slot="footer" class="dialog-footer">
    <el-button @click="table = false;confirmSeen()">确 定</el-button>

  </span>
              </el-dialog>

              <el-button

                  type="primary" icon="el-icon-edit" round
                  size="mini"
                  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-dialog title="修改用户" :center="true" :visible.sync="visible" :modal="false">
                <el-form :model="updateUser" :rules="rules" :ref="updateUser" >
                  <el-form-item  prop="loginName" label="LOGIN_NAME:" :label-width="formLabelWidth">
                    <el-input v-model="updateUser.loginName" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item  prop="password" label="PASSWORD:" :label-width="formLabelWidth">
                    <el-input v-model="updateUser.password" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item label="NAME:" :label-width="formLabelWidth">
                    <el-input v-model="updateUser.name" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item label="手机号码:" :label-width="formLabelWidth">
                    <el-input v-model="updateUser.mobilePhone" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item label="邮箱:" :label-width="formLabelWidth">
                  <el-input v-model="updateUser.email" autocomplete="off"></el-input>
                </el-form-item>
                  <el-form-item label="地址:" :label-width="formLabelWidth">
                    <el-input v-model="updateUser.address" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item label="SEX:" :label-width="formLabelWidth">
                    <el-select v-model="updateUser.sex" placeholder="请选择性别">
                      <el-option label="女" :value=0></el-option>
                      <el-option label="男" :value=1></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="VERSION" :label-width="formLabelWidth">
                    <el-select v-model="updateUser.version" placeholder="请选择用户VERSION">
                      <el-option label="0" value="0"></el-option>
                      <el-option label="1" value="1"></el-option>
                      <el-option label="2" value="2"></el-option>
                      <el-option label="3" value="3"></el-option>
                    </el-select>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="visible = false">取 消</el-button>
                  <el-button type="primary" @click="updateForm(updateUser)">确 定</el-button>
                </div>
              </el-dialog>
            </template>
          </el-table-column>
        </el-table>
      </template>

      <el-pagination class="mpage"
                     background
                     layout="prev, pager, next"
                     :current-page="current"
                     :page-size="size"
                     :total="total"
                     @current-change="page"
                     v-if="showPage"

      >
      </el-pagination>
      <el-pagination class="mpage"
                     background
                     layout="prev, pager, next"
                     :current-page="current"
                     :page-size="size"
                     :total="total"
                     v-if="showFindPage"
                     @current-change="searchHandler"
      >


      </el-pagination>

    </el-card>
    <el-dialog title="新增用户" :visible.sync="dialogFormVisible" :modal="false" :center="true">
      <el-form :model="addUser" :rules="rules" :ref="updateUser">
        <el-form-item prop="loginName" label="LOGIN_NAME:" :label-width="formLabelWidth">
          <el-input v-model="addUser.loginName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="PASSWORD:" :label-width="formLabelWidth">
          <el-input v-model="addUser.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="NAME:" :label-width="formLabelWidth">
          <el-input v-model="addUser.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="ADDRESS:" :label-width="formLabelWidth">
          <el-input v-model="addUser.address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="EMAIL:" :label-width="formLabelWidth">
          <el-input v-model="addUser.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="MOBILE_PHONE:" :label-width="formLabelWidth">
          <el-input v-model="addUser.mobilePhone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="SEX:" :label-width="formLabelWidth">
          <el-select v-model="addUser.sex" placeholder="请选择用户性别">
            <el-option label="女" value="0"></el-option>
            <el-option label="男" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="VERSION" :label-width="formLabelWidth">
          <el-select v-model="updateUser.version" placeholder="请选择用户VERSION">
            <el-option label="0" value="0"></el-option>
            <el-option label="1" value="1"></el-option>
            <el-option label="2" value="2"></el-option>
            <el-option label="3" value="3"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary"@click="submitForm(updateUser)">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Element from "element-ui";
import qs from "qs";


export default {
  name: "UserManager",


  data() {
    return {
      bindRoles:[],
      roleNums:[],
      checkArr:'',
      checkedRoles: [],
      userId:'',
      roles:[],
      isIndeterminate: true,
      Id:"",
      showPage:true,
      showFindPage:false,
      bindRole:{},
      userDetail:{},
      updateUser:{},
      addUser:{},
      rules: {
        loginName: [
          { required: true, message: '必填', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '必填（建议数字和字符结合）', trigger: 'blur' },
        ]
      },
      bindVisible:false,
      unbindVisible:false,
      visible:false,
      flag:0,
      table:false,
      searchName:'',
      tableData: {
        sex:''
      },
      total: '',
      size: '',
      current:'',
      multipleSelection:[],



      dialogFormVisible: false,
      formLabelWidth: '150px'

    }
  }, created() {
    this.page(1)
    this.showPage=true;
    this.showFindPage=false;
    if (this.$store.getters.getUser.loginName) {
      this.Id = this.$store.getters.getUser.id;

    }

  },
  methods: {
    confirmUnbind(){
      let params = [];
      this.checkArr.forEach(function (item) {
        params.push(item.id);
      });
      this.$axios.delete("/role/deleteRoleByroleIdAndUserId",{
        params:{
          userId:this.userId,
          roleids:params
        },paramsSerializer:params=>{
          return qs.stringify(params,{indices:false})
        }
      }).then(res=>{
        this.page(this.current)
        this.roles=[];
        this.checkedRoles=[]
        this.checkArr=[]
        Element.Message.success("解绑角色成功",{duration :2*1000});

      })

    },
    deleteRole(){
      for (let i = 0; i < this.multipleSelection.length; i++) {
        let uid = this.multipleSelection[i].id
        this.userId = uid;
        this.$axios.get("/role/selectUserRoles?id="+uid).then(res=>{
          this.roles=res.data.data;

        })

      }

    },

    open() {
      this.$confirm('此操作将批量删除所选用户,是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '删除成功!',
        });
        this.multiDelete()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    multiDelete(){
      let checkArr = this.multipleSelection;   // multipleSelection存储了勾选到的数据
      let params = [];
      checkArr.forEach(function (item) {
        params.push(item.id);
      });
      this.$axios.delete("/user/deleteUser",{
        params:{
          ids:params
        },paramsSerializer:params=>{
          return qs.stringify(params,{indices:false})
        }
      }).then(res=>{
        this.page(this.current)

      })

    },
    /**
     * row：当前行数据
     * index：当前第几位
     */
    checkSelect (row,index) {
      let isChecked = true;
      if (row.id === this.Id) { // 判断里面是否存在某个参数
        isChecked = false
      } else {
        isChecked = true
      }
      return isChecked
    },



    addRole(){
      for (let i = 0; i < this.multipleSelection.length; i++) {
        let uid = this.multipleSelection[i].id
        this.userId = uid;
        this.$axios.get("/role/selectnotrole?id="+uid).then(res=>{
          this.roles=res.data.data;

        })

      }


    },

    confirmBind(){
      let params = [];
      this.checkArr.forEach(function (item) {
        params.push(item.id);
      });
      this.$axios.delete("/user/adduserole",{
        params:{
          userId:this.userId,
          roleids:params
        },paramsSerializer:params=>{
          return qs.stringify(params,{indices:false})
        }
      }).then(res=>{
        this.page(this.current)
        this.checkedRoles=[]
        this.checkArr=[]
        Element.Message.success("绑定角色成功",{duration :2*1000});

      })


    },

    handleCheckedRolesChange(value) {
      this.checkArr = value  // multipleSelection存储了勾选到的数据

    },




    page(current) {
      this.$axios.get("/user/getUsers?currentPage=" + current).then(res => {
        this.tableData = res.data.data.records;
        this.current = res.data.data.current;
        this.total = res.data.data.total;
        this.size = res.data.data.size;
      })
    },
    handleEdit(index, row) {
      if (row.id==this.Id) {
        this.open2()
      }else {
        this.visible=true

        this.$axios.get("/user/findIdUser?id="+row.id).then(res=>{
          this.updateUser=res.data.data

        })

      }
    },
    handleSeen(index, row) {
      if (row.id==this.Id) {
        this.openFullScreen2();
      }else {
        this.table=true;
        this.$axios.post("/user/find?id="+row.id).then(res=>{
          this.bindRoles= res.data.data;
          for (let i = 0; i < this.bindRoles.length; i++) {
           this.roleNums=this.bindRoles[i].tbaseRoleDTOS;
          }
        })
      }
    },

    confirmSeen() {
      this.roleNums=[];
      this.bindRoles=[];
      this.page(this.current)

    },
    openFullScreen2() {
      const loading = this.$loading({
        lock: true,
        text: '正在跳转到个人中心>>>',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      });
      setTimeout(() => {
        loading.close();
        this.$router.push("/admin/personalInfo")
      }, 1000);
    },

    open2() {
      this.$notify({
        title: '警告',
        message: '抱歉，该用户是超级管理员，请前往个人中心完成相关操作',
        type: 'warning'
      });
    },

    handleSelectionChange(val) {
      this.multipleSelection = val;
      this.flag = val.length;

    },
    searchHandler(currentFind) {
      if (this.searchName == '') {
        Element.Message.error('搜索内容不能为空', {duration: 1 * 1000});
      } else {
        this.$axios.get("/user/finduserlike",{
          params:{
            name:this.searchName,
            currentPage:currentFind
          }
        }).then(res=>{
          this.showPage=false;
          this.showFindPage=true;
          this.tableData=res.data.data.records;
          this.current =  res.data.data.current
          this.total = res.data.data.total;
          this.size =  res.data.data.size;
        })


      }
    },
     async updateForm(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.visible = false;
          this.$axios.post("/user/update",this.updateUser).then(res=>{
            Element.Message.info(res.data.data,{duration :2*1000});
            this.page(this.current);
          })
        } else {
          return false;
        }
      });
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post("/user/addNewUser",this.addUser).then(res=>{
            this.dialogFormVisible=false;
            Element.Message.info(res.data.data,{duration :2*1000});
            this.page(1);
          })

        } else {
          return false;
        }
      });
    },
  },
}
</script>

<style scoped>
.user {
  width: 100%;
  height: 600px;
  position: fixed;

}

.card_set {
  height: 100%;
  width: 80%;
}

.mpage {
  margin: 0 auto;
  text-align: center;
}
.select {
  width: 200px;
  position: absolute;
  right: 500px;
  top: 20px;
}
.select_but {
  position: absolute;
  right: 400px;
  top: 20px;
}



</style>
