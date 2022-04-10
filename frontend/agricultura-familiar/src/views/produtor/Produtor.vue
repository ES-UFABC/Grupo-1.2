<template>
  <b-container>
    <!-- Just an image -->
    <b-navbar variant="faded" type="light">
      <b-navbar-brand href="#">
        <img src="../../../public/muda_purple_small.png" alt="Muda"> Muda
      </b-navbar-brand>
    </b-navbar>
    <b-card header-tag="header" footer-tag="footer">
      <template #header>
        <h6 class="mb-0"><Voltar /></h6>
      </template>
      <router-view />
    </b-card>

  </b-container>
</template>


<script>
  import Voltar from '../../components/voltar/Voltar'
  export default {
    name: 'Produtor',
    components: { Voltar }
  }
</script>
