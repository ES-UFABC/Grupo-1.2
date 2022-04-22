<template>
  <p>{{ notificacao.assunto }}: {{ notificacao.mensagem }}</p>
</template>
<script>
  export default {
    name: 'Notificacao',
    props: {
      notificacao: {
        type: Object,
        required: true
      }
    }
  }
</script>
