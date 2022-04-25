import Vue from 'vue';
import VueRouter from 'vue-router';

import Root from './views/root/Root.vue'

import Consumidor from './views/consumidor/Consumidor.vue';
import ConsumidorHome from './views/consumidor/ConsumidorHome.vue';
import ConsumidorLogin from './views/consumidor/ConsumidorLogin.vue';
import ConsumidorCadastro from './views/consumidor/ConsumidorCadastro.vue';
import ConsumidorPanel from './views/consumidor/ConsumidorPanel.vue';

import Produtor from './views/produtor/Produtor.vue';
import ProdutorHome from './views/produtor/ProdutorHome.vue';
import ProdutorLogin from './views/produtor/ProdutorLogin.vue';
import ProdutorCadastro from './views/produtor/ProdutorCadastro.vue';

import Profile from './views/profile/Profile.vue'
import EmailConfirmation from './views/email-confirmation/EmailConfirmation.vue';
import CodeEnter from './views/email-confirmation/CodeEnter.vue';
import ConfirmationDone from './views/email-confirmation/ConfirmationDone.vue';


Vue.use(VueRouter);

const router = new VueRouter({
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
      component: Consumidor,
      children: [
        {
          path: '/',
          name: 'home_consumidor',
          title: 'Home',
          component: ConsumidorHome,
        },
        {
          path: 'login',
          name: 'login_consumidor',
          title: 'Login',
          component: ConsumidorLogin,
        },
        {
          path: 'cadastro',
          name: 'cadastro_consumidor',
          title: 'Cadastro Consumidor',
          component: ConsumidorCadastro,
        }
      ]
    },
    {
      path: '/produtor',
      name: 'produtor_home',
      title: 'Produtor',
      component: Produtor,
      children: [
        {
          path: '/',
          name: 'home_produtor',
          title: 'Home',
          component: ProdutorHome,
        },
        {
          path: 'login',
          name: 'login_produtor',
          title: 'Login',
          component: ProdutorLogin,
        },
        {
          path: 'cadastro',
          name: 'cadastro_produtor',
          title: 'Cadastro Produtor',
          component: ProdutorCadastro,
        },
      ]
    },
    {
      path: '/profile',
      name: 'perfil',
      title: 'Perfil',
      component: Profile
    },
    {
      path: '/consumidor-panel',
      name: 'panel_consumidor',
      title: 'Panel Consumidor',
      component: ConsumidorPanel
    },
    {
      path: '/email-confirmation',
      name: 'email_confirmation',
      title: 'Email Confirmation',
      component: EmailConfirmation,
      children: [
        {
          path: '/',
          name: 'code_enter',
          title: 'Code Enter',
          component: CodeEnter
        },
        {
          path: 'confirmation-done',
          name: 'confirmation_done',
          title: 'Confirmation Done',
          component: ConfirmationDone
        }
      ]
    },
  ]
});

router.beforeEach((to, from, next) => {
  const publicPages = ['/', '/consumidor', '/consumidor/home', '/consumidor/login', '/consumidor/cadastro',
    '/produtor', '/produtor/home', '/produtor/login', '/produtor/cadastro', '/consumidor-panel', '/email-confirmation/',
    '/email-confirmation/confirmation-done'];

  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem(process.env.LOCAL_STORAGE_AUTH_KEY);

  if (authRequired && !loggedIn) // trying to access a restricted page + not logged in
    next('/'); // redirect to root page
  else if (!authRequired && loggedIn) // trying to access a public page + logged in
    next('/profile'); // redirect to profile
  else //authRequired + logged in
    next(); //do nothing

});

export default router;
