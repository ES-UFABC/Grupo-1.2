<template>
  <b-input-group class="search">
    <b-input-group-prepend>
      <span class="input-group-text">
        <b-img src="../../../src/assets/search.svg" alt="Muda"></b-img>
      </span>
    </b-input-group-prepend>

    <b-form-input
      class="SearchInput"
      size="lg"
      placeholder="Busque por item ou produtor"
      list="historico"
    >
    </b-form-input>

    <b-input-group-append>
      <span class="input-group-text">
        <b-img src="../../../src/assets/location.svg" alt="Location"></b-img>
      </span>
    </b-input-group-append>

    <datalist id="historico">
      <option v-for="item in history" :key="item">{{ item }}</option>
    </datalist>
  </b-input-group>
</template>

<script>
export default {
  data() {
    return {
      item: "",
      history: [
        "A Throne Too Far",
        "The Cat Wasn't Invited",
        "You Only Meow Once",
        "Catless in Seattle",
      ],
    };
  },
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