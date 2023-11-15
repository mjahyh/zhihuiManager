/*
 * @Description: 动物管理api
 * @Author: Ronda
 * @Date: 2022-03-28 17:24:23
 * @LastEditors: Ronda
 * @LastEditTime: 2022-04-11 17:00:46
 */

import { get, postJSON, del } from '@/utils/request'

/**
 * @description: 根据id删除动物
 * @param {*} animalId
 * @return {*}
 */
export function delById(aAnimalId) {
  return del(`/animal/deleteById/${aAnimalId}`)
}

/**
 * @description: 批量删除动物
 * @param {*} animalIds
 * @return {*}
 */
export function deleteByIdAll(animalIds) {
  return postJSON(`/animal/deleteByIdAll`, animalIds)
}

/**
 * @description: 分页并根据条件查询动物
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
  return get(`/animal/query`, obj)
}

/**
 * @description: 保存或更新动物
 * @param {*} data
 * @return {*}
 */
export function saveOrUpdate(data) {
  return postJSON(`/animal/saveOrUpdate`, data)
}

/**
 * @description: 根据动物编号查询信息
 * @param {*} animalId
 * @return {*}
 */
export function findByAnimalId(animalId) {
  return get(`/animal/findByAnimalId`, { animalId })
}

/**
 * @description: 生成二维码
 * @param {*} animalId
 * @return {*}
 */
export function productQRCode(animalId) {
  return get(`/nologin/product`, { animalId })
}

/**
 * @description: 二维码跳转
 * @param {*} animalId
 * @return {*}
 */
export function redirectQRCode(animalId) {
  return get(`/nologin/redirect`, { animalId })
}

