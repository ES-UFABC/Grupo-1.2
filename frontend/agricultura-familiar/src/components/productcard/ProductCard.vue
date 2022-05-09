<template>
  <b-card :title="produto.nome"
          img-top
          style="margin:8px; max-width:20rem">
      <img :src="produto.foto.length ? produto.foto : urlsemfoto" alt="image" style="width:100%">
    <b-card-text>
      <b-badge href="#" :variant="produto.quantidade ? 'success' : 'danger'">
        Disponibilidade: {{ produto.quantidade }} {{produto.unidadeDeMedida}}.
      </b-badge>
    </b-card-text>
    <template #footer>
      <small class="text-muted">R$ {{produto.preco}} / {{produto.unidadeDeMedida}}</small>
    </template>
  </b-card>
</template>

<script>
export default {
    props: {
    name: 'ProductCard',
    produto: {
        type: Object,
        required: true
    },
    },
    data() {
      return {
        urlsemfoto: "https://img.freepik.com/vetores-gratis/fazenda-vazia-na-cena-do-por-do-sol-com-celeiro-vermelho-e-moinho-de-vento_1639-23498.jpg?w=2000"
      }
    }
};
</script>
