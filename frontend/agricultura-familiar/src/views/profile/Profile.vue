<template>
  <div>
    Olá {{ nome }}, você está logado.
    <a @click.prevent="logoff" href="#">Logoff</a>

    <Geolocalizacao/>
  </div>
</template>

<script>
  import Geolocalizacao from '../../components/geolocalizacao/Geolocalizacao';

  export default {
    name: 'Profile',
    components: { Geolocalizacao },
    data() {
      return {
        nome: 'usuario'
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
