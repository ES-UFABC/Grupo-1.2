<template>
  <button @click="goBack">Voltar</button>
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
