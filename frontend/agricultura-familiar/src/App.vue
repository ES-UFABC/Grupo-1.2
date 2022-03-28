<template>
  <div :class="{root: true, sidebarClose: false}">
    <transition name="router-animation">
      <router-view />
    </transition>
  </div>
</template>

<script>
  export default {
    name: 'App',
    created() {
      //this.$router.push('/home');
      //const currentPath = this.$router.history.current.path;
      //if (window.localStorage.getItem('authenticated') === 'false') {
      //  this.$router.push('/login');
      //}
      //if (currentPath === '/' || currentPath === '/app') {
      //  this.$router.push('/app/dashboard');
      //}
    }
  };
</script>

<!--<style src="./styles/theme.scss" lang="scss" />-->
