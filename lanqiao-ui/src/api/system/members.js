import request from '@/utils/request'

// 查询会员管理列表
export function listMembers(query) {
  return request({
    url: '/system/members/list',
    method: 'get',
    params: query
  })
}

// 查询会员管理详细
export function getMembers(memberId) {
  return request({
    url: '/system/members/' + memberId,
    method: 'get'
  })
}

// 新增会员管理
export function addMembers(data) {
  return request({
    url: '/system/members',
    method: 'post',
    data: data
  })
}

// 修改会员管理
export function updateMembers(data) {
  return request({
    url: '/system/members',
    method: 'put',
    data: data
  })
}

// 删除会员管理
export function delMembers(memberId) {
  return request({
    url: '/system/members/' + memberId,
    method: 'delete'
  })
}

//初始化会员管理所需数据
export function initUsers() {
  return request({
    url: '/system/members/initUsers',
    method: 'get'
  })
}
