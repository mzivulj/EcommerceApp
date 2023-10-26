<template>
  <div id="home">
    <div id="background-div" class="page-holder bg-cover">
      <div class="container py-5">
        <header class="text-left text-white py-5">
            <router-link :to="{name: 'AdminProduct'}">
              <h3 class="mb-4 rounded" id="heading"> Start shopping </h3>
            </router-link>
        </header>
      </div>
      <hr />
    </div>

    <!--    display categories-->
    <div class="container">
      <div class="row">
        <div class="col-12 text-center">
          <h2 class="pt-3"> Top Sellers</h2>
        </div>
      </div>
      <div class="row">
        <div v-for="index in this.categorySize" :key="index"
             class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex">
          <CategoryBox :category="categories[index-1]" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CategoryBox from "../components/Category/CategoryBox";
export default {
  name: "HomeView",
  components: {CategoryBox},
  props: ["categories"],
  data() {
    return {
      categorySize: 0
    }
  },
      mounted() {
    this.categorySize = Math.min(6, this.categories.length );
  }
};
</script>
<style>
.page-holder {
  min-height: 100vh;
}
.bg-cover{
  background-size: cover !important;
}
#background-div {
  background: url("../assets/home.png");
}
#heading {
  font-weight: 400;
}
</style>