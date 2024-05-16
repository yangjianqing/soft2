import request from '@/utils/request'

// 查询销售明细管理列表
export function listSaledeta(query) {
  return request({
    url: '/system/saledeta/list',
    method: 'get',
    params: query
  })
}

// 查询销售明细管理详细
export function getSaledeta(saledetaId) {
  return request({
    url: '/system/saledeta/' + saledetaId,
    method: 'get'
  })
}

// 新增销售明细管理
export function addSaledeta(data) {
  return request({
    url: '/system/saledeta',
    method: 'post',
    data: data
  })
}

// 修改销售明细管理
export function updateSaledeta(data) {
  return request({
    url: '/system/saledeta',
    method: 'put',
    data: data
  })
}

// 删除销售明细管理
export function delSaledeta(saledetaId) {
  return request({
    url: '/system/saledeta/' + saledetaId,
    method: 'delete'
  })
}
