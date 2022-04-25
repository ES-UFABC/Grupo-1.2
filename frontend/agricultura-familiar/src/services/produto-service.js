import axios from 'axios';
import authHeader from './auth-header';
import Endereco from '../models/endereco';

class ProdutoService {
  salvarProduto(produto) {
    let uri = `${process.env.SERVER_URI}/produto`;
    return axios.post(uri, {
      headers: authHeader(),
      params: { produto }
    });
  }
}
export default new ProdutoService();
