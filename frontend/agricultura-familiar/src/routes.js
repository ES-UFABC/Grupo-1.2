import Vue from 'vue';
import VueRouter from 'vue-router';

import Root from './views/root/Root.vue'

import HomeConsumidor from './views/consumidor/home/HomeConsumidor.vue';
import LoginConsumidor from './views/consumidor/login/LoginConsumidor.vue';
import CadastroConsumidor from './views/consumidor/cadastro/CadastroConsumidor.vue';

import HomeProdutor from './views/produtor/home/HomeProdutor.vue';
import LoginProdutor from './views/produtor/login/LoginProdutor.vue';
import CadastroProdutor from './views/produtor/cadastro/CadastroProdutor.vue';

Vue.use(VueRouter);


export default new VueRouter ({
  mode: 'hash',
  routes: [
    {
      path: '/',
      name: 'root',
      title: 'Junte-se',
      component: Root
    },
    {
      path: '/consumidor',
      name: 'consumidor_home',
      title: 'Consumidor',
      component: HomeConsumidor,
      children: [
        {
          path: 'login',
          name: 'login_consumidor',
          title: 'Login',
          component: LoginConsumidor,
        },
        {
          path: 'cadastro',
          name: 'cadastro_consumidor',
          title: 'Cadastro Consumidor',
          component: CadastroConsumidor,
        }
      ]
    },
    {
      path: '/produtor',
      name: 'produtor_home',
      title: 'Produtor',
      component: HomeProdutor,
      children: [
        {
          path: 'login',
          name: 'login_produtor',
          title: 'Login',
          component: LoginProdutor,
        },
        {
          path: 'cadastro',
          name: 'cadastro_produtor',
          title: 'Cadastro Produtor',
          component: CadastroProdutor,
        },
      ]
    },
  ]
});
