import axios from 'axios';
import authHeader from './auth-header';
import Endereco from '../models/endereco';

class ProdutorService {
  carregarProdutoresPorLocalizacao(estado, municipio) {
    let uri = `${process.env.SERVER_URI}/produtor/geolocalizacao`;
    return axios.get(uri, {
      headers: authHeader(),
      params: { estado, municipio }
    });
  }
  carregarNotificacoes() {
    let uri = `${process.env.SERVER_URI}/notificacao`;
    return axios.get(uri, {
      headers: authHeader(),
      params: { }
    });
  }
}
export default new ProdutorService();
