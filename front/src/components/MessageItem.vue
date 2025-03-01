<template>
  <div class="message-item" :class="{ 'user-message': isUser, 'ai-message': !isUser }">
    <div class="avatar">
      <el-icon v-if="isUser" class="user-avatar">
        <User/>
      </el-icon>
      <el-icon v-else class="ai-avatar">
        <Cpu/>
      </el-icon>
    </div>
    <div class="content">
      <div class="text">{{ text }}</div>
      <div class="timestamp">{{ formattedTime }}</div>
    </div>
  </div>
</template>

<script setup>
import {User, Cpu} from '@element-plus/icons-vue'
import {computed} from 'vue'

const props = defineProps({
  text: String,
  isUser: Boolean,
  timestamp: String
})

const formattedTime = computed(() => {
  return new Date(props.timestamp).toLocaleTimeString([], {
    hour: '2-digit',
    minute: '2-digit'
  })
})
</script>

<style lang="scss" scoped>
.message-item {
  display: flex;
  gap: 12px;
  padding: 12px 16px;
  border-radius: 8px;

  // 通用样式
  .content {
    .text {
      white-space: pre-wrap;
      line-height: 1.6;
    }

    .timestamp {
      margin-top: 6px;
      font-size: 0.75rem;
      color: #666;
    }
  }

  // 用户消息样式
  &.user-message {
    background: #e8f4ff;
    //flex-direction: row-reverse;


    .user-avatar {
      background: #409eff;
      color: white;
    }
  }

  // AI消息样式
  &.ai-message {
    background: #f5f7fa;

    .ai-avatar {
      background: #909399;
      color: white;
    }
  }

  .avatar .el-icon {
    width: 32px;
    height: 32px;
    padding: 6px;
    border-radius: 50%;
  }
}
</style>