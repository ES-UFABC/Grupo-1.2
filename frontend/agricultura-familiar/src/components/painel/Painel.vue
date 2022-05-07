
<template>
  <div class="panel">
    <NavBar @abrirGeolocalizacao="abrirGeolocalizacao" />
    <DeckProdutores v-if="produtores.length" :produtores="produtores" />
    <transition name="router-animation">
      <router-view />
    </transition>
  </div>
</template>

<script>
  import NavBar from "../../components/navbar/NavBar.vue";
  import ProdutorService from '../../services/produtor.service'
export default {
  components: {
    NavBar
  },
  data() {
    return {
      produtores: []
    };
  },
  methods: {
    abrirGeolocalizacao() {
      this.$router.push("/painel/consumidor");
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
