<script>
  import alerts from '../../utils/alerts';
  export default {
    name: 'Cadastro',
    data () {
      return {
      }
    },

    methods: {
      enviar(event) {
        event.preventDefault()
        let uri = `${process.env.SERVER_URI}/${this.endpoint}`
        console.log(uri)

        this.$http.post(uri, this.model)
          .then((response) => {
            var opts = this.tratarResponse(response);
            if (opts.type == 'error')
              alerts.ErrorAlert.fire({ text: opts.text });
            else if (opts.type == 'success')
              alerts.SucessToaster.fire({ text: opts.text }).then(() => this.$router.push('/'));
            else if (opts.type == 'warning')
              alerts.WarningToaster.fire({ text: text });
          })
          .catch((error) => {
            console.log(error);
          });
      }
    }
  }
</script>
