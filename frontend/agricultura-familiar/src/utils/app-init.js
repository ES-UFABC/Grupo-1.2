import Vue from 'vue'
import '../styles/app.scss';

//Bootstrap
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

//Vue mask
import VueMask from 'v-mask';
Vue.use(VueMask);

//SweetAlert2
import VueSweetalert2 from "vue-sweetalert2";
import "sweetalert2/dist/sweetalert2.min.css";
Vue.use(VueSweetalert2);

//axios
import axios from 'axios'
Vue.prototype.$http = axios;
