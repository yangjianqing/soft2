import request from '@/utils/request'

// 根据商品编码查询商品详细
export function getGoodsList(coding) {
  return request({
    url: '/system/goods/Goods/' + coding,
    method: 'get'
  })
}

// 新增商品
export function addGoodsList(formData) {
  return request({
    url: '/system/goods/addGoodsList',
    method: 'post',
    data: formData
  });
}

// 根据用户电话号码查询用户名称
export function selectMemberName(memberPhone) {
  return request({
    url: '/system/users/member/' + memberPhone,
    method: 'get'
  })
}
