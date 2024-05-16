import request from '@/utils/request'

// 查询商品分类列表
export function listCategory(query) {
  return request({
    url: '/system/category/list',
    method: 'get',
    params: query
  })
}

// 查询商品分类详细
export function getCategory(deptId) {
  return request({
    url: '/system/category/' + deptId,
    method: 'get'
  })
}

// 新增商品分类
export function addCategory(data) {
  return request({
    url: '/system/category',
    method: 'post',
    data: data
  })
}

// 修改商品分类
export function updateCategory(data) {
  return request({
    url: '/system/category',
    method: 'put',
    data: data
  })
}

// 删除商品分类
export function delCategory(deptId) {
  return request({
    url: '/system/category/' + deptId,
    method: 'delete'
  })
}
