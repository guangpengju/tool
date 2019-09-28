// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'

import router from './router'
import store from './store'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)

import MuseUI from 'muse-ui';
import 'muse-ui/dist/muse-ui.css';
Vue.use(MuseUI);

import common from '@/common/common';
Vue.prototype.common = common;

Vue.config.productionTip = false
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
// TODO
/*
1.主布局样式调整，maintemplate长宽计算方式，主布局回行布局【定宽布局，定长布局，定长宽布局，变长宽布局】 1h
2.组件选择后的按钮实现 2h
3.属性编辑实现 2h
4.事件编辑实现 2h
5.预览实现 2h
6.源码编辑实现 2h
 */