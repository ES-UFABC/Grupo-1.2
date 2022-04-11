<template>
  <b-card
    title="Nome do Produto"
    img-top
    style="margin:8px; max-width:20rem"
  > 
    <img v-bind:src=image alt="image" style="width:100%">
    <b-card-text>
      ({{ index }}) Pequena descrição do produto.
    </b-card-text>
    <template #footer>
      <small class="text-muted">Nome do Produtor</small>
    </template>
  </b-card>
</template>

<script>
export default {
  props: ["index", "image"],
};
</script>
