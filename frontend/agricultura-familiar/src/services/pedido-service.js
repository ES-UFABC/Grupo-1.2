import axios from 'axios';
import authHeader from './auth-header';

class PedidoService {
  salvarPedido(idConsumidor, idProdutor, pedido) {
    let uri = `${process.env.SERVER_URI}/consumidor/pedidos`;
    return axios.post(uri, {
      headers: authHeader(),
      params: {idConsumidor, idProdutor, pedido}
    });
  }
}

export default new PedidoService();