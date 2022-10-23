import Vue from 'vue';
import user from '../utils/user';
import Vuex from 'vuex';
Vue.use(Vuex);

const store = new Vuex.Store({
  state:{
    user:'',
    password: ''
  },
  // mutations: Write methods specifically for updating values in state
  mutations: {
    // login
    doLogin(state,name) {
      state.user = name;
    },
    dopassword(state,password) {
      state.password = password;
    },
    //logout
    doLogout(state) {
    state.user = '';
    }
  }
});
export default store
