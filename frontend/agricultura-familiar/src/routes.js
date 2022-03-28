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
      name: 'Home',
      component: Home,
      children: [
        {
          path: 'login',
          name: 'Login',
          component: Login,
        },
        {
          path: 'cadastro/consumidor',
          name: 'Cadastro Consumidor',
          component: CadastroConsumidor,
        },
        {
          path: 'cadastro/produtor',
          name: 'Cadastro Produtor',
          component: CadastroProdutor,
        }
      ]
    },
  ]
});
