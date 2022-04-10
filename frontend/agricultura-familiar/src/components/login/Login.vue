<template>
  <b-form @submit.prevent="enviar">
    <b-card-text>Informe seu e-mail</b-card-text>
      <b-form-row>
        <b-col>
          <b-form-group id="input-group-txt-email">
            <b-input-group>
              <b-input-group-prepend>
                <b-input-group-text>
                  <b-icon icon="x" />
                </b-input-group-text>
              </b-input-group-prepend>
              <b-form-input id="txt-email"
                            type="email"
                            v-model="email"
                            placeholder="Informe seu e-mail"></b-form-input>
            </b-input-group>

          </b-form-group>
        </b-col>
      </b-form-row>
      <b-form-row>
        <b-col>
          <b-form-group id="input-group-txt-email">
            <b-input-group>
              <b-input-group-prepend>
                <b-input-group-text>
                  <b-icon icon="x" />
                </b-input-group-text>
              </b-input-group-prepend>
              <b-form-input id="txt-email"
                            type="password"
                            v-model="password"
                            placeholder="Informe seu e-mail"></b-form-input>
            </b-input-group>

          </b-form-group>
        </b-col>
      </b-form-row>
      <b-button type="submit" variant="primary">Entrar</b-button>
  </b-form>
</template>

<script>
  export default {
    name: 'Login',
    data() {
      return {
        email: '',
        password: ''
      }
    },
    methods: {
      enviar() {

        const { email, password } = this;

        if (email && password) {
          this.$store.dispatch('auth/login', { email, password })
            .then(() => {
                this.$router.push('/profile');
            },
            error => {
                console.log(error);
                //(error.response && error.response.data) ||
                //error.message ||
                //error.toString();
            }
          );
        }

      }
    }
  }
</script>
