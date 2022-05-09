<template>
  <b-form class="my-lg-5">
    <!-- estado e cidade-->
    <b-form-row>
      <b-col>
        <b-form-group id="input-group-cmb-estado"
                      label="Estado"
                      label-for="cmb-estado">
          <b-form-select id="cmb-estado"
                         v-model="estado"
                         :options="estados"
                         type="text"
                         @change="carregarMunicipios"
                         placeholder=""
                         required></b-form-select>

        </b-form-group>
      </b-col>
      <b-col>
        <b-form-group id="input-group-cmb-municipio"
                      label="Municipio"
                      label-for="cmb-municipio">

          <b-form-select id="cmb-municipio"
                         v-model="municipio"
                         :options="municipios"
                         type="text"
                         @change="atualizar"
                         placeholder=""
                         required></b-form-select>

        </b-form-group>
      </b-col>
    </b-form-row>
  </b-form>
</template>

<script>
  export default {
    name: 'Busca',
    props: {
      estadoDefault: String,
      municipioDefault: String
    },
    data() {
      return {
        estado: this.estadoDefault,
        estados: [],
        municipio: this.municipioDefault,
        municipios: []
      }
    },
    mounted() {
      this.carregarEstados();
      if (this.estadoDefault)
        this.carregarMunicipios();

    },
    methods: {
      carregarEstados() {
        var self = this;
        let apiUri = process.env.VUE_APP_IBGE_ESTADOS_API_URI

        this.$http.get(apiUri)
          .then(response => {
            let opts = response.data.map(uf => ({ value: uf.sigla, text: uf.nome }));
            self.estados = [{ value: '', text: 'Selecione uma opção' }].concat(opts);
          })
          .catch((error) => {
            console.log(error);
          })
      },
      carregarMunicipios() {
        var self = this;
        let apiUri = process.env.VUE_APP_IBGE_MUNICIPIOS_API_URI.replace('{UF}', this.estado || '')

        this.$http.get(apiUri)
          .then((response) => {
            let opts = response.data.map(city => ({ value: city.nome, text: city.nome }));
            self.municipios = [{ value: '', text: 'Selecione uma opção' }].concat(opts);
            
          })
          .catch((error) => {
            console.log(error);
          })
      },
      atualizar() {
        const { estado, municipio } = this;
        if (estado && municipio)
          this.$emit('atualizar', { estado, municipio })
      }
    }
  }
</script>
