import Vue from 'vue';
import VueRouter from 'vue-router';

import Home from './components/home/Home'
import Login from './views/login/Login';
import CadastroConsumidor from './views/cadastro-consumidor/CadastroConsumidor';
import CadastroProdutor from './views/cadastro-produtor/CadastroProdutor';

Vue.use(VueRouter);


export default new VueRouter ({
  mode: 'hash',
  routes: [
    {
      path: '/',
      name: 'home',
      title: 'Home',
      component: Home,
      children: [
        {
          path: 'login',
          name: 'login',
          title: 'Login',
          component: Login,
        },
        {
          path: 'cadastro/consumidor',
          name: 'cadastro_consumidor',
          title: 'Cadastro Consumidor',
          component: CadastroConsumidor,
        },
        {
          path: 'cadastro/produtor',
          name: 'cadastro_produtor',
          title: 'Cadastro Produtor',
          component: CadastroProdutor,
        }
      ]
    },
  ]
});
