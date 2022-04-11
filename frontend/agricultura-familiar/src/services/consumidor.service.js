import axios from 'axios';
import authHeader from './auth-header';
import Endereco from '../models/endereco';
const API_URL = 'http://localhost:8080/api/test/';

class ConsumidorService {
  obterEnderecoDoConsumidor() {
    return Promise.resolve(new Endereco("Rua Joaquim Manoel de Macedo", "817", "Serpa", "Caieiras", "SP", "07714-600", ""));
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
