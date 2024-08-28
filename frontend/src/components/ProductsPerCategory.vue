<template>
  <q-card class="q-pa-md">
    <div class="text-h6 text-left">Products per Category</div>
    <div class="q-pt-md">
      <div class="scrollable-container">
        <q-intersection
          v-for="(category, index) in categories"
          :key="index"
          transition="scale"
          class="example-item"
        >
          <q-item clickable v-ripple>
            <q-item-section avatar>
              <q-avatar icon="category" color="primary" text-color="white">
              </q-avatar>
            </q-item-section>

            <q-item-section>
              <q-item-label>{{ category.name }}</q-item-label>
              <!-- <q-item-label caption lines="1"
                >Number of Products: {{ category.product_count }}</q-item-label
              > -->
            </q-item-section>

            <q-item-section>
              <q-item-label caption lines="1"
                >Products: {{ category.product_count }}</q-item-label
              >
            </q-item-section>

            <q-item-section side>
              <q-icon name="format_list_bulleted" color="secondary" />
            </q-item-section>
          </q-item>
        </q-intersection>
      </div>
    </div>

    <!-- Show a banner if no categories are found -->
    <q-banner v-if="searchPerformed && categories.length === 0" class="q-mt-md">
      No categories found.
    </q-banner>
  </q-card>
</template>

<script lang="ts">
import { ref, onMounted, onBeforeUnmount } from 'vue';
import { defineComponent } from 'vue';
import { api } from 'src/boot/axios';

export default defineComponent({
  setup() {
    const categories = ref<{ name: string; product_count: number }[]>([]);
    const searchPerformed = ref<boolean>(false);
    let intervalId: number;

    const fetchCategories = async () => {
      console.log('fetching categories');
      searchPerformed.value = false;
      try {
        const response = await api.get('/product/per-category');
        console.log('Categories:', response.data);
        categories.value = response.data;
      } catch (error) {
        console.error('Error fetching categories:', error);
        categories.value = [];
      } finally {
        searchPerformed.value = true;
      }
    };

    onMounted(() => {
      fetchCategories();
      // Poll backend every 10 seconds for updates
      intervalId = window.setInterval(fetchCategories, 10000);
    });

    onBeforeUnmount(() => {
      if (intervalId) {
        clearInterval(intervalId); // Clear the interval when the component is destroyed
      }
    });

    return {
      categories,
      searchPerformed,
      fetchCategories,
    };
  },
});
</script>

<style lang="sass" scoped>
.scrollable-container
  max-height: 300px
  overflow-y: auto
</style>
