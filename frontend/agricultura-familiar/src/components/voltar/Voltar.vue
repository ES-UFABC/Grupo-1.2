<template>
  <button @click="goBack">
    <img src="../../assets/arrow.svg" alt="voltar">
  </button>
</template>

<script>
  export default {
    name: 'Voltar',
    methods: {
      goBack() {
        this.$router.go(-1);
      }
    }
  }
</script>

<style scoped>
button {
  background: none;
  border: none;
}
</style>
