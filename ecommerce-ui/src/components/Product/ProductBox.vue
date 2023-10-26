<template>
  <div class="card h-100 w-100">
    <div class="embed-responsive embed-responsive-16by9">
      <img
          class="card-img-top embed-responsive-item"
          :src="product.imageURL"
          alt="Card image cap"
      />
    </div>
    <div class="card-body">
      <h5 class="card-title">{{ product.name }}</h5>
      <router-link :to="{name: 'EditProduct', params: {id: product.id}}">
        <button class="btn btn-primary">Edit</button>
      </router-link>
      <br>
      <button   @click=addToWishlist class="btn btn-secondary">Add to WishList</button>
      <button   @click=addToCart class="btn" id="add-to-cart-button">Add to Cart</button>
    </div>
  </div>
</template>
<script>
import swal from "sweetalert";
import axios from "axios";
export default {
  name: "ProductBox",
  props: ["product"],
  data() {
    return {
    baseURL : "https://limitless-lake-55070.herokuapp.com/",
      quantity: 1
  }; },
    methods: {
      addToWishlist() {
        if (!this.token) {
          // user is not logged in
          // show some error
          swal({
            text: "please login to add item in wishlist",
            icon: "error",
          });
          return;
        }
        // add item to wishlist
        axios
            .post(`http://localhost:8080/wishlist/add?token=${this.token}`, {
              id: this.product.id
            })
            .then((res) => {
              if (res.status === 201) {
                this.wishListString = "Added to Wishlist";
                swal({
                  text: "Added to Wishlist",
                  icon: "success",
                });
              }
            })
            .catch((err) => {
              console.log("err", err);
            });
      },
      addToCart() {
        if (!this.token) {
          // user is not logged in
          // show some error
          swal({
            text: "please login to add item in cart",
            icon: "error",
          });
          return;
        }

        // add to cart

        axios
            .post(`${this.baseURL}/cart/add?token=${this.token}`, {
              productId: this.product.id,
              quantity: this.quantity,
            })
            .then((res) => {
              if (res.status == 201) {
                swal({
                  text: "Product added in cart",
                  icon: "success",
                });
              }
            })
            .catch((err) => console.log("err", err));
      },
    },
  mounted() {
    this.token = localStorage.getItem("token");
  },
}
</script>
<style scoped>
.card-img-top {
  object-fit: cover;
}
</style>