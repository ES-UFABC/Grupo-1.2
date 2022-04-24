<template>
  <div class="page-container">
    <div>
      <b-card style="max-width: 560px">
        <b-card-img src="public\muda_purple_large.png" alt="Muda Logo">
        </b-card-img>
        <b-card-title style="text-align: center"
          >{{ ProdutorName }}, seu cadastro foi finalizado &#128516;</b-card-title
        >

        <p>Nossa equipe agradece por você participar da nossa comunidade!</p>
        <b-button @click="pushHome">Ir para a página inicial</b-button>
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
img {
  width: 50%;
  margin-top: 1rem;
  margin-bottom: 2rem;
}

.card-body {
  justify-content: center;
  align-items: center;
  display: flex;
  flex-direction: column;
}

button {
  background-color: #de7be0;
  border: none;
  box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.15);
  padding: 0.9rem;
  border-radius: 16px;
  margin-top: 0.5rem;
}
</style>