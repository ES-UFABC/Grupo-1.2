<template>
  <b-input-group class="search">
    <b-input-group-prepend>
      <button class="input-group-text" @click="pesquisar">
        <b-img src="../../../src/assets/search.svg" alt="Muda"></b-img>
      </button>
    </b-input-group-prepend>

    <b-form-input
      class="SearchInput"
      size="lg"
      placeholder="Busque por item ou produtor"
      list="historico"
      v-model="term"
      autocomplete="off"
      ref="search"
      @focus.prevent="carregarHistorico"
    >
    </b-form-input>

    <b-input-group-append>
      <button class="input-group-text" @click="abrirGeolocalizacao">
        <b-img src="../../../src/assets/location.svg" alt="Location"></b-img>
      </button>
    </b-input-group-append>

    <datalist id="historico">
      <option v-for="item in history" :key="item">{{ item }}</option>
    </datalist>
  </b-input-group>
</template>

<script>
import SearchHistoryService from '../../services/search-history-service'
export default {
  data() {
    return {
      term: "",
      history: [],
    };
  },
  methods: {
    pesquisar() {
      this.$emit('pesquisar', this.term)
      SearchHistoryService.salvarPesquisaNoHistoricoDeBuscas(this.term)
    },
    abrirGeolocalizacao() {
      this.$emit('abrirGeolocalizacao')
    },
    carregarHistorico() {
      let self = this;
      SearchHistoryService.carregarHistoricoDeBuscas()
        .then(historico => {
          self.history = historico.termos
        })
    }
  }
};
</script>

<style scoped>
.input-group-text {
  width: 50px;
  border: none;
  background-color: #f8f8f8;
  display: flex;
  align-items: center;
  justify-content: center;
}
.SearchInput {
  background-color: #f8f8f8;
}
.input-group-text img {
  height: 26px;
}
.SearchInput {
  border: none;
}

/* select {
  -webkit-appearance: none;
  -moz-appearance: none;
  -o-appearance: none;
  -ms-appearance: none;
  appearance: none;
} */
</style>
