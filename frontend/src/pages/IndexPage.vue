<template>
  <q-page class="q-pa-md items-center justify-evenly page-background">
    <div class="row q-col-gutter-md q-mb-md">
      <div class="col-12 col-md-4">
        <BestSalesMonth />
        <div class="q-mt-md">
          <Top5Products />
        </div>
      </div>
      <div class="col-12 col-md-4">
        <CustomerPurchaseTotal />
      </div>
      <div class="col-12 col-md-4">
        <OrderValuePerCity />
      </div>
    </div>
    <div class="row q-col-gutter-md q-mb-md">
      <div class="col-12 col-md-4">
        <ProductsPerCategory />
      </div>
      <div class="col-12 col-md-4">
        <SearchOrder />
      </div>
      <div class="col-12 col-md-4">
        <AddProductToCart />
      </div>
    </div>
    <q-page-sticky position="bottom-right" :offset="[18, 18]">
      <q-btn
        round
        icon="shopping_cart"
        padding="md"
        size="22px"
        color="secondary"
        @click="shoppingCartDialog = true"
      >
        <!-- Dynamic Badge Showing Number of Items in Cart -->
        <q-badge color="pink" floating style="font-size: 16px">
          {{ itemCount }}
        </q-badge>
      </q-btn>
    </q-page-sticky>

    <!-- Shopping Cart Dialog -->
    <ShoppingCartDialog
      :model-value="shoppingCartDialog"
      :cart-items="cartItems"
      @update:modelValue="shoppingCartDialog = $event"
    />
  </q-page>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';
import { useCartStore } from 'src/store/cart';
import BestSalesMonth from 'src/components/BestSalesMonth.vue';
import CustomerPurchaseTotal from 'src/components/CustomerPurchaseTotal.vue';
import OrderValuePerCity from 'src/components/OrderValuePerCity.vue';
import ProductsPerCategory from 'src/components/ProductsPerCategory.vue';
import SearchOrder from 'src/components/SearchOrder.vue';
import Top5Products from 'src/components/Top5Products.vue';
import AddProductToCart from 'src/components/AddProductToCart.vue';
import ShoppingCartDialog from 'src/components/ShoppingCartDialog.vue';

const shoppingCartDialog = ref<boolean>(false);

// Initialize the shopping cart store
const shoppingCart = useCartStore();

// Computed property for dynamically showing the number of items in the cart
const itemCount = computed(() => shoppingCart.itemCount);

// Reactive cart items from the store
const cartItems = computed(() => shoppingCart.items);
</script>

<style scoped>
.page-background {
  background-image: url('../assets/bg_1.jpg');
  background-size: cover;
  background-position: center;
}
</style>
