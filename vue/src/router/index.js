import {createRouter, createWebHistory} from "vue-router"

const router = createRouter({

        history: createWebHistory(),
        routes: [
            {
                path:'/',
                name:"login",
                component:()=>import('@/components/login')
            },
        ],
    }
)
export default router