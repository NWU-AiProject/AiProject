import axios from 'axios'

const service = axios.create({
    baseURL: 'http://localhost:8080/api',
    timeout: 10000
})

// 请求拦截器
service.interceptors.request.use(config => {
    return config
})

// 响应拦截器
service.interceptors.response.use(
    response => {
        // 在request.js中添加状态码拦截
        if (response.status !== 200) {
            return Promise.reject(new Error(response.data.message || 'Error'))
        }
        return response.data
    },
    error => {
        return Promise.reject(error)
    }
)

export default service