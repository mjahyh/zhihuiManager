/*
 * @Description: 指标记录接口API
 * @Author: Ronda
 * @Date: 2022-03-30 15:16:25
 * @LastEditors: Ronda
 * @LastEditTime: 2022-03-31 09:20:08
 */

import { get, postJSON, del } from '@/utils/request'

/**
 * @description: 根据id删除指标记录
 * @param {*} irdId
 * @return {*}
 */
export function delById(irdId) {
  return del(`/indexRecord/deleteById/${irdId}`)
}

/**
 * @description: 批量删除指标记录
 * @param {*} irdIds
 * @return {*}
 */
export function deleteByIdAll(irdIds) {
  return postJSON(`/indexRecord/deleteByIdAll`, irdIds)
}

/**
 * @description: 分页并根据条件查询指标记录
 * @param {*} data
 * @return {*}
 */
export function findByOption(data) {
  const obj = { ...data }
  for (const key in obj) {
    if (!obj[key]) {
      delete obj[key]
    }
  }
  return get(`/indexRecord/query`, obj)
}

/**
 * @description: 保存或更新指标记录
 * @param {*} data
 * @return {*}
 */
export function saveOrUpdate(data) {
  return postJSON(`/indexRecord/saveOrUpdate`, data)
}

/**
 * @description: 查询检测人员信息
 * @param {*}
 * @return {*}
 */
export function findAllRecordAccount() {
  return get(`/indexRecord/selectAllAccount`)
}
