import request from "@/axios/request"

export function login(user) {
  return request({
    url: '/api/fresh/LoginUsers',
    method: 'post',
    data:user,
  })
}

// 登录方法
// export function login(usersPhone, usersPassword) {
//   const data = {
//     usersPhone,
//     usersPassword,
//   }
//   return request({
//     url: 'api/fresh/login',
//     headers: {
//       isToken: false
//     },
//     method: 'post',
//     data: data
//   })
// }

// 注册方法
// export function register(usersPhone,usersPassword,code,uuid) {
//   const data = {
//     usersPhone,
//     usersPassword,
//     code,
//     uuid
//   }
//   return request({
//     url: '/api/fresh/registered',
//     headers: {
//       isToken: false
//     },
//     method: 'post',
//     data: data
//   })
// }
// 获取验证码
export function sendCode() {
  return request({
    url: '/api/fresh/sendCode',
    headers: {
      isToken: false
    },
    method: 'get',
    timeout: 20000
  })
}


// 退出方法
export function logout() {
  return request({
    url: 'api/fresh/logout',
    method: 'post'
  })
}


