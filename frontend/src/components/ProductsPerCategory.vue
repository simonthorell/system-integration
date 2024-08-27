<template>
  <q-card class="q-pa-md">
    <div class="text-h6 text-left">Products per Category</div>
    <q-btn
      @click="fetchCategories"
      label="List Categories"
      color="primary"
      class="q-mt-md"
    />

    <q-list v-if="categories.length" class="q-mt-md">
      <q-item-label>Number of Products per Category</q-item-label>
      <q-item v-for="category in categories" :key="category.name" clickable>
        <q-item-section
          >{{ category.name }}:
          {{ category.productCount }} products</q-item-section
        >
      </q-item>
    </q-list>
    <!-- Show a banner if no categories are found -->
    <q-banner v-else-if="searchPerformed" class="q-mt-md">
      No categories found.
    </q-banner>
  </q-card>
</template>

<script lang="ts">
import { ref } from 'vue';
import { defineComponent } from 'vue';
import { api } from 'src/boot/axios';

export default defineComponent({
  setup() {
    const categories = ref<{ name: string; productCount: number }[]>([]);
    const searchPerformed = ref<boolean>(false);

    const fetchCategories = async () => {
      searchPerformed.value = false;
      try {
        const response = await api.get('/categories/product-count');
        console.log('Categories:', response.data);
        categories.value = response.data;
      } catch (error) {
        console.error('Error fetching categories:', error);
        categories.value = [];
      } finally {
        searchPerformed.value = true;
      }
    };

    return {
      categories,
      searchPerformed,
      fetchCategories,
    };
  },
});
</script>
