<template>
  <div>
    <header>
      <h1 class="title_name">YourCode</h1>
      <p>
        <el-dropdown :show-timeout="0" placement="bottom" style="text-align:right">
            <span class="el-dropdown-link">
              <img :src="require('../../../../assets/Images/user.png')" :alt="this.$store.state.user" width="30px" height="30px">{{ this.$store.state.user }}
            </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="userdetails()">Profile</el-dropdown-item>
            <el-dropdown-item @click.native="updatePasswordHandle()">Change password</el-dropdown-item>
            <el-dropdown-item @click.native="logoutHandle()">Exit</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </p>
      <hr>
      <nav>
        <div class="navbar">
          <ul class="container gray highlightTextIn">
            <li><a alt ="Homepage" @click="home">Homepage</a></li>
            <li><a alt="Create" @click="addOrUpdateHandle()">Create</a></li>
            <li><a alt="Leader Board"@click="func3">Leader Board</a></li>
          </ul>
        </div>
      </nav>
    </header>
    <div id="topics">
      <button @click="topics='AllTopics'">AllTopics</button>
      <button @click="topics='Algorithms'">Algorithms</button>
      <button @click="topics='Databases'">Databases</button>
      <button @click="topics='Mathematics'">Mathematics</button>
      <button @click="topics='BasicProgramming'">BasicProgramming</button>
    </div>
    <div id="difficulties">
      <select name="" id="difficulty" v-model="difficulties">
        <option id ="green" value="Easy">Easy</option>
        <option id ="yellow" value="Medium">Medium</option>
        <option id ="red" value="Hard">Hard</option>
      </select>
      <div class="tags">
        <div class="select-field">
      <el-select v-model="value1" multiple placeholder="please Select" >
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
          <el-button @click="getDataListlike()">Inquire</el-button>
        </div>
      </div>
    </div>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm">
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="title"
        header-align="center"
        align="center"
        label="Title">
      </el-table-column>
      <el-table-column
        prop="solution"
        header-align="center"
        align="center"
        label="Solution">
        <template slot-scope="scope2">
          <el-button type="text" size="small" @click="func1(scope2.row.id)">Solution</el-button>
        </template>
      </el-table-column>
      <el-table-column
        prop="difficulty"
        header-align="center"
        align="center"
        label="Difficulty">
      </el-table-column>
      <el-table-column
        prop="submissions"
        header-align="center"
        align="center"
        label="CommentNumber">
      </el-table-column>
      <el-table-column
        prop="rating"
        header-align="center"
        align="center"
        label="Rating">
        <template slot-scope="scope1">
        <el-rate
          :value = scope1.row.rating
          disabled
          show-score
          text-color="#ff9900"
          score-template="{value}">
        </el-rate>
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="operation"
        v-if="this.$store.state.user ==='admin'"
      >
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
    <el-dialog
      title="modification"
      :close-on-click-modal="false"
      :visible.sync="passwordvisible">
      <el-form :model="passwordataForm"  ref="dataForm"  label-width="110px" class="demo-ruleForm">
        <el-form-item label="User Name" prop="User Name">
          <el-input v-model="passwordataForm.username" placeholder="address name"></el-input>
        </el-form-item>
        <el-form-item label="Old password" prop="Old password">
          <el-input v-model="passwordataForm.password" placeholder="Old password"></el-input>
        </el-form-item>
        <el-form-item label="New password" prop="New password">
          <el-input v-model="passwordataForm.passwordnew" placeholder="New password"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
      <el-button @click="passwordvisible = false">cancel</el-button>
      <el-button type="primary" @click="passworddataFormSubmit()">confirm</el-button>
    </span>
    </el-dialog>
    <el-dialog
      title="User detail"
      :close-on-click-modal="false"
      :visible.sync="uservisible">
      <el-form :model="UserdetailsdataForm" ref="dataForm" @keyup.enter.native="UserdetailsdataFormSubmit()" label-width="110px" class="demo-ruleForm">
        <el-form-item label="User Name" prop="User Name">
          <el-input v-model="UserdetailsdataForm.username" placeholder="User Name"></el-input>
        </el-form-item>
        <el-form-item label="email" prop="email">
          <el-input v-model="UserdetailsdataForm.email" placeholder="email"></el-input>
        </el-form-item>
        <el-form-item label="Instagram" prop="Instagram">
          <el-input v-model="UserdetailsdataForm.phone" placeholder="Instagram"></el-input>
        </el-form-item>
        <el-row>
          <el-col :span="8"><div class="grid-content bg-purple">
            <el-form-item label="Academic Year" prop="Academic Year">
              <el-select v-model="UserdetailsdataForm.sex" placeholder="Academic Year">
                <el-option label="1 Year" value="1 Year"></el-option>
                <el-option label="2 Year" value="2 Year"></el-option>
                <el-option label="3 Year" value="3 Year"></el-option>
              </el-select>
            </el-form-item>
          </div></el-col>
        </el-row>
        <el-form-item label="description" prop="description">
          <el-input
            type="textarea"
            :rows="2"
            placeholder="description"
            v-model="UserdetailsdataForm.selfintroduction">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
      <el-button @click="uservisible = false">cancel</el-button>
      <el-button type="primary" @click="UserdetailsdataFormSubmit()">confirm</el-button>
    </span>
    </el-dialog>
  </div>
