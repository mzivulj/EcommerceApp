<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-info">
    <!-- Navbar content -->
    <!--    Logo-->
    <router-link class="navbar-brand" :to="{ name: 'HomeView' }">
      <img id="logo" src="../assets/store1024x768.jpg" />
    </router-link>
    <!--    Burger Button-->
    <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <form class="form-inline ml-auto mr-auto" >
        <div class="input-group">
          <input
              size="100"
              type="text"
              class="form-control"
              placeholder="Search Items"
              aria-label="Username"
              aria-describedby="basic-addon1"
              v-model="searchparam"
          />
          <div class="input-group-prepend">
            <span class="input-group-text" id="search-button-navbar">
              <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="16"
                  height="16"
                  fill="currentColor"
                  class="bi bi-search"
                  viewBox="0 0 16 16"
              >
                <path
                    d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"
                />
              </svg>
            </span>
          </div>
        </div>
      </form>
      <!-- dropdown for browse -->
      <!-- dropdown for account -->
      <ul class="navbar-nav mr-auto">
        <li class="nav-item dropdown">
          <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarAccount"
              data-toggle="dropdown"
          >
            Account
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarAccount">
            <router-link
                v-if="token"
                class="dropdown-item"
                :to="{ name: 'WishList' }"
            >Wishlist
            </router-link>
            <router-link
                v-if="token"
                class="dropdown-item"
                :to="{ name: 'CartView' }"
            >Cart
            </router-link>
            <router-link
                v-if="!token"
                class="dropdown-item"
                :to="{ name: 'SignUp' }"
            >Sign up
            </router-link>
            <router-link
                v-if="!token"
                class="dropdown-item"
                :to="{ name: 'SignIn' }"
            >Sign in
            </router-link>
            <a class="dropdown-item" v-if="token" href="#" @click="signout"
            >Sign out
            </a>
          </div>
        </li>
      </ul>
      <ul class="navbar-nav mr-auto">
        <li class="nav-item dropdown">
          <a color="#9900FF"
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarAccount2"
              data-toggle="dropdown"
          >
            Navigate
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarAccount">
            <router-link
                v-if="token"
                class="dropdown-item"
                :to="{ name: 'AdminProduct' }"
            >Products
            </router-link>
            <router-link
                v-if="token"
                class="dropdown-item"
                :to="{ name: 'AdminCategory' }"
            >Categories
            </router-link>
            <router-link
                v-if="token"
                class="dropdown-item"
                :to="{ name: 'TopCategories' }"
            >TopCategories
            </router-link>
          </div>
        </li>
      </ul>
    </div>
  </nav>
</template>
<script>
  export default {
  name: "NavBar",
  data() {
  return {
  token: null,
  searchparam: null
};
},
  methods: {
  signout() {
  localStorage.removeItem("token");
  this.token = null;
  window.location.reload();
},
    },
  mounted() {
  this.token = localStorage.getItem("token");
}
}
</script>
<style scoped>
#logo {
  width: 150px;
  margin-left: 20px;
  margin-right: 20px;
}
.nav-link {
  color: rgba(255, 255, 255);
}
</style>