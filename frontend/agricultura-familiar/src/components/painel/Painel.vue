
<template>
  <div class="panel">
    <NavBar @abrirGeolocalizacao="abrirGeolocalizacao" />
    <transition name="router-animation">
      <router-view />
    </transition>
  </div>
</template>

<script>
  import NavBar from "../../components/navbar/NavBar.vue";
export default {
  components: { NavBar },
  data() {
    return {
    };
  },
  methods: {
    abrirGeolocalizacao() {
      this.$router.push("/painel/consumidor/");
    },
  },
};
</script>

<style>
   body {
    background-attachment: fixed;
    margin-top: 70px;
  }
</style>
