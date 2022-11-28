import {createRouter, createWebHistory} from "vue-router"

const router = createRouter({

        history: createWebHistory(),
        routes: [
            {
                path:'/',
                name:"login",
                component:()=>import('@/components/login')
            },
            {
                path: '/forget_password',
                name: 'forget_password',
                component: () => import('@/components/forget_password')
            },
            {
                path: '/error',
                name: 'error',
                component:()=>import('@/components/error')
            }
        ],
    }
)
export default router