<template>
  <b-form @submit.prevent="enviar">
    <b-card-text>Informe seu e-mail</b-card-text>
      <b-form-row>
        <b-col>
          <b-form-group id="input-group-txt-pedido">
              <b-form-input id="txt-pedido"
                            type="pedido"
                            v-model="pedido"
                            placeholder="Informe seu pedido"></b-form-input>
          </b-form-group>
        </b-col>
      </b-form-row>
      <b-button type="submit" variant="primary">Entrar</b-button>
  </b-form>
</template>

<script>

  import alerts from '../../utils/alerts';
  import PedidoService from '../../services/pedido-service';
  export default {
    name: 'ConsumidorPedido',
    props: {
      idConsumidor: {
        type: String,
        required: true
      },
      idProdutor: {
        type: Number,
        required: true
      } 
    },
    data() {
      return {
        pedido: '',
      }
    },
    methods: {
      enviar() {
        PedidoService.salvarPedido(idConsumidor, idProdutor, pedido)
          .then(response => {
            alerts.SucessToaster.fire({ html: "Enviado com sucesso!" })
          },
            error => alerts.ErrorAlert.fire({ html: error })
          )

      }
    }
  }
</script>
