<template>
  <div id="app">
    <div id="nav">
      <NavbarComponent :cartCount="cartCount" @resetCartCount="resetCartCount" v-if="$route.name != 'SignIn'"/>
    </div>
    <div style="min-height: 60vh">
      <router-view v-if="products && categories"
                   :baseURL="baseURL"
                   :products="products"
                   :categories="categories"
                   @fetchData = "fetchData">
      </router-view>
    </div>
    <FooterComponent v-if="$route.name != 'SignIn'"/>
  </div>
</template>

<script>

const axios = require('axios');
import NavbarComponent from "./components/NavbarComponent.vue"
import FooterComponent from "./components/FooterComponent.vue"

export default {
  data() {
    return {
      baseURL : "https://limitless-lake-55070.herokuapp.com/",
      products : null,
      categories : null,
      cartCount: 0,
      token : null,
      searchparam : null
    }
  },

  components : { NavbarComponent, FooterComponent},
  methods : {
    async fetchData() {

      await axios.get(this.baseURL + "product/")
          .then(res => this.products = res.data)
          .catch(err => console.log(err))

      //fetch categories
      await axios.get(this.baseURL + "category/")
          .then(res => this.categories = res.data)
          .catch(err => console.log(err))
      if (this.token) {
        axios
            .get(`${this.baseURL}cart/?token=${this.token}`)
            .then((res) => {
              const result = res.data;
              this.cartCount = result.cartItems.length;
            })
            .catch((err) => console.log("err", err));
      }
    },
    resetCartCount() {
      this.cartCount = 0;
    },
  },
  mounted() {
    this.token = localStorage.getItem('token');
    this.fetchData();
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>