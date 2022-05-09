<template>
  <b-form @submit.prevent="enviar">
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;700&family=Roboto:wght@500&display=swap"
      rel="stylesheet"
    />
    <b-card-text>Informe seu e-mail</b-card-text>
    <b-form-row>
      <b-col>
        <b-form-group id="input-group-txt-email">
          <b-input-group>
            <b-input-group-prepend>
              <img src="../../assets/email-purple.svg" alt="email" />
            </b-input-group-prepend>
            <b-form-input
              id="txt-email"
              type="email"
              v-model="email"
              placeholder="Informe seu e-mail"
            ></b-form-input>
          </b-input-group>
        </b-form-group>
      </b-col>
    </b-form-row>
    <b-form-row>
      <b-col>
        <b-form-group id="input-group-txt-email">
          <b-input-group>
            <b-input-group-prepend>
              <img src="../../assets/password-purple.svg" alt="senha" />
            </b-input-group-prepend>
            <b-form-input
              id="txt-email"
              type="password"
              v-model="password"
              placeholder="Informe sua senha"
            ></b-form-input>
          </b-input-group>
        </b-form-group>
      </b-col>
    </b-form-row>
    <div class="footer">
      <button type="submit" variant="primary">Entrar</button>
    </div>
  </b-form>
</template>

<script>
import alerts from "../../utils/alerts";
export default {
  name: "Login",
  data() {
    return {
      email: "",
      password: "",
    };
  },
  methods: {
    enviar() {
      const { email, password } = this;

      if (email && password) {
        this.$store.dispatch("auth/login", { email, password }).then(
          (response) => {
            console.log(response);
            this.$emit("logged");
          },
          (error) => {
            alerts.ErrorAlert.fire({ html: error });
          }
        );
      }
    },
  },
};
</script>

<style scoped>
.card-text {
  margin-top: 12px;
  margin-bottom: 0;
  text-align: center;
  font-family: "Poppins";
  font-style: normal;
  font-weight: 300;
  font-size: 25px;
  line-height: 38px;
}
.form-group {
  margin-top: 43px;
  margin-bottom: 0;
  height: 80px;
}
input {
  height: 80px;
}

.footer {
  display: flex;
  justify-content: end;
}

button {
  margin-top: 43px;
  height: 58px;
  width: 200px;
  border-radius: 16px;
  border: none;
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.25);

  font-family: "Roboto";
  font-style: normal;
  font-weight: 500;
  font-size: 17px;
  line-height: 20px;
  color: white;
  background-color: #de7be0;
}
.input-group {
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.25);
  border-radius: 8px;
}

.input-group-prepend {
  display: flex;
  justify-content: center;
  width: 100px;
  border: none;
  background-color: #f8f8f8;
}

.input-group-prepend img {
  width: 45px;
}

.input-group input {
  background-color: #f8f8f8;
  border: none;
}

@media (max-width: 576px) {
  button {
    width: 100%;
  }
  .card-text {
    font-size: 22px;
  }
  .input-group-prepend {
    display: flex;
    justify-content: center;
    width: 60px;
    border: none;
    background-color: #f8f8f8;
  }
  .input-group-prepend img {
    width: 30px;
  }
}
</style>