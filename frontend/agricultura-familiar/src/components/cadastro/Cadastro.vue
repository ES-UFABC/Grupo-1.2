<script>
  import alerts from '../../utils/alerts';
  export default {
    name: 'Cadastro',
    data () {
      return {
      }
    },

    methods: {
      exibirResposta(response) {
        var opts = this.tratarResponse(response);
        if (opts.type == 'error')
          alerts.ErrorAlert.fire({ html: opts.text });
        else if (opts.type == 'success')
          alerts.SucessToaster.fire({ html: opts.text }).then(() => this.$router.push('/'));
        else if (opts.type == 'warning')
          alerts.WarningToaster.fire({ html: text });
      }
    }
  }
</script>
