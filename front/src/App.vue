<template>
  <div id="app">
    <Sidebar />
    <div class="main-container" :class="{ 'collapsed': uiStore.sidebarCollapsed }">
      <Header />
      <HomeView />
    </div>
  </div>
</template>

<script setup>
import Sidebar from './components/Sidebar.vue'
import Header from './components/Header.vue'
import HomeView from './views/HomeView.vue'
import { useUIStore } from './stores/ui'

const uiStore = useUIStore()
</script>

<style lang="scss" scoped>
#app {
  display: flex;
  min-height: 100vh;
  position: fixed;


  .main-container {
    flex: 1;
    margin-left: 240px;
    transition: margin-left 0.3s ease;
    display: flex;
    flex-direction: column;
    min-width: 0; // 修复flex布局溢出问题

    &.collapsed {
      margin-left: 64px;
    }
  }
}
</style>