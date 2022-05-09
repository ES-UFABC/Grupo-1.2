<template>
  <div class="page-container">
    <LogoTopbar />
    <div>
      <link rel="preconnect" href="https://fonts.googleapis.com" />
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
      <link
        href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;700&family=Roboto:wght@500&display=swap"
        rel="stylesheet"
      />
      <b-card>
        <b-card-title>
          Como você vai fazer parte da nossa comunidade?
        </b-card-title>
        <div class="buttons">
          <button class="button-row button-consumidor" @click="pushConsumidor">
            <img src="../../assets/wanna-buy.svg" alt="sacola de mercado" />
            <div class="button-text">Quero Comprar!</div>
          </button>
          <button class="button-row button-produtor" @click="pushProdutor">
            <img src="../../assets/im_a_producer.svg" alt="rastelo" />
            <div class="button-text">Sou Produtor!</div>
          </button>
        </div>
      </b-card>
    </div>
  </div>
</template>

<script>
import LogoTopbar from "../../components/logo-topbar/LogoTopbarGreen.vue";

export default {
  methods: {
    pushConsumidor() {
      this.$router.push("/consumidor");
    },
    pushProdutor() {
      this.$router.push("/produtor");
    },
  },
  components: {
    LogoTopbar,
  },
};


</script>

<style scoped src="../../styles/root.css"></style>