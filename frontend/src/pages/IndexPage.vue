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
        ><q-badge color="pink" floating style="font-size: 16px">4</q-badge>
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
import { ref } from 'vue';
import BestSalesMonth from 'src/components/BestSalesMonth.vue';
import CustomerPurchaseTotal from 'src/components/CustomerPurchaseTotal.vue';
import OrderValuePerCity from 'src/components/OrderValuePerCity.vue';
import ProductsPerCategory from 'src/components/ProductsPerCategory.vue';
import SearchOrder from 'src/components/SearchOrder.vue';
import Top5Products from 'src/components/Top5Products.vue';
import AddProductToCart from 'src/components/AddProductToCart.vue';
import ShoppingCartDialog from 'src/components/ShoppingCartDialog.vue';

const shoppingCartDialog = ref<boolean>(false);

// Example cart items
const cartItems = ref([
  { name: 'Product 1', price: 10 },
  { name: 'Product 2', price: 15 },
  { name: 'Product 3', price: 20 },
]);
</script>

<style scoped>
.page-background {
  background-image: url('../assets/bg_1.jpg');
  background-size: cover;
  background-position: center;
}
</style>
