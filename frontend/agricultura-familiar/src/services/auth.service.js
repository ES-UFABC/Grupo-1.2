import axios from 'axios';

class AuthService {
  login(user) {
    return axios
      .post(`${process.env.SERVER_URI}/login`, {
        email: user.email,
        password: user.password
      })
      .then(response => {
        if (response.data.token) {
          localStorage.setItem(process.env.LOCAL_STORAGE_AUTH_KEY, JSON.stringify(response.data));
        }
        return response.data;
      })
      .catch(error => {
        console.log(error);
        return null;
      });
  }
  logout() {
    localStorage.removeItem(process.env.LOCAL_STORAGE_AUTH_KEY);
  }
  register(user) {
    return axios.post(`${process.env.SERVER_URI}/cadastro`, {
      username: user.username,
      email: user.email,
      password: user.password
    });
  }
}
export default new AuthService();
