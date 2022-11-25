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
                path:'/index',
                name:"index",
                component:()=>import('@/components/index/index')
            },
            {
                path:'/create_csp',
                name:"create_csp",
                component:()=>import('@/components/index/create_csp')
            },

        ],
    }
)
export default router