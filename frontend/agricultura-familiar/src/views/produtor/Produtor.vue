<template>
  <div class="page-container">
    <LogoTopbar />
    <b-card>
      <Voltar />
      <router-view />
    </b-card>
  </div>
</template>


<script>
import LogoTopbar from "../../components/logo-topbar/LogoTopbarPurple.vue";
import Voltar from "../../components/voltar/Voltar";
export default {
  name: "Produtor",
  components: { Voltar, LogoTopbar },
};
</script>

<style scoped src="../../styles/produtor.css"></style>