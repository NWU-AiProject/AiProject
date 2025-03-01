<template>
  <div class="sidebar" :class="{ 'collapsed': isCollapsed }">
    <!-- 顶部区域 -->
    <div class="top-section">
      <transition name="fade">
        <img
            v-if="!isCollapsed"
            src="../assets/logo.svg"
            alt="Logo"
            class="logo"
        >
      </transition>
      <el-tooltip :content="isCollapsed ? '展开' : '收起'" placement="right">
        <el-button
            class="collapse-btn"
            @click="toggleSidebar"
            circle
        >
          <el-icon v-if="isCollapsed">
            <Expand/>
          </el-icon>
          <el-icon v-else>
            <Fold/>
          </el-icon>
        </el-button>
      </el-tooltip>
    </div>

    <!-- 中间内容区域 -->
    <div class="history-section" v-show="!isCollapsed">
      <div class="history-container">
        <!-- today -->
        <div class="history-group">
          <h3 class="history-title">今日记录</h3>
          <div
              v-for="item in todayHistory"
              :key="item.id"
              class="history-item-wrapper"
          >
            <div class="history-item">
              <span class="truncate-text">{{ item.content }}</span>
              <el-dropdown
                  trigger="click"
                  placement="bottom-end"
                  @command="handleCommand($event,item)"
              >
                <el-button
                    class="action-btn"
                    :icon="MoreFilled"
                    size="small"
                    circle
                />
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item command="rename">重命名</el-dropdown-item>
                    <el-dropdown-item command="delete" divided>删除</el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
            </div>
          </div>
        </div>
        <div class="history-group">
          <h3 class="history-title">最近7天</h3>
          <div
              v-for="item in weeklyHistory"
              :key="item.id"
              class="history-item-wrapper"
          >
            <div class="history-item">
              <span class="truncate-text">{{ item.content }}</span>
              <el-dropdown
                  trigger="click"
                  placement="bottom-end"
                  @command="handleCommand($event, item)"
              >
                <el-button
                    class="action-btn"
                    :icon="MoreFilled"
                    size="small"
                    circle
                />
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item command="rename">重命名</el-dropdown-item>
                    <el-dropdown-item command="delete" divided>删除</el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
            </div>
          </div>
        </div>
        <!-- 底部区域 -->
      </div>
    </div>

    <div class="bottom-section">
      <div class="bottom-content">
        <img
            src="../assets/logo.svg"
            alt="Logo"
            class="bottom-logo"
            :class="{ 'centered': isCollapsed }"
        >
        <span v-show="!isCollapsed" class="user-info">个人信息</span>
      </div>
    </div>
  </div>
</template>

<script setup>
import {Fold, Expand, MoreFilled} from '@element-plus/icons-vue'
import {useUIStore} from '@/stores/ui'
import {computed, onMounted} from "vue"
import {useHistoryStore} from "@/stores/history";
// import ElementUI from 'element-ui'

const uiStore = useUIStore()
const historyStore = useHistoryStore()
const isCollapsed = computed(() => uiStore.sidebarCollapsed)

//模拟
const userId = 1

onMounted(async () => {
  await historyStore.fetchHistories(userId)
})

// const loading = computed(() => historyStore.loading)
// const error = computed(() => historyStore.error)
const todayHistory = computed(() => historyStore.todayHistory)
console.log(todayHistory)
// const weeklyHistory = computed(() => historyStore.weeklyHistory)

const toggleSidebar = () => {
  uiStore.toggleSidebar()
}

// 模拟操作处理
const handleCommand = (command, item) => {
  if (command === 'delete') {
    // 实际应通过store删除
    todayHistory.value = todayHistory.value.filter(i => i !== item)
  } else if (command === 'rename') {
    // 弹出重命名对话框
    const newName = prompt('请输入新名称', item.content)
    if (newName) {
      item.content = newName
    }
  }
}
</script>

<style lang="scss" scoped>
.sidebar {

  .history-section {
    flex: 1;
    overflow: hidden;
    display: flex;
    flex-direction: column;
    margin-top: 40px;
    padding: 10px 16px 16px;
    border-top: 1px solid #ebeef5;

    .history-container {
      flex: 1;
      overflow-y: auto;
      padding-right: 8px;
      margin-right: -8px; // 抵消滚动条空间

      .history-group {
        .history-item-wrapper {
          position: relative;
          margin: 4px 0;

          .history-item {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 8px 12px;
            background: white;
            border-radius: 6px;
            cursor: pointer;
            transition: all 0.2s;

            &:hover {
              background: #f5f7fa;

              .action-btn {
                opacity: 1;
              }
            }

            // 文字截断样式
            & > :first-child {
              white-space: nowrap;
              overflow: hidden;
              text-overflow: ellipsis;
              flex: 1;
              margin-right: 8px;
            }
          }

          .action-btn {
            opacity: 0;
            transition: opacity 0.2s;
            padding: 4px;
            width: 24px;
            height: 24px;

            &:hover {
              background: #e8f4ff;
            }
          }
        }

        margin-bottom: 24px;
        background: #f8f9fa;
        border-radius: 8px;
        padding: 12px;

        &:last-child {
          margin-bottom: 0;
        }

        .history-title {
          margin: 0 0 12px 0;
          font-size: 18px;
          color: black;
          font-weight: 600;
          padding-bottom: 8px;
          border-bottom: 0 solid $border-color-base;
        }

        &:hover {
          background: #e9ecef;
          transform: translateX(4px);
        }
      }
    }
  }

  // 滚动条样式
  .history-container::-webkit-scrollbar {
    width: 6px;
  }

  .history-container::-webkit-scrollbar-thumb {
    background-color: rgba(144, 147, 153, 0.3);
    border-radius: 3px;
  }

  width: 220px;
  height: 940px;
  background: #ffffff;
  border-right: 1px solid #ebeef5;
  display: flex;
  flex-direction: column;
  transition: width 0.3s ease;
  position: relative;

  &.collapsed {
    width: 64px;

    .bottom-logo {
      width: 28px !important;
    }
  }

  .top-section {
    padding: 20px 20px 15px;
    min-height: 120px;
    position: relative;
    display: flex;
    justify-content: space-between;
    align-items: flex-start;

    .logo {
      width: 120px;
      transition: width 0.3s ease;
    }

    .collapse-btn {
      position: absolute;
      right: 20px;
      top: 140px;
      width: 32px;
      height: 32px;
      font-size: 16px;
      z-index: 1;
    }
  }

  .bottom-section {
    margin-top: auto; // 关键属性，固定到底部
    padding: 20px;
    border-top: 1px solid $border-color-base;

    .bottom-content {
      display: flex;
      align-items: center;
      gap: 12px;

      .bottom-logo {
        width: 36px;
        transition: all 0.3s ease;

        &.centered {
          margin: 0 auto;
        }
      }

      .user-info {
        font-size: 14px;
        color: $text-secondary;
        white-space: nowrap;
      }
    }
  }

}
</style>