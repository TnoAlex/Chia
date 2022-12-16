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
                path: '/index',
                name: "index",
                component: () => import('@/components/index/index'),
                meta: {
                    requireAuth: true
                }
            },
            {
                path: '/studentList',
                name: "studentList",
                component: () => import('@/components/studentInformation/studentList'),
                meta: {
                    requireAuth: true
                }
            },
            {
                path: '/error',
                name: 'error',
                component: () => import('@/components/error')
            },
            {
                path: '/personal_info',
                name: 'personal-info',
                component: () => import('@/components/personal_info'),
                meta: {
                    requireAuth: true
                }
            },

            {
                path: '/message',
                name: 'message',
                component: () => import('@/components/message'),
                meta: {
                    requireAuth: true
                }
            },
            {
                path: '/officialEnroll',
                name: 'officialEnroll',
                component: () => import('@/components/officialEnroll'),
                meta: {
                    requireAuth: true
                }
            },
            {
                path: '/teacherManager',
                name: 'teacherManager',
                component: () => import('@/components/teacherManager'),
                meta: {
                    requireAuth: true
                }
            },
            {
                path: '/forget_password',
                name: 'forgetPassword',
                component: () => import('@/components/forget_password')
            },
            {
                path: '/enable_account',
                name: 'enableAccount',
                component: () => import('@/components/enable_account')
            }
        ],
    }
)
export default router