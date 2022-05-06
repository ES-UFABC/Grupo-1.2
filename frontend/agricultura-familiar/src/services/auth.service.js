import axios from 'axios';
class AuthService {
  login(user) {
    return axios
      .post(`${process.env.VUE_APP_SERVER_URI}/login`, {
        email: user.email,
        password: user.password
      })
      .then(response => {
        if (response.data.token) {
          localStorage.setItem(process.env.VUE_APP_LOCAL_STORAGE_AUTH_KEY, JSON.stringify(response.data));
        }
        return response.data;
      })
      .catch(error => {
        console.log(error);
        return null;
      });
  }
  logout() {
    localStorage.removeItem(process.env.VUE_APP_LOCAL_STORAGE_AUTH_KEY);
  }
  registerProdutor(produtor) {
    return axios.post(`${process.env.VUE_APP_SERVER_URI}/cadastro/produtor`, produtor);
  }
  registerConsumidor(consumidor) {
    return axios.post(`${process.env.VUE_APP_SERVER_URI}/cadastro/consumidor`, consumidor);
  }

  confirmationEmail(user) {
    return axios
    .post(`${process.env.SERVER_URI}/enable-account`, {
      email: user.email,
      token: user.token
    })
  }
  
  carregarUsuario() {
    let user = JSON.parse(localStorage.getItem(process.env.LOCAL_STORAGE_AUTH_KEY)).user;
    return user;
  }
}
export default new AuthService();
