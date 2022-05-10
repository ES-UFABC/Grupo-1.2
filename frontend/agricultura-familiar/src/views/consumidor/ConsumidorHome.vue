
<template>
  <div>
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;700&family=Roboto:wght@500&display=swap"
      rel="stylesheet"
    />
    <b-card-title>
      Quase lá para entrar na <span style="color: #38C961">comunidade!</span>
    </b-card-title>
    <b-card-text> Escolha uma para continuar </b-card-text>
    <div class="buttons">
      <button class="button-row" style="background-color: white; color: black">
        <img src="../../assets/google.png" alt="google" />
        <div class="button-text">Continuar com Google</div>
      </button>
      <button class="button-row" style="background-color: #3b5999">
        <img src="../../assets/facebook.png" alt="facebook" />
        <div class="button-text">Continuar com Facebook</div>
      </button>
      <button
        class="button-row"
        @click="loginEmail"
        style="background-color: #38C961"
      >
        <img src="../../assets/email.svg" alt="email" />
        <div class="button-text">Continuar com Email</div>
      </button>
    </div>
    <div class="footer">
      Ainda não tem cadastro?
      <router-link to="/consumidor/cadastro" class="card-link"
        >Registre-se aqui.</router-link
      >
    </div>
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

<style scoped src="../../styles/home.css"></style>
