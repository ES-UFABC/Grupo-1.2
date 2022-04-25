<template>
  <div class="page-container">
    <div>
      <b-card style="max-width: 560px">
        <router-view />
      </b-card>
    </div>
  </div>
</template>


<script>
export default {
  methods: {
    pushHome() {
      this.$router.push("/");
    }
  },
  data() {
    return {
      ProdutorName: "NomeDaPessoa",
    };
  },
  
};
</script>

<style scoped>
.page-container {
  width: 100vw;
  height: 100vh;
  padding: 4vw 5vw;
  display: flex;
  justify-content: center;
  background-image: url("../../../public/background-tile.png");
  background-repeat: repeat;
  background-size: 200px;
}
.card {
  background-color: white;
  border-color: #f0f0f0;
  box-shadow: 4px 4px 4px rgba(0, 0, 0, 0.15);
  border-radius: 15px;
}
</style>