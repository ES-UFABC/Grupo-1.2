<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-brand href="/">
        <b-img src="../../../public/muda_green_small.png" alt="Muda"></b-img> Muda
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#">Orgânicos</b-nav-item>
          <b-nav-item href="#">Estação</b-nav-item>
          <b-nav-item href="#">Exóticos</b-nav-item>
          <b-nav-item href="#">Frutas</b-nav-item>
          <b-nav-item href="#">Legumes</b-nav-item>
        </b-navbar-nav>
        
        <b-input-group>
          <b-input-group-prepend>
            <span class="input-group-text">
              <b-img src="../../../src/assets/search.svg" alt="Muda"></b-img>
            </span>
          </b-input-group-prepend>

          <b-form-input class="SearchInput" size="lg" placeholder="Busque por item ou produtor">
          </b-form-input>

          <b-input-group-append>
             <span class="input-group-text">
              <b-img src="../../../src/assets/location.svg" alt="Muda"></b-img>
            </span>
          </b-input-group-append>
        </b-input-group>


        <!-- <b-form-input
          size="sm"
          class="mr-sm-2"
          placeholder="Search"
        ></b-form-input>
        <b-button size="sm" class="my-2 my-sm-0" type="submit"
          >Search</b-button
        > -->

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          
          
          <b-nav-item-dropdown  no-caret right>
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <em><img src="../../assets/user.svg" alt="Muda"></em>
            </template>
            <b-dropdown-item href="#">Profile</b-dropdown-item>
            <b-dropdown-item href="#">Sign Out</b-dropdown-item>
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
    background-color: #ffffff;
    display: flex;
    align-items: center;
    justify-content: center;
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
</style>