<template>
  <b-container fluid>
    <NavBar/>
    <b-row>
      <b-col lg="3">
        <Busca @atualizar="obterEnderecoBusca" :estado-default="estado" :municipio-default="municipio" />
        <ListaProdutores :produtores="produtores" />
      </b-col>
      <b-col lg="9">
        <Geolocalizacao v-if="this.enderecoConsumidor"
                        :enderecoCentral="this.enderecoConsumidor"
                        :enderecos="this.enderecosProdutores" />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
  import ProdutorService from '../../services/produtor.service';
  import ConsumidorService from '../../services/consumidor.service';
  import NavBar from '../../components/navbar/NavBar'

  import Geolocalizacao from '../../components/geolocalizacao/Geolocalizacao';
  import ListaProdutores from '../../components/lista-produtores/ListaProdutores';
  import Busca from '../../components/endereco/Busca';
  export default {
    name: 'Profile',
    components: { Geolocalizacao, ListaProdutores, Busca, NavBar },
    data() {
      return {
        nome: 'usuario',
        municipio: 'Caieiras',
        estado: 'SP',
        produtores: [],
        enderecoConsumidor: null
      }
    },
    computed: {
      enderecosProdutores() {
        return this.produtores.map(p => p.enderecos[0]);
      }
    },
    mounted() {
      ConsumidorService.obterEnderecoDoConsumidor().then(address => {
        this.enderecoConsumidor = address;
        this.pesquisar();
      });
    },
    methods: {
      pesquisar() {
        const { municipio, estado } = this;
        return ProdutorService.carregarProdutoresPorLocalizacao(estado, municipio)
          .then(response => {
            this.produtores = response.data;
          },
          error => {
            console.log(error);
          }
        )
      },
      obterEnderecoBusca(endereco) {
        this.municipio = endereco.municipio;
        this.estado = endereco.estado;
        this.pesquisar();
      },
      logoff() {
        this.$store.dispatch('auth/logout');
        this.$router.push('/');
      }
    }
  }
</script>