</template>

<script>
  import AddOrUpdate from './problem-add-or-update'
  import common from "../../../common";
  export default {
    data () {
      return {
        passwordataForm: {
          username: '',
          password: '',
          passwordnew: ''
        },
        UserdetailsdataForm: {
          username: '',
          password: '',
          email: '',
          phone:'',
          sex:'',
          selfintroduction:''
        },
        passwordvisible:false,
        uservisible:false,
        options: [{
          value: '1',
          label: 'Array'
        }, {
          value: '2',
          label: 'String'
        }, {
          value: '3',
          label: 'Math'
        }, {
          value: '4',
          label: 'Sorting'
        }, {
          value: '5',
          label: 'Tree'
        },
          {
            value: '6',
            label: 'Binary Search'
          }, {
            value: '7',
            label: 'Matrix'
          }, {
            value: '8',
            label: 'Binary Tree'
          }, {
            value: '9',
            label: 'Bit Manipulation'
          }, {
            value: '10',
            label: 'Stack'
          }, {
            value: '11',
            label: 'Counting'
          },
          {
            value: '12',
            label: 'Linked List'
          }, {
            value: '13',
            label: 'Recursion'
          }, {
            value: '14',
            label: 'Queue'
          }, {
            value: '15',
            label: 'Merge Sort'
          }, {
            value: '16',
            label: 'Bubble Sort'
          }, {
            value: '17',
            label: 'Selection Sort'
          }],
        value1: [],
        dataForm: {
          key: ''
        },
        difficulties:'Easy',
        topics:'AllTopics',
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    created() {
      this.getDataList()
    },
    activated () {
      this.getDataList()
    },
    methods: {
      home() {
        this.$router.push({ path: "/" });
      },
      func1(id) {
        console.log('ssss')
        this.$router.push({ path: "description",query:{id:id} });
      },
      func2() {
        this.$router.push({ path: "create"});
      },
      func3() {
        this.$router.push({ path: "leaderboard"});
      },
      func5() {
        this.$router.push({ path: "Login"});
      },
      userdetails () {
        this.uservisible = true
        this. UserdetailsdataForm.username = this.$store.state.user
        this.getuserdetail1()
      },
      UserdetailsdataFormSubmit() {
        this.axios({
          url: `http://localhost:8080/login/user/userupdate`,
          method: 'post',
          data: this.UserdetailsdataForm
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({
              message: 'operate successfully',
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
      },
      // condition query
      getDataListlike () {
        var string = ''
        for (var i = 0; i < this.value1.length; i++) {
          this.value1.sort((x,y)=> x - y)
          string=string + this.value1[i]+';'
        }
        this.axios.get(common.adress+'problams/problem/listlike',{
          params:{
            page: this.pageIndex,
            limit: this.pageSize,
            key: this.dataForm.key,
            type: string,
            difficulties: this.difficulties,
            topics: this.topics
          }}).then(
          ({data}) => {
            if (data && data.code === 0) {
              this.dataList = data.page.list
              this.totalPage = data.page.totalCount
            } else {
              this.dataList = []
              this.totalPage = 0
            }
            this.dataListLoading = false
          }
        )
        this.dataListLoading = true
      },
      // Get the data list
      getDataList () {
        this.axios.get(common.adress+'problams/problem/list',{
          params:{
            page: this.pageIndex,
            limit: this.pageSize,
            key: this.dataForm.key,
          }}).then(
          ({data}) => {
            if (data && data.code === 0) {
              this.dataList = data.page.list
              this.totalPage = data.page.totalCount
            } else {
              this.dataList = []
              this.totalPage = 0
            }
            this.dataListLoading = false
          }
        )
        this.dataListLoading = true
      },
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      deleteHandle (id) {
        var ids=id
        this.$confirm(`Confirm the deletion?`, 'hint', {
          confirmButtonText: 'confirm',
          cancelButtonText: 'cancel',
          type: 'warning'
        }).then(() => {
          this.axios.get('http://localhost:8080/problams/problem/delete',{
            params:{
              ids:ids,
            }}).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: 'operate successfully',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      },
      updatePasswordHandle () {
        this.passwordvisible = true
        this.passwordataForm.username = this.$store.state.user
      },
      logoutHandle () {
        this.$confirm(`Confirm to proceed with [Exit] operation?`, 'hint', {
          confirmButtonText: 'ensure',
          cancelButtonText: 'cancel',
          type: 'warning'
        }).then(() => {
          this.func5()
        }).catch(() => {})
      } ,
      passworddataFormSubmit () {
        if(this.passwordataForm.username === ''||this.passwordataForm.password === ''){
          this.$message.error('Please enter the complete information')
          return
        }
        this.axios({
          url: `http://localhost:8080/login/user/passwordupdate`,
          method: 'post',
          data: this.passwordataForm
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({
              message: 'operate successfully',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.visible = false
                this.$emit('refreshDataList')
              }
            })
            this.func5()
          } else {
            this.$message.error(data.msg)
          }
        })
      },
      // Get the data listthis.getuserdetail()
      getuserdetail1 () {
        this.axios.get(common.adress+'login/user/getdetail',{
          params:{
            user: this. UserdetailsdataForm.username,
          }}).then(
          ({data}) => {
            if (data && data.code === 0) {
              this.UserdetailsdataForm = data.user

            } else {
              this.UserdetailsdataForm = []
            }
          }
        )
      }
    }
  }
</script>
<style>

  .login_form {
    padding-top: 10%;
    padding-left: 10%;
    padding-right: 10%;
  }
  .qxs-ic_user {
    background-size: 13px 15px;
    background-position: 3%;
  }
  .qxs-ic_password {
    background-size: 13px 15px;
    background-position: 3%;
    margin-bottom: 20px;
  }
  .login_logo {
    height: 100%;
  }
  .login_btn {
    width: 100%;
    font-size: 16px;
    background: -webkit-linear-gradient(left, #000099, #2154FA); /* Safari 5.1 - 6.0 */
    background: -o-linear-gradient(right, #000099, #2154FA); /* Opera 11.1 - 12.0 */
    background: -moz-linear-gradient(right, #000099, #2154FA); /* Firefox 3.6 - 15 */
    background: linear-gradient(to right, #000099 , #2154FA);
    filter: brightness(1.4);
  }
  * logo of website *
  #logo {
    width: 80px;
    height: 80px;
    display: inline-block;
  }
  /* gray line in the header */
  hr{
    border:none;
    color:#000000;
    background-color: #000000;
    height: 1px;

  }
  header{
    position: relative;
  }
  header p{
    position:relative;
    float:right;
    display: block;
    margin-top: -40px;
    margin-right: 60px;
    font-family: Inter,sans-serif;
  }
  /*user icon*/
  .user{
    position: relative;
    margin-top: -50px;
    margin-right: 20px;
    float:right;
  }
  .logo{
    position: absolute;
    margin-top: -63px;
    margin-left: 20px;
  }

  /*full page*/
  * {
    padding: 0;
    margin: 0;
    text-decoration: none;
    list-style: none;
    box-sizing: border-box;
  }

  body {
    font-family: 'Open Sans',Arial,sans-serif;
    margin: 0;
    padding: 0;
    min-height: 3000px;
    background-color: #f8f8f8;
  }

  /* div for nav bar, the whole nav bar */
  .navBar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #ffffff;
  }

  .container li a
  {
    size: 100px;
    text-decoration: none;
    margin: 0px 10px;
    padding: 10px 10px;
    position: relative;
    z-index: 0;
    cursor: pointer;
  }

  /* Highlight text out  */
  .highlightTextIn li a:before
  {
    position: absolute;
    color: #000000;
    top: 0px;
    left: 0px;
    padding: 10px;
    overflow: hidden;
    content: attr(alt);
    transition: all 0.3s;
    transform: scale(0.8);
    opacity: 0;
  }

  .highlightTextIn li a:hover:before
  {
    transform: scale(1);
    opacity: 1;
  }

  /* Highlight text in  */
  .highlightTextIn li a
  {
    color: rgba(0, 0, 0, 0.4);
  }

  .highlightTextIn li a:before
  {
    transform: scale(1.2);
  }

  /* Homepage, Problems, Create, Leader Board  */
  ul {
    padding-top: 18px;
    padding-bottom: 15px;
    list-style-type: none;
    text-align: center;
  }

  ul li {
    display: inline-block;
    margin-right:30px;
  }
  ul li a {
    margin: 0 10px;
    border-radius: 10px;
    padding: 8px;
    font-size: 100px;
    color: #929292;
    position: relative;
    text-decoration: none;
  }

  ul li a::after{
    position: absolute;
    content: "";
    width: 0%;
    height: 4px;
    background-color: #929292;
    left: 50%;
    bottom: -1px;
    transition: all 0.4s ease-in-out;
  }

  .navBar:hover ul li a::after {
    width: 100%;
    left: 0;
  }


  /* all buttons on nav bar */
  .navbar ul li a{
    color: #929292;
    font-size:  15px;
    letter-spacing: 2px;
    margin: -8px;
  }


  /*star image*/
  .star{
    max-width: 2.5rem;
    object-fit: cover;
    margin-bottom: 2rem;
    margin-top:4rem;
    display: block;
    margin-left: auto;
    margin-right: auto;
  }
  /*star image spinning*/
  /* .star {
      max-width: 2.5rem;
      object-fit: cover;
      margin-bottom: 2rem;
      margin-top:4rem;
      display: block;
      margin-left: auto;
      margin-right: auto;
      animation: spin 2s infinite;
    }

    @keyframes spin {
      0% {
        transform: rotate(0deg);
      }
      100% {
        transform: rotate(360deg);
      }
    } */


  /* div for all text on home page */
  #text {
    font-size: 30px;
    letter-spacing: 3px;
  }
  #create{
    letter-spacing: 6px;
  }
  #text p {
    margin-top: 10px;
    display:block;
    font-size: 15px;
    text-transform: uppercase;
  }
  #text2 {
    text-align: center;
    margin-left: auto;
    margin-right: auto;
  }
  #title2 {
    font-size: 100px;
    color:rgb(0, 0, 0);
  }


  /* login/sign up buttons */
  #button{
    margin-top: 4rem;
    text-align: center;
  }
  #button span {
    color:#000000;
    border: 1px solid black;
    border-radius: 40px;
    padding: 12px 40px;
    margin-right: 5px;
    text-align: center;
    font-size: 1rem;
  }

  #button span:hover{
    background-color: rgb(0, 0, 0);
    color:#f8f8f8;
    transition: all 0.5s;
  }



  /* footer at bottom of page */
  #footer {
    text-align: center;
  }
  #footer p{
    color:#000000;
  }


  /* svg{
      margin-top: -500px;
  } */

  /* div for text on home page */
  div#text {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }



  /* login box */
  #login {
    padding: 40px 40px 20px 40px;
    border-radius: 50px;
    text-align: center;
    margin-top:80px;
    margin-bottom: 100px;
  }

  /* login box title buttons */

  #label{
    margin-bottom: 10px;
  }
  #label a:hover {
    background-color:#f8f8f8;
    transition: all 0.5s;
    border-radius: 10px;
  }

  /* username input for both login and sign up page */
  #username {
    border-radius: 20px;
    border: 10px solid transparent;
    cursor: pointer;
    transition: all 1s;
    margin: 10px 0px;
    width: 250px;
  }

  /* password input for both login and sign up page */
  #password {
    border-radius: 20px;
    border: 10px solid transparent;
    cursor: pointer;
    transition: all 1s;
    margin: 10px 0px;
    width: 250px;
  }

  /* email input for sign up page only*/
  #email {
    border-radius: 20px;
    border: 10px solid transparent;
    cursor: pointer;
    transition: all 1s;
    margin: 10px 0px;
    width: 250px;
  }

  /* submit button for both login and sign up page */
  #submit {
    border-radius: 20px;
    width: 250px;
    border: 1px solid #000000;
    color: #000000;
    background-color: #f8f8f8;
    cursor: pointer;
    margin: 10px 0px;
    padding: 12px 40px;
    margin-right: 5px;
    text-align: center;
    font-size: 1rem;
  }

  #submit:hover {
    background-color: #000000;
    color:#ffffff;
    transition: all 0.5s;
  }

  /* align all text in input bux to center */
  ::placeholder {
    text-align: center;
  }

  /* check box for remember me */
  #checkbox {
    text-align: center;
    color: #000000;
  }

  /* link to sign up page in check box */
  #checkbox a {
    color: #000000;
  }

  #checkbox a:hover {
    color: #000000;
    transition: all 0.5s;
  }
  input[type="text"]{
    padding:2px;
  }
  input[type="password"]{
    padding:2px;
  }




  /* div for profile icon and user's Username */
  #profile {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin: 0px 10px;
  }

  /* profile icon */
  div#profile img {
    width: 40px;
    height: 40px;
    cursor: pointer;
  }








  div#profile a:hover {
    background-color: #000000;
    transition: all 0.5s;
    border-radius: 7px;
  }

  /* div for welcome box on loggedin page */
  #welcome {
    background-color: #000000;
    margin-right: 140px;
    padding: 40px 40px 50px 40px;
    border-radius: 50px;
    text-align: center;
    font-size: 30px;
    color: #30a379;
  }

  /* link to profile on welcome box */
  #welcome a {
    color: rgb(49, 36, 60);
    border: 2px solid black;
    border-radius: 40px;
    padding: 7px;
    background-color: lightgreen;
  }

  #welcome a:hover {
    background-color:#000000;
    transition: all 0.5s;
  }

  /*footer text style*/
  #footer h1{
    text-transform: uppercase;
  }
  #footer p{
    font-size:15px;
    margin-top:10px;
    letter-spacing: 3px;
  }

</style>
