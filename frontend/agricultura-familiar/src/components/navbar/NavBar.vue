<template>
  <div>
    <b-navbar toggleable="lg">
      <b-navbar-brand href="/">
        <b-img src="../../../public/muda_green_small.png" alt="Muda"></b-img>
        Muda
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#">Orgânicos</b-nav-item>
          <b-nav-item href="#">Exóticos</b-nav-item>
          <b-nav-item href="#">Frutas</b-nav-item>
          <b-nav-item href="#">Legumes</b-nav-item>
          <b-nav-item href="#">Verduras</b-nav-item>
        </b-navbar-nav>

        <b-input-group>
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

        <!-- Right aligned nav items -->

        <b-container
          class="bv-example-row"
          style="background-color: #38C961; max-width: 160px; height: 40px; border-radius: 20px"
        >
          <b-row class="text-center">
            <b-col cols="3">0</b-col>
            <b-col cols="3"
              ><b-img
                src="../../../src/assets/shopping-cart.svg"
                alt="Shopping cart"
              ></b-img
            ></b-col>
            <b-col>R$0,00</b-col>
          </b-row>
        </b-container>

        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown no-caret right>
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <em><img src="../../assets/user.svg" alt="User" /></em>
            </template>
            <b-dropdown-item href="#">Minha conta</b-dropdown-item>
            <b-dropdown-item href="#">Pedidos</b-dropdown-item>
            <b-dropdown-item href="#">Chat</b-dropdown-item>
            <b-dropdown-item href="#">Carteira</b-dropdown-item>
            <hr>
            <b-dropdown-item href="#">Sair</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<style scoped>
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
.input-group {
  margin-left: 1rem;
  margin-right: 1rem;
}
.navbar {
  background-color: #ffffff;
}
.shooping-cart {
  height: 16px;
}
.button {
}
</style>