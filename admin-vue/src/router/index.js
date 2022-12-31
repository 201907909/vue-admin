import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/admin/Home'
import MenuManager from '../views/admin/MenuManager'
import PersonalInfo from '../views/admin/PersonalInfo'
import RoleManager from '../views/admin/RoleManager'
import UserManager from '../views/admin/UserManager'
import Admin from '../views/Admin'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    redirect:{name:'Login'}
  },
  {
    path: '/login',
    name: 'Login',
    component: Login

  },
  {
    path: '/admin',
    name: 'Admin',
    component: Admin,
    redirect:{name:'Home'},
    meta:{
      requireAuth:true
    }

  },
  {  path: '/admin',
    component:Admin,
    children: [
      {
        path:'home',
        name:'Home',
        component: resolve => require(['../views/admin/Home'],resolve),
        meta:{
          requireAuth:true
        }

      },
      {
        path:'menuManager',
        name:'MenuManager',
        component: resolve => require(['../views/admin/MenuManager'],resolve),
        meta:{
          requireAuth:true
        }
      },
      {
        path:'roleManager',
        name:'RoleManager',
        component: resolve => require(['../views/admin/RoleManager'],resolve),
        meta:{
          requireAuth:true
        }
      },
      {
        path:'userManager',
        name:'UserManager',
        component: resolve => require(['../views/admin/UserManager'],resolve),
        meta:{
          requireAuth:true
        }
      },
      {
        path:'personalInfo',
        name:'PersonalInfo',
        component: resolve => require(['../views/admin/PersonalInfo'],resolve),
        meta:{
          requireAuth:true
        }
      },
    ]
  }









]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
