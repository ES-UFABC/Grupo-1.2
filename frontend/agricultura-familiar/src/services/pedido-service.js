import axios from 'axios';
import authHeader from './auth-header';

class PedidoService {
  salvarPedido(idConsumidor, idProdutor, pedido) {
    let uri = `${process.env.VUE_APP_SERVER_URI}/consumidor/pedidos`;
    return axios.post(uri, { idConsumidor, idProdutor, pedido }, {
      headers: authHeader() 
    });
  }
}

export default new PedidoService();
