import request from '@/utils/request'

// 查询订单明细列表
export function listPartslist(query) {
  return request({
    url: '/system/partslist/list',
    method: 'get',
    params: query
  })
}

// 查询订单明细详细
export function getPartslist(id) {
  return request({
    url: '/system/partslist/' + id,
    method: 'get'
  })
}

// 新增订单明细
export function addPartslist(data) {
  return request({
    url: '/system/partslist',
    method: 'post',
    data: data
  })
}

// 修改订单明细
export function updatePartslist(data) {
  return request({
    url: '/system/partslist',
    method: 'put',
    data: data
  })
}

// 删除订单明细
export function delPartslist(id) {
  return request({
    url: '/system/partslist/' + id,
    method: 'delete'
  })
}
