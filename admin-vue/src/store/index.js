import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userInfo:JSON.parse(sessionStorage.getItem("userInfo")),
    path: ""

  },
  getters: {
    //get
    getUser:state => {
      return state.userInfo

    },
    pathInfo(state) {
      return  state.path
    },

  },
  mutations: {
    SET_USERINFO:(state,userInfo)=>{
      state.userInfo=userInfo
      sessionStorage.setItem("userInfo",JSON.stringify(userInfo))
    },
    REMOVE_INFO:(state)=>{
      state.userInfo={}
      sessionStorage.setItem("userInfo",JSON.stringify(''))
    },
    SET_PATH: (state, path) => {
      state.path = path
      console.log(state.path)
    }
  },
  actions: {
  },
  modules: {
  }
})
