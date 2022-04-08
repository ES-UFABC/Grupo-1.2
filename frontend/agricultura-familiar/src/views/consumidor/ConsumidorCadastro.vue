<template>
  <b-row>
    <b-col sm="10" md="10" lg="8" offset-sm="1" offset-md="1" offset-lg="2" class="content animated fadeInUp">
      <b-form @submit="enviar" v-if="show">
          <h3>Seus dados</h3>
          <hr />
          <!-- Nome -->
          <b-form-row>
            <b-col>
              <b-form-group id="input-group-txt-nome-completo" label="Nome Completo" label-for="txt-nome-completo">
                <b-form-input id="txt-nome-completo"
                              v-model="consumidor.nome"
                              placeholder=""
                              @change="changeNome"
                              required></b-form-input>
              </b-form-group>
            </b-col>
          </b-form-row>
          <!-- Email -->
          <b-form-row>
            <b-col>
              <b-form-group id="input-group-txt-email"
                            label="Email"
                            label-for="txt-email">
                <b-form-input id="txt-email"
                              v-model="consumidor.email"
                              type="email"
                              placeholder=""
                              required></b-form-input>
              </b-form-group>
            </b-col>
          </b-form-row>

          <!-- Celulares -->
          <b-form-row>
            <b-col>
              <b-form-group id="input-group-txt-celular1"
                            label="Celular"
                            label-for="txt-celular1">
                <b-input-group>
                  <b-input-group-prepend>
                    <b-input-group-text>
                      <b-icon icon="x" />
                    </b-input-group-text>
                  </b-input-group-prepend>
                  <b-form-input id="txt-celular1"
                                v-model="consumidor.telefone"
                                v-mask="'(##) #####-####'"
                                type="tel"
                                placeholder=""></b-form-input>
                </b-input-group>

              </b-form-group>
            </b-col>
            <!--<b-col>
          <b-form-group id="input-group-txt-celular2"
                        label="Celular para contato 2"
                        label-for="txt-celular2">
            <b-input-group>
              <b-input-group-prepend>
                <b-input-group-text>
                  <b-icon icon="x" />
                </b-input-group-text>
              </b-input-group-prepend>
              <b-form-input id="txt-celular2"
                            v-model="form.telefone"
                            v-mask="'(##) #####-####'"
                            type="tel"
                            placeholder=""></b-form-input>
            </b-input-group>

          </b-form-group>
        </b-col>-->
          </b-form-row>

          <!-- CPF -->
          <b-form-row>
            <b-col>
              <b-form-group id="input-group-txt-cpf"
                            label="CPF"
                            label-for="txt-cpf">
                <b-form-input id="txt-cpf"
                              v-model="consumidor.CPF"
                              v-mask="'###.###.###-##'"
                              type="text"
                              placeholder=""
                              required></b-form-input>
              </b-form-group>
            </b-col>
          </b-form-row>



          <div :class="{'bg-secondary': false, 'text-light': false, 'my-lg-4': true}">
            <Endereco titulo="Endereço de Entrega"
                      @salvar-endereco="preencherEndereco"></Endereco>
          </div>
        <b-card-footer>
          <b-button type="submit" variant="primary">Salvar Cadastro</b-button>
        </b-card-footer>
      </b-form>
    </b-col>
  </b-row>
</template>


<script>
  import Cadastro from '../../components/cadastro/Cadastro';
  import Endereco from '../../components/endereco/Endereco';
  import Voltar from '../../components/voltar/Voltar'
  export default {
    name: 'CadastroConsumidor',
    extends: Cadastro,
    components: { Endereco, Voltar },
    data() {
      return {
        show: true,
        endpoint: 'cadastro/consumidor/',
        consumidor: {
          nome: '',
          email: '',
          telefone: '',
          cpf: '',
        },
        endereco: null,
      }
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
            password: '12345'
          }
        }
      }
    },
    mounted() {
    },
    methods: {
      tratarResponse(response) {
        switch (response.status) {
          case 201:
            return { text: 'Consumidor cadastrado com sucesso!', type: 'success' };
          case 409:
            return { text: 'Consumidor já cadastrado...', type: 'warning' };
          default:
          case 400:
            return { text: `Erro: ${response.data.description} <br/>Por favor contate o suporte.`, type: 'error' };
        }
      },
      preencherEndereco(payload) {
        this.endereco = {
          ...payload,
          ...{
            flagEnderecoPrincipal: 'S'
          }
        };
      },
      changeNome() {
        console.log(this.consumidor.nome)
      }
    },

  }
</script>
