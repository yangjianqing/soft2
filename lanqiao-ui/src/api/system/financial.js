import request from '@/utils/request'

// 查询财务报表管理列表
export function listFinancial(query) {
  return request({
    url: '/system/financial/list',
    method: 'get',
    params: query
  })
}

// 查询财务报表管理详细
export function getFinancial(financialId) {
  return request({
    url: '/system/financial/' + financialId,
    method: 'get'
  })
}

// 新增财务报表管理
export function addFinancial(data) {
  return request({
    url: '/system/financial',
    method: 'post',
    data: data
  })
}

// 修改财务报表管理
export function updateFinancial(data) {
  return request({
    url: '/system/financial',
    method: 'put',
    data: data
  })
}

// 删除财务报表管理
export function delFinancial(financialId) {
  return request({
    url: '/system/financial/' + financialId,
    method: 'delete'
  })
}
