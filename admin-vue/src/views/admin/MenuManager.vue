<template>
  <div class="manger">
    <el-card shadow="hover" class="card_set">
      <el-button type="danger"  @click="open" :disabled="flag==0"><i class="el-icon-minus"></i>
      批量删除
    </el-button>

      <el-button type="success"  @click="dialogFormVisible = true"><i class="el-icon-plus"></i>
        <span>
      新增
    </span></el-button>
      <div class="select"> <el-input placeholder="菜单或者ID关键字" v-model:value="searchName"></el-input></div>
      <div class="select_but"> <!--自定义searchHandler函数-->
        <el-button type="primary" @click="searchHandler(1)"  icon="el-icon-search">查询</el-button> </div>
    <el-table
        ref="multipleTable"
        tooltip-effect="dark"
        @selection-change="handleSelectionChange"
        :data="menus"
        style="width: 100%"
   >
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="菜单名称:">
              <span>{{ props.row.name }}</span>
            </el-form-item>
            <el-form-item label="TITLE:">
              <span>{{ props.row.title }}</span>
            </el-form-item>
            <el-form-item label="TYPE:">
              <span>{{ props.row.type}}</span>
            </el-form-item>
            <el-form-item label="MARK_URL:">
              <span>{{ props.row.markUrl}}</span>
            </el-form-item>
            <el-form-item label="URL:">
              <span>{{ props.row.url}}</span>
            </el-form-item>
            <el-form-item label="UPDATE_TIME:">
              <span>{{ props.row.updateTime}}</span>
            </el-form-item>
            <el-form-item label="描述：">
              <span>{{ props.row.content}}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
          label="菜单名称"
          prop="name">
      </el-table-column>
      <el-table-column
          label="菜单TITLE"
          prop="title">
      </el-table-column>
      <el-table-column
          label="菜单URL"
          prop="url">
      </el-table-column>
      <el-table-column
          label="菜单ICO"
          prop="ico">
      </el-table-column>
      <el-table-column
          label="菜单content"
          prop="content">
      </el-table-column>
      <el-table-column>
        <template slot-scope="scope">
          <el-button
              type="primary" icon="el-icon-edit" round
              size="mini"
              @click="visible = true;handleEdit(scope.row.id)"
          >编辑</el-button>
          <el-dialog title="修改菜单" :center="true" :visible.sync="visible" :modal="false" :fullscreen="true">
            <el-form :model="formEdit" :rules="rules"  ref="formEdit">
              <el-form-item  prop="name" label="菜单名称:" :label-width="formLabelWidth">
                <el-input v-model="formEdit.name" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="菜单TITLE:" :label-width="formLabelWidth">
                <el-input v-model="formEdit.title" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="菜单URL:" :label-width="formLabelWidth">
                <el-input v-model="formEdit.url" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="菜单ICO:" :label-width="formLabelWidth">
                <el-input v-model="formEdit.ico" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="菜单MARK_URL:" :label-width="formLabelWidth">
                <el-input v-model="formEdit.markUrl" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item prop="type" label="菜单TYPE:" :label-width="formLabelWidth">
                <el-input v-model.number="formEdit.type" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="菜单VERSION:" :label-width="formLabelWidth">
                <el-select v-model="formEdit.version" placeholder="请选择菜单VERSION">
                  <el-option label="1" value="1"></el-option>
                  <el-option label="2" value="2"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="菜单描述" :label-width="formLabelWidth">
                <el-input
                    type="textarea"
                    :rows="2"
                    placeholder="请输入描述"
                    v-model="formEdit.content">
                </el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="visible = false">取 消</el-button>
              <el-button type="primary" @click="updateForm('formEdit')">确 定</el-button>
            </div>
          </el-dialog>

          <el-popconfirm
              confirm-button-text='好的'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="确定删除吗？"
              @confirm="del(scope.row.id,scope.$index)"
          >
            <el-button
                circle
                size="mini"
                type="danger"
                slot="reference"
               ><span><i class="el-icon-delete"></i></span></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
      <el-pagination class="mpage"
                     background
                     layout="prev, pager, next"
                     :current-page="current"
                     :page-size="size"
                     :total="total"
                     v-if="showPage"

      @current-change="page">


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
    <el-dialog title="新增菜单" :visible.sync="dialogFormVisible" :modal="false" :center="true">
      <el-form :model="form"  :rules="rules" ref="form" >
        <el-form-item label="菜单名称" prop="name":label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="菜单TITLE":label-width="formLabelWidth">
          <el-input v-model="form.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="菜单ICO" :label-width="formLabelWidth">
          <el-input v-model="form.ico" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="菜单URL" :label-width="formLabelWidth">
          <el-input v-model="form.url" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="菜单MARK_URL" :label-width="formLabelWidth">
          <el-input v-model="form.markUrl" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="type" label="菜单TYPE" :label-width="formLabelWidth">
          <el-input v-model.number="form.type" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="VERSION":label-width="formLabelWidth">
          <el-select v-model="form.version" placeholder="请选择版本">
            <el-option label="1" value="1"></el-option>
            <el-option label="2" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="菜单描述" :label-width="formLabelWidth">
          <el-input
              type="textarea"
              :rows="2"
              placeholder="请输入描述"
              v-model="form.content">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('form')">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import Element from "element-ui";
