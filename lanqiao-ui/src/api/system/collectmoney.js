import request from '@/utils/request'

// 根据商品编码查询商品详细
export function getGoods(coding) {
  return request({
    url: '/system/goods/Goods/' + coding,
    method: 'get'
  })
}

// 购物车结算
export function addGoodsList(data) {
  return request({
    url: '/system/ordeers/addGoodsList',
    method: 'post',
    data: data
  });
}

// 根据用户电话号码查询用户名称
export function selectMemberName(memberPhone) {
  return request({
    url: '/system/users/member/' + memberPhone,
    method: 'get'
  })
}

// 新增会员
export function addUsers(data) {
  return request({
    url: '/system/users',
    method: 'post',
    data: data
  })
}

// 查询部门下拉树结构
export function cateTreeSelect() {
  return request({
    url: '/system/goods/categoryTree',
    method: 'get'
  })
}

// 新增商品
export function addGoods(data) {
  return request({
    url: '/system/goods',
    method: 'post',
    data: data
  })
}
