
<template>
  <div>
    <b-card-text>
      Quase lá para entrar na comunidade!
    </b-card-text>
    <b-card-text>
      Escolha uma para continuar
    </b-card-text>
    <b-button-group vertical>
      <b-button>Continuar com Google</b-button>
      <b-button>Continuar com Facebook</b-button>
      <b-button @click="loginEmail">Continuar com Email</b-button>
    </b-button-group>
    <b-card-text>
      Ainda não tem cadastro? <router-link to="/consumidor/cadastro" class="card-link">Registre-se aqui.</router-link>
    </b-card-text>
  </div>
</template>

<script>
  import Voltar from '../../components/voltar/Voltar'
  export default {
    name: 'ConsumidorHome',
    components: { Voltar },
    methods: {
      loginEmail() {
        this.$router.push('/consumidor/login')
      }
    }
  }
</script>
