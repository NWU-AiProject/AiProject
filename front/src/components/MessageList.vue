<template>
  <div class="message-list">

    <!-- 新增欢迎提示 -->
    <div v-if="showWelcome" class="welcome-message">
      <div class="welcome-content">
        <el-icon class="welcome-icon">
          <ChatRound/>
        </el-icon>
        <h2>欢迎您提问！</h2>
        <p>请输入您的问题开始对话</p>
      </div>
    </div>

    <div
        v-for="(msg, index) in messages"
        :key="index"
        class="message-container"
        :class="{'user-container':msg.isUser,'ai-container':!msg.isUser}"
    >
      <MessageItem
          :text="msg.text"
          :is-user="msg.isUser"
          :timestamp="msg.timestamp"
      />
    </div>
  </div>
</template>

<script setup>
import MessageItem from './MessageItem.vue'
import {ChatRound} from "@element-plus/icons-vue";

defineProps({
  // messages: {
  //   type: Array,
  //   required: true
  // },
  messages: Array,
  showWelcome: Boolean
})
</script>

<style lang="scss" scoped>
.message-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding: 20px;

  .message-container {
    max-width: 80%;

    &.user-container {
      margin-left: auto;
    }

    &.ai-container {
      margin-right: auto;
    }
  }

  .welcome-message {
    height: calc(100vh - 300px);
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;
    color: black;

    .welcome-content {
      max-width: 400px;

      .welcome-icon {
        font-size: 80px;
        color: $primary-color;
        margin-bottom: 20px;
      }

      h2 {
        font-size: 24px;
        margin: 10px 0;
      }

      p {
        font-size: 16px;
        color: $text-regular;
      }
    }
  }
}
</style>