<template>
  <div>
    <Notificacao v-for="(notificacao, index) in notificacoes" :key="index" :notificacao="notificacao" />
  </div>
</template>
<script>
  import Notificacao from '../../components/notificacao/Notificacao'
  export default {
    name: 'PainelNotificacoes',
    components: { Notificacao },
    data() {
      return {
        notificacoes: [{
          "id": 1,
          "assunto": "Nova Compra",
          "mensagem": "Você possui uma nova compra",
          "dataPublicacao": [
            2022,
            4,
            22,
            4,
            9,
            4
          ]
        }]
      }
    }
  }
</script>
