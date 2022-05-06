
<template>
  <b-container class="" style="margin-top:16px">
    <b-row align-h="center" cols-md="2">
      <ProductCard v-for="(image, index) in images" :key="index" :index="index" :image=image />
      <!-- <ProductCard v-for:"i in range" :index="0" :image=images[0] /> -->
    </b-row>
  </b-container>
</template>

<script>
import ProductCard from "../../components/productcard/ProductCard.vue";
  export default {
  name: 'PainelProdutor',
  components: { ProductCard },
  data() {
    return {
      cards: Array.from(Array(3).keys()),
      images: [
        "https://cdn.pixabay.com/photo/2017/06/09/16/39/carrots-2387394_960_720.jpg",
        "https://cdn.pixabay.com/photo/2014/08/06/20/32/potatoes-411975_960_720.jpg",
        "https://cdn.pixabay.com/photo/2016/11/18/13/48/food-1834645_960_720.jpg",
        "https://cdn.pixabay.com/photo/2016/11/18/13/48/food-1834645_960_720.jpg",
        "https://cdn.pixabay.com/photo/2017/06/09/16/39/carrots-2387394_960_720.jpg",
        "https://cdn.pixabay.com/photo/2014/08/06/20/32/potatoes-411975_960_720.jpg",
        "https://cdn.pixabay.com/photo/2017/06/09/16/39/carrots-2387394_960_720.jpg",
        "https://cdn.pixabay.com/photo/2016/11/18/13/48/food-1834645_960_720.jpg",
        "https://cdn.pixabay.com/photo/2014/08/06/20/32/potatoes-411975_960_720.jpg"]
    };
  },
  methods: {
    splitArrayIntoChunksOfLen(arr, len) {
      var chunks = [],
        i = 0,
        n = arr.length;
      while (i < n) {
        chunks.push(arr.slice(i, (i += len)));
      }
      return chunks;
    },
  },
};
</script>

<style scoped>
  .products {
    display: flex;
    align-items: center;
    justify-content: center;
  }
</style>
