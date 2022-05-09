<template>
  <b-row>
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;700&family=Roboto:wght@500&display=swap"
      rel="stylesheet"
    />
    <b-col
      sm="10"
      md="10"
      lg="8"
      offset-sm="1"
      offset-md="1"
      offset-lg="2"
      class="content animated fadeInUp"
    >
      <b-form @submit.prevent="enviar" v-if="show" :state="this.senhaValida">
        <h3 class="titulo">Seus dados</h3>
        <hr />
        <!-- Nome -->
        <b-form-row>
          <b-col>
            <b-form-group
              id="input-group-txt-nome-completo"
              label="Nome Completo"
              label-for="txt-nome-completo"
            >
              <b-form-input
                id="txt-nome-completo"
                v-model="consumidor.nome"
                placeholder=""
                required
              ></b-form-input>
            </b-form-group>
          </b-col>
        </b-form-row>
        <!-- Email -->
        <b-form-row>
          <b-col>
            <b-form-group
              id="input-group-txt-email"
              label="Email"
              label-for="txt-email"
            >
              <b-form-input
                id="txt-email"
                v-model="consumidor.email"
                type="email"
                placeholder=""
                required
              ></b-form-input>
            </b-form-group>
          </b-col>
        </b-form-row>

        <!-- Senha e Confirmação -->
        <b-form-row>
          <b-col>
            <b-form-group
              id="input-group-txt-senha"
              label="Senha"
              label-for="txt-senha"
            >
              <b-form-input
                id="txt-senha"
                v-model="consumidor.senha"
                type="password"
                :state="this.senhaValida"
                required
              ></b-form-input>
            </b-form-group>
          </b-col>
          <b-col>
            <b-form-group
              id="input-group-txt-confirmacaoDeSenha"
              label="Confirmar Senha"
              label-for="txt-confirmacaoDeSenha"
            >
              <b-form-input
                id="txt-confirmacaoDeSenha"
                v-model="consumidor.confirmacaoDeSenha"
                type="password"
                :state="this.senhaValida"
                required
              ></b-form-input>
            </b-form-group>
          </b-col>
        </b-form-row>

        <!-- Celulares -->
        <b-form-row>
          <b-col>
            <b-form-group
              id="input-group-txt-celular1"
              label="Celular"
              label-for="txt-celular1"
            >
              <b-input-group>
                <b-input-group-prepend>
                    <img src="../../assets/brazil.png" alt="Brasil" />
                    (+55)
                </b-input-group-prepend>
                <b-form-input
                  id="txt-celular1"
                  v-model="consumidor.telefone"
                  v-mask="'(##) #####-####'"
                  type="tel"
                  placeholder=""
                ></b-form-input>
              </b-input-group>
            </b-form-group>
          </b-col>
        </b-form-row>

        <!-- CPF -->
        <b-form-row>
          <b-col>
            <b-form-group
              id="input-group-txt-cpf"
              label="CPF"
              label-for="txt-cpf"
            >
              <b-form-input
                id="txt-cpf"
                v-model="consumidor.CPF"
                v-mask="'###.###.###-##'"
                type="text"
                placeholder=""
                required
              ></b-form-input>
            </b-form-group>
          </b-col>
        </b-form-row>

        <div
          :class="{
            'bg-secondary': false,
            'text-light': false,
            'my-lg-4': true,
          }"
        >
          <Endereco
            titulo="Endereço de Entrega"
            @salvar-endereco="preencherEndereco"
          ></Endereco>
        </div>
        <b-card-footer>
          <b-button type="submit" variant="primary">Salvar Cadastro</b-button>
        </b-card-footer>
      </b-form>
    </b-col>
  </b-row>
</template>


<script>
import Cadastro from "../../components/cadastro/Cadastro";
import Endereco from "../../components/endereco/Endereco";
import Voltar from "../../components/voltar/Voltar";
export default {
  name: "CadastroConsumidor",
  extends: Cadastro,
  components: { Endereco, Voltar },
  data() {
    return {
      show: true,
      endpoint: "cadastro/consumidor/",
      consumidor: {
        nome: "",
        email: "",
        telefone: "",
        cpf: "",
        senha: "",
        confirmacaoDeSenha: "",
      },
      endereco: null,
    };
  },
  computed: {
    model() {
      return {
        nome: this.consumidor.nome,
        email: this.consumidor.email,
        telefone: this.consumidor.telefone,
        cpf: this.consumidor.CPF,
        endereco: [this.endereco],
        user: {
          email: this.consumidor.email,
          password: this.consumidor.senha,
        },
      };
    },
    senhaValida() {
      return (
        this.consumidor.senha === this.consumidor.confirmacaoDeSenha &&
        this.consumidor.senha.length > 5
      );
    },
  },
  mounted() {},
  methods: {
    enviar() {
      this.$store.dispatch("auth/registerConsumidor", this.model).then(
        (response) => {
          this.exibirResposta(response);
        },
        (error) => {
          this.exibirResposta(error.response);
        }
      );
    },
    tratarResponse(response) {
      switch (response.status) {
        case 201:
          return {
            text: "Consumidor cadastrado com sucesso!",
            type: "success",
          };
        case 409:
          return { text: "Consumidor já cadastrado...", type: "warning" };
        default:
        case 400:
          return {
            text: `Erro: ${response.data.description} <br/>Por favor contate o suporte.`,
            type: "error",
          };
      }
    },
    preencherEndereco(payload) {
      this.endereco = {
        ...payload,
        ...{
          flagEnderecoPrincipal: "S",
        },
      };
    },
    change() {
      console.log(this.consumidor.nome);
    },
  },
};
</script>


<style scoped>
.titulo {
  font-size: 24px;
}
.form-control {
  height: 56px;
  background-color: #f8f8f8;
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.25);
  border: none;
  border-radius: 8px;
}

.input-group-prepend {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100px;
  border: none;
  background-color: #f8f8f8;
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.25);
  border: none;
  border-right: 2px solid #D0D0D0;
  border-radius: 8px 0 0 8px;
}

.input-group-prepend img {
  width: 26px;
  height: 26px;
  margin-right: 5px;
}

.card-footer {
  background-color: white;
  display: flex;
  justify-content: flex-end;
}

.card-footer button {
  height: 58px;
  width: 200px;
  border: none;
  border-radius: 16px;
  background-color: #38C961;

  font-family: "Roboto";
  font-style: normal;
  font-weight: 500;
  font-size: 17px;
}

@media (max-width: 576px) {
  .card-footer button {
  width: 100%;
}
}

</style>