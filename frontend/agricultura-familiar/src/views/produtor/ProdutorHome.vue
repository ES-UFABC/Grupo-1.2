<template>
  <div>
    <b-card-text>
      Então, Produtor, como deseja fazer diferença?
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
      Ainda não tem cadastro? <router-link to="/produtor/cadastro" class="card-link">Registre-se aqui.</router-link>
    </b-card-text>
  </div>
</template>

<script>
  export default {
    name: 'ProdutorHome',
    methods: {
      loginEmail() {
        this.$router.push('/produtor/login')
      }
    }
  }
</script>

<style type="scss">
</style>
