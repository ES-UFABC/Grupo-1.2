import Vue from 'vue'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

import './styles/app.scss';

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)




import App from './App'
import router from './routes';


new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
