
<template>
  <b-container fluid>
    <b-row>
      <b-col lg="3">
        <Busca @atualizar="obterEnderecoBusca" :estado-default="busca.estado" :municipio-default="busca.municipio" />
        <ListaProdutores :produtores="busca.produtores" />
      </b-col>
      <b-col lg="9">
        <Geolocalizacao 
                        :enderecoCentral="this.busca.enderecoConsumidor"
                        :enderecos="this.enderecosProdutores" />
      </b-col>
    </b-row>
  </b-container>
  <!--<b-container class="" style="margin-top:16px">
    <b-row align-h="center" cols-md="2">
      <ProductCard v-for="(image, index) in images" :key="index" :index="index" :image=image />-->
  <!-- <ProductCard v-for:"i in range" :index="0" :image=images[0] /> -->
  <!--</b-row>
  </b-container>-->
</template>

<script>
  import ProductCard from "../../components/productcard/ProductCard.vue";
  import ProdutorService from '../../services/produtor.service';
  import ConsumidorService from '../../services/consumidor.service';

  import Geolocalizacao from '../../components/geolocalizacao/Geolocalizacao';
  import ListaProdutores from '../../components/lista-produtores/ListaProdutores';
  import Busca from '../../components/endereco/Busca';
  export default {
  name: 'PainelProdutor',
  components: { ProductCard, Geolocalizacao, ListaProdutores, Busca },
  props: { teste: String },
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
        "https://cdn.pixabay.com/photo/2014/08/06/20/32/potatoes-411975_960_720.jpg"
      ],
      busca: {
        nome: 'usuario',
        municipio: 'Caieiras',
        estado: 'SP',
        produtores: [],
        enderecoConsumidor: null
      }
    };
  },
  computed: {
    enderecosProdutores() {
      return this.busca.produtores.map(p => p.enderecos[0]);
    },
    searchTerm() {
      return this.$store.state.search.term;
    }
  },
    mounted() {
    ConsumidorService.obterEnderecoDoConsumidor().then(address => {
      this.busca.enderecoConsumidor = address;
      this.pesquisarPorGeolocalizacao();
    });
  },
  methods: {
    pesquisarPorGeolocalizacao() {
      const { municipio, estado } = this.busca;
      return ProdutorService.carregarProdutoresPorLocalizacao(estado, municipio)
        .then(response => {
          this.busca.produtores = response.data;
        },
          error => {
            console.log(error);
          }
        )
    },
    pesquisarPorNomeFantasia(nome) {
      ProdutorService.carregarProdutoresPorNome(nome).then(response => {
        this.busca.produtores = response.data;
      }).catch(error => {
        console.log("Erro na consulta", error);
      })
    },
    obterEnderecoBusca(endereco) {
      this.busca.municipio = endereco.municipio;
      this.busca.estado = endereco.estado;
      this.pesquisarPorGeolocalizacao();
    },
  },
  watch: {
    searchTerm (newTerm, oldTerm) {
      if(newTerm)
        this.pesquisarPorNomeFantasia(newTerm)
      else
        this.pesquisarPorGeolocalizacao()
    }
  }
};
</script>

<style scoped>
  .products {
    display: flex;
    align-items: center;
    justify-content: center;
  }
</style>
