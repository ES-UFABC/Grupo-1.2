<template>
  <b-container fluid>
    <b-row>
      <b-col lg="2">
        <div>
          Olá {{ nome }}, você está logado.
          <a @click.prevent="logoff" href="#">Logoff</a>

        </div>
        <Busca @atualizar="obterEnderecoBusca" :estado-default="estado" :municipio-default="municipio" />
        <ListaProdutores :produtores="produtores" />
      </b-col>
      <b-col lg="10">
        <Geolocalizacao :defaultCenter="{ lat: -22.8305, lng: -43.2192 }" :enderecos="this.enderecosProdutores" />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
  import ProdutorService from '../../services/produtor.service';

  import Geolocalizacao from '../../components/geolocalizacao/Geolocalizacao';
  import ListaProdutores from '../../components/lista-produtores/ListaProdutores';
  import Busca from '../../components/endereco/Busca';
  export default {
    name: 'Profile',
    components: { Geolocalizacao, ListaProdutores, Busca },
    data() {
      return {
        nome: 'usuario',
        municipio: 'Caieiras',
        estado: 'SP',
        produtores: [],
      }
    },
    computed: {
      enderecosProdutores() {
        return this.produtores.map(p => (p.endereco && p.endereco.length) ? p.endereco[0] : p.endereco);
      }
    },
    mounted() {
      this.pesquisar();
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
