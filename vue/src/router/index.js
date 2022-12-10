import {createRouter, createWebHistory} from "vue-router"

const router = createRouter({

        history: createWebHistory(),
        routes: [
            {
                path: '/',
                name: "login",
                component: () => import('@/components/login')
            },
            {
                path:'/index',
                name:"index",
                component:()=>import('@/components/index/index')
            },
            {
                path: '/studentList',
                name: "studentList",
                component: () => import('@/components/studentInformation/studentList')
            },
            {
                path: '/error',
                name: 'error',
                component: () => import('@/components/error')
            },
            {
                path: '/personal_info',
                name: 'personal-info',
                component: ()=>import('@/components/personal_info')
            },

            {
                path: '/message',
                name: 'message',
                component: ()=>import('@/components/message')
            },
        ],
    }
)
export default router