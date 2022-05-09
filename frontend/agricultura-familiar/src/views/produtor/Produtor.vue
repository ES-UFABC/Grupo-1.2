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

<style scoped>
.page-container {
  width: 100vw;
  height: 100vh;
  padding: 0 5vw;
  padding-top: 0 ;
  justify-content: center;
  margin-bottom: 35px;
}
.card {
  margin: 0 auto;
  background-color: white;
  border-color: #f0f0f0;
  box-shadow: 4px 4px 4px rgba(0, 0, 0, 0.15);
  border-radius: 15px;
  padding: 12px 20px;
  max-width: 736px;
}

@media (max-width: 576px) {
  .card {
    width: 90vw;
    padding: 3vw;
  }
}
</style>