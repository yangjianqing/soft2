import request from "@/axios/request"



// 查询商品管理列表
export function listShopping(query) {
  return request({
    url: '/api/fresh/goodsList',
    method: 'get',
    params: query
  })
}


//根据商品id查询商品详情
export function selectIdByInfo(id) {
  return request({
    url: '/api/fresh/goodsId/'+id,
    method: 'get',
  })
}

//根据大分类查询商品
export function categoryList( query){
  return request({
    url: '/api/fresh/categoryList',
    method: 'get',
    params: query
  })

}


//根据大分类查询商品
export function goodsList2( deptId){
  return request({
    url: '/api/fresh/goodsList2/'+deptId,
    method: 'get',
  })
}


//根据tab栏的id查询对应商品推荐
export function recommendedList(sortNum){
  return request({
    url: '/api/fresh/recommendedList/'+sortNum,
    method: 'get',
  })
}

//根据分类显示对应的分类图片
export function selectPicture(query){
  return request({
    url: '/api/fresh/selectPicture',
    method: 'get',
    params: query,
  })
}



//根据搜索内容查询商品
export function goods(query){
  return request({
    url: '/api/fresh/goods',
    method: 'get',
    params: query,
  })
}
