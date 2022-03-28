<template>
  <b-row>
    <b-col sm="10" md="10" lg="8" offset-sm="1" offset-md="1" offset-lg="2" class="content animated fadeInUp">
      <b-form @submit="onSubmit" v-if="show">
        <b-card>
          <h3>Seus dados</h3>
          <hr />
          <!-- Nome -->
          <b-form-row>
            <b-col>
              <b-form-group id="input-group-txt-nome-completo" label="Nome Completo" label-for="txt-nome-completo">
                <b-form-input id="txt-nome-completo"
                              v-model="form.nome"
                              placeholder=""
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
                              v-model="form.email"
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
                                v-model="form.telefone"
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
                              v-model="form.CPF"
                              v-mask="'###.###.###-##'"
                              type="text"
                              placeholder=""
                              required></b-form-input>
              </b-form-group>
            </b-col>
          </b-form-row>

          <div :class="{'bg-secondary': false, 'text-light': false}">

            <h3>Endereço de Entrega</h3>
            <hr />
            <!-- CEP e Cidade -->
            <b-form-row>
              <b-col>
                <b-form-group id="input-group-txt-cep"
                              label="CEP"
                              label-for="txt-cep">
                  <b-form-input id="txt-cep"
                                v-model="form.CEP"
                                v-mask="'#####-###'"
                                type="text"
                                placeholder=""
                                required debounce="500"></b-form-input>

                </b-form-group>
              </b-col>
              <b-col>
                <b-form-group id="input-group-txt-municipio"
                              label="Municipio"
                              label-for="txt-municipio">
                  <b-form-input id="txt-municipio"
                                v-model="form.municipio"
                                type="text"
                                placeholder=""
                                required></b-form-input>

                </b-form-group>
              </b-col>
            </b-form-row>

            <!-- Endereço -->
            <b-form-row>
              <b-col>
                <b-form-group id="input-group-txt-endereco"
                              label="Endereço"
                              label-for="txt-endereco">
                  <b-input-group>
                    <b-input-group-prepend>
                      <b-input-group-text>
                        <b-icon icon="x" />
                      </b-input-group-text>
                    </b-input-group-prepend>
                    <b-form-input id="txt-endereco"
                                  v-model="form.rua"
                                  type="text"
                                  placeholder=""></b-form-input>
                  </b-input-group>

                </b-form-group>
              </b-col>
            </b-form-row>


            <!-- Numero, Bairro e Complemento -->
            <b-form-row>
              <b-col>
                <b-form-group id="input-group-txt-numero"
                              label="Número"
                              label-for="txt-numero">

                  <b-form-input id="txt-numero"
                                v-model="form.numero"
                                type="text"
                                placeholder=""></b-form-input>

                </b-form-group>
              </b-col>
              <b-col>
                <b-form-group id="input-group-txt-bairro"
                              label="Bairro"
                              label-for="txt-bairro">
                  <b-form-input id="txt-bairro"
                                v-model="form.bairro"
                                type="text"
                                placeholder=""></b-form-input>

                </b-form-group>
              </b-col>
              <b-col>
                <b-form-group id="input-group-txt-complemento"
                              label="Complemento"
                              label-for="txt-complemento">

                  <b-form-input id="txt-complemento"
                                v-model="form.complemento"
                                type="text"
                                placeholder=""></b-form-input>

                </b-form-group>
              </b-col>
            </b-form-row>
          </div>

        </b-card>
        <b-card-footer>
          <b-button type="submit" variant="primary">Salvar Cadastro</b-button>
        </b-card-footer>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
  import axios from 'axios';
  export default {
    data() {
      return {
        form: {
          nome: '',
          email: '',
          telefone: '',
          CPF: '',
          CEP: '',
          numero: '',
          complemento: '',
          rua: '',
          bairro: '',
          municipio: ''
        },
        show: true
      }
    },
    computed: {
      apiUrl: () => 'http://localhost:8080/v1/cadastro_consumidor',
      apiCepUrl: () => 'https://viacep.com.br/ws/{CEP}/json/',
      model() {
        return {
          nome: this.form.nome,
          email: this.form.email,
          telefone: this.form.telefone,
          CPF: this.form.CPF,
          endereco: [
            {
              flag_endereco_principal: "S",
              CEP: this.form.CEP,
              numero: this.form.numero,
              complemento: this.form.complemento,
              rua: this.form.rua,
              bairro: this.form.bairro,
              municipio: this.form.municipio
            }
          ]
        }
      }
    },
    mounted() {
      console.log(process.env)
    },
    methods: {
      onSubmit(event) {
        event.preventDefault()
        console.log(this.form);
        axios.post(this.apiUrl, this.model)
          .then((response) => {
            console.log(response);
            switch (response.status == 201) {
              case 201:
                alert(`Consumidor ${this.form.nome} cadastrado com sucesso!`);
                break;
              case 400:
                alert(`Erro ao cadastrar, por favor contate o suporte.`);
                break;
              case 409:
                alert(`Consumidor já cadastrado...`);
                break;
              default:
                alert('Erro...');
                break;
            }
          }, (error) => {
            console.log(error);
            switch (error.status == 201) {
              case 201:
                alert(`Consumidor ${this.form.nome} cadastrado com sucesso!`);
                break;
              case 400:
                alert(`Erro ao cadastrar, por favor contate o suporte.`);
                break;
              case 409:
                alert(`Consumidor já cadastrado...`);
                break;
              default:
                alert('Erro...');
                break;
            }
          });

      },
      getCep() {
        axios.get(this.apiCepUrl.replace('{CEP}', this.form.CEP))
          .then((response) => {
            this.form.complemento = response.data.complemento;
            this.form.rua = response.data.logradouro;
            this.form.bairro = response.data.bairro;
            this.form.municipio = response.data.localidade;
          })
          .error((error) => {
          })
      }
    },
    watch: {
      'form.CEP': function (newCEP, oldCEP) {
        console.log(newCEP, oldCEP)
        let cep = newCEP.replace('-', '');
        if (cep.length == 8)
          this.getCep()
      }
    }
  }
</script>
