<template>
  <q-card class="q-pa-md">
    <div class="text-h6 text-left">Products per Category</div>

    <div class="q-pt-md">
      <div class="scrollable-container">
        <q-intersection
          class="intersection"
          v-for="(category, index) in categories"
          :key="index"
          transition="flip-right"
        >
          <q-item clickable v-ripple>
            <q-item-section avatar>
              <q-avatar
                rounded
                icon="category"
                :color="getRandomColor()"
                text-color="white"
              >
              </q-avatar>
            </q-item-section>

            <q-item-section>
              <q-item-label class="category-name-label">{{
                category.name
              }}</q-item-label>
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

<script setup lang="ts">
import { ref, onMounted, onBeforeUnmount } from 'vue';
import { api } from 'src/boot/axios';

const categories = ref<{ name: string; product_count: number }[]>([]);
const searchPerformed = ref<boolean>(false);
let intervalId: number;

// Used to assign the icon a random color
const colors = ['primary', 'secondary', 'accent'];
const getRandomColor = () => {
  return colors[Math.floor(Math.random() * colors.length)];
};

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
  // Poll backend for updates
  intervalId = window.setInterval(fetchCategories, 3000);
});

onBeforeUnmount(() => {
  if (intervalId) {
    clearInterval(intervalId); // Clear the interval when the component is destroyed
  }
});
</script>

<style lang="sass" scoped>
.scrollable-container
  height: 300px
  overflow-y: auto

.intersection
  height: 65px

.category-name-label
  font-size: 1.2rem
  font-weight: bold
  color: $secondary
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2)
  transition: color 0.3s ease

  &:hover
    color: $primary
</style>
