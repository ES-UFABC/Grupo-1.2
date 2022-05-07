import axios from 'axios';
import Endereco from '../models/endereco';

class GeolocationService {
  carregarCoordenadasPorEndereco(endereco) {
    if (!endereco)
      return Promise.reject('eee');

    let uri = `https://maps.googleapis.com/maps/api/place/findplacefromtext/json`;
    return axios.get(uri, {
      params: {
        input: `${endereco.rua}, n${endereco.numero}, ${endereco.bairro} ${endereco.municipio}, ${endereco.cep}`,
        inputtype: 'textquery',
        fields: 'formatted_address,name,rating,opening_hours,geometry',
        key: process.env.VUE_APP_MAPS_API_KEY,
        //locationbias: 'circle:radius@-22.8305,-43.2192 '
      }
    }).then(response => {
      if (!response.data.candidates)
        return { lat: 0, lng: 0 }

      return response.data.candidates[0].geometry.location;
    })
      .catch(error => {
        console.log(error);
        return { lat: 0, lng: 0 }
      });
  }
}
export default new GeolocationService();
