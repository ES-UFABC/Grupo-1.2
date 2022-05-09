<template>
  <div class="text-center">
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;700&family=Roboto:wght@500&display=swap"
      rel="stylesheet"
    />
    <b-button @click="abrirTelaProduto"> Cadastrar Produto </b-button>
    <b-button @click="abrirNotificacoes">
      Notificações <b-badge variant="light">4</b-badge>
    </b-button>
  </div>
</template>

<script>
import PainelNotificacoes from "../../components/painel-notificacoes/PainelNotificacoes";
export default {
  name: "PainelProdutor",
  components: { PainelNotificacoes },
  methods: {
    abrirNotificacoes() {
      this.$router.push("/painel/notificacoes");
    },
    abrirTelaProduto() {
      this.$router.push("/painel/produto");
    },
  },
};
</script>

<style scoped src="../../styles/painel-produtor.css"></style>