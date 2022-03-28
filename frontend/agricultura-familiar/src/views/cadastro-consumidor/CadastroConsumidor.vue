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
                            label="Celular para contato 1"
                            label-for="txt-celular1">
                <b-input-group>
                  <b-input-group-prepend>
                    <b-input-group-text>
                      <b-icon icon="x" />
                    </b-input-group-text>
                  </b-input-group-prepend>
                  <b-form-input id="txt-celular1"
                                v-model="form.telefone"
                                type="tel"
                                placeholder=""></b-form-input>
                </b-input-group>

              </b-form-group>
            </b-col>
            <b-col>
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
                                type="tel"
                                placeholder=""></b-form-input>
                </b-input-group>

              </b-form-group>
            </b-col>
          </b-form-row>

          <!-- CPF -->
          <b-form-row>
            <b-col>
              <b-form-group id="input-group-txt-cpf"
                            label="CPF"
                            label-for="txt-cpf">
                <b-form-input id="txt-cpf"
                              v-model="form.CPF"
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
                                v-model="form.endereco[0].CEP"
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
                                v-model="form.endereco[0].municipio"
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
                                  v-model="form.endereco[0].rua"
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
                                v-model="form.endereco[0].numero"
                                type="text"
                                placeholder=""></b-form-input>

                </b-form-group>
              </b-col>
              <b-col>
                <b-form-group id="input-group-txt-bairro"
                              label="Bairro"
                              label-for="txt-bairro">
                  <b-form-input id="txt-bairro"
                                v-model="form.endereco[0].bairro"
                                type="text"
                                placeholder=""></b-form-input>

                </b-form-group>
              </b-col>
              <b-col>
                <b-form-group id="input-group-txt-complemento"
                              label="Complemento"
                              label-for="txt-complemento">

                  <b-form-input id="txt-complemento"
                                v-model="form.endereco[0].complemento"
                                type="text"
                                placeholder=""></b-form-input>

                </b-form-group>
              </b-col>
            </b-form-row>

            <!-- Checkbox -->
            <b-form-row>
              <b-col>
                <b-form-group id="input-group-4" v-slot="{ ariaDescribedby }">
                  <b-form-checkbox value="me">Não entrego na mesma região que produzo</b-form-checkbox>
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
          nome: "joao",
          email: "joao@gmail.com",
          telefone: "11975295602",
          CPF: "572.355.330-56",
          endereco: [
            {
              flag_endereco_principal: "S",
              CEP: "06233-030",
              numero: "72",
              complemento: "apto 2",
              rua: "santa carolina",
              bairro: "vila campestre",
              municipio: "santo andré"
            }
          ]
        },
        show: true
      }
    },
    computed: {
      apiUrl: () => ''
    },
    mounted() {
      console.log(process.env)
    },
    methods: {
      onSubmit(event) {
        event.preventDefault()

        axios.post(apiUrl, this.form)
          .then((response) => {
            console.log(response);
          }, (error) => {
            console.log(error);
          });

        //alert(JSON.stringify(this.form))
      }
    }
  }
</script>
