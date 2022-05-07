<template>
  <div>
    <!--<div>
      <h2>Buscar</h2>
      <GmapAutocomplete @place_changed='setPlace' />
      <button @click='addMarker'> Add </button>
    </div>
    <br>-->
    <GmapMap :v-if="this.coordenadasCentro"
             :center='coordenadasCentro || {}'
             :zoom='12'
             style='width:100%;  height: 100vh;'>
      <GmapMarker :key="index"
                  v-for="(m, index) in markers"
                  :position="m.position"
                  :title="m.title"
                  :clickable="true"
                  @click="coordenadasCentro=m.position" />
    </GmapMap>
  </div>
</template>

<script>
  import GeolocalizacaoService from '../../services/geolocation-service';
export default {
    name: 'Geolocalizacao',
    props: {
      enderecoCentral: Object,
      enderecos: {
        type: Array,
        required: true
      }
    },
    data() {
      return {
        markers: [],
        places: [],
        coordenadasCentro: null
      }
    },
    mounted() {
      this.geolocate();
    },
    methods: {
      carregarEnderecoCentro() {
        GeolocalizacaoService.carregarCoordenadasPorEndereco(this.enderecoCentral).then(coords => {
          this.coordenadasCentro = coords;
        });
      },
      addMarker() {
        if (this.currentPlace) {
          const marker = {
            lat: this.currentPlace.geometry.location.lat(),
            lng: this.currentPlace.geometry.location.lng(),
          };
          this.markers.push({ position: marker });
        }
      },
      geolocate: function () {
        //carrego pelo o endereço cadastrado
        this.carregarEnderecoCentro();
        //se tiver permissão na localização, uso ela
        navigator.geolocation.getCurrentPosition(position => {
          this.coordenadasCentro = {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          };
        });
      },
    },
    watch: {
      enderecos: {
        handler(newValue, oldValue) {
          this.markers = [this.coordenadasCentro];
          if(newValue.length)
            newValue.forEach(e => {
              GeolocalizacaoService.carregarCoordenadasPorEndereco(e).then(coords => {
                this.markers.push({ position: coords })
              });
            })
        },
        deep: true
      }
    }
}
</script>
