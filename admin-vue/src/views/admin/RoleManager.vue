<template>
  <div class="role">

    <el-card shadow="hover" class="card_set">
      <el-button type="danger"  @click="open" :disabled="flag==0"><i class="el-icon-minus"></i>
      批量删除
   </el-button>
      <el-button type="success"   @click="dialogFormVisible = true"><i class="el-icon-plus"></i>
        <span>
      新增
    </span></el-button>
      <el-button  type="warning" size="medium" v-if="flag==1"  @click="addMenu();bindVisible = true"> <i class="el-icon-plus"></i>
        <span>
      单个所选角色绑定菜单
    </span></el-button>
      <el-dialog title="下列是未绑定的菜单" :visible.sync="bindVisible" :modal="false" center="center">
        <h1 v-if="menus.length==0">所有菜单都已经绑定完了</h1>
        <template>
          <el-checkbox-group v-model="checkedMenus" @change="handleCheckedMenusChange">
            <el-checkbox v-for="menu in menus" :label="menu" :key="menu">{{menu.name}}</el-checkbox>
          </el-checkbox-group>
        </template>
        <div slot="footer" class="dialog-footer">
          <el-button @click="bindVisible = false">取 消</el-button>
          <el-button :disabled="checkedMenus==0" type="primary" @click="bindVisible = false;confirmBind()">确 定</el-button>
        </div>
      </el-dialog>
      <el-button style="margin-left: 10px" type="info" size="medium" v-if="flag==1"  @click="deleteMenu();unbindVisible = true"> <i class="el-icon-minus"></i>
        <span>
      单个所选角色解绑菜单
    </span></el-button>
      <el-dialog title="下列是已绑定的菜单" :visible.sync="unbindVisible" :modal="false" center="center">
        <h1 v-if="menus.length==0">该角色还没有绑定菜单</h1>
        <template>
          <el-checkbox-group v-model="checkedMenus" @change="handleCheckedMenusChange">
            <el-checkbox v-for="menu in menus" :label="menu" :key="menu">{{menu.name}}</el-checkbox>
          </el-checkbox-group>
        </template>
        <div slot="footer" class="dialog-footer">
          <el-button @click="unbindVisible = false">取 消</el-button>
          <el-button :disabled="checkedMenus==0" type="primary" @click="unbindVisible = false;confirmUnbind()">确 定</el-button>
        </div>
      </el-dialog>



      <div class="select"> <el-input placeholder="角色名关键字" v-model:value="searchName"></el-input></div>
      <div class="select_but"> <!--自定义searchHandler函数-->
        <el-button type="primary" @click="searchHandler(1)"  icon="el-icon-search">查询</el-button> </div>
      <el-table
          :data="tableData"
          style="width: 100%"
          @selection-change="handleSelectionChange">
        <el-table-column
            type="selection"
            width="55">
        </el-table-column>
        <el-table-column
            label="角色名"
            prop="name"
            width="200"

        >
        </el-table-column>
        <el-table-column
            label="角色型号"
            width="200"
            prop="version">
        </el-table-column>
        <el-table-column
            label="描述"
            width="300"
            prop="description">
        </el-table-column>

        <el-table-column
            align="left">
          <template slot-scope="scope">
            <el-button
                type="info" icon="el-icon-view" round
                size="mini"
                @click="handleSeen(scope.$index, scope.row);table=true">查看</el-button>

            <el-dialog
                title="该角色拥有的菜单"
                :center="true"
                :modal="false"
                :visible.sync="table"
                width="30%">
              <el-descriptions :column="1"
                               v-for="(item,index) in menuNums"
                               :key="index"
              >
                <el-descriptions-item label="菜单名">
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
                @click="visible = true;handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-dialog title="修改角色" :center="true" :visible.sync="visible" :modal="false">
              <el-form :model="roleEdit" :rules="rules" :ref="roleEdit">
                <el-form-item prop="name" label="NAME:" :label-width="formLabelWidth">
                  <el-input v-model="roleEdit.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="VERSION" :label-width="formLabelWidth">
                  <el-select v-model="roleEdit.version" placeholder="请选择角色VERSION">
                    <el-option label="0" value="1"></el-option>
                    <el-option label="1" value="2"></el-option>
                    <el-option label="2" value="1"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="描述" :label-width="formLabelWidth">
                  <el-input
                      type="textarea"
                      :rows="2"
                      placeholder="请输入角色描述"
                      v-model="roleEdit.description">
                  </el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="visible = false">取 消</el-button>
                <el-button type="primary" @click="edit(roleEdit)">确 定</el-button>
              </div>
            </el-dialog>

          </template>
        </el-table-column>
      </el-table>
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
    <el-dialog title="新增角色" :visible.sync="dialogFormVisible" :modal="false" :center="true">
      <el-form :model="roleForm" :rules="rules"  ref="roleForm">
        <el-form-item  label="角色名称 " prop="name" :label-width="formLabelWidth">
          <el-input v-model:value="roleForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述" :label-width="formLabelWidth">
          <el-input
              type="textarea"
              :rows="2"
              placeholder="请输入角色描述"
              v-model="roleForm.description">
          </el-input>
        </el-form-item>
        <el-form-item label="角色VERSION" :label-width="formLabelWidth">
          <el-select v-model="roleForm.version" placeholder="请选择角色VERSION">
            <el-option label="1" value="1"></el-option>
            <el-option label="2" value="2"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('roleForm')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Element from "element-ui";
