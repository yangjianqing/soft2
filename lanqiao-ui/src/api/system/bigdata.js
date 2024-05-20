import request from '@/utils/request'

// 查询大数据可视化页面列表
export function listBigdata(query) {
  return request({
    url: '/system/bigdata/list',
    method: 'get',
    params: query
  })
}

