
<template>
  <div class="panel">
    <NavBar />
    <transition name="router-animation">
      <router-view />
    </transition>
  </div>
</template>

<script>
import NavBar from "../../components/navbar/NavBar.vue";
export default {
  components: {
    NavBar,
  },
  data() {
    return {
    };
  },
  methods: {
  },
};
</script>

<style>
   body {
    background-image: url("../../../public/background-tile.png");
    background-repeat: repeat;
    background-size: 200px;
    background-attachment: fixed;
  }
</style>
