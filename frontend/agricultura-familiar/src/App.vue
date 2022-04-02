<template>
  <div :class="{root: true, sidebarClose: false}">
    <div v-if="isLoading" class="loading"></div>
    <transition name="router-animation">
      <router-view />
    </transition>
  </div>
</template>

<script>
  import axios from 'axios';
  import { mapActions, mapState } from 'vuex';

  export default {
    name: 'App',
    methods: {
      ...mapActions(
        'layout', ['changeLoading'],
      )
    },
    computed: {
      ...mapState('layout', {
        isLoading: state => state.isLoading,
        refCount: state => state.refCount,
      }),
    },
    created() {
      axios.interceptors.request.use((config) => {
        this.changeLoading(true);
        return config;
      }, (error) => {
        this.changeLoading(false);
        return Promise.reject(error);
      });

      axios.interceptors.response.use((response) => {
        this.changeLoading(false);
        return response;
      }, (error) => {
        this.changeLoading(false);
        return Promise.reject(error);
      });
      //this.$store.commit('loading', true);
      //this.changeLoading(true)
      //setTimeout(() => { console.log('entrou'); this.changeLoading(false) }, 5000);
      //this.$router.push('/home');
      //const currentPath = this.$router.history.current.path;
      //if (window.localStorage.getItem('authenticated') === 'false') {
      //  this.$router.push('/login');
      //}
      //if (currentPath === '/' || currentPath === '/app') {
      //  this.$router.push('/app/dashboard');
      //}
    },
    mounted() {

    }
  };
</script>

<!--<style src="./styles/theme.scss" lang="scss" />-->
