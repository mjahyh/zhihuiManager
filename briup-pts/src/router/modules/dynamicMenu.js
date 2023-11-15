/*
 * @Description: 动态菜单
 * @Author: Ronda
 * @Date: 2021-04-10 07:59:18
 * @LastEditors: Ronda
 * @LastEditTime: 2022-03-18 10:43:38
 */
// 动态路由
import Layout from '@/layout'
import { construct } from '@aximario/json-tree'
import { getAuthsMenu } from '@/api/base/user'
async function parseMenu() {
  const response = await getAuthsMenu()
  response.data = response.data.filter(item => item.type !== 2)
  const privileges = construct(response.data, {
    id: 'id',
    pid: 'parentId',
    children: 'children'
  })
  const routes = []
  for (const privilege of privileges) {
    // 0目录    1菜单   2按钮
    if (privilege.type !== 2) {
      const route = {
        path: privilege.url,
        component: Layout,
        meta: { title: privilege.name, icon: privilege.icon, affix: true },
        children: []
      }
      if (privilege.children && privilege.children.length > 0) {
        for (const p of privilege.children) {
          const son_route = {
            path: p.url,
            component: function component(resolve) {
              // 这里捕获文件不存在异常
              require(['@/pages' + p.url], resolve, () => {
                require(['@/pages/base/error-page/404.vue'], resolve)
              })
            },
            name: p.url.replace(/\//g, '_'),
            meta: { title: p.name, affix: true },
            // p.hidden为0则隐藏
            hidden: p.hidden === 0
          }
          route.children.push(son_route)
        }
      }

      routes.push(route)
    }
  }

  // console.log(JSON.stringify(routes), '-=-=-=-')
  routes.push({ path: '*', redirect: '/404', hidden: true })
  return routes
}

// let routes = parseMenu();

export default parseMenu

