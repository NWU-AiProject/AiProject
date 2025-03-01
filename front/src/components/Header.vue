<template>
  <header class="main-header">
    <h1 class="logo">西大 Chat</h1>
    <div class="left-actions">
      <el-button type="primary" plain @click="handleNewChat">
        <el-icon>
          <Plus/>
        </el-icon>
        新对话
      </el-button>
      <el-upload
          class="upload-btn"
          action="/api/upload"
          :show-file-list="false"
          :before-upload="beforeUpload"
          :on-success="handleSuccess"
          :on-error="handleError"
          :data="{ userId: currentUserId }"
      >
        <el-button type="info" plain @click="handleFileUpload">
          <el-icon>
            <Upload/>
          </el-icon>
          上传文件
        </el-button>
      </el-upload>
    </div>

    <div class="right-actions">
      <el-button
          class="user-center-btn"
          type="info"
          circle
          @click="handleUserCenter"
      >
        <el-icon>
          <User/>
        </el-icon>
      </el-button>
    </div>
  </header>
</template>

<script setup>
import {Plus, Upload, User} from "@element-plus/icons-vue";
import {useChatStore} from "@/stores/chat";
import {ref} from "vue";
import {ElMessage} from "element-plus";

const chatStore = useChatStore()

const currentUserId = ref('1') // 替换为实际用户ID
// 文件上传前校验
const beforeUpload = (file) => {
  const isLt10M = file.size / 1024 / 1024 < 10
  if (!isLt10M) {
    ElMessage.error('文件大小不能超过10MB')
    return false
  }
  return true
}

// 上传成功处理
const handleSuccess = (response) => {
  if (response.code === 200) {
    ElMessage.success('上传成功')
    // 将文件信息发送到聊天窗口
    chatStore.addMessage({
      text: `已上传文件：${response.data.fileName}`,
      isUser: true,
      timestamp: new Date().toISOString(),
      fileInfo: response.data
    })
  }
}

// 上传失败处理
const handleError = () => {
  ElMessage.error('文件上传失败')
}

const handleNewChat = () => {
  // TODO: 实现新对话逻辑
  chatStore.clearMessages()
}

const handleFileUpload = () => {
  // TODO: 实现文件上传逻辑
}

const handleUserCenter = () => {
  // TODO: 实现用户中心逻辑
}
</script>

<style lang="scss" scoped>
.main-header {
  //background: #ffffff;
  //color: $text-primary;
  //padding: 1rem 2rem;
  //border-bottom: 1px solid $border-color-base;
  //height: 64px;
  //display: flex;
  //align-items: center;
  //position: relative;

  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  background: white;
  height: 64px;
  //transition: width;
  //width: 1400px;

  .left-actions {
    display: flex;
    gap: 12px;

    .el-button {
      padding: 8px 16px;
    }
  }

  .user-center-btn {
    width: 40px;
    height: 40px;
    padding: 8px;

    .el-icon {
      font-size: 20px;
    }
  }

  h1 {
    font-size: 1.5rem;
    margin: 0;
    font-weight: 600;
  }
}
</style>
