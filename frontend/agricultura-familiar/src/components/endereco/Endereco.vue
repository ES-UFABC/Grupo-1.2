<template>
  <div class="my-lg-5">
    <h3>{{ titulo }}</h3>
    <hr />

    <!-- cep, Cidade e estado -->
    <b-form-row>
      <b-col>
        <b-form-group id="input-group-txt-cep"
                      label="CEP"
                      label-for="txt-cep">
          <b-form-input id="txt-cep"
                        v-model="endereco.cep"
                        v-mask="'#####-###'"
                        type="text"
                        placeholder=""
                        @change="carregarEndereco"
                        required></b-form-input>

        </b-form-group>
      </b-col>
      <b-col>
        <b-form-group id="input-group-txt-municipio"
                      label="Municipio"
                      label-for="txt-municipio">
          <b-form-input id="txt-municipio"
                        v-model="endereco.municipio"
                        type="text"
                        @change="salvarEndereco"
                        placeholder=""
                        required></b-form-input>

        </b-form-group>
      </b-col>
      <b-col>
        <b-form-group id="input-group-cmb-estado"
                      label="Estado"
                      label-for="cmb-estado">
          <b-form-select id="cmb-estado"
                        v-model="endereco.estado"
                        :options="options"
                        type="text"
                        @change="salvarEndereco"
                        placeholder=""
                        required></b-form-select>

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
                          v-model="endereco.rua"
                          type="text"
                          @change="salvarEndereco"
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
                        v-model="endereco.numero"
                        type="text"
                        @change="salvarEndereco"
                        placeholder=""></b-form-input>

        </b-form-group>
      </b-col>
      <b-col>
        <b-form-group id="input-group-txt-bairro"
                      label="Bairro"
                      label-for="txt-bairro">
          <b-form-input id="txt-bairro"
                        v-model="endereco.bairro"
                        type="text"
                        @change="salvarEndereco"
                        placeholder=""></b-form-input>

        </b-form-group>
      </b-col>
      <b-col>
        <b-form-group id="input-group-txt-complemento"
                      label="Complemento"
                      label-for="txt-complemento">

          <b-form-input id="txt-complemento"
                        v-model="endereco.complemento"
                        type="text"
                        @change="salvarEndereco"
                        placeholder=""></b-form-input>

        </b-form-group>
      </b-col>
    </b-form-row>
  </div>
</template>

<script>
  import Endereco from '../../models/endereco';
  export default {
    name: 'Endereco',
    props: {
      titulo: {
        type: String,
        required: true
      }
    },
    data() {
      return {
        endereco: new Endereco('', '', '', '', '', '', ''),
        options: []
      }
    },
    mounted() {
      var self = this;
      let apiUri = process.env.IBGE_ESTADOS_API_URI
      this.$http.get(apiUri)
        .then(response => {
          let opts = response.data.map(uf => ({ value: uf.sigla, text: uf.nome }));
          self.options = [{ value: '', text: 'Selecione uma opção' }].concat(opts);
        })
    },
    methods: {
      carregarEndereco() {
        let apiUri = process.env.VIACEP_API_URI.replace('{CEP}', this.endereco.cep || '')

        this.$http.get(apiUri)
          .then((response) => {
            this.endereco.complemento = response.data.complemento;
            this.endereco.rua = response.data.logradouro;
            this.endereco.bairro = response.data.bairro;
            this.endereco.municipio = response.data.localidade;
            this.endereco.estado = response.data.uf;
            //TODO: colocar foco no input referente ao número
            this.salvarEndereco();
          })
          .catch((error) => {
            console.log(error);
          })
      },
      salvarEndereco() {
        this.$emit('salvar-endereco', this.endereco)
      }
    }
  }
</script>
