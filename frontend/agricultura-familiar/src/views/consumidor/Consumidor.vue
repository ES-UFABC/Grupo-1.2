<template>
  <b-container class="bg consumidor">
    <!-- Just an image -->
    <b-navbar variant="faded" type="light">
      <b-navbar-brand href="#">
        <img src="../../../public/muda_green_small.png" alt="Muda"> Muda
      </b-navbar-brand>
    </b-navbar>
    <b-card header-tag="header" footer-tag="footer">
      <template #header>
        <h6 class="mb-0"><Voltar/></h6>
      </template>
      <router-view />
    </b-card>
    
  </b-container>
</template>


<script>
  import Voltar from '../../components/voltar/Voltar'
  export default {
    name: 'Consumidor',
    components: { Voltar }
  }
</script>


<style>
  .bg {
    background-size: cover;
  }

    .bg.consumidor {
      /*background-image: require('@/assets/bg-vector-green.svg');*/
      background: url(../../assets/bg-vector-green.svg) no-repeat;
    }

    .bg.produtor {
      /*background-image: require('@/assets/bg-vector-green.svg');*/
      background: url(../../assets/bg-vector-purple.svg) no-repeat;
    }
</style>
