import { login, logout, getInfo, getAuthsMenuTree } from '@/api/base/user'
import { getToken, setToken, removeToken, getAuths, setAuths, removeAuths } from '@/utils/auth'
import router, { resetRouter } from '@/router'
// import { get } from '@/utils/request'
// import { Message } from 'element-ui'

const state = {
  // 用户token
  token: getToken(),
  // 用户权限树
  authsMe: getAuths(),
  userId: '',
  name: '',
  avatar: '',
  roles: [],
  roleIds: [],
  user: {}
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_AUTHSME: (state, authsMe) => {
    state.authsMe = authsMe
  },

  SET_USER_ID: (state, userId) => {
    state.userId = userId
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  SET_ROLE_IDS: (state, roleIds) => {
    state.roleIds = roleIds
  },
  SET_USER: (state, user) => {
    state.user = user
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const { data } = response
        commit('SET_TOKEN', data.token)
        setToken(data.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  async getInfo({ commit, state, dispatch }) {
    // 获取用户的权限信息，并设置到仓库中和localStorage中
    const res = await getAuthsMenuTree()
    commit('SET_AUTHSME', res.data)
    setAuths(res.data)
    // 获取用户基本信息
    const { data } = await getInfo()
    // 判断用户类型
    /* if (!data) {
        reject('Verification failed, please Login again.')
      } */
    const { username: name, avatar, accountId: id } = data
    const roles = data.roles.map(item => item.roleName)
    const roleIds = data.roles.map(item => item.roleId)
    commit('SET_ROLE_IDS', roleIds)
    // 注意：roles数组不能为空数组，否则会死循环
    if (!roles || roles.length <= 0) {
      roles.push('')
    }
    commit('SET_USER', data)
    commit('SET_USER_ID', id)
    commit('SET_ROLES', roles)
    commit('SET_NAME', name)
    commit('SET_AVATAR', avatar)
  },

  // user logout
  logout({ commit, state, dispatch }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        commit('SET_TOKEN', '')
        commit('SET_ROLES', [])
        commit('SET_AUTHSME', [])
        removeToken()
        removeAuths()
        // localStorage.removeItem("studentExam");
        resetRouter()
        dispatch('tagsView/delAllViews', null, { root: true })
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      commit('SET_TOKEN', '')
      commit('SET_ROLES', [])
      removeToken()
      resolve()
    })
  },

  // dynamically modify permissions
  changeRoles({ commit, dispatch }, role) {
    return new Promise(async resolve => {
      const token = role + '-token'

      commit('SET_TOKEN', token)
      setToken(token)

      const { roles } = await dispatch('getInfo')

      resetRouter()

      // generate accessible routes map based on roles
      const accessRoutes = await dispatch('permission/generateRoutes', roles, { root: true })

      // dynamically add accessible routes
      router.addRoutes(accessRoutes)

      // reset visited views and cached views
      dispatch('tagsView/delAllViews', null, { root: true })

      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
