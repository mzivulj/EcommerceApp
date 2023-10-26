import {createRouter,createWebHistory} from "vue-router";
import AddCategory from "../views/Category/AddCategory";
import CategoryComponent from "../views/Category/Category";
import EditCategory from "../views/Category/EditCategory";
import ProductView from '../views/Product/ProductView';
import AddProduct from "../views/Product/AddProduct";
import EditProduct from "../views/Product/EditProduct";
import HomeView from "../views/HomeView";
import TopCategories from "../views/TopCategories";
import SignUp from "../views/SignUp";
import SignIn from '../views/SignIn';
import WishList from "../views/Product/WishList";
import CartView from '../views/CartView';
import CheckOut from '../views/CheckOut/CheckOut';

const routes = [
  {
    path: "/admin/category",
    name: "AdminCategory",
    component: CategoryComponent,
  },
  {
    path: "/home",
    name: "HomeView",
    component: HomeView,
  },
  {
    path: "/admin/category/:id",
    name: "EditCategory",
    component: EditCategory,
    props: true,
  },
  {
    path: "/admin/category/add",
    name: "AddCategory",
    component: AddCategory,
  },
  {
    path: '/topcategories',
    name: 'TopCategories',
    component: TopCategories
  },
  {
    path: '/admin/product',
    name: 'AdminProduct',
    beforeEnter: ifAuth,
    component: ProductView
  },
  // add product
  {
    path: '/admin/product/new',
    name: 'AddProduct',
    component: AddProduct
  },
  {
    path: '/admin/product/:id',
    name: 'EditProduct',
    component: EditProduct
  },

  {
    path: '/signup',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/signin',
    name: 'SignIn',
    component: SignIn
  },
  {
    path: '/wishlist',
    name: 'WishList',
    component: WishList,
  },
  {
    path: '/cart',
    name: 'CartView',
    component: CartView,
  }
  ,
  {
    path: '/checkout',
    name: 'CheckOut',
    component: CheckOut,
  }
];

function ifAuth() {
  let token = localStorage.getItem('token');
  if (token) {
    console.log("login done");
    return;
  }
  router.push({  name: 'SignIn' });
}

const router = new createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;