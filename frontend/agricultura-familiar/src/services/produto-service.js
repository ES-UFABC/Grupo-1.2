import axios from 'axios';
import authHeader from './auth-header';
import Endereco from '../models/endereco';

class ProdutoService {
  salvarProduto(produto) {
    let uri = `${process.env.VUE_APP_SERVER_URI}/produto`;
    return axios.post(uri, produto, {
      headers: authHeader(),
    });
  }
}
export default new ProdutoService();
