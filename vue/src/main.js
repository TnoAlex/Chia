import { createApp } from 'vue'
import App from './App.vue'
import axios from "axios"
import router from "@/router"
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import locale from 'element-plus/lib/locale/lang/zh-cn'

const app = createApp(App)
app.use(router)
app.use(ElementPlus,{locale})
app.config.globalProperties.$axios = axios
axios.defaults.baseURL = "/api"
app.mount('#app')

