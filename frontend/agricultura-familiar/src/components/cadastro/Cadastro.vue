<script>
export default {
  name: 'Cadastro',
  data () {
    return {
      form: {}
    }
  },

  methods: {
    /* Add functions here that will be available in all components that inherit from this one */

    /* Here we can add complicated functions, that do things like parse a url for search params*/
    parseURLFilters () {
      const parsedFilters = Object.keys(this.filters).map(filter => {
        return { [filter]: `filters[${filter}]` }
      })
      const url = new URLSearchParams(window.location.search)

      parsedFilters.map(filterObj => {
        let dataProp = Object.keys(filterObj)[0]
        let filterName = Object.values(filterObj)[0]
         return this[dataProp] = url.get(filterName)
      })
    },

    submitSearch (searchUrl, filters = {}) {
      this.$http.get(searchUrl, {
        params: { query: this.searchText, filters }
      }).then(response => {
        /* emit event to handle results in extended components */
        this.$emit('setSearchResults', {searchResults: response.body})
      }, error => {
        /* handle errors */
      })
    },

    async obterEnderecoPorCEP(cep) {
      let apiUri = process.env.VIACEP_API_URI?.replace('{CEP}', cep)
      await this.$http.get(apiUri)
        .then((response) => {
          //this.form.complemento = response.data.complemento;
          //this.form.rua = response.data.logradouro;
          //this.form.bairro = response.data.bairro;
          //this.form.municipio = response.data.localidade;
          console.log(response.data);
          return response.data;
        })
        .error((error) => {
          console.log(error);
        })
    }
  }
}
</script>
