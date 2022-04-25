<template>
  <div class="text-center">
    <b-button variant="primary" @click="abrirNotificacoes">
      Notificações <b-badge variant="light">4</b-badge>
    </b-button>
  </div>
</template>

<script>
  import PainelNotificacoes from '../../components/painel-notificacoes/PainelNotificacoes'
  export default {
    name: 'PainelProdutor',
    components: { PainelNotificacoes },
    methods: {
      abrirNotificacoes() {
        this.$router.push('/painel/notificacoes')
      }
    }
  }
</script>
