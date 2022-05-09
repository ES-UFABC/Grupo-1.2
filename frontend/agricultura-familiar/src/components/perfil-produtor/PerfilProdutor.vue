<template>
  <b-container class="rounded bg-white" v-if="this.produtor">
    <button @click="voltar">Voltar</button>
    <b-row>
      <b-col md="4" class="border-right">
        <div class="d-flex flex-column align-items-center text-center">
          <b-avatar variant="primary"
                    icon="people-fill"
                    class="my-5"
                    size="150px"></b-avatar>
          <span class="font-weight-bold">{{ this.produtor.nomeFantasia }}</span>
          <span class="font-weight-bold" v-html="this.endereco"></span>
          <span class="text-black-50">{{ this.telefones }}</span>
        </div>
        <!-- Seção Pedido -->
        <ConsumidorPedido :id-consumidor="idConsumidor" :id-produtor="this.id" />
      </b-col>
      <b-col md="8" class="border-right" >
        <div class="p-3 py-5">
          <div class="d-flex justify-content-between align-items-center mb-3">
            <h4 class="text-right">Produtos</h4>
          </div>
          <b-row class="row mt-2">

            <b-col v-if="this.produtor.produtos.length">
              <b-card-group columns>
                <ProductCard v-for="(produto, index) in this.produtor.produtos" :key="index" :produto="produto" />
              </b-card-group>
            </b-col>
            <b-col v-else>
              <div><p>Não há produtos cadastrados.</p></div>
            </b-col>
          </b-row>
        </div>
      </b-col>
      <!--<b-col md="4">
        
      </b-col>-->
    </b-row>
  </b-container>
</template>

<script>
  import ProdutorService from '../../services/produtor.service'
  import ProductCard from '../productcard/ProductCard'
  import ConsumidorPedido from '../consumidor-pedido/ConsumidorPedido'
  export default {
    name: 'PerfilProdutor',
    components: { ProductCard, ConsumidorPedido },
    props: {
      //id: {
      //  required: false,
      //  type: Number
      //}
    },
    data() {
      return {
        produtor: null,
        idConsumidor: ""

      }
    },
    computed: {
      id() {
        return this.$route.params.id
      },
      endereco() {
        let endereco = this.produtor.enderecos[0];
        return `${endereco.rua}, ${endereco.numero} ${endereco.bairro}<br/>${endereco.municipio} - ${endereco.estado}`
      },
      telefones() {
        return this.produtor.telefones.join('/')
      }
    },
    mounted() {
      this.carregarProdutor()
    },
    methods: {
      voltar() {
        this.$router.push('/painel/consumidor/maps')
      },
      carregarProdutor() {
        let self = this;
        ProdutorService.carregarProdutorPorId(self.id)
          .then(response => { self.produtor = response.data; console.log(response.data) })
      }
    },
    watch: {
      id: function (newId, oldId) {
        this.carregarProdutor()
      }
    }
  }
</script>


<style scoped>
  body {
    background: rgb(99, 39, 120)
  }

  .form-control:focus {
    box-shadow: none;
    border-color: #BA68C8
  }

  .profile-button {
    background: rgb(99, 39, 120);
    box-shadow: none;
    border: none
  }

    .profile-button:hover {
      background: #682773
    }

    .profile-button:focus {
      background: #682773;
      box-shadow: none
    }

    .profile-button:active {
      background: #682773;
      box-shadow: none
    }

  .back:hover {
    color: #682773;
    cursor: pointer
  }

  .labels {
    font-size: 11px
  }

  .add-experience:hover {
    background: #BA68C8;
    color: #fff;
    cursor: pointer;
    border: solid 1px #BA68C8
  }
</style>
