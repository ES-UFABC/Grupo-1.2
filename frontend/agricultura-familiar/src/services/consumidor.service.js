import axios from 'axios';
import authHeader from './auth-header';
import Endereco from '../models/endereco';

class ConsumidorService {
  obterConsumidorAutenticado() {
    let user = JSON.parse(localStorage.getItem(process.env.VUE_APP_LOCAL_STORAGE_AUTH_KEY)).user;
    let uri = `${process.env.VUE_APP_SERVER_URI}/cadastro/consumidor/${user ? user.id : 0}`;
    return axios.get(uri, { headers: authHeader() });
  }
  obterEnderecoDoConsumidor() {
    return this.obterConsumidorAutenticado().then(address => {
      const { rua, numero, bairro, municipio, estado, cep, complemento } = address.data.endereco[0];
      return Promise.resolve(new Endereco(rua, numero, bairro, municipio, estado, cep, complemento));
    });
  }
  //getUserBoard() {
  //  return axios.get(API_URL + 'user', { headers: authHeader() });
  //}
  //getModeratorBoard() {
  //  return axios.get(API_URL + 'mod', { headers: authHeader() });
  //}
  //getAdminBoard() {
  //  return axios.get(API_URL + 'admin', { headers: authHeader() });
  //}
}
export default new ConsumidorService();
