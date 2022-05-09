<template>
  <div :v-if="this.coordenadasCentro">
    <GmapMap :center='this.coordenadasCentro'
             :zoom='12'
             style='width:100%;  height: 100vh;'>
      <GmapMarker :key="index"
                  v-for="(m, index) in this.markers"
                  :position="m.position"
                  icon="http://maps.google.com/mapfiles/ms/icons/purple-dot.png"
                  :clickable="true"
                  @click="openInfoWindowTemplate(index)" />
      <GmapMarker :position="this.coordenadasCentro"
                  icon="http://maps.google.com/mapfiles/ms/icons/green-dot.png"
                  :clickable="true"
                  @click="openInfoWindowTemplate(-1)"/>
      <gmap-info-window :options="infoWindow.options"
          :position="infoWindow.position"
          :opened="infoWindow.open"
          @closeclick="infoWindow.open=false">
          <div v-html="infoWindow.template"></div>
      </gmap-info-window>
    </GmapMap>
  </div>
</template>

<script>
  import GeolocalizacaoService from '../../services/geolocation-service';

  export default {
    name: 'Geolocalizacao',
    data() {
      return {
        infoWindow: {
          options: {
            maxWidth: 300,
            pixelOffset: { width: 0, height: -35 }
          },
          position: { lat: 0, lng: 0 },
          open: false,
          template: ''
        }
      }
    },
    mounted() {
      this.geolocate();
    },
    computed: {
      coordenadasCentro() {
        return this.$store.state.geolocation.consumerCoords
      },
      enderecoCentral() {
        return this.$store.state.geolocation.consumerAddress
      },
      markers() {
        return this.$store.state.geolocation.producersCoords
      },
    },
    methods: {
      carregarEnderecoCentro() {
        GeolocalizacaoService.carregarCoordenadasPorEndereco(this.enderecoCentral)
          .then(coords => this.$store.dispatch('geolocation/setconsumerCoords', coords));
      },
      geolocate: function () {
        //carrego pelo o endereço cadastrado
        this.carregarEnderecoCentro();
      },
      openInfoWindowTemplate(index) {
        let getTemplate = function(titulo, rua, num, cidade){
          return `<b>${titulo}</b><br>${rua},${num}<br>${cidade}<br>`
        }
        if (index > -1) {
          const { position, address } = this.markers[index]
          this.infoWindow.position = position
          this.infoWindow.template = getTemplate(address.produtor, address.rua, address.numero, address.municipio)
        }
        else {
          const address = this.enderecoCentral;
          this.infoWindow.position = this.coordenadasCentro
          this.infoWindow.template = getTemplate('Você', address.rua, address.numero, address.municipio)
        }
         this.infoWindow.open = true
      },
    }
}
</script>
