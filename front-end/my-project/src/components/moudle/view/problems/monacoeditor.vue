<template>
  <div
    ref="container"
    class="monaco-editor"
    :style="`height: ${height}px`"
  ></div>
</template>
<script>
  import * as monaco from 'monaco-editor'
  export default {
    name: 'AcMonaco',
    props: {
      opts: {
        type: Object,
        default () {
          return {}
        }
      },
      height: {
        type: Number,
        default: 300
      }
    },
    data () {
      return {

        defaultOpts: {
          value: '',
          theme: 'vs-dark',
          roundedSelection: true,
        },

        monacoEditor: {}
      }
    },
    watch: {
      opts: {
        handler () {
          this.init()
        },
        deep: true
      }
    },
    mounted () {
      this.init()
    },
    methods: {
      home() {
        this.$router.push({ path: "/" });
      },
      init () {

        this.$refs.container.innerHTML = ''

        let editorOptions = Object.assign(this.defaultOpts, this.opts)

        this.monacoEditor = monaco.editor.create(this.$refs.container, editorOptions)

        this.monacoEditor.onDidChangeModelContent(() => {
          this.$emit('change', this.monacoEditor.getValue())
        })
      },

      getVal () {
        return this.monacoEditor.getValue()
      },
      //
      setVal(val){
        return  this.monacoEditor.setValue(val)
      }
    }
  }
</script>

