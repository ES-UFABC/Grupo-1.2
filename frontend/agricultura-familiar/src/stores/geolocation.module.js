import GeolocalizacaoService from '../services/geolocation-service';

export const geolocation = {
  namespaced: true,
  state: {
    consumerCoords: null,
    consumerAddress: null,
    producersCoords: []
  },
  getters: {
    consumerCoords(state) {
      return state.consumerCoords;
    },
    consumerAddress(state) {
      return state.consumerAddress;
    },
    producersCoords(state) {
      return state.producersCoords;
    }
  },
  actions: {
    setconsumerCoords({ commit }, coords) {
      commit('setconsumerCoords', coords);
    },
    setConsumerAddress({ commit }, address) {
      commit('setConsumerAddress', address);
    },
    setProducersAddresses({ commit }, addresses) {

      return new Promise((resolve, reject) => {
        var promises = addresses.map((address) => {
          return GeolocalizacaoService.carregarCoordenadasPorEndereco(address).then(function (coords) {
            return { position: coords, address: address }
          })
        });
        Promise.all(promises).then(function (coords) {
          commit('setProducersCoords', coords);
          resolve();
        })        
      })
    }
  },
  mutations: {
    setconsumerCoords(state, coords) {
      console.log(`setting lat: ${coords.lat}, long: ${coords.lng}`)
      state.consumerCoords = coords;
    },
    setConsumerAddress(state, address) {
      console.log(`setting address: ${address}`)
      state.consumerAddress = address;
    },
    setProducersCoords(state, coords) {
      console.log(`setting coords: ${coords}`)
      state.producersCoords = coords;
    },
  }
};