import qs from "qs";

export default {
  name: "RoleManager",
  data() {
    return {
      bindMenus:[],
      menuNums:[],
      checkArr:'',
      checkedMenus:[],
      menus:[],
      roleId:'',
      showPage:true,
      showFindPage:false,
      bindMenu:{},
      roleDetail:{},
      visible:false,
      roleEdit:{
        name:'',
      },
      searchName:'',
      multipleSelection:[],
      flag:0,
      table:false,
      search: '',
      bindVisible:false,
      unbindVisible:false,
      dialogFormVisible: false,
      roleForm:{},
      rules: {
        name: [
          { required: true, message: '必填', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        status:[{
          required: true,type: 'number',message: '必填且是数字类型',trigger: 'blur'
        }]
      },
      formLabelWidth: '200px',

      total: '',
      size: '',
      current:'',
      tableData:{}

    }
  },created() {
    this.page(1)
    this.showPage=true;
    this.showFindPage=false;


  },

  methods: {
    confirmUnbind() {
      let params = [];
      this.checkArr.forEach(function (item) {
        params.push(item.id);
      });
      this.$axios.delete("/menu/deleteRoleByroleIdAndUserId",{
        params:{
          roleId:this.roleId,
          menuIds:params
        },paramsSerializer:params=>{
          return qs.stringify(params,{indices:false})
        }
      }).then(res=>{
        this.page(this.current)
        this.menus=[]
            this.checkArr=[]
            this.checkedMenus=[]

        Element.Message.success("解绑菜单成功",{duration :2*1000});

      })

    },

    deleteMenu() {
      for (let i = 0; i < this.multipleSelection.length; i++) {
        let uid = this.multipleSelection[i].id
        this.roleId = uid;
        this.$axios.get("/menu/selectRoleMenus?id="+uid).then(res=>{
          this.menus=res.data.data;

        })

      }

    },
    open() {
      this.$confirm('此操作将批量删除所选角色,是否继续?', '提示', {
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
      this.$axios.delete("/role/deleteRole",{
        params:{
          ids:params
        },paramsSerializer:params=>{
          return qs.stringify(params,{indices:false})
        }
      }).then(res=>{
        this.page(this.current)

      })

    },
    confirmBind(){
      let params = [];
      this.checkArr.forEach(function (item) {
        params.push(item.id);
      });
      this.$axios.delete("/role/addRolemenu",{
        params:{
          roleId:this.roleId,
          menuids:params
        },paramsSerializer:params=>{
          return qs.stringify(params,{indices:false})
        }
      }).then(res=>{
        this.page(this.current)
        this.checkedMenus=[]
        this.checkArr=[]
        Element.Message.success("绑定菜单成功",{duration :2*1000});

      })


    },

    handleCheckedMenusChange(value){
      this.checkArr = value  // multipleSelection存储了勾选到的数据
    },
    addMenu(){
      for (let i = 0; i < this.multipleSelection.length; i++) {
        let uid = this.multipleSelection[i].id
        this.roleId = uid;
        this.$axios.get("/menu/selectnotmenu?id="+uid).then(res=>{
          this.menus=res.data.data;

        })

      }


    },

    page(current) {
      this.$axios.get("/role/getRoles?currentPage=" + current).then(res => {
        this.tableData = res.data.data.records;
        this.current = res.data.data.current;
        this.total = res.data.data.total;
        this.size = res.data.data.size;
      })
    },
    handleEdit(index, row) {

      this.$axios.get("/role/findIdRole?id="+row.id).then(res=>{
        this.roleEdit=res.data.data

      })

    },
    handleSeen(index, row) {
      this.$axios.post("/role/findRoleMenus?id="+row.id).then(res=>{
        this.bindMenus= res.data.data;
        for (let i = 0; i < this.bindMenus.length; i++) {
          this.menuNums=this.bindMenus[i].tbaseMenuDTOS;
        }
      })

    },

    confirmSeen(){
      this.menuNums=[];
      this.bindMenus=[];
      this.page(this.current)

    },



    handleSelectionChange(val) {
     this.multipleSelection = val;
      this.flag = val.length;
      console.log(val)



    },
    searchHandler(currentFind){
      if(this.searchName=='') {
        Element.Message.error('搜索内容不能为空',{duration :1*1000});
      }else{
        this.$axios.get("/role/findrolelike",{
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
    edit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.visible = false;
          this.$axios.post("/role/update",this.roleEdit).then(res=>{
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
          this.$axios.post("/role/addNewRole",this.roleForm).then(res=>{
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
.role {
  width: 1200px;
  height: 600px;
  position: fixed;

}
.card_set {
  height:100%;
  width: 100%;
}
.mpage {
  margin: 0 auto;
  text-align: center;
}
.select {
  width: 200px;
  position: absolute;
  right: 200px;
  top: 20px;
}
.select_but {
  position: absolute;
  right: 100px;
  top: 20px;
}


</style>
