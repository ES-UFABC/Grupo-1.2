<template>
  <button @click="goBack">
    <img src="../../assets/arrow.svg" alt="voltar">
  </button>
</template>

<script>
  export default {
    name: 'Voltar',
    props: {
      override: Boolean
    },
    methods: {
      goBack() {
        if(!this.override)
          this.$router.go(-1);
        else
          this.$emit('goback')
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
