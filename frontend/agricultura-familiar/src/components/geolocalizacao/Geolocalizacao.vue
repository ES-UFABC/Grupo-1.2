<template>
  <div>
    <div>
      <h2>Buscar</h2>
      <GmapAutocomplete @place_changed='setPlace' />
    </div>
    <br>
    <GmapMap :center='center'
             :zoom='12'
             style='width:100%;  height: 400px;' />
  </div>
</template>

<script>
export default {
    name: 'GoogleMap',
    data() {
      return {
        center: { lat: -22.8305, lng: -43.2192 },
      }
    },
    mounted() {
      this.geolocate();
    },
    methods: {
      geolocate: function () {
        navigator.geolocation.getCurrentPosition(position => {
          console.log(position)
          this.center = {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          };
        });
      },
    }
}
</script>
