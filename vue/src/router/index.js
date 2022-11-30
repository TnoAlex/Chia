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
                path: '/create_csp',
                name: "create_csp",
                component: () => import('@/components/index/createCsp')
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
                path: '/forget_password',
                name: 'forget_password',
                component: () => import('@/components/forget_password')
            }

        ],
    }
)
export default router