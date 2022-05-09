<template>
  <b-list-group-item href="#" :active="false" class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">{{ notificacao.assunto }}</h5>
      <small :title="this.dataCompleta">{{ this.dataSimplificada }}</small>
    </div>

    <p class="mb-1"> {{ notificacao.mensagem }} </p>

    <!--<small>Donec id elit non mi porta.</small>-->
  </b-list-group-item>
</template>
<script>
  export default {
    name: 'Notificacao',
    props: {
      notificacao: {
        type: Object,
        required: true
      }
    },
    data() {
      return {

      }
    },
    computed: {
      data() {
        return new Date(this.notificacao.dataPublicacao);
      },
      dataCompleta() {
        return `${this.data.toLocaleDateString()} ${this.data.toLocaleTimeString()}`
      },
      dataSimplificada() {
        return this.tempoDesde(this.data);
      }
    },
    methods: {
      tempoDesde(date) {

        var seconds = Math.floor((new Date() - date) / 1000);

        var interval = seconds / 31536000;

        if (interval > 1)
          return `${Math.floor(interval)} anos atrás`;
        
        interval = seconds / 2592000;
        if (interval > 1)
          return `${Math.floor(interval)} meses atrás`;

        interval = seconds / 86400;
        if (interval > 1)
          return `${Math.floor(interval)} dias atrás`;
        
        interval = seconds / 3600;
        if (interval > 1) 
          return `${Math.floor(interval)} horas atrás`;
        
        interval = seconds / 60;
        if (interval > 1) 
          return `${Math.floor(interval)} minutos atrás`;
        
        return `${Math.floor(seconds)} segundos atrás`;
      }
    }
  }
</script>