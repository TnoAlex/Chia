import { createApp } from 'vue'
import App from './App.vue'
import axios from "axios"
import router from "@/router"
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

const app = createApp(App)
app.use(router)
app.use(ElementPlus)
app.config.globalProperties.$axios = axios
axios.defaults.baseURL = "/api"
app.mount('#app')

