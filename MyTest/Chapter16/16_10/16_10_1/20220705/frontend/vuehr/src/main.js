// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from './store'
import {getRequest} from './utils/api'
import {postRequest} from './utils/api'
import {deleteRequest} from './utils/api'
import {putRequest} from './utils/api'
import {initMenu} from './utils/utils'


//少了以下几行代码，少了包导入，
//会导致右上角无法显示小铃铛，无法进入在线聊天有关页面 
import {isNotNullORBlank} from './utils/utils'
import './utils/filter_utils'
import 'font-awesome/css/font-awesome.min.css'
Vue.prototype.isNotNullORBlank = isNotNullORBlank;



Vue.config.productionTip = false
Vue.use(ElementUI)

Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.putRequest = putRequest;




/**
 * 开启路由全局守卫
 */
router.beforeEach((to, from, next)=> {
    if (to.name == 'Login') {
      next();
      return;
    }

    // 获取store 中保存的当前登录的用户数据
    var name = store.state.user.name;
    
    // 判断用户是否已经登录
    if (name == '未登录') {

      // 判断该页面是否要求登陆后才能访问
      if (to.meta.requireAuth || to.name == null) {
      
        //跳转至登录页面，并配置redirect 参数  
        next({path: '/', query: {redirect: to.path}})
      }
       else {
        next();
      }
    } else {
      initMenu(router, store);

      if(to.path=='/chat')
      store.commit("updateMsgList", []);

      next();
    }
  }
)



new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {App}
})
