
<template>
  <b-container fluid>
    <b-row>
      <b-col lg="3">
        <Busca @atualizar="obterEnderecoBusca" :estado-default="busca.estado" :municipio-default="busca.municipio" />
        <ListaProdutores :produtores="busca.produtores" />
      </b-col>
      <b-col lg="9">
        <transition name="router-animation">
          <router-view />
        </transition>
      </b-col>
    </b-row>
  </b-container>
  <!--<b-container class="" style="margin-top:16px">
    <b-row align-h="center" cols-md="2">
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
      busca: {
        nome: 'usuario',
        municipio: 'Caieiras',
        estado: 'SP',
        produtores: []
      }
    };
  },
  created() {    
  },
  computed: {
    enderecosProdutores() {
      return this.$store.state.geolocation.producersAdresses;
    },
    searchTerm() {
      return this.$store.state.search.term;
    }
  },
  mounted() {
    this.carregarEnderecoConsumidor()
    
  },
  methods: {
    carregarEnderecoConsumidor() {
        ConsumidorService.obterEnderecoDoConsumidor()
          .then(address => this.$store.dispatch('geolocation/setConsumerAddress', address));
      this.pesquisarPorGeolocalizacao();
    },
    pesquisarPorGeolocalizacao() {
      const { municipio, estado } = this.busca;
      return ProdutorService.carregarProdutoresPorLocalizacao(estado, municipio)
        .then(response => {
          this.busca.produtores = response.data;
          this.$store.dispatch('geolocation/setProducersAddresses', this.obterEnderecoProdutor(response.data))
        })
        .then(() => this.atualizarMapa())
        .catch(error => console.log("Erro na consulta", error))
    },
    pesquisarPorNomeFantasia(nome) {
      ProdutorService.carregarProdutoresPorNome(nome)
        .then(response => {
          this.busca.produtores = response.data;
          this.$store.dispatch('geolocation/setProducersAddresses', this.obterEnderecoProdutor(response.data))
        })
        .then(() => this.atualizarMapa())
        .catch(error => console.log("Erro na consulta", error))
    },
    obterEnderecoProdutor(produtor) {
      return produtor.map(p => Object.assign(p.enderecos[0], { produtor: p.nomeFantasia || p.nome }));
    },
    atualizarMapa() {
      this.$router.push({
        name: 'maps', params: {
          enderecos: this.enderecosProdutores
        }
      })
    },
    obterEnderecoBusca(endereco) {
      this.busca.municipio = endereco.municipio;
      this.busca.estado = endereco.estado;
      this.pesquisarPorGeolocalizacao();
    },
    carregarGeolocalizacao() {
      navigator.geolocation.getCurrentPosition(position => {
        this.$store.dispatch('geolocation/setconsumerCoords', {
          lat: position.coords.latitude,
          lng: position.coords.longitude,
        })
      });
    }
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
