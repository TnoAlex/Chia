import {createApp} from 'vue'
import App from './App.vue'
import axios from "axios"
import router from "@/router"
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import locale from 'element-plus/lib/locale/lang/zh-cn'
import VueCookies from 'vue-cookies'
import util from "@/utils/commonUtil"

const app = createApp(App)
app.use(router)
app.use(VueCookies)
app.use(ElementPlus, {locale})
app.config.globalProperties.$axios = axios
axios.defaults.baseURL = "/api"
app.mount('#app')

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requireAuth)) {
        const user = localStorage.getItem('userInfo');
        if (user)
            next(true)
        else {
            next('/')
            util.messageBox(
                '请先登录', 'error')
        }
    } else {
        next(true)
    }
})