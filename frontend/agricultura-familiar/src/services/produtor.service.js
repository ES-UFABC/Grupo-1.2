import axios from 'axios';
import authHeader from './auth-header';
import Endereco from '../models/endereco';

class ProdutorService {
  obterProdutorAutenticado() {
    let user = JSON.parse(localStorage.getItem(process.env.LOCAL_STORAGE_AUTH_KEY)).user;
    console.log(user);
    let uri = `${process.env.SERVER_URI}/cadastro/produtor/${user ? user.id : 0}`;
    return axios.get(uri, { headers: authHeader() });
  }
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
