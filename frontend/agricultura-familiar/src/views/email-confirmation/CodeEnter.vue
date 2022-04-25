<template>
  <b-form class="body-card" @submit.prevent="enviar">
    <b-card-title style="text-align: center"> Verifição de Email </b-card-title>
    <b-form-group id="input-group-txt-email">
      Insira seu Email:
      <b-input-group>
        <b-input-group-prepend>
          <b-input-group-text>
            <b-icon icon="envelope" />
          </b-input-group-text>
        </b-input-group-prepend>
        <b-form-input
          id="txt-email"
          type="email"
          v-model="email"
          placeholder="Informe seu e-mail"
        ></b-form-input>
      </b-input-group>
      Insira o código recebido em seu Email:
      <b-input-group>
        <div class="input-wrapper">
          <PincodeInput v-model="code" placeholder="*" length="6" />
        </div>
      </b-input-group>
    </b-form-group>

    <b-button type="submit">Verificar</b-button>
  </b-form>
</template>

<script>
import PincodeInput from "vue-pincode-input";

export default {
  name: "CondeEnter",
  components: { PincodeInput },
  data() {
    return {
      code: "",
    };
  },
};
</script>

<style scoped>
.input-group {
  margin-bottom: 0.5rem;
}
.form-group {
  margin-bottom: 0.25rem;
}
.body-card {
  justify-content: center;
  align-items: center;
  display: flex;
  flex-direction: column;
}
button {
  background-color: #de7be0;
  border: none;
  box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.15);
  padding: 0.9rem;
  border-radius: 16px;
  width: 150px;
}
</style>