import qs from 'qs'
export default {
  name: "MenuManager",
  data() {

    return {
      showPage:true,
      showFindPage:false,
      flag:0,
      searchName:'',
      formEdit:{
        id:""
      },
      visible:false,
      dialogFormVisible:false,
      multipleSelection:[],
      form: {
      },
      rules: {
        name: [
          { required: true, message: '必填', trigger: 'blur' },
          { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
        ],
        type:[{
          type: 'number',message: '必填且是数字类型',trigger: 'blur'
        }]

      },
      formLabelWidth: '150px',
      total: '',
      size: '',
      current:'',
      menus: {},


    }
  },
  mounted() {

  },
  methods: {
    handleEdit(id) {
      this.$axios.get("/menu/findMenuById?id="+id).then(res=>{
      this.formEdit = res.data.data;
      this.formEdit.id=id;

      })

    },
    open() {
      this.$confirm('此操作将批量删除所选菜单,是否继续?', '提示', {
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
      this.$axios.delete("/menu/deleteMenu",{
        params:{
          ids:params
        },paramsSerializer:params=>{
          return qs.stringify(params,{indices:false})
        }
      }).then(res=>{
        this.page(this.current)

      })

    },

    async handleSelectionChange(val){

      this.multipleSelection=val;

      this.flag = val.length;

    },


    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post("/menu/addNewMenu",this.form).then(res=>{
            this.dialogFormVisible = false;
            Element.Message.info(res.data.data,{duration :2*1000});
           this.page(1);
          })
        } else {
          return false;
        }
      });
    },
    updateForm(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
         this.$axios.post("/menu/update",this.formEdit).then(res=>{
           this.visible = false;
           Element.Message.info(res.data.data,{duration :2*1000});
           this.page(this.current);
         })

        } else {
          return false;
        }
      });
    },



    page(current) {
      this.$axios.get("/menu/getMenus?currentPage=" + current).then(res => {
        this.menus = res.data.data.records;
        this.current = res.data.data.current;
        this.total = res.data.data.total;
        this.size = res.data.data.size;
      })
    },

   async del(id,index){
      this.$axios.post("/menu/delMenuById?id="+id).then(res=>{
        this.menus.splice(index,1)
        Element.Message.success(res.data.data,{duration :2*1000});
        this.page(this.current)

      })
    },
      searchHandler(currentFind){
      if(this.searchName=='') {
        Element.Message.error('搜索内容不能为空',{duration :1*1000});
      }else{
        this.$axios.get("/menu/findmenulike",{
          params:{
            name:this.searchName,
            currentPage:currentFind
        }
        }).then(res=>{
          this.showPage=false;
          this.showFindPage=true;
          this.menus = res.data.data.records;
          this.current =  res.data.data.current
          this.total = res.data.data.total;
          this.size =  res.data.data.size;
        })
      }



    }




  }, created() {
      this.page(1)
    this.showPage=true;
    this.showFindPage=false;
  }
}

</script>

<style scoped>
.manger {
  width: 100%;
  position: fixed;
  height: 600px;
}
.card_set {
  height:100%;
  width: 85%;
}
.mpage {
  margin: 0 auto;
  text-align: center;
}

.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}


.select {
  width: 200px;
  position: absolute;
  right: 600px;
  top: 20px;
}
.select_but {
  position: absolute;
  right: 500px;
  top: 20px;
}
</style>
