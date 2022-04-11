import axios from 'axios';
import authHeader from './auth-header';
import Endereco from '../models/endereco';
const API_URL = 'http://localhost:8080/api/test/';

class ProdutorService {
  carregarProdutoresPorLocalizacao(estado, municipio) {
    let uri = `${process.env.SERVER_URI}/produtor/geolocalizacao`;
    return axios.get(uri, {
      headers: authHeader(),
      params: { estado, municipio }
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
export default new ProdutorService();
