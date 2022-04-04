<template>
  <b-container :class="classObj" fluid ref="content">
    <router-view />
  </b-container>
</template>
<script>
  export default {
    name: 'Home',
    data() {
      return {
        classObj: {
          bg: true,
          consumidor: false,
          produtor: false,
        }
      }
    },
    mounted() {
      this.escolherBg();

      //const currentPath = this.$router.history.current.path;
      //if (window.localStorage.getItem('authenticated') === 'false') {
      //  this.$router.push('/login');
      //}
      //if (currentPath === '/' || currentPath === '/app') {
      //  this.$router.push('/app/dashboard');
      //}
    },
    methods: {
      escolherBg() {
        this.classObj.consumidor = this.$route.name === 'cadastro_consumidor';
        this.classObj.produtor = this.$route.name === 'cadastro_produtor';
        console.log(this.classObj);
      }
    },
    watch: {
      '$route'(to, from) {
        // Put your logic here...
        this.escolherBg();
      }
    },
  };
</script>

<style type="scss">
  .bg {
    background-size: cover;
  }
    .bg.consumidor {
      /*background-image: require('@/assets/bg-vector-green.svg');*/
      background: url(../../assets/bg-vector-green.svg) no-repeat;
    }
    .bg.produtor {
      /*background-image: require('@/assets/bg-vector-green.svg');*/
      background: url(../../assets/bg-vector-purple.svg) no-repeat;
    }
</style>
