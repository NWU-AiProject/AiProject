<template>
  <el-dialog
      v-model="visible"
      title="重命名记录"
      width="400px"
  >
    <el-input v-model="newName" />
    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="confirmRename">确认</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref } from 'vue'

const props = defineProps({
  item: Object
})

const emit = defineEmits(['rename'])

const visible = ref(false)
const newName = ref('')

const open = () => {
  newName.value = props.item.content
  visible.value = true
}

const confirmRename = () => {
  emit('rename', props.item.id, newName.value)
  visible.value = false
}

defineExpose({ open })
</script>