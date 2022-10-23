import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import Home from '@/components/login/homepage'

import Login from '@/components/login/Login'
import user from '@/components/login/user'
import login from '@/components/login/Login'
import register from '@/components/login/register'
import problem from '@/components/moudle/view/problems/problem'
import description from '@/components/moudle/view/problems/description'
import discuss from '@/components/moudle/view/problems/discuss'
import leaderboard from '@/components/moudle/view/problems/leaderboard'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/Login',
      name: 'Login',
      component: Login
    },
    {
      path: '/users',
      name: 'users',
      component: user
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/register',
      name: 'register',
      component: register
    },
    {
      path: '/problem',
      name: 'problem',
      component: problem
    },
    {
      path: '/description',
      name: 'description',
      component: description
    },
    {
      path: '/discuss',
      name: 'discuss',
      component: discuss
    },
    {
      path: '/leaderboard',
      name: 'leaderboard',
      component: leaderboard
    }
  ]
})
