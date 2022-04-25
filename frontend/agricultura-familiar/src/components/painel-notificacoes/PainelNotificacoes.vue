<template>
  <b-card no-body style="min-width: 100vw">
    <template #header>
      <h4 class="mb-0"><Voltar /> Notificações</h4>
    </template>
    <b-list-group flush>
      <Notificacao v-for="(notificacao, index) in notificacoes" :key="index" :notificacao="notificacao" v-if="notificacoes" />
    </b-list-group>
</b-card>
</template>
<script>
  import Voltar from '../../components/voltar/Voltar'
  import Notificacao from '../../components/notificacao/Notificacao'
  import ProdutorService from '../../services/produtor.service'
  export default {
    name: 'PainelNotificacoes',
    components: { Voltar, Notificacao },
    data() {
      return {
        notificacoes: []
      }
    },
    mounted: function () {
      let self = this
      ProdutorService.carregarNotificacoes().then(response => {
        let notificacoes = response.data.content
        if (notificacoes && notificacoes.length)
          self.notificacoes = notificacoes;
      })
    }
  }
</script>
