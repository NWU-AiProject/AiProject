import {defineStore} from 'pinia'

export const useChatStore = defineStore('chat', {
    state: () => ({
        messages: [],
        loading: false
    }),
    actions: {
        addMessage(message) {
            this.messages.push(message)
        },
        clearMessages() {
            this.messages = []
        },
        async sendMessage(payload) {
            this.loading = true
            try {
                // 添加用户消息
                this.addMessage({
                    text: payload.message,
                    isUser: true,
                    timestamp: new Date().toISOString()
                })
                // 这里会添加AI回复（实际由后端返回）
            } catch (error) {
                console.error('发送消息失败:', error)
            } finally {
                this.loading = false
            }
        }
    }
})