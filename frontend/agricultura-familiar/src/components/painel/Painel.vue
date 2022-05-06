
<template>
  <div class="panel">
    <NavBar @pesquisar="pesquisar" @abrirGeolocalizacao="abrirGeolocalizacao" />
    <transition name="router-animation">
      <router-view />
    </transition>
  </div>
</template>

<script>
import NavBar from "../../components/navbar/NavBar.vue";
export default {
  components: {
    NavBar,
  },
  data() {
    return {
    };
  },
  methods: {
    pesquisar() {
      console.log('pesquisando');
    },
    abrirGeolocalizacao() {
      this.$router.push("/painel/buscar-produtor");
    },
  },
};
</script>

<style>
   body {
    background-image: url("../../../public/background-tile.png");
    background-repeat: repeat;
    background-size: 200px;
    background-attachment: fixed;
    margin-top: 70px;
  }
</style>
