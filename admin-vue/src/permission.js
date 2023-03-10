import router from "./router";
// 路由判断登录 根据路由配置文件的参数
router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requireAuth)) { // 判断该路由是否需要登录权限
        const info = JSON.parse(sessionStorage.getItem("userInfo"))
        if (info) { // 判断当前的userInfo是否存在
            if (to.path === '/login') {
            } else {
                next()
            }
        } else {
            next({
                path: '/login'
            })


        }
    } else {
        next()
    }
})
