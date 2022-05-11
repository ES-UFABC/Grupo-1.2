<template>
  <b-form @submit.prevent="enviar">
      <b-form-row>
        <b-col>
          <b-form-group id="input-group-txt-pedido">
              <b-form-textarea id="txt-pedido"
                            type="pedido"
                            v-model="pedido"
                            required
                            max="255"
                            placeholder="Pedido"></b-form-textarea>
          </b-form-group>
        </b-col>
      </b-form-row>
      <b-button type="submit" variant="primary">Enviar Pedido</b-button>
  </b-form>
</template>

<script>

  import alerts from '../../utils/alerts';
  import PedidoService from '../../services/pedido-service';
  export default {
    name: 'ConsumidorPedido',
    props: {
      consumidor: {
        type: String,
        required: true
      },
      produtor: {
        type: Number,
        required: true
      } 
    },
    data() {
      return {
        pedido: '',
      }
    },
    computed: {
      model() {
        return {
          idConsumidor: this.consumidor,
          idProdutor: this.produtor,
          pedido: this.pedido
        }
      }
    },
    methods: {
      enviar() {
        PedidoService.salvarPedido(this.model)
          .then(response => alerts.SucessToaster.fire({ html: "Enviado com sucesso!" }),
                error => alerts.ErrorAlert.fire({ html: error }))

      }
    }
  }
</script>
