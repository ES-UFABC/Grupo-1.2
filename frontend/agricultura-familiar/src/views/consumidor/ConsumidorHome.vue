
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

<style scoped>
.card-title {
  margin: 0 auto;

  font-family: "Poppins";
  font-style: normal;
  font-weight: 700;
  font-size: 36px;
  line-height: 54px;
  text-align: center;
  max-width: 454px;
}
.buttons {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
.button-row {
  margin-top: 43px;
  width: 100%;
  height: 82px;
  max-width: 380px;
  border-radius: 16px;
  border: none;
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.25);

  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 35px 32px;

  font-family: "Roboto";
  font-style: normal;
  font-weight: 500;
  font-size: 22px;
  line-height: 26px;
  color: white;
}

.button-text {
  width: 100%;
}

.buttons img {
  height: 45px;
  margin-right: 15px;
}

.card-text {
  margin-top: 43px;
  margin-bottom: 0;
  text-align: center;
  font-family: "Poppins";
  font-style: normal;
  font-weight: 300;
  font-size: 25px;
  line-height: 38px;
}

.footer {
  text-align: center;
  margin-top: 43px;
  font-family: "Roboto";
  font-style: normal;
  font-weight: 400;
  font-size: 22px;
  line-height: 26px;
}

@media (max-width: 576px) {
  .card-title {
    margin: 0 1.2rem;
    font-size: 1.6rem;
    line-height: 2.5rem;
  }
  .card-text {
    margin-top: 30px;
    font-size: 16px;
  }
  .button-row {
    margin-top: 30px;
    font-size: 17px;
    line-height: 20px;
  }
  .footer {
    margin-top: 30px;
    font-size: 16px;
  }
}
</style>
