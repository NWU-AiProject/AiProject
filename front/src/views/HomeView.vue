<template>
  <div class="chat-container" :class="{ 'sidebar-collapsed': uiStore.sidebarCollapsed }">
    <div class="chat-content">
      <ChatWindow :messages="chatStore.messages" :show-welcome="showWelcome"/>
    </div>
    <InputArea
        @send-message="handleSend"
        :loading="chatStore.loading"
    />
  </div>
</template>

<script setup>
import ChatWindow from '../components/ChatWindow.vue'
import InputArea from '../components/InputArea.vue'
import {useChatStore} from '@/stores/chat'
import request from '../utils/request'
import {useUIStore} from "@/stores/ui";
import {computed} from "vue";

const uiStore = useUIStore()
const chatStore = useChatStore()

const showWelcome = computed(() => {
  return chatStore.messages.length === 0
})

const handleSend = async (message) => {

  if (chatStore.loading) return
  chatStore.loading = true
  try {

    chatStore.addMessage({
      text: message,
      isUser: true,
      timestamp: new Date().toISOString()
    });

    const response = await request.post('/chat', {
      message: message,
      userId: 1 // 替换为实际用户ID获取逻辑
    });

    chatStore.addMessage({
      text: response.data, // 根据实际返回结构调整
      isUser: false,
      timestamp: new Date().toISOString()
    })
  } catch (error) {
    chatStore.addMessage({
      text: '请求失败，请稍后重试',
      isUser: false,
      timestamp: new Date().toISOString(),
      isError: true
    })
  } finally {
    chatStore.loading = false
  }
}
</script>

<style lang="scss" scoped>
.chat-container {
  flex: 1;
  display: flex;
  flex-direction: column;
  background: white;
  padding: 20px 20px 80px; // 底部预留输入框空间
  height: calc(1000px - 270px);
  max-width: 100px;
  margin: 0 auto;
  position: relative;
  box-sizing: border-box; // 新增关键属性
  overflow: hidden; // 防止内容溢出

  // 侧边栏展开时的调整
  &:not(.sidebar-collapsed) {
    margin-left: -100px;
    max-width: calc(100vw - 520px); // 根据视口动态计算
    max-height: 840px;
    width: calc(100vw - 320px);


  }

  // 侧边栏收起时的调整
  &.sidebar-collapsed {
    max-width: calc(100vw - 300px); // 动态适配宽度
    width: calc(100vw - 100px);
  }

  .chat-content {
    flex: 1;
    overflow-y: auto;
    padding-bottom: 20px;
  }
}
</style>