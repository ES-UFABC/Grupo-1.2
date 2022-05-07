<template>
  <div>
    <b-navbar fixed="top">
      <b-navbar-brand href="/">
        <b-img src="../../../public/muda_green_small.png" alt="Muda"></b-img>
        <h1>Muda</h1>
      </b-navbar-brand>

      <b-navbar-nav class="left">
        <b-nav-item href="#">Orgânicos</b-nav-item>
        <b-nav-item href="#">Exóticos</b-nav-item>
        <b-nav-item href="#">Frutas</b-nav-item>
        <b-nav-item href="#">Legumes</b-nav-item>
        <b-nav-item href="#">Verduras</b-nav-item>
      </b-navbar-nav>

      <Search @abrirGeolocalizacao="abrirGeolocalizacao" />

      <b-navbar-nav class="ml-auto">
        <div class="search-minimized mt-auto mb-auto mr-2">
          <a href=""
            ><img src="../../../src/assets/search.svg" alt="search" />
          </a>
        </div>

        <b-navbar-nav class="cart">
          <div class="cart-container">
            <div class="items">0</div>
            <div>
              <img
                src="../../../src/assets/shopping-cart.svg"
                alt="Shopping cart"
              />
            </div>
            <div class="total">R$000,00</div>
          </div>
        </b-navbar-nav>
        
        <div class="cart-minimized mt-auto mb-auto mr-1">
          <a href=""
            ><img
              src="../../../src/assets/shopping-cart-green.svg"
              alt="shopping cart"
            />
          </a>
        </div>

        <b-nav-item-dropdown no-caret right>
          <!-- Using 'button-content' slot -->
          <template #button-content>
            <img
              class="user-photo"
              src="https://picsum.photos/200/300?random=1"
              alt="User"
            />
          </template>
          <b-dropdown-item href="#">Minha conta</b-dropdown-item>
          <b-dropdown-item href="#">Pedidos</b-dropdown-item>
          <b-dropdown-item href="#">Chat</b-dropdown-item>
          <b-dropdown-item href="#">Carteira</b-dropdown-item>
          <hr />
          <b-dropdown-item href="#" @click="logoff()">Sair</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>

    </b-navbar>
  </div>
</template>
<script>
import Search from "../search/Search.vue";

export default {
  components: {
    Search,
  },
  methods: {
    logoff() {
      this.$store.dispatch("auth/logout");
      this.$router.push("/");
    },
    abrirGeolocalizacao() {
      this.$emit('abrirGeolocalizacao')
    },
  },
};
</script>
<style scoped>
@media (max-width: 576px) {
  .search {
    display: none;
  }
}

@media (min-width: 577px) {
  .search-minimized {
    display: none;
  }
}

@media (max-width: 375px) {
  .cart {
    display: none;
  }
}

@media (min-width: 376px) {
  .cart-minimized {
    display: none;
  }
}

.navbar-nav a img {
  height: 30px;
}

@media (max-width: 1200px) {
  .left {
    display: none;
  }
}

.cart-container {
  display: flex;
  flex-direction: row;
  background-color: #38c961;
  padding: 12px;
  border-radius: 24px;
  margin-left: 12px;
  color: white;
}

.cart-container .items {
  background-color: white;
  width: 24px;
  height: 24px;
  border-radius: 12px;
  margin-right: 6px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #38c961;
}

.cart-container .total {
  margin-left: 6px;
}

.navbar {
  background-color: white;
}

.user-photo {
  height: 30px;
  width: 30px;
  border-radius: 15px;
}

.navbar-brand {
  display: flex;
  flex-direction: row;
  align-items: center;
  color: #8af0a7;
}
.navbar-brand h1 {
  font-size: 27px;
  margin: auto;
  margin-left: 8px;
}
</style>
