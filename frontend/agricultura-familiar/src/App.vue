<template>
  <div :class="{root: true, sidebarClose: false}">
    <!--<Helper />
  <Header />
  <Sidebar />-->
    <div>

    </div>
    <b-container ref="content">
      <transition name="router-animation">
        <router-view />
      </transition>
    </b-container>
    <footer class="contentFooter">
      Muda
    </footer>
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
    },
  };
</script>

<!--<style src="./styles/theme.scss" lang="scss" />-->
