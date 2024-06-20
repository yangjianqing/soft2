import request from "@/axios/request"



// 查询商品管理列表
export function listShopping(query) {
  return request({
    url: '/api/fresh/goodsList',
    method: 'get',
    params: query
  })
}

export function listShoppings(phone) {
  return request({
    url: '/api/fresh/selectShopData'+phone,
    method: 'get',
    params: phone
  })
}

//根据商品id查询商品详情
export function selectIdByInfo(id) {
  return request({
    url: '/api/fresh/goodsId/'+id,
    method: 'get',
  })
}

//查询超盒算列表
export function selectFGoodsByFavorable(query){
  return request({
    url: '/api/fresh/favorableList',
    method: 'get',
    params: query
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
export function goods(keyword){
  return request({
    url: '/api/fresh/goods/'+keyword,
    method: 'get',
    params:  { keyword },
  })
}

//首页广告图片
export function selectFadvertisementList(query){
  return request({
    url: '/api/fresh/selectFadvertisementList',
    method: 'get',
    params: query,
  })
}


//添加商品到购物车
export function addCarList(phone,code){
  return request({
    url: '/api/fresh/insertShopData/'+phone+'/'+code,
    method: 'get',
  })
}
//获取商品到购物车

//用户地址列表查询
export function addressList(keyword){
  return request({
    url: '/api/fresh/selectUsersAddressList/'+keyword,
    method: 'get',
    params: {keyword},
  })
}

//新增地址
export function addAddress(address){
  return request({
    url: 'api/fresh/addUsersAddressList',
    method: 'post',
    data: address
  })
}

//修改地址
export function editUsersAddress(address){
  return request({
    url: 'api/fresh/editUsersAddressList',
    method: 'put',
    data: address
  })
}

//删除地址
export function removeUsersAddress(addressIds){
  return request({
    url: 'api/fresh/removeUsersAddressList/'+addressIds,
    method: 'delete'
  })
}

export function getCarList(phone){
  return request({
    url: '/api/fresh/selectShopData/'+phone,
    method: 'get',
  })
}
export function selectOrders(phone){
  return request({
    url: '/api/fresh/selectOrders/'+phone,
    method: 'get',
  })
}

//获取商品到购物车
export function deleteCarList(phone,code){
  return request({
    url: '/api/fresh/deleteShopData/'+phone+'/'+code,
    method: 'get',
  })
}
//首页地址
export function selectUsersAddressList(userPhone){
  return request({
    url:"/api/fresh/selectUsersAddressList/"+userPhone,
    method: 'get',

  })
}
export function insertSettlement(data){
  return request({
    url:"/api/fresh/insertSettlement",
    method: 'post',
    data:data,
  })
}



