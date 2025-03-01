import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
// import ElementUI from 'element-ui'

const app = createApp(App)
app.use(createPinia())
app.use(ElementPlus)
app.mount('#app')