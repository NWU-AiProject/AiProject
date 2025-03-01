<template>
  <div class="input-area">
    <div class="input-wrapper">
      <el-input
          class="send-area"
          v-model="inputMessage"
          type="textarea"
          :rows="3"
          :resize="none"
          placeholder="输入你的问题..."
          :disabled="loading"
          @keydown.enter.exact.prevent="handleSend"
      />
      <el-button
          type="primary"
          class="send-btn"
          :disabled="loading"
          :class="{'disabled-btn':loading}"
          @click="handleSend"
      >
        发送
      </el-button>
    </div>
  </div>
</template>

<script setup>
import {ref} from 'vue'

const props = defineProps({
  loading: Boolean
})

const inputMessage = ref('')
const emit = defineEmits(['send-message'])

const handleSend = () => {
  if (inputMessage.value.trim() && !props.loading) {
    emit('send-message', inputMessage.value.trim())
    inputMessage.value = ''
  }
}
</script>

<style lang="scss" scoped>
.input-area {
  .disabled-btn {
    background-color: #606266 !important;
    border-color: #606266 !important;
    cursor: not-allowed !important;

    &:hover {
      background-color: #606266 !important;
      border-color: #606266 !important;
    }
  }

  .send-btn {
    margin-left: 1120px;
  }

  ::-webkit-scrollbar {
    width: 4px !important;
    height: 4px !important;
  }

  // 输入框透明边框
  ::v-deep .el-textarea__inner {
    font-size: 20px !important;
    line-height: 1.2 !important;
    border: none !important;
    box-shadow: none !important;
    padding-right: 40px !important; // 给按钮留出空间

    // 滚动条样式
    &::-webkit-scrollbar {
      width: 4px !important;
      background-color: transparent;
    }

    &::-webkit-scrollbar-thumb {
      background-color: rgba(0, 0, 0, 0.2);
      border-radius: 2px;
    }

    // 禁用resize
    resize: none !important;
  }

  // 聚焦状态样式
  ::v-deep .el-textarea.is-focus .el-textarea__inner {
    border: none !important;
    box-shadow: 0 0 0 1px rgba(64, 158, 255, .2) inset !important;
  }

  position: relative; // 改为固定定位
  bottom: -60px;
  left: 50%;
  transform: translateX(-50%);
  width: calc(100% - 40px); // 保留边距
  max-width: 1200px;
  background: white;
  box-shadow: 0 -2px 12px rgba(0, 0, 0, 0.05);
  border-radius: 8px;
  z-index: 100;
  //border: 2px black;

  .input-wrapper {
    position: relative;
    padding: 10px;

    //border: 2px black;
  }
}
</style>