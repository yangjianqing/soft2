import request from "@/axios/request"

// 查询会员管理列表
export function listMembers(query) {
  return request({
    url: '/api/userlist',
    method: 'get',
    params: query
  })
}

// 删除会员管理
export function delMembers(id) {
  return request({
    url: '/member/' + id,
    method: 'delete'
  })
}

// 查询商品管理列表
export function listShopping(query) {
  return request({
    url: '/api/fresh/goodsList',
    method: 'get',
    params: query
  })
}


