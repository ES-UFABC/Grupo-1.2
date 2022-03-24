<template>
  <div>
    <p>aaaa</p>
    <router-view />

  </div>
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
