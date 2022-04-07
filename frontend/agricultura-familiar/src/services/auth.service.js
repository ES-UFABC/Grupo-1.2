import axios from 'axios';

class AuthService {
  login(user) {
    return axios
      .post(`${process.env.SERVER_URI}/login`, {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem(process.env.LOCAL_STORAGE_AUTH_KEY, JSON.stringify(response.data));
        }
        return response.data;
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
