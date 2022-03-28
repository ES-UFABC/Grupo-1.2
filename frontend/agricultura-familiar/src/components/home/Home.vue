<template>
  <b-container class="bg" fluid ref="content">
    <router-view />
  </b-container>
</template>
<script>
  export default {
    name: 'Home',
    created() {
      console.log(this.$router);
      //const currentPath = this.$router.history.current.path;
      //if (window.localStorage.getItem('authenticated') === 'false') {
      //  this.$router.push('/login');
      //}
      //if (currentPath === '/' || currentPath === '/app') {
      //  this.$router.push('/app/dashboard');
      //}
    },
  };
</script>

<style type="scss">
  div.bg {
    /*background-image: require('@/assets/bg-vector-green.svg');*/
    background: url(../../assets/bg-vector-green.svg) no-repeat;
    background-size: cover;
  }
</style>
