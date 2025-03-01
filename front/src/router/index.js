import { createRouter, createWebHistory } from 'vue-router'
import ChatView from '../views/HomeView.vue'

const routes = [
    {
        path: '/',
        name: 'Chat',
        component: ChatView
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router