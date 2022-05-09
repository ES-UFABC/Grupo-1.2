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

<style scoped src="../../styles/login.css"></style>