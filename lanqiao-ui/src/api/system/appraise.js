import request from '@/utils/request'

// 查询评价管理列表
export function listAppraise(query) {
  return request({
    url: '/system/appraise/list',
    method: 'get',
    params: query
  })
}

// 查询评价管理详细
export function getAppraise(reviewid) {
  return request({
    url: '/system/appraise/' + reviewid,
    method: 'get'
  })
}

// 新增评价管理
export function addAppraise(data) {
  return request({
    url: '/system/appraise',
    method: 'post',
    data: data
  })
}

// 修改评价管理
export function updateAppraise(data) {
  return request({
    url: '/system/appraise',
    method: 'put',
    data: data
  })
}

// 删除评价管理
export function delAppraise(reviewid) {
  return request({
    url: '/system/appraise/' + reviewid,
    method: 'delete'
  })
}
