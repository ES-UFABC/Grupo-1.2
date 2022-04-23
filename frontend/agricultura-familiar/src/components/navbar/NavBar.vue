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

      <b-input-group class="search">
        <b-input-group-prepend>
          <span class="input-group-text">
            <b-img src="../../../src/assets/search.svg" alt="Muda"></b-img>
          </span>
        </b-input-group-prepend>

        <b-form-input
          class="SearchInput"
          size="lg"
          placeholder="Busque por item ou produtor"
        >
        </b-form-input>

        <b-input-group-append>
          <span class="input-group-text">
            <b-img
              src="../../../src/assets/location.svg"
              alt="Location"
            ></b-img>
          </span>
        </b-input-group-append>
      </b-input-group>

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
          <b-dropdown-item href="#">Sair</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>

      <!-- <b-navbar-toggle target="nav-collapse"></b-navbar-toggle> -->
      <!-- <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#">Link</b-nav-item>
          <b-nav-item href="#" disabled>Disabled</b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto">
          <b-nav-form>
            <b-form-input
              size="sm"
              class="mr-sm-2"
              placeholder="Search"
            ></b-form-input>
            <b-button size="sm" class="my-2 my-sm-0" type="submit"
              >Search</b-button
            >
          </b-nav-form>

          <b-nav-item-dropdown text="Lang" right>
            <b-dropdown-item href="#">EN</b-dropdown-item>
            <b-dropdown-item href="#">ES</b-dropdown-item>
            <b-dropdown-item href="#">RU</b-dropdown-item>
            <b-dropdown-item href="#">FA</b-dropdown-item>
          </b-nav-item-dropdown>

          <b-nav-item-dropdown right>
            <template #button-content>
              <em>User</em>
            </template>
            <b-dropdown-item href="#">Profile</b-dropdown-item>
            <b-dropdown-item href="#">Sign Out</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse> -->
    </b-navbar>
  </div>
</template>

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
.input-group-text {
  width: 50px;
  border: none;
  background-color: #f8f8f8;
  display: flex;
  align-items: center;
  justify-content: center;
}
.SearchInput {
  background-color: #f8f8f8;
}
.input-group-text img {
  height: 26px;
}
.SearchInput {
  border: none;
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
  color: #8AF0A7;
}
.navbar-brand h1 {
  font-size: 27px;
  margin: auto;
  margin-left: 8px;
}
</style>

// 56