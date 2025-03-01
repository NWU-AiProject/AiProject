<template>
  <div class="chat-window">
    <div class="scroll-container">
      <MessageList :messages="messages" :show-welcome="showWelcome"/>
    </div>
  </div>
</template>

<script setup>
import MessageList from './MessageList.vue'
// import {useChatStore} from '@/stores/chat'

defineProps({
  messages: Array,
  showWelcome: Boolean
})
</script>

<style lang="scss" scoped>
.chat-window {
  flex: 1;
  position: relative;
  overflow: hidden;

  .scroll-container {
    height: calc(100vh - 400px); /* 根据实际布局调整 */
    overflow-y: auto;
    padding: 10px;

    /* 自定义滚动条 */
    &::-webkit-scrollbar {
      width: 0;
    }

    &::-webkit-scrollbar-thumb {
      background-color: rgba(144, 147, 153, 0.3);
      border-radius: 3px;
    }
  }
}
</style>