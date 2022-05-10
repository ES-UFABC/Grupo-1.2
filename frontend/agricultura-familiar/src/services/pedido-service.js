import axios from 'axios';
import authHeader from './auth-header';

class PedidoService {
  salvarPedido(requestDTO) {
    let uri = `${process.env.VUE_APP_SERVER_URI}/consumidor/pedidos`;
    console.log(requestDTO);
    return axios.post(uri, requestDTO, {
      headers: authHeader()
    });
  }
}

export default new PedidoService();
