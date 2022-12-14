import { createStore } from 'vuex'
import ModuleUser from './user'
import ModulePk from './pk'
import ModuleRecord from './record'

export default createStore({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {   //定义state的子模块
      user: ModuleUser,
      pk: ModulePk,
      record: ModuleRecord,
  }
})
