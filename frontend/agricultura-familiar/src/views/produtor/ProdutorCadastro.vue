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
      lg="10"
      offset-sm="1"
      offset-md="1"
      offset-lg="1"
      class="content animated fadeInUp"
    >
      <b-form @submit.prevent="enviar">
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
                v-model="produtor.nome"
                placeholder=""
                required
              ></b-form-input>
            </b-form-group>
          </b-col>
        </b-form-row>
        <!-- Apelido -->
        <b-form-row>
          <b-col>
            <b-form-group
              id="input-group-txt-apelido"
              label="Apelido"
              label-for="txt-apelido"
            >
              <b-form-input
                id="txt-apelido"
                v-model="produtor.apelido"
                placeholder=""
                required
              ></b-form-input>
              <b-form-text id="input-live-help"
                >Nome que aparecerá para seus clientes.</b-form-text
              >
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
                v-model="produtor.email"
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
                v-model="produtor.senha"
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
                v-model="produtor.confirmacaoDeSenha"
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
                  v-model="produtor.telefone"
                  v-mask="'(##) #####-####'"
                  type="tel"
                  placeholder=""
                ></b-form-input>
              </b-input-group>
            </b-form-group>
          </b-col>
          <b-col>
            <b-form-group
              id="input-group-txt-celular2"
              label="Celular 2"
              label-for="txt-celular2"
            >
              <b-input-group>
                <b-input-group-prepend>
                  <img src="../../assets/brazil.png" alt="Brasil" />
                  (+55)
                </b-input-group-prepend>
                <b-form-input
                  id="txt-celular2"
                  v-model="produtor.telefone2"
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
              id="input-group-txt-cpf-cnpj"
              label="CPF ou CNPJ"
              label-for="txt-cpf-cnpj"
            >
              <b-form-input
                id="txt-cpf"
                v-model="produtor.CPFCNPJ"
                v-mask="cpfCnpjMask"
                type="text"
                placeholder=""
                required
              ></b-form-input>
            </b-form-group>
          </b-col>
          <b-col>
            <b-form-group
              id="input-group-txt-site-externo"
              label="Site externo"
              label-for="txt-site-externo"
            >
              <b-form-input
                id="txt-site-externo"
                v-model="produtor.site"
                type="text"
                placeholder=""
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
            titulo="Endereço de Produção"
            @salvar-endereco="preencherEnderecoProducao"
          ></Endereco>

          <b-form-checkbox
            id="bool-tipo-endereco-principal"
            v-model="tipoEnderecoPrincipal"
            name="tipoEnderecoPrincipal"
            value="COMERCIALIZACAO"
            unchecked-value="PRODUCAO"
          >
            Não entrego na mesma região que produzo
          </b-form-checkbox>

          <Endereco
            v-if="!this.entregaNaMesmaRegiaoDeProducao"
            titulo="Endereço de Entrega"
            @salvar-endereco="preencherEnderecoEntrega"
          ></Endereco>
        </div>

        <!-- Biografia -->
        <b-form-row>
          <b-col>
            <b-form-group
              id="input-group-txt-cpf-cnpj"
              label="Biografia"
              label-for="txt-biografia"
            >
              <b-form-textarea
                id="txt-biografia"
                v-model="produtor.biografia"
                type="text"
                placeholder=""
              ></b-form-textarea>

              <b-form-text id="input-live-help"
                >Escreva um pouco sobre o que você produz e pode
                oferecer</b-form-text
              >
            </b-form-group>
          </b-col>
        </b-form-row>
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
export default {
  name: "CadastroProdutor",
  extends: Cadastro,
  components: { Endereco },
  data() {
    return {
      show: true,
      endpoint: "cadastro/produtor/",
      produtor: {
        nome: "",
        apelido: "",
        email: "",
        telefone: "",
        telefone2: "",
        CPFCNPJ: "",
        entregaNaMesmaRegiaoDeProducao: true,
        senha: "",
        confirmacaoDeSenha: "",
      },
      enderecoProducao: null,
      enderecoEntrega: null,
      tipoEnderecoPrincipal: "PRODUCAO",
    };
  },
  computed: {
    entregaNaMesmaRegiaoDeProducao() {
      return this.tipoEnderecoPrincipal === "PRODUCAO";
    },
    senhaValida() {
      return (
        this.produtor.senha === this.produtor.confirmacaoDeSenha &&
        this.produtor.senha.length > 5
      );
    },
    model() {
      return {
        nome: this.produtor.nome,
        cpfOuCnpj: this.produtor.CPFCNPJ,
        email: this.produtor.email,
        nomeFantasia: this.produtor.apelido,
        agroecologico: true,
        atendeNoEnderecoDeProducao: !this.entregaNaMesmaRegiaoDeProducao,
        cadastroEntidade: true,
        certificacaoAgroecologico: true,
        telefones: [this.produtor.telefone, this.produtor.telefone2].filter(
          (t) => t.length
        ),
        enderecos: [this.enderecoProducao, this.enderecoEntrega].filter(
          (e) => e
        ),
        entidadesAtendidas: [],
        formasPagamento: [],
        geolocalizacao: "teste",
        organico: "teste",
        paginasExternas: [],
        regiaoDeProducao: "teste",
        registroOuCertificacao: true,
        registrosOuCertificacoes: [],
        tipoProdutor: "INDIVIDUAL",
        tiposProducao: [],
        user: {
          email: this.produtor.email,
          password: this.produtor.senha,
        },
      };
    },
    cpfCnpjMask() {
      return this.produtor.CPFCNPJ && this.produtor.CPFCNPJ.length > 14
        ? "##.###.###/####-##"
        : "###.###.###-##X";
    },
  },
  mounted() {
    console.log(this);
  },
  methods: {
    enviar() {
      this.$store.dispatch("auth/registerProdutor", this.model).then(
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
          return { text: "Produtor cadastrado com sucesso!", type: "success" };
        case 409:
          return { text: "Produtor já cadastrado...", type: "warning" };
        default:
        case 400:
          return {
            text: `Erro: ${response.data.description} <br/>Por favor contate o suporte.`,
            type: "error",
          };
      }
    },
    preencherEnderecoProducao(payload) {
      this.enderecoProducao = {
        ...payload,
        ...{
          tipoEndereco: "PRODUCAO",
        },
      };
    },
    preencherEnderecoEntrega(payload) {
      this.enderecoEntrega = {
        ...payload,
        ...{
          tipoEndereco: "COMERCIALIZACAO",
        },
      };
    },
  },
};
</script>

<style scoped src="../../styles/cadastro.css"></style>
<style scoped src="../../styles/cadastro-produtor.css"></style>
