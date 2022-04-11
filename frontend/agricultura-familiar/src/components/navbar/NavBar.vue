<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-brand href="/">
        <img src="../../../public/muda_green_small.png" alt="Muda"> Muda
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#">Orgânicos</b-nav-item>
          <b-nav-item href="#">Da Estação</b-nav-item>
          <b-nav-item href="#">Exóticos</b-nav-item>
          <b-nav-item href="#">Frutas</b-nav-item>
          <b-nav-item href="#">Legumes</b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
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