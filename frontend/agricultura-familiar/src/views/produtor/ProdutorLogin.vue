<template>
  <Login @logged="redirect()"/>
</template>

<script>
  import Login from '../../components/login/Login'
  export default {
    components: { Login },
    methods: {
      redirect() {
        this.$router.push('/painel/produtor');
      }
    }
  }
</script>

