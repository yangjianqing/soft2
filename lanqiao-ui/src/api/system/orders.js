import request from '@/utils/request'
//获取所有类型列表
export function goodsListAll(query) {
  return request({
    url: '/system/orders/goodsListAll',
    method: 'get',
    params: query
  })
}
// 查询采购信息列表
export function listOrders(query) {
  return request({
    url: '/system/orders/list',
    method: 'get',
    params: query
  })
}

// 查询采购信息详细
export function getOrders(orderId) {
  return request({
    url: '/system/orders/' + orderId,
    method: 'get'
  })
}

// 新增采购信息
export function addOrders(data) {
  return request({
    url: '/system/orders',
    method: 'post',
    data: data
  })
}

// 修改采购信息
export function updateOrders(data) {
  return request({
    url: '/system/orders',
    method: 'put',
    data: data
  })
}

// 删除采购信息
export function delOrders(orderId) {
  return request({
    url: '/system/orders/' + orderId,
    method: 'delete'
  })
}
