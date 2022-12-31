import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'

// 全局注册

import mavonEditor from 'mavon-editor'
import "element-ui/lib/theme-chalk/index.css"

import 'mavon-editor/dist/css/index.css'
import axios from 'axios'




Vue.prototype.$axios = axios //
import "./axios"
import "./permission"
Vue.use(Element)
// use
Vue.use(mavonEditor)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
