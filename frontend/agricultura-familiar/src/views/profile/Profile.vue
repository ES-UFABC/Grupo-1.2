<template>
  <div>
    Olá {{ nome }}, você está logado.
    <a @click.prevent="logoff" href="#">Logoff</a>
  </div>
</template>

<script>
  export default {
    name: 'Profile',
    data() {
      return {
        nome: 'aa'
      }
    },
    methods: {
      logoff() {
        this.$store.dispatch('auth/logout');
        this.$router.push('/');
      }
    }
  }
</script>
