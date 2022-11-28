import { createApp } from 'vue'
import App from './App.vue'
import axios from "axios"
import router from "@/router"
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import locale from 'element-plus/lib/locale/lang/zh-cn'
import MyUtil from '@/assets/Common/myUtil'
const app = createApp(App)
app.use(router)
app.use(ElementPlus,{locale})
app.config.globalProperties.$axios = axios
app.config.globalProperties.$MyUtil = MyUtil
axios.defaults.baseURL = "/api"
app.mount('#app')
