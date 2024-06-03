import request from '@/utils/request'

// 根据商品编码查询商品详细
export function getGoodsList(coding) {
  return request({
    url: '/system/goods/Goods/' + coding,
    method: 'get'
  })
}

// 新增商品
export function addGoodsList(data) {
  return request({
    url: '/system/goods/addGoodsList',
    method: 'post',
    data: data
  })
}
