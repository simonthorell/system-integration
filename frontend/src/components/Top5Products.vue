<template>
  <q-card class="q-pa-md">
    <div class="text-h6 text-left">Top 5 Products</div>
    <q-btn
      @click="fetchTopProducts"
      label="Get Top 5 Products"
      color="primary"
      class="q-mt-md"
    />

    <div class="q-pt-md">
      <div class="scrollable-container">
        <q-intersection
          v-for="index in 15"
          :key="index"
          transition="flip-right"
          class="example-item"
        >
          <q-item clickable v-ripple>
            <q-item-section avatar>
              <q-avatar icon="checkroom" color="accent" text-color="white">
              </q-avatar>
            </q-item-section>

            <q-item-section>
              <q-item-label>Item #{{ index }}</q-item-label>
              <q-item-label caption lines="1">Color:</q-item-label>
            </q-item-section>

            <q-item-section side>
              <q-icon name="chat_bubble" color="secondary" />
            </q-item-section>
          </q-item>
        </q-intersection>
      </div>
    </div>
    <!-- <q-list v-if="products.length" class="q-mt-md">
      <q-item-label>Top 5 Most Sold Products</q-item-label>
      <q-item v-for="product in products" :key="product.name" clickable>
        <q-item-section
          >{{ product.name }}: {{ product.quantitySold }} sold</q-item-section
        >
      </q-item>
    </q-list> -->
    <!-- Show a banner if no products are found -->
    <!-- <q-banner v-else-if="searchPerformed" class="q-mt-md">
      No products found.
    </q-banner> -->
  </q-card>
</template>

<script lang="ts">
import { ref } from 'vue';
import { defineComponent } from 'vue';
import { api } from 'src/boot/axios';

export default defineComponent({
  setup() {
    const products = ref<{ name: string; quantitySold: number }[]>([]);
    const searchPerformed = ref<boolean>(false);

    const fetchTopProducts = async () => {
      searchPerformed.value = false;
      try {
        const response = await api.get('/products/top-5');
        console.log('Top 5 Products:', response.data);
        products.value = response.data;
      } catch (error) {
        console.error('Error fetching top products:', error);
        products.value = [];
      } finally {
        searchPerformed.value = true;
      }
    };

    return {
      products,
      searchPerformed,
      fetchTopProducts,
    };
  },
});
</script>

<style lang="sass" scoped>
.example-item
  height: 56px

.scrollable-container
  max-height: 300px /* Set the desired height */
  overflow-y: auto /* Enable vertical scrolling */
</style>
