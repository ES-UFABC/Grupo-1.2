<template>
  <div class="body-card">
    <b-card-img src="public\muda_purple_large.png" alt="Muda Logo">
    </b-card-img>
    <b-card-title style="text-align: center"
      >{{ ProdutorName }}, seu cadastro foi finalizado &#128516;</b-card-title
    >

    <p>Nossa equipe agradece por você participar da nossa comunidade!</p>
    <b-button @click="pushHome">Ir para a página inicial</b-button>
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
.body-card {
  justify-content: center;
  align-items: center;
  display: flex;
  flex-direction: column;
}
img {
  width: 50%;
  margin-top: 1rem;
  margin-bottom: 2rem;
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
