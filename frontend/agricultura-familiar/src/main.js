import Vue from 'vue'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import VueMask from 'v-mask';

import './styles/app.scss';

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)

Vue.use(VueMask);

import store from './stores';

import App from './App'
import router from './routes';


new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App)
})
