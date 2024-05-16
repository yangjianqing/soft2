import request from '@/utils/request'

// 查询销售记录管理列表
export function listSales(query) {
  return request({
    url: '/system/sales/list',
    method: 'get',
    params: query
  })
}

// 查询销售记录管理详细
export function getSales(salesId) {
  return request({
    url: '/system/sales/' + salesId,
    method: 'get'
  })
}

// 新增销售记录管理
export function addSales(data) {
  return request({
    url: '/system/sales',
    method: 'post',
    data: data
  })
}

// 修改销售记录管理
export function updateSales(data) {
  return request({
    url: '/system/sales',
    method: 'put',
    data: data
  })
}

// 删除销售记录管理
export function delSales(salesId) {
  return request({
    url: '/system/sales/' + salesId,
    method: 'delete'
  })
}

//初始化销售记录管理所需数据
export function initUsers() {
  return request({
    url: '/system/sales/initUsers',
    method: 'get'
  })
}
