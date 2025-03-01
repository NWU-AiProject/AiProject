import {defineStore} from 'pinia'
import request from '@/utils/request'
import {ref} from "vue";

export const useHistoryStore = defineStore('history', {
    state: () => ({
        todayHistory: ref([
            {
                id: 1,
                content: "如何学习Vue3？",
                timestamp: "2023-07-20T09:00:00Z"
            },
            {
                id: 2,
                content: "Spring Boot配置问题",
                timestamp: "2023-07-20T10:30:00Z"
            }
        ]),
        weeklyHistory: ref([
            {
                id: 3,
                content: "前端性能优化方法",
                timestamp: "2023-07-18T14:00:00Z"
            },
            {
                id: 4,
                content: "REST API设计规范",
                timestamp: "2023-07-17T16:45:00Z"
            }
        ]),
        loading: false,
        error: null
    }),
    actions: {
        deleteHistoryItem(id) {
            this.todayHistory = this.todayHistory.filter(item => item.id !== id)
            this.weeklyHistory = this.weeklyHistory.filter(item => item.id !== id)
        },
        renameHistoryItem(id, newContent) {
            const renameItem = (items) => {
                return items.map(item => {
                    if (item.id === id) {
                        return {...item, content: newContent}
                    }
                    return item
                })
            }
            this.todayHistory = renameItem(this.todayHistory)
            this.weeklyHistory = renameItem(this.weeklyHistory)
        },
        async fetchHistories(userId) {
            this.loading = true
            try {
                // 获取今日记录
                const todayRes = await request.get('/history/today', {
                    params: {userId}
                })
                this.todayHistory = todayRes.data || []

                // 获取7天记录
                const weeklyRes = await request.get('/history/weekly', {
                    params: {userId}
                })
                this.weeklyHistory = weeklyRes.data || []

            } catch (error) {
                this.error = error.message
                console.error('获取历史记录失败:', error)
            } finally {
                this.loading = false
            }
        },
        persist: {
            enabled: true,
            strategies: [
                {
                    key: 'history-store',
                    storage: localStorage
                }
            ]
        }
    }
})