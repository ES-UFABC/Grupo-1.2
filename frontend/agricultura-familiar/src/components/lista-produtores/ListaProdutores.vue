<template>
  <b-list-group>
    <!--<b-list-group-item class="d-flex align-items-center">
      <b-avatar class="mr-3"></b-avatar>
      <span class="mr-auto">J. Circlehead</span>
      <b-badge>5</b-badge>
    </b-list-group-item>
    <b-list-group-item class="d-flex align-items-center">
      <b-avatar variant="primary" text="BV" class="mr-3"></b-avatar>
      <span class="mr-auto">BootstrapVue</span>
      <b-badge>12</b-badge>
    </b-list-group-item>
    <b-list-group-item class="d-flex align-items-center">
      <b-avatar variant="info" src="https://placekitten.com/300/300" class="mr-3"></b-avatar>
      <span class="mr-auto">Super Kitty</span>
      <b-badge>9</b-badge>
    </b-list-group-item>-->
    <b-list-group-item :key="index"
                       v-for="(produtor, index) in produtores"
                       class="d-flex align-items-center">

      <b-avatar variant="primary"
                :badge="produtor.produtos.length"
                badge-variant="success"
                icon="people-fill"
                class="mr-3"></b-avatar>

      <span class="mr-auto">
        <router-link class="link" :to="{ name: 'perfil_produtor', params: { id: produtor.id }}">{{ produtor.nomeFantasia }}</router-link>
      </span>
      
      <b-badge pill :variant="produtor.agroecologico ? 'success' : 'warning'">{{produtor.agroecologico ? 'Agroecológico' : 'Não certificado'}}</b-badge>

    </b-list-group-item>
  </b-list-group>
</template>

<script>
  export default {
    name: 'ListaProdutores',
    props: {
      produtores: {
        type: Array,
        required: true
      }
    },
    mounted() {
      console.log(this.produtores)
    }
  }
</script>


<style>
  .b-avatar {
    background-color: #8e67fc;
  }
  .link{
      color: #000;
  }
  .router-link-active{
      font-weight: bold
  }
</style>
