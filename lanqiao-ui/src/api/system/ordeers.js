import request from '@/utils/request'

// 查询订单管理列表
export function listOrdeers(query) {
  return request({
    url: '/system/ordeers/list',
    method: 'get',
    params: query
  })
}

// 查询订单管理详细
export function getOrdeers(ordersId) {
  return request({
    url: '/system/ordeers/' + ordersId,
    method: 'get'
  })
}

// 新增订单管理
export function addOrdeers(data) {
  return request({
    url: '/system/ordeers',
    method: 'post',
    data: data
  })
}

// 修改订单管理
export function updateOrdeers(data) {
  return request({
    url: '/system/ordeers',
    method: 'put',
    data: data
  })
}

// 删除订单管理
export function delOrdeers(ordersId) {
  return request({
    url: '/system/ordeers/' + ordersId,
    method: 'delete'
  })
}

// 根据usersId查询地址详细信息
export function getType(usersId) {
  return request({
    url: '/system/address/type/' + usersId,
    method: 'get'
  })
}

// 查询地址管理列表
export function listAddress(ids) {
  return request({
    url: '/system/ordeers/listAddress',
    method: 'post',
    data: ids // 直接发送数组数据
  })
}
