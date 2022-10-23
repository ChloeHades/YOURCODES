<template>
  <el-dialog
    title="Create"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="90px">
    <el-form-item label="Title" prop="title" style="text-align:left">
      <el-input v-model="dataForm.title" placeholder="Title"></el-input>
    </el-form-item>
      <el-row>
        <el-col :span="8"><div class="grid-content bg-purple">
          <el-form-item label="Topic" prop="topic">
            <el-select v-model="dataForm.topic" placeholder="please choose">
              <el-option label="Algorithms" value="Algorithms"></el-option>
              <el-option label="Databases" value="Databases"></el-option>
              <el-option label="Mathematics" value="Mathematics"></el-option>
              <el-option label="Basic Programming" value="Basic Programming"></el-option>
            </el-select>
          </el-form-item>
        </div></el-col>
        <el-col :span="8"><div class="grid-content bg-purple">
          <el-form-item label="Difficulty" prop="difficulty">
            <el-select v-model="dataForm.difficulty" placeholder="please choose">
              <el-option label="Easy" value="Easy"></el-option>
              <el-option label="Medium" value="Medium"></el-option>
              <el-option label="Hard" value="Hard"></el-option>
            </el-select>
          </el-form-item>
        </div></el-col>
        <el-col :span="8"><div class="grid-content bg-purple-light">
          <el-form-item label="Type" prop="type">
            <el-select v-model="value1" multiple placeholder="select" style="text-align:left">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </div></el-col>
      </el-row>
    <el-form-item label="Description" prop="description">
      <el-input v-model="dataForm.description" placeholder="Description"></el-input>
    </el-form-item>
      <el-row>
      <el-col :span="12"><div class="grid-content bg-purple">
        <el-form-item label="Test Case 1" prop="input1">
          <el-input v-model="dataForm.input1" placeholder="Input1"></el-input>
      </el-form-item>
      </div></el-col>
      <el-col :span="12"><div class="grid-content bg-purple-light">
        <el-form-item label="" prop="output1">
          <el-input v-model="dataForm.output1" placeholder="Output1"></el-input>
        </el-form-item>
      </div></el-col>
      </el-row>
      <el-row>
        <el-col :span="12"><div class="grid-content bg-purple">
          <el-form-item label="Test Case 2" prop="input2">
            <el-input v-model="dataForm.input2" placeholder="Input2"></el-input>
          </el-form-item>
        </div></el-col>
        <el-col :span="12"><div class="grid-content bg-purple-light">
          <el-form-item label="" prop="output2">
            <el-input v-model="dataForm.output2" placeholder="Output2"></el-input>
          </el-form-item>
        </div></el-col>
      </el-row>
      <template>
        <div>
          <div id="app">
            <div>
              language ：
              <el-select
                v-model="opts.language"
                clearable
                placeholder="please choose"
                size="mini"
                @change="changeLanguage"
              >
                <el-option
                  v-for="item in sets.language"
                  :key="item"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
              style：
              <el-select
                v-model="opts.theme"
                clearable
                placeholder="please choose"
                size="mini"
                @change="changeTheme"
              >
                <el-option
                  v-for="item in sets.theme"
                  :key="item"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
              <el-button type="primary" size="mini" @click="getcode">run</el-button
              >
            </div>
          </div>
          <div>

            <monaco
              ref="monaco"
              :opts="opts"
              @change="changeValue"
              :height="600"
            ></monaco>
          </div>

          <el-descriptions title="computational results">
          </el-descriptions>
          <el-row>
            <span style="font-size:15px;width:200px;margin-right: 30px">"Result: "{{this.res.result}}</span>
          </el-row>
          <el-row>
            <span style="font-size:15px;width:200px;margin-right: 30px">"RunningTime: "{{this.res.elapsedTime}}ms</span>
          </el-row>
        </div>

      </template>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">cancel</el-button>
      <el-button type="primary" @click="dataFormSubmit()">ensure</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import monaco from './monacoeditor'
  import axios from "axios"
  export default {
    data () {
      return {
        res:'',
        mycodedataForm: {
          id:0,
          problem: '',
          code: '',
          username: '',
          lang:''
        },
        sets: {
          language: {
            'c': 'c',
            'java': 'java',
            'python': 'python'
          },
          theme: {
            'vs': 'vs',
            'vs-dark': 'vs-dark',
            'hc-black': 'hc-black'
          }
        },
        opts: {
          value: '',
          readOnly: false,
          language: 'python',
          theme: 'vs-dark' ,
          autoClosingBrackets: 'always',
          autoClosingDelete: 'always',
          autoIndent: 'None'
        },
        visible: false,
        dataForm: {
          id: 0,
          description:'',
          topic:'',
          title: '',
          difficulty: '',
          input1: '',
          input2: '',
          output1: '',
          output2: '',
          code: '',
          type:'',
          user:''
        },
        value1:'',
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
        dataRule: {
          title: [
            { required: true, message: 'Title can not be empty', trigger: 'blur' }
          ],
          solution: [
            { required: true, message: 'Solution can not be empty', trigger: 'blur' }
          ],
          difficulty: [
            { required: true, message: 'Difficulty can not be empty', trigger: 'blur' }
          ],
          submissions: [
            { required: true, message: 'Submissions can not be empty', trigger: 'blur' }
          ],
          rating: [
            { required: true, message: 'Rating can not be empty', trigger: 'blur' }
          ],
          topic: [
            { required: true, message: 'topic can not be empty', trigger: 'blur' }
          ],
          input1: [
            { required: true, message: 'Input1 can not be empty', trigger: 'blur' }
          ],
          input2: [
            { required: true, message: 'Input2 can not be empty', trigger: 'blur' }
          ],
          output1: [
            { required: true, message: 'Output1 Rating can not be empty', trigger: 'blur' }
          ],
          output2: [
            { required: true, message: 'Output2 can not be empty', trigger: 'blur' }
          ],
          code: [
            { required: true, message: 'Code can not be empty', trigger: 'blur' }
          ]
        }
      }
    },
    components: {
      monaco
    },
    created() {
      this.dataForm.user=this.$store.state.user
    },
    methods: {
      changeLanguage (val) {
        this.opts.language = val
      },
      changeTheme (val) {
        this.opts.theme = val
      },
      getcode(){
        this.mycodedataForm.code=this.$refs.monaco.getVal()
        this.mycodedataForm.lang=this.opts.language
        this.axios({
          url: `http://localhost:8080/prloblemcode/problemcode/test`,
          method: 'post',
          data: this.mycodedataForm
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({
              message: 'operate successfully',
              type: 'success',
              duration: 1500
            })
            this.res = JSON.parse(data.res.data)
            // console.log(JSON.stringify(data.res.data.result))
          } else {
            this.$message.error(data.msg)
          }
        })
      },

      getValue () {
      },

      changeValue (val) {
        this.dataForm.code = this.$refs.monaco.getVal()
      },
      func2() {
        window.open( "run");
      },
      func4() {
        this.$router.push({ path: "problem"});
      },
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/problams/problem/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.title = data.problem.title
                this.dataForm.solution = data.problem.solution
                this.dataForm.difficulty = data.problem.difficulty
                this.dataForm.submissions = data.problem.submissions
                this.dataForm.rating = data.problem.rating
              }
            })
          }
        })
      },
      dataFormSubmit () {
        var string = ''
        for (var i = 0; i < this.value1.length; i++) {
          this.value1.sort((x,y)=> x - y)
          string=string + this.value1[i]+';'
        }
        this.dataForm.user='admin'
        this.dataForm.type=string
        this.axios({
          url: `http://localhost:8080//problams/problem/save`,
          method: 'post',
          data: this.dataForm
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
      }
    }
  }
</script>
<style>
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: left;
    color: #2c3e50;
    margin-top: 0px;
  }
</style>
