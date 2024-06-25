import request from '@/utils/request'

// 查询广告管理列表
export function listAdvertisement(query) {
  return request({
    url: '/system/advertisement/list',
    method: 'get',
    params: query
  })
}

// 查询广告管理详细
export function getAdvertisement(id) {
  return request({
    url: '/system/advertisement/' + id,
    method: 'get'
  })
}

// 新增广告管理
export function addAdvertisement(data) {
  return request({
    url: '/system/advertisement',
    method: 'post',
    data: data
  })
}

// 修改广告管理
export function updateAdvertisement(data) {
  return request({
    url: '/system/advertisement',
    method: 'put',
    data: data
  })
}

// 删除广告管理
export function delAdvertisement(id) {
  return request({
    url: '/system/advertisement/' + id,
    method: 'delete'
  })
}
