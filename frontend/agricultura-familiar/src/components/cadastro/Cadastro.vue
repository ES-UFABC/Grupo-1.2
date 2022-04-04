<script>
  export default {
    name: 'Cadastro',
    data () {
      return {
        form: {},
        endereco: {
          CEP: '',
          numero: '',
          complemento: '',
          rua: '',
          bairro: '',
          municipio: ''
        }
      }
    },

    methods: {
      enviar(event) {
        event.preventDefault()
        let uri = `${process.env.SERVER_URI}/${this.endpoint}`
        console.log(uri)

        this.$http.post(uri, this.model)
          .then((response) => {
            this.exibirRespostaDaSubmissao(response);
          })
          .catch((error) => {
            console.log(error);
          });
      }
    }
  }
</script>
