/*
 * @Author: yaonan
 * @Date: 2022-03-25 10:30:18
 * @LastEditors: yaonan
 * @LastEditTime: 2022-03-29 14:05:35
 * @Description: 栏圈管理后台接口api
 */
import { get, postJSON, del } from '@/utils/request.js'

/**
 * @description: 根据id删除栏圈
 * @param {*} hId
 * @return {*}
 */
export function delById(hId) {
  return del(`/hurdles/deleteById/${hId}`)
}
/**
 * @description: 批量删除栏圈
 * @param {*} hIds
 * @return {*}
 */
export function deleteByIdAll(hIds) {
  return postJSON(`/hurdles/deleteByIdAll`, hIds)
}
/**
 * @description: 分页并根据条件查询栏圈基本信息以及栏舍信息
 * @param {*} data
 * @return {*}
 */
export function findByOption(data) {
  return get(`/hurdles/query`, data)
}
/**
 * @description: 查询所有栏圈容量
 * @param {*} data
 * @return {*}
 */
export function findAllMax(data) {
  return get(`/hurdles/queryAllMax`, data)
}
/**
 * @description: 保存或更新栏舍
 * @param {*} data
 * @return {*}
 */
export function saveOrUpdate(data) {
  return postJSON(`/hurdles/saveOrUpdate`, data)
}

/**
 * @description: 查询所有栏圈
 * @param {*} data
 * @return {*}
 */
export function findAll(data) {
  return get(`/hurdles/selectAllHurdles`, data)
}
/**
 * @description: 根据id更新以存量
 * @param {*} hId
 * @return {*}
 */
export function updateById(hId) {
  return get(`/hurdles/updateByIdSaved/${hId}`)
}

