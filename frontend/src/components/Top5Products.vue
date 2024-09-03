<template>
  <q-card class="q-pa-md">
    <div class="text-h6 text-left">Top 5 Products</div>
    <div class="q-pt-md">
      <div class="scrollable-container">
        <q-intersection
          v-for="(product, index) in products"
          :key="index"
          transition="flip-right"
          class="example-item"
        >
          <q-item clickable v-ripple>
            <q-item-section avatar>
              <q-avatar color="accent" text-color="white">
                {{ index + 1 }}
              </q-avatar>
            </q-item-section>

            <q-item-section>
              <q-item-label class="product-name-label">
                {{ product.name }}
              </q-item-label>
              <q-item-label caption lines="1"
                >Sold: {{ product.amountSold }} units</q-item-label
              >
            </q-item-section>

            <q-item-section>
              <q-item-label caption lines="1"
                >Brand: {{ product.brand }}</q-item-label
              >
              <q-item-label caption lines="1"
                >Price: {{ product.price }} SEK</q-item-label
              >
            </q-item-section>

            <q-item-section side>
              <q-icon name="checkroom" color="primary" />
            </q-item-section>
          </q-item>
        </q-intersection>
      </div>
    </div>

    <!-- Show a banner if no products are found -->
    <q-banner v-if="searchPerformed && products.length === 0" class="q-mt-md">
      No products found.
    </q-banner>
  </q-card>
</template>

<script setup lang="ts">
import { ref, onMounted, onBeforeUnmount } from 'vue';
import { api } from 'src/boot/axios';

const products = ref<
  {
    name: string;
    brand: string;
    price: number;
    amountSold: number;
  }[]
>([]);
const searchPerformed = ref<boolean>(false);
let intervalId: number;

const fetchTopProducts = async () => {
  searchPerformed.value = false;
  try {
    const response = await api.get('/sales/top-5-products');
    // console.log('Top 5 Products:', response.data);
    products.value = response.data;
  } catch (error) {
    console.error('Error fetching top products:', error);
    products.value = [];
  } finally {
    searchPerformed.value = true;
  }
};

onMounted(() => {
  fetchTopProducts();
  // Poll backend every 10 seconds for updates
  intervalId = window.setInterval(fetchTopProducts, 10000);
});

onBeforeUnmount(() => {
  if (intervalId) {
    clearInterval(intervalId); // Clear the interval when the component is destroyed
  }
});
</script>

<style lang="sass" scoped>
.example-item
  height: 56px

.scrollable-container
  max-height: 300px /* Set the desired height */
  overflow-y: auto /* Enable vertical scrolling */

.product-name-label
  font-size: 1.2rem
  font-weight: bold
  color: $primary
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2)
  background: linear-gradient(45deg, $secondary, $primary)
  -webkit-background-clip: text
  -webkit-text-fill-color: transparent
  transition: transform 0.3s ease, color 0.3s ease

  &:hover
    transform: scale(1.1) rotate(-3deg)
    color: $accent

  &:active
    transform: scale(1) rotate(0deg)
</style>